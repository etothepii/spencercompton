package uk.co.epii.conservatives.spencercompton;

import uk.co.epii.conservatives.spencercompton.candidateExtrator.Ward;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by james on 25/04/14.
 */
public class Council {

  public String name;
  public String url;
  public Extractor candidateExtractor;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public Extractor getCandidateExtractor() {
    return candidateExtractor;
  }

  public void setCandidateExtractor(Extractor candidateExtractor) {
    this.candidateExtractor = candidateExtractor;
  }

  public String display() {
    StringBuilder stringBuilder = new StringBuilder(name);
    URL url = null;
    try {
      url = new URL(this.url);
    }
    catch (MalformedURLException e) {
      throw new RuntimeException(e);
    }
    for (Ward ward : candidateExtractor.extract(url)) {
      stringBuilder.append(String.format("\n%s", ward.display()));
    }
    return stringBuilder.toString();
  }
}
