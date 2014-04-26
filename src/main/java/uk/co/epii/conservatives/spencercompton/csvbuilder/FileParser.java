package uk.co.epii.conservatives.spencercompton.csvbuilder;

import org.springframework.beans.support.PagedListHolder;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * User: James Robinson
 * Date: 26/04/2014
 * Time: 22:57
 */
public class FileParser {

  private static Pattern parentDirectoryMatcher = Pattern.compile("/[^/]*/\\.\\.");
  private static Pattern candidateMatcher = Pattern.compile("^\\* ?([^a-z]*) ([A-Z].*) - ?(.*)$");

  private final List<Candidate> candidates;
  private final List<PollingArea> pollingAreas;
  private final List<PollingArea> hierarchy;

  private int candidateCount = 0;
  private int indent = 0;
  private String workingFile;
  private Map<String, Integer> politicalPartyMap;

  public FileParser(List<Candidate> candidates, List<PollingArea> pollingAreas, List<PollingArea> hierarchy) {
    this.candidates = candidates;
    this.pollingAreas = pollingAreas;
    this.hierarchy = hierarchy;
  }

  public FileParser() {
    this(new ArrayList<Candidate>(), new ArrayList<PollingArea>(), new ArrayList<PollingArea>());
  }

  public FileParser(Map<String, Integer> politicalPartyMap, String workingFile, PollingArea parent,
                    List<PollingArea> pollingAreas, List<Candidate> candidates) {
    this.politicalPartyMap = politicalPartyMap;
    this.workingFile = workingFile;
    this.hierarchy = new ArrayList<PollingArea>();
    if (parent != null) {
      hierarchy.add(parent);
    }
    this.pollingAreas = pollingAreas;
    this.candidates = candidates;
  }

  public List<Candidate> getCandidates() {
    return candidates;
  }

  public List<PollingArea> getPollingAreas() {
    return pollingAreas;
  }

  public List<PollingArea> getHierarchy() {
    return hierarchy;
  }

  public String getWorkingFile() {
    return workingFile;
  }

  public void setWorkingFile(String workingFile) {
    this.workingFile = workingFile;
  }

  public Map<String, Integer> getPoliticalPartyMap() {
    return politicalPartyMap;
  }

  public void setPoliticalPartyMap(Map<String, Integer> politicalPartyMap) {
    this.politicalPartyMap = politicalPartyMap;
  }

  public void process() throws IOException {
    FileReader fileReader = new FileReader(workingFile);
    BufferedReader bufferedReader = new BufferedReader(fileReader);
    String in;
    while ((in = bufferedReader.readLine()) != null) {
      processLine(in);
    }
    bufferedReader.close();
    fileReader.close();
  }

  private void processLine(String in) {
    int indent = getIndent(in);
    in = in.trim();
    if (in.startsWith(".") || in.startsWith("/")) {
      try {
        readSubDocument(in);
      } catch (IOException e) {
        throw new RuntimeException("File not found: " + in);
      }
    }
    if (in.startsWith("*")) {
      candidates.add(parseCandidate(in));
    }
    else if (in.startsWith("!")) {
      getActive().setChildType(in);
    }
    else {
      if (indent <= this.indent) {
        returnToCommonAncestor(indent);
      }
      addChild(in);
    }
  }

  private void readSubDocument(String in) throws IOException {
    String fileLocation = getFile(workingFile, in);
    FileParser fileParser = new FileParser(politicalPartyMap, fileLocation, getActive(), pollingAreas, candidates);
    fileParser.process();
  }

  private void addChild(String in) {
    PollingArea parent = getActive();
    PollingArea pollingArea = new PollingArea(in, parent == null ? 0 : parent.getId());
    pollingAreas.add(pollingArea);
    hierarchy.add(pollingArea);
    candidateCount = 0;
  }

  private Candidate parseCandidate(String in) {
    candidateCount++;
    String[] candidateParts = parseCandidateParts(in);
    Candidate candidate = new Candidate(getActive().getId());
    candidate.setSurname(candidateParts[0]);
    candidate.setFirstNames(candidateParts[1]);
    candidate.setOrder(candidateCount * 10);
    candidate.setPoliticalParty(politicalPartyMap.get(candidateParts[2]));
    return candidate;
  }

  static String[] parseCandidateParts(String in) {
    Matcher matcher = candidateMatcher.matcher(in);
    String[] candidateParts = new String[3];
    if (!matcher.find()) {
      throw new RuntimeException("Not a recognized candidate: " + in);
    }
    for (int i = 0; i < 3; i++) {
      candidateParts[i] = matcher.group(i + 1);
    }
    return candidateParts;
  }

  private void returnToCommonAncestor(int indent) {
    int generations = (this.indent - indent) / 2 + 1;
    for (int i = 0; i < generations; i++) {
      hierarchy.remove(hierarchy.size() - 1);
    }
  }

  private PollingArea getActive() {
    return hierarchy.isEmpty() ? null : hierarchy.get(hierarchy.size() - 1);
  }

  private int getIndent(String in) {
    for (int i = 0; i < in.length(); i++) {
      if (in.charAt(i) != ' ') {
        return i;
      }
    }
    return in.length();
  }

  static String getFile(String workingFile, String relativePath) {
    StringBuilder concat = new StringBuilder(concat(workingFile, relativePath));
    while (true) {
      Matcher matcher = parentDirectoryMatcher.matcher(concat);
      if (!matcher.find()) {
        return concat.toString();
      }
      concat.delete(matcher.start(), matcher.end());
    }
  }

  private static String concat(String workingFile, String relativePath) {
    if (relativePath.startsWith("/")) {
      return relativePath;
    }
    String stem = workingFile.substring(0, workingFile.lastIndexOf('/') + 1);
    if (relativePath.startsWith("./")) {
      return stem + relativePath.substring(2);
    }
    if (relativePath.startsWith("../")) {
      return stem + relativePath;
    }
    throw new RuntimeException("Unsupported relative path: '" + relativePath + "'");
  }
}
