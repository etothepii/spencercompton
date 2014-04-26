package uk.co.epii.conservatives.spencercompton.candidateExtrator;

import edu.emory.mathcs.backport.java.util.Arrays;

import java.util.ArrayList;
import java.util.List;

/**
 * User: James Robinson
 * Date: 26/04/2014
 * Time: 00:08
 */
public class HammersmithAndFulhumWardExtractor extends PDFWardExtractor {

  private CandidateBuilder candidateBuilder = new HammersmithAndFulhumCandidateBuilder();

  @Override
  protected Ward extractFromText(String text) {
    List<String> nonBlankLines = getNonBlankLines(text);
    Ward ward = new Ward(grep("Ward", nonBlankLines).get(0));
    ward.candidates.addAll(getCandidates(nonBlankLines));
    return ward;
  }

  private List<Candidate> getCandidates(List<String> nonBlankLines) {
    List<List<String>> candidateSections = getCandidateSections(nonBlankLines);
    List<Candidate> candidates = new ArrayList<Candidate>();
    for (List<String> candidteSection : candidateSections) {
      candidates.add(candidateBuilder.parseCandidate(candidteSection));
    }
    return candidates;
  }

  private List<List<String>> getCandidateSections(List<String> nonBlankLines) {
    List<String> active = new ArrayList<String>();
    List<List<String>> candidateSections = new ArrayList<List<String>>();
    boolean start = false;
    boolean add = false;
    nextLine: for (String line : nonBlankLines) {
      if (!start) {
        if (line.equals("invalid")) {
          start = true;
        }
        continue;
      }
      if (!add) {
        if (line.isEmpty()) {
          continue;
        }
        for (char c : line.toCharArray()) {
          if (Character.isLetter(c) && !Character.isUpperCase(c)) {
            continue nextLine;
          }
        }
        add = true;
      }
      if (line.contains("(P)")) {
        String lastLine = removeNominator(line);
        if (!lastLine.isEmpty()) {
          active.add(lastLine);
        }
        candidateSections.add(active);
        active = new ArrayList<String>();
        add = false;
      }
      if (add) {
        active.add(line);
      }
    }
    return candidateSections;
  }

  private String removeNominator(String line) {
    StringBuilder stringBuilder = new StringBuilder(line.length());
    List<String> words = new ArrayList<String>(Arrays.asList(line.split("\\(P\\)")[0].split(" ")));
    int lastWord = words.lastIndexOf("Candidate");
    if (lastWord < 0) {
      lastWord = guessLastWordIndex(words);
    }
    for (int i = 0; i <= lastWord; i++) {
      if (i != 0) {
        stringBuilder.append(" ");
      }
      stringBuilder.append(words.get(i));
    }
    return stringBuilder.toString();
  }

  private int guessLastWordIndex(List<String> words) {
    for (int i = words.size() - 2; i >= 0; i--) {
      if (words.get(i).matches(".*[a-z].*")) {
        return i - 1;
      }
    }
    return -1;
  }

  private List<String> getNonBlankLines(String text) {
    List<String> nonBlankLines = new ArrayList<String>();
    for (String line : text.split("\n")) {
      line = line.trim();
      if (!line.isEmpty()) {
        nonBlankLines.add(line);
      }
    }
    return nonBlankLines;
  }

  private List<String> grep(String regex, List<String> lines) {
    List<String> matchingLines = new ArrayList<String>();
    for (String line : lines) {
      if (line.contains(regex)) {
        matchingLines.add(line);
      }
    }
    return matchingLines;
  }
}
