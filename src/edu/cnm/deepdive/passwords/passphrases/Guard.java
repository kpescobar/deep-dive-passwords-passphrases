/**
 * Guard.java
 */
package edu.cnm.deepdive.passwords.passphrases;

import java.util.HashMap;

/**
 * Program that generates random passwords/passphrases.
 * 
 * Generation uses a cryptographically secure random number generator
 * to select words from a list or characters from a pool.
 * 
 * @author Kelly Escobar
 *
 */
public class Guard {

  /**
   * Parse command line arguments using Apache Commons CLI library,
   * then instantiate and invoke the appropriate classes and methods 
   * to generate the requested artifact.
   * 
   * @param args   Command line arguments, specifying generation options. 
   */
  public static void main(String[] args) {
    HashMap<String, Object> map = Options.getOptions(args);
    if (map != null) {
      String artifact = generateArtifact(map);
      emitArtifact(artifact);
    }
  }
  
  // TODO Set fields for all specified options.
  // TODO Set length (min/max) to option value.
  // TODO Select/combine/print string of characters for user.
  static String generateArtifact(HashMap<String, Object> map) {
    if (map.containsKey("m")) {
      PasswordGenerator gen = new SecurePasswordGenerator();
      return gen.generate();
    }
    return null;
  }
  
  static void emitArtifact(String artifact) {
    // TODO Make this smarter.
    System.out.println(artifact);
  }

}
