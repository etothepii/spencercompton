package uk.co.epii.conservatives.spencercompton;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import uk.co.epii.conservatives.spencercompton.csvbuilder.FileParser;

import java.io.*;

public class Main {

  public static void main(String[] args) throws IOException {
    if (args.length > 1 && args[0].equals("DISPLAY")) {
      ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
      for (int i = 1; i < args.length; i++) {
        Council council = (Council)context.getBean(args[i]);
        System.out.println(council.display());
      }
    }
    if (args.length > 0 && args[0].equals("EXPAND")) {
      Reader input = args.length > 1 ? new FileReader(args[1]) : new InputStreamReader(System.in);
      Writer output = args.length > 2 ? new FileWriter(args[2]) : new OutputStreamWriter(System.out);
      FileExpander fileExpander = new FileExpander(input, output);
      fileExpander.process();
      output.close();
      input.close();
    }
    if (args.length > 1 && args[0].equals("CSV")) {
      ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
      FileParser fileParser = (FileParser)context.getBean("fileParser");
      fileParser.setWorkingFile(args[1]);
      fileParser.process();
      PrintWriter candidates =
              new PrintWriter(args.length > 3 ? new FileWriter(args[2]) : new OutputStreamWriter(System.out)) ;
      for (Object candidate : fileParser.getCandidates()) {
        candidates.println(candidate);
      }
      candidates.flush();
      candidates.close();
      PrintWriter pollingAreas =
              new PrintWriter(args.length > 3 ? new FileWriter(args[3]) : new OutputStreamWriter(System.out)) ;
      for (Object pollingArea : fileParser.getPollingAreas()) {
        pollingAreas.println(pollingArea);
      }
      pollingAreas.flush();
      pollingAreas.close();
    }
  }

}
