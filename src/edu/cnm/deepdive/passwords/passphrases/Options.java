/**
 * 
 */
package edu.cnm.deepdive.passwords.passphrases;

import java.util.HashMap;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.MissingArgumentException;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.UnrecognizedOptionException;

/**
 * @author Kelly Escobar
 *
 */
public class Options {
  // TODO Create properties file to call from.
  // TODO Include warning messages.
  public static final String JAR_FILE_NAME = "guard.jar";
  
  private static final String INVALID_DELIMITERS = "[<>&|*?^]";
  private static final String MESSAGES_BUNDLE = "resources/messages";
  private static final String OPTIONS_DESCRIPTION_BUNDLE = "resources/options";
  
  private static final String FATAL_MESSAGE = "Not able to load messages bundle.";
  private static final String MISSING_ARGUMENT_KEY = "error.missingargument.message";
  private static final String BAD_OPTION_KEY = "pp.error.option.message";
  private static final String BAD_ARGUMENT_KEY = "error.badargument.message";
  private static final String MISSING_OPTIONS_BUNDLE_KEY = "res.error.options.message";
  
  private static final String PASSWORD_EXTREME_LENGTH_WARNING = "pw.warning.extremelength.message";
  private static final String PASSPHRASE_EXTREME_LENGTH_WARNING = "pp.warning.extremelength.message";
  private static final String PASSPHRASE_LENGTH_WARNING = "pp.warning.length.message";
  private static final String PASSWORD_LENGTH_WARNING = "pw.warning.length.message";
  private static final String OPTIONS_CONFLICT_WARNING = "opts.conflict.warning.message";
  private static final String LENGTH_ERROR = "error.length.message";
  private static final String RESERVED_CHARACTER_ERROR = "pp.error.reserve.message";
  
  private static final String HELP_OPTION_KEY = "help.option";
  private static final String LENGTH_OPTION_KEY = "length.option";
  private static final String DELIMITER_OPTION_KEY = "delimiter.option";
  private static final String LIST_OPTION_KEY = "word-list.option";
  private static final String MODE_OPTION_KEY = "password-mode.option";
  private static final String UPPER_OPTION_KEY = "exclude-upper.option";
  private static final String LOWER_OPTION_KEY = "exclude-lower.option";
  private static final String DIGITS_OPTION_KEY = "exclude-digits.option";
  private static final String PUNCTUATION_OPTION_KEY = "exclude-punctuation.option";
  private static final String AMBIGUOUS_OPTION_KEY = "include-ambiguous.option";
  private static String usageMessage = "java -jar %s [options]";
  
  static HashMap<String, Object> getOptions(String[] args) {
    ResourceBundle messageBundle = null;
    org.apache.commons.cli.Options opts = null;
    try {
      messageBundle = ResourceBundle.getBundle(MESSAGES_BUNDLE);
    } catch (MissingResourceException ex) {
      System.out.println(FATAL_MESSAGE);
      return null;
    }
        
    try {
      opts = buildOptions();  
      HashMap<String, Object> map = parseCommandLine(args, opts);
      if (map != null) {
        validateCommandLine(map, messageBundle);
      }
      
//      int length = ((Number)map.get("L")).intValue();
//      if (cmdLine.hasOption("m")) {
//        if (length < 5) {
//          System.out.println(messageBundle.getString(PASSWORD_LENGTH_WARNING));
//        }
//        if (length > 16) {
//          System.out.println(messageBundle.getString(PASSWORD_EXTREME_LENGTH_WARNING));
//        }
//      }
      return map;
    } catch (MissingArgumentException ex) {
      Option missing = ex.getOption();
      String optName = missing.getOpt();
      displayError(messageBundle, MISSING_ARGUMENT_KEY, opts, optName);  
      return null;
    } catch (UnrecognizedOptionException ex) {
      String optName = ex.getOption();
      displayError(messageBundle, BAD_OPTION_KEY, opts, optName);
      return null;
    } catch (ParseException ex) {
      displayError(messageBundle, BAD_ARGUMENT_KEY, opts, ex.getLocalizedMessage());
      return null;
    } catch (MissingResourceException ex) {
      displayError(messageBundle, MISSING_OPTIONS_BUNDLE_KEY, opts, OPTIONS_DESCRIPTION_BUNDLE);
      return null;
    } catch (IllegalArgumentException ex) {
      displayError(messageBundle, LENGTH_ERROR, opts, null);
      return null;
    }

  }
  
