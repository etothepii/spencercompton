package uk.co.epii.conservatives.spencercompton.candidateExtractor;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * User: James Robinson
 * Date: 26/04/2014
 * Time: 12:45
 */
public class HammersmithAndFulhumCandidateBuilderTests {

  @Test
  public void removeNoinatorTest1() {
    StringBuilder stringBuilder = new StringBuilder("Conservative Party Candidate William R B Gladstone(P) Rupert A J");
    StringBuilder result = HammersmithAndFulhumCandidateBuilder.removeNominator(stringBuilder);
    StringBuilder expected = new StringBuilder("Conservative Party Candidate");
    assertEquals(expected.toString(), result.toString());
  }

  @Test
  public void removeNoinatorTest2() {
    StringBuilder stringBuilder = new StringBuilder("Liberal Democrat William R B Gladstone(P) Rupert A J");
    StringBuilder result = HammersmithAndFulhumCandidateBuilder.removeNominator(stringBuilder);
    StringBuilder expected = new StringBuilder("Liberal Democrat");
    assertEquals(expected.toString(), result.toString());
  }

}
