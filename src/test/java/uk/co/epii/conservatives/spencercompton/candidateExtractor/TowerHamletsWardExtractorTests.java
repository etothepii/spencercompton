package uk.co.epii.conservatives.spencercompton.candidateExtractor;

import edu.emory.mathcs.backport.java.util.Arrays;
import org.junit.Test;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.Assert.assertEquals;

/**
 * User: James Robinson
 * Date: 26/04/2014
 * Time: 00:20
 */
public class TowerHamletsWardExtractorTests {

  @Test
  public void testExtractFile() {
    URL url;
    try {
      url = new URL(
              "file:///Users/jrrpl/git/politics/spencercompton/src/test/resources/" +
                      "towHamBethnalGreenWard.doc");
    } catch (MalformedURLException e) {
      throw new RuntimeException(e);
    }
    Ward result = null;
    try {
      result = new TowerHamletsWardExtractor().extract(url);
    }
    catch (IOException e) {
      throw new RuntimeException(e);
    }
    Ward expected = new Ward("Bethnal Green Ward",
            "file:///Users/jrrpl/git/politics/spencercompton/src/test/resources/" +
                    "towHamBethnalGreenWard.doc",
            Arrays.asList(new Candidate[] {
                    new Candidate("CHOWDHURY Babu", "Tower Hamlets First"),
                    new Candidate("GIBBS Amy Whitelock", "The Labour Party Candidate"),
                    new Candidate("GULAID Abdirashid", "The Labour Party Candidate"),
                    new Candidate("HAQUE Shafiqul", "Tower Hamlets First"),
                    new Candidate("HEEMSKERK Clive", "Trade Unionist and Socialist Coalition"),
                    new Candidate("ISLAM Sirajul", "The Labour Party Candidate"),
                    new Candidate("KENYON PEERS Ellen", "Trade Unionist and Socialist Coalition"),
                    new Candidate("KHA`LIQUE Taz", "The Conservative Party Candidate"),
                    new Candidate("MAK Alan", "The Conservative Party Candidate"),
                    new Candidate("PATEL Meera Amrish", "The Conservative Party Candidate"),
                    new Candidate("SHAJAHAN Kamrun", "Liberal Democrat"),
                    new Candidate("THORNE Chris", "Green Party"),
                    new Candidate("ULLAH Salim", "Tower Hamlets First"),
                    new Candidate("ZSIKHOTSKA Lubov", "UK Independence Party (UKIP)")
            }));
    assertEquals("Name", expected.name, result.name);
    assertEquals("Number of Candidates", expected.candidates.size(), result.candidates.size());
    for (int i = 0; i < expected.candidates.size(); i++) {
      assertEquals("Candidate[" + i + "]", expected.candidates.get(i), result.candidates.get(i));
    }
  }

}