  private static void validateCommandLine(HashMap<String, Object> map, ResourceBundle messageBundle) {
    if (map.containsKey("m")) {
      for (Map.Entry<String, Object> entry : map.entrySet()) {
        switch (entry.getKey()) {
          case "L":
            int length = ((Number) entry.getValue()).intValue();
            if (length <= 0) {
              throw new NegativeArraySizeException();
            }
            break;
          case "a":
            break;
          case "d":
          case "w":
            break;
          case "b":
          case "s":
          case "n":
          case "p":
          default:                   
        }
      }
    } else {
      for (Map.Entry<String, Object> entry : map.entrySet()) {
        switch (entry.getKey()) {
          case "L":
            int length = ((Number) entry.getValue()).intValue();
            if (length <= 0) {
              throw new NegativeArraySizeException();
            }
            break;
          case "d":
            String delimiter = (String) entry.getValue();
            if (delimiter.matches(INVALID_DELIMITERS)) {
              throw new IllegalArgumentException();
            }
            break;
          case "w":
            break;
          case "b":
          case "s":
          case "n":
          case "p":
          case "a":
          default:
            break;
        }
      }
    }
  }
  

  private static HashMap<String, Object> parseCommandLine(String[] args,
      org.apache.commons.cli.Options opts) throws ParseException {
    DefaultParser parser = new DefaultParser();
    HashMap<String, Object> map = new HashMap<>();
    CommandLine cmdLine = parser.parse(opts, args);
    if (cmdLine.hasOption('?')) {
      display(null, usageMessage, opts);
      return null;
    }
    for (Option option : cmdLine.getOptions()) {
      String opt = option.getOpt();
      map.put(opt, cmdLine.getParsedOptionValue(opt));
    }
    return map;
  }

  private static org.apache.commons.cli.Options buildOptions() 
      throws MissingResourceException {
    org.apache.commons.cli.Options opts;
    ResourceBundle bundle = ResourceBundle.getBundle(OPTIONS_DESCRIPTION_BUNDLE);
    Option lengthOption = Option.builder("L").argName("length")
                                             .hasArg()
                                             .longOpt("length")
                                             .numberOfArgs(1)
                                             .type(Number.class)
                                             .desc(bundle.getString(LENGTH_OPTION_KEY))
                                             .build();
    Option delimiterOption = Option.builder("d").argName("delimiter")
                                                .hasArg()
                                                .longOpt("delimiter")
                                                .numberOfArgs(1)
                                                .optionalArg(true)
                                                .type(String.class)
                                                .desc(bundle.getString(DELIMITER_OPTION_KEY))
                                                .build();
    Option wordListOption = Option.builder("w").argName("path-to-list-file")
                                               .hasArg()
                                               .longOpt("word-list")
                                               .numberOfArgs(1)
                                               .type(String.class)
                                               .desc(bundle.getString(LIST_OPTION_KEY))
                                               .build();
    Option excludeUpperOption = Option.builder("b").longOpt("exclude-upper")
                                                   .hasArg(false)
                                                   .desc(bundle.getString(UPPER_OPTION_KEY))
                                                   .build();
    Option excludeLowerOption = Option.builder("s").longOpt("exclude-lower")
                                                   .hasArg(false)
                                                   .desc(bundle.getString(LOWER_OPTION_KEY))
                                                   .build();
    Option excludeDigitsOption = Option.builder("n").longOpt("exclude-digits")
                                                    .hasArg(false)
                                                    .desc(bundle.getString(DIGITS_OPTION_KEY))
                                                    .build();
    Option excludePunctOption = Option.builder("p").longOpt("exclude-punctuation")
                                                   .hasArg(false)
                                                   .desc(bundle.getString(PUNCTUATION_OPTION_KEY))
                                                   .build();
    Option includeAmbigOption = Option.builder("a").longOpt("exclude-ambiguous")
                                                     .hasArg(false)
                                                     .desc(bundle.getString(AMBIGUOUS_OPTION_KEY))
                                                     .build();
    Option helpOption = Option.builder("?").longOpt("help")
                                           .hasArg(false)
                                           .desc(bundle.getString(HELP_OPTION_KEY))
                                           .build();
    Option modeOption = Option.builder("m").longOpt("password-mode")
                                           .hasArg(false)
                                           .desc(bundle.getString(MODE_OPTION_KEY))
                                           .build();

    
    opts = new org.apache.commons.cli.Options().addOption(lengthOption)
                                              .addOption(helpOption)
                                              .addOption(delimiterOption)
                                              .addOption(wordListOption)
                                              .addOption(excludeUpperOption)
                                              .addOption(excludeLowerOption)
                                              .addOption(excludeDigitsOption)
                                              .addOption(excludePunctOption)
                                              .addOption(includeAmbigOption)
                                              .addOption(modeOption);
    return opts;
  }

  private static void displayError(ResourceBundle messageBundle, String messageKey,
      org.apache.commons.cli.Options opts, String optName) {
    String message = messageBundle.getString(messageKey); 
    message = String.format(message, optName);
    display(message, usageMessage, opts);
  } 
  
  private static void display(String message, String usage, org.apache.commons.cli.Options opts) {
    if (message != null) {
      System.out.println(message);
    }
    if (opts != null) {
    new HelpFormatter().printHelp(usage, opts);
    }
  }
  
  
}














