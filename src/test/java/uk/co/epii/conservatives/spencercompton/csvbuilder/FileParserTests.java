package uk.co.epii.conservatives.spencercompton.csvbuilder;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * User: James Robinson
 * Date: 26/04/2014
 * Time: 23:05
 */
public class FileParserTests {

  @Test
  public void getFileTest1() {
    String workingFile = "data/elections/2014/pollingAreas";
    String relativePath = "./londonBoroughs";
    String result = FileParser.getFile(workingFile, relativePath);
    String expected = "data/elections/2014/londonBoroughs";
    assertEquals(expected, result);
  }

  @Test
  public void getFileTest2() {
    String workingFile = "data/elections/2014/pollingAreas";
    String relativePath = "../londonBoroughs";
    String result = FileParser.getFile(workingFile, relativePath);
    String expected = "data/elections/londonBoroughs";
    assertEquals(expected, result);
  }

  @Test
  public void getFileTest3() {
    String workingFile = "data/elections/2014/pollingAreas";
    String relativePath = "/londonBoroughs";
    String result = FileParser.getFile(workingFile, relativePath);
    String expected = "/londonBoroughs";
    assertEquals(expected, result);
  }

  @Test
  public void extractCandidateTest1() {
    String in = "* BROCKLEBANK-FOWLER Victoria Stephanie Amy - Conservative Party Candidate";
    String[] result = FileParser.parseCandidateParts(in);
    String[] expected = new String[] {
            "BROCKLEBANK-FOWLER",
            "Victoria Stephanie Amy",
            "Conservative Party Candidate"
    };
    assertArrayEquals(expected, result);
  }

  @Test
  public void extractCandidateTest2() {
    String in = "* BROCKLEBANK FOWLER Victoria Stephanie Amy - Conservative Party Candidate";
    String[] result = FileParser.parseCandidateParts(in);
    String[] expected = new String[] {
            "BROCKLEBANK FOWLER",
            "Victoria Stephanie Amy",
            "Conservative Party Candidate"
    };
    assertArrayEquals(expected, result);
  }

  @Test
  public void extractCandidateTest3() {
    String in = "* BROCKLEBANK-FOWLER Victoria Stephanie Amy -";
    String[] result = FileParser.parseCandidateParts(in);
    String[] expected = new String[] {
            "BROCKLEBANK-FOWLER",
            "Victoria Stephanie Amy",
            ""
    };
    assertArrayEquals(expected, result);
  }
}
