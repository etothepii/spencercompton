package uk.co.epii.conservatives.spencercompton.candidateExtractor;

import edu.emory.mathcs.backport.java.util.Arrays;
import org.junit.Test;

import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.Assert.assertEquals;

/**
 * User: James Robinson
 * Date: 26/04/2014
 * Time: 00:20
 */
public class HammersmithAndFulhumWardExtractorTests {

  @Test
  public void regexTest1() {
    String test = "A(B)C";
    String expected = "A";
    String result = test.split("\\(B\\)")[0];
    assertEquals(expected, result);
  }

  @Test
  public void testExtractFile() {
    URL url;
    try {
      url = new URL(
              "file:///Users/jrrpl/git/politics/spencercompton/src/test/resources/" +
                      "hamFulAddisonWard.pdf");
    } catch (MalformedURLException e) {
      throw new RuntimeException(e);
    }
    Ward result = new HammersmithAndFulhumWardExtractor().extract(url);
    Ward expected = new Ward("Addison Ward",
            "file:///Users/jrrpl/git/politics/spencercompton/src/test/resources/" +
                    "hamFulAddisonWard.pdf",
            Arrays.asList(new Candidate[] {
                    new Candidate("BURDEN Janet", "Liberal Democrat"),
                    new Candidate("CAWLEY Joe", "Conservative Party Candidate"),
                    new Candidate("CONNELL Adam", "Labour Party Candidate"),
                    new Candidate("DONOVAN Belinda", "Conservative Party Candidate"),
                    new Candidate("FENNIMORE Sue", "Labour Party Candidate"),
                    new Candidate("FORSYTH Charles", "Conservative Party Candidate"),
                    new Candidate("KAREEM Khafi", "Labour Party Candidate")
            }));
    assertEquals("Name", expected.name, result.name);
    assertEquals("Number of Candidates", expected.candidates.size(), result.candidates.size());
    for (int i = 0; i < expected.candidates.size(); i++) {
      assertEquals("Candidate[" + i + "]", expected.candidates.get(i), result.candidates.get(i));
    }
  }

}
