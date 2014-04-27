package uk.co.epii.conservatives.spencercompton.candidateExtractor;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import uk.co.epii.conservatives.spencercompton.Extractor;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * User: James Robinson
 * Date: 26/04/2014
 * Time: 00:10
 */
public abstract class HtmlExtractor extends Extractor {

  public List<Ward> extractWardsFromLinks(URL page) {
    Document doc = null;
    try {
      doc = Jsoup.parse(page, 10000);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    Elements links = doc.select("a[href]");
    List<Ward> wards = new ArrayList<Ward>();
    for (int i = 0; i < links.size(); i++) {
      if (isValidWardLink(links.get(i).text())) {
        wards.add(processWardLink(page, links.get(i).attr("href")));
      }
    }
    return wards;
  }

  protected Ward processWardLink(URL page, String href) {
    URL url;
    if (href.startsWith("http:")) {
      try {
        url = new URL(href);
      }
      catch (MalformedURLException e) {
        throw new RuntimeException(e);
      }
    }
    else if (href.startsWith("/")) {
      try {
        url = new URL(String.format("http://%s%s", page.getHost(), href));
      }
      catch (MalformedURLException e) {
        throw new RuntimeException(e);
      }
    }
    else {
      try {
        url = new URL(String.format("http://%s/%s", page.getHost(), href));
      }
      catch (MalformedURLException e) {
        throw new RuntimeException(e);
      }
    }
    return processWardLink(url);
  }

  protected abstract Ward processWardLink(URL url);

  protected abstract boolean isValidWardLink(String text);

}
