package uk.co.epii.conservatives.spencercompton.csvbuilder;

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

  private static Pattern parentDirectoryMatcher = Pattern.compile("/[^/]*/..");

  private final List<Candidate> candidates;
  private final List<PollingArea> pollingAreas;
  private final List<PollingArea> hierarchy;

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

  public FileParser(Map<String, Integer> politicalPartyMap, String workingFile, List<PollingArea> hierarchy,
                    List<PollingArea> pollingAreas, List<Candidate> candidates) {
    this.politicalPartyMap = politicalPartyMap;
    this.workingFile = workingFile;
    this.hierarchy = hierarchy;
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
