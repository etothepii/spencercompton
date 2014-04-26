package uk.co.epii.conservatives.spencercompton.candidateExtractor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 * User: jrrpl
 * Date: 26/04/2014
 * Time: 01:48
 */
public class HammersmithAndFulhumCandidateBuilder extends CandidateBuilder {

  private static final Pattern backWardsStartOfName = Pattern.compile("[^A-Z ][A-Z] [^-]");
  private static final Pattern backWardsStartOfNameToEnd = Pattern.compile("[^A-Z ][A-Z]$");

  boolean seenComma;
  boolean seenPostcodeTerminus;

  @Override
  protected void processLine(String line) {
    line = line.trim();
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
  protected void postProcessing() {
    if (description.length() > 0) {
      description = removeNominator(description);
    }
  }

  static StringBuilder removeNominator(StringBuilder description) {
    description = description.delete(description.lastIndexOf("(P)"), description.length());
    int lastIndex = description.lastIndexOf("Candidate");
    if (lastIndex > 0) {
      return description.delete(lastIndex + 9, description.length());
    }
    description.reverse();
    for (int i = 0; i < 2; i++) {
      Matcher matcher = backWardsStartOfName.matcher(description);
      Matcher matcherToEnd = backWardsStartOfNameToEnd.matcher(description);
      if (matcher.find()) {
        description.delete(0, matcher.start() + 3);
      }
      else if (matcherToEnd.find()) {
        description.delete(0, matcherToEnd.start() + 2);
      }
    }
    return description.reverse();
  }

  @Override
  protected void reset() {
    seenComma = false;
    seenPostcodeTerminus = false;
  }
}
