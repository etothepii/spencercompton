package uk.co.epii.conservatives.spencercompton;

import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {

  private static Google google = new Google();

  public static void main(String[] args) {
      for (Council council : getCouncils(args.length > 0 ? args[0] : null)) {
          processCouncil(council);
      }
  }

    private static List<Council> getCouncils(String fileName) {
        try {
            Reader fileReader = fileName == null ? new InputStreamReader(System.in) : new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            List<Council> councils = new ArrayList<Council>();
            String in;
            while ((in = bufferedReader.readLine()) != null) {
                String[] parts = in.split(" - ");
                councils.add(new Council(parts[0], parts[1]));
            }
            bufferedReader.close();
            fileReader.close();
            return councils;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void processCouncil(Council council) {
      System.out.println(council.name);
      getSearchResults(council, "Statement of Persons Nominated");
      getSearchResults(council, "Polling Stations");
      getSearchResults(council, "Notice of Election");
  }

  private static void getSearchResults(Council council, String search) {
      for (int i = 0; i < 100; i++) {
          try {
              getSearchResultsUnsafe(council, search);
              return;
          }
          catch (NullPointerException npe) {
              try {
                  Thread.sleep(1000L);
              } catch (InterruptedException e) {
                  throw new RuntimeException(e);
              }
          }
      }
  }

  private static void getSearchResultsUnsafe(Council council, String search) {
    List<Result> results = google.search(council.url, search);
    System.out.println("  " + search);
    for (Result result : results) {
      System.out.println(String.format("    %s - %s", result.getTitle(), result.getUrl()));
    }
  }

}
