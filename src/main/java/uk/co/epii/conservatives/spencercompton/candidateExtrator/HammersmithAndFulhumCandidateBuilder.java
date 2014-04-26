package uk.co.epii.conservatives.spencercompton.candidateExtrator;

/**
 * Created with IntelliJ IDEA.
 * User: jrrpl
 * Date: 26/04/2014
 * Time: 01:48
 */
public class HammersmithAndFulhumCandidateBuilder extends CandidateBuilder {

  boolean seenComma;
  boolean seenPostcodeTerminus;

  @Override
  protected void processLine(String line) {
    if (line.isEmpty()) {
      return;
    }
    if (!seenComma && line.contains(",")) {
      seenComma = true;
      return;
    }
    if (!seenComma) {
      append(name, line);
      return;
    }
    if (!seenPostcodeTerminus && line.matches(".*[0-9][A-Z][A-Z].*")) {
      seenPostcodeTerminus = true;
      return;
    }
    if (seenPostcodeTerminus) {
      append(description, line);
    }
  }

  private void append(StringBuilder stringBuilder, String line) {
    if (line.isEmpty()) {
      return;
    }
    if (stringBuilder.length() > 0) {
      stringBuilder.append(" ");
    }
    stringBuilder.append(line);
  }

  @Override
  protected void reset() {
    seenComma = false;
    seenPostcodeTerminus = false;
  }
}
