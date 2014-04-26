package uk.co.epii.conservatives.spencercompton;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

/**
 * User: James Robinson
 * Date: 26/04/2014
 * Time: 22:16
 */
public class FileExpanderTests {

  @Test
  public void expandTest1() {
    String in = "    YT_{D}";
    String[] result = FileExpander.expand(in);
    String[] expected = new String[] {
            "    YTA",
            "    YTB",
            "    YTC",
            "    YTD"
    };
    assertArrayEquals(expected, result);
  }

  @Test
  public void expandTest2() {
    String in = "    YT_{4}";
    String[] result = FileExpander.expand(in);
    String[] expected = new String[] {
            "    YT1",
            "    YT2",
            "    YT3",
            "    YT4"
    };
    assertArrayEquals(expected, result);
  }

  @Test
  public void expandTest3() {
    String in = "    YT_{D}A";
    String[] result = FileExpander.expand(in);
    String[] expected = new String[] {
            "    YTAA",
            "    YTBA",
            "    YTCA",
            "    YTDA"
    };
    assertArrayEquals(expected, result);
  }

  @Test
  public void expandTest4() {
    String in = "    YTA";
    String[] result = FileExpander.expand(in);
    String[] expected = new String[] {
            "    YTA"
    };
    assertArrayEquals(expected, result);
  }

}
