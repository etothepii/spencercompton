package uk.co.epii.conservatives.spencercompton.candidateExtractor;

import java.io.IOException;
import java.net.URL;
import java.util.List;

/**
 * User: James Robinson
 * Date: 26/04/2014
 * Time: 00:11
 */
public class TowerHamletsExtractor extends HtmlExtractor {

  TowerHamletsWardExtractor wardExtractor = new TowerHamletsWardExtractor();

  @Override
  protected Ward processWardLink(URL url) {
      try {
        return wardExtractor.extract(url);
      }
      catch (IOException e) {
        throw new RuntimeException(e);
      }
  }

  @Override
  protected boolean isValidWardLink(String text) {
    return text.matches(".*ward.*");
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
