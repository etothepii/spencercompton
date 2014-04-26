package uk.co.epii.conservatives.spencercompton.candidateExtractor;

import java.util.List;

/**
 * User: James Robinson
 * Date: 26/04/2014
 * Time: 01:48
 */
public abstract class CandidateBuilder {

  protected StringBuilder name;
  protected StringBuilder description;

  public Candidate parseCandidate(List<String> candidateSection) {
    name = new StringBuilder();
    description = new StringBuilder();
    reset();
    for (String line : candidateSection) {
      processLine(line);
    }
    postProcessing();
    return new Candidate(name.toString(), description.toString());
  }

  protected abstract void postProcessing();

  protected abstract void processLine(String line);

  protected abstract void reset();
}
