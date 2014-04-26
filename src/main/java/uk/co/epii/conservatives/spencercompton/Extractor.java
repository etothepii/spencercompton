package uk.co.epii.conservatives.spencercompton;

import uk.co.epii.conservatives.spencercompton.candidateExtractor.Ward;

import java.net.URL;
import java.util.List;

/**
 * User: James Robinson
 * Date: 26/04/2014
 * Time: 02:59
 */
public abstract class Extractor {

  public abstract String display(URL url);
  public abstract List<Ward> extract(URL url);

}
