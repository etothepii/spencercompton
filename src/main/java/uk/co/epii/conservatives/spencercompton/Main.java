package uk.co.epii.conservatives.spencercompton;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

  public static void main(String[] args) {
    if (args.length > 1 && args[0].equals("DISPLAY")) {
      ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
      for (int i = 1; i < args.length; i++) {
        Council council = (Council)context.getBean(args[i]);
        System.out.println(council.display());
      }
    }
  }

}
