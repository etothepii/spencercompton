package uk.co.epii.conservatives.spencercompton;

import java.io.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * User: James Robinso
 * Date: 26/04/2014
 * Time: 22:11
 */
public class FileExpander {

  private static final Pattern postalDistrictRegex = Pattern.compile("_\\{([0-9A-Z])\\}");

  private Reader input;
  private Writer output;

  public FileExpander(Reader input, Writer output) {
    this.input = input;
    this.output = output;
  }

  public void process() throws IOException {
    BufferedReader bufferedReader = new BufferedReader(input);
    PrintWriter printWriter = new PrintWriter(output);
    String in;
    while ((in = bufferedReader.readLine()) != null) {
      for (String line : expand(in)) {
        printWriter.println(line);
      }
    }
    printWriter.flush();
    printWriter.close();
    bufferedReader.close();
  }

  public static String[] expand(String line) {
    ArrayList<String> lines = new ArrayList<String>();
    Matcher matcher = postalDistrictRegex.matcher(line);
    if (!matcher.find()) {
      return new String[] {line};
    }
    StringBuilder stringBuilder = new StringBuilder(line);
    stringBuilder.delete(matcher.start() + 1, matcher.start() + 4);
    char terminus = matcher.group(1).charAt(0);
    for (char c = Character.isLetter(terminus) ? 'A' : '1'; c <= terminus; c++) {
      stringBuilder.setCharAt(matcher.start(), c);
      lines.add(stringBuilder.toString());
    }
    return lines.toArray(new String[lines.size()]);
  }
}
