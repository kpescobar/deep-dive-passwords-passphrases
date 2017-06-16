package edu.cnm.deepdive.passwords.passphrases;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class WordList {

  public static final int MINIMUM_WORD_LENGTH = 5;

  private static final String PROPERTIES_FILE = "resources/text.properties";

  private static String usageMessage;
  private static String errorMessage;
  private static String warningMessage;

  public static void main(String[] args) {
    try {
      loadResources();
      int phraseLength = (args.length > 0) ? Integer.parseInt(args[0]) : MINIMUM_WORD_LENGTH;
      if (phraseLength <= 0) {
        throw new IllegalArgumentException(errorMessage);
      } else if (phraseLength < MINIMUM_WORD_LENGTH) {
        System.out.println(warningMessage);
      }
    } catch (NumberFormatException ex) {
      ex.printStackTrace();
      System.out.println(errorMessage);
      System.out.println(usageMessage);
      System.exit(1);
    } catch (IllegalArgumentException ex) {
      System.out.println(errorMessage);
      System.out.println(usageMessage);
      System.exit(1);
    } catch (IOException ex) {
      ex.printStackTrace();
      System.exit(1);
    }
  }

  private static void loadResources() 
      throws IOException {
    Properties properties = new Properties();
    try (InputStream input = WordList.class.getClassLoader().getResourceAsStream(PROPERTIES_FILE)) {
      properties.load(input);
      usageMessage = properties.getProperty("usage.messge");
      errorMessage = properties.getProperty("error.message");
      warningMessage = properties.getProperty("warning.message");
    }
  }
}
