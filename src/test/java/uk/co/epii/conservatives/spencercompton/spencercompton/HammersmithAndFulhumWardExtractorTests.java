package uk.co.epii.conservatives.spencercompton.spencercompton;

import edu.emory.mathcs.backport.java.util.Arrays;
import org.junit.Test;
import uk.co.epii.conservatives.spencercompton.candidateExtrator.Candidate;
import uk.co.epii.conservatives.spencercompton.candidateExtrator.HammersmithAndFulhumWardExtractor;
import uk.co.epii.conservatives.spencercompton.candidateExtrator.Ward;

import static org.junit.Assert.assertEquals;

/**
 * User: James Robinson
 * Date: 26/04/2014
 * Time: 00:20
 */
public class HammersmithAndFulhumWardExtractorTests {

  @Test
  public void testExtractFile(String file) {
    Ward result = new HammersmithAndFulhumWardExtractor().extract(
            "file:///Users/jrrpl/git/politics/spencercompton/src/test/resources/" +
                    "hamFulAddisonWard.pdf");
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
    assertEquals("Hammersmith and Fulhum Addison Ward", expected, result);
  }

}
