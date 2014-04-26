package uk.co.epii.conservatives.spencercompton.candidateExtrator;

import java.net.URL;
import java.util.List;

/**
 * User: James Robinson
 * Date: 26/04/2014
 * Time: 00:11
 */
public class HammersmithAndFulhumExtractor extends HtmlExtractor {

  HammersmithAndFulhumWardExtractor wardExtractor = new HammersmithAndFulhumWardExtractor();

  @Override
  protected Ward processWardLink(URL url) {
    return wardExtractor.extract(url);
  }

  @Override
  protected boolean isValidWardLink(String text) {
    return text.matches(".*Statement of Persons Nominated.*Ward.*");
  }

  @Override
  public String display(URL url) {
    StringBuilder stringBuilder = new StringBuilder();
    for (Ward ward : extractWardsFromLinks(url)) {
      if (stringBuilder.length() > 0) {
        stringBuilder.append("\n");
      }
      stringBuilder.append(ward.display());
    }
    return stringBuilder.toString();
  }

  @Override
  public List<Ward> extract(URL url) {
    return extractWardsFromLinks(url);
  }


}
