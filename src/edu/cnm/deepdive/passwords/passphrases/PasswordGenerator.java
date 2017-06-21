package edu.cnm.deepdive.passwords.passphrases;

import java.util.Random;

/**
 * Implementation of a simple password generator.
 * @author Kelly Escobar
 * @version 1.0
 */

public class PasswordGenerator {

  /** (Not cryptographically secure) pseudo-random number generator. */
  protected Random rng = new Random();
  
  private static final String UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
  private static final String LOWERCASE = UPPERCASE.toLowerCase();
  private static final String NUMBERS = "0123456789";
  private static final String PUNCTUATION = "!@#$%&*,.";
  private static final String AMBIGUOUS = "[Ol]";
  
  private char[] pool = null;
  private int minLength = 6;
  private int maxLength = 12;
  private boolean includeUpperCase = true;
  private boolean includeLowerCase = true;
  private boolean includeNumbers = true;
  private boolean includePunctuation = false;
  private boolean excludeAmbiguous = true;
  
  /**
   * Not currently implemented.
   * 
   * @param args Command-line parameters for password generation options.
   */
  public static void main(String[] args) {

  }
  /**
   * Initialize with default value.
   */
  public PasswordGenerator() {
    
  }
  
  /**
   * Initialize minimum and maximum length of password.
   * 
   * @param minLength
   * @param maxLength
   */
  
  public PasswordGenerator(int minLength, int maxLength) {
    this();
    this.minLength = minLength;
    this.maxLength = maxLength;
  }
  
  /**
   * Initializes generator with character pool excludes a small amount of ambiguous characters.
   * 
   * @param minLength
   * @param maxLength
   * @param includeUpperCase
   * @param includeLowerCase
   * @param includeNumbers
   * @param includePunctuation
   * @param excludeAmbiguous excludes the following characters: O, l (Uppercase O and Lowercase l)
   */
  public PasswordGenerator(int minLength, int maxLength, 
      boolean includeUpperCase, boolean includeLowerCase, 
      boolean includeNumbers, boolean includePunctuation, 
      boolean excludeAmbiguous) {
    this(minLength, maxLength);
    this.includeUpperCase = includeUpperCase;
    this.includeLowerCase = includeLowerCase;
    this.includeNumbers = includeNumbers;
    this.includePunctuation = includePunctuation;
    this.excludeAmbiguous = excludeAmbiguous;
    
  }
  
  private void setupPool() {
    if (pool == null) {
      StringBuilder builder = new StringBuilder();
      if (includeLowerCase) {
        builder.append(LOWERCASE);
      }
      if (includeUpperCase) {
        builder.append(UPPERCASE);
      }
      if (includeNumbers) {
        builder.append(NUMBERS);
      }
      if (includePunctuation) {
        builder.append(PUNCTUATION);
      }
      String work = builder.toString();
      if (excludeAmbiguous) {
        work.replaceAll(AMBIGUOUS, "");
      }
      pool = work.toCharArray();
    }
  }
  /**
   * Randomly selects a password and sets the length within the set parameters.
   * 
   * @return
   */
  public String generate() {
    setupPool();
    int passwordLength = minLength + rng.nextInt(maxLength - minLength + 1);
    StringBuilder builder = new StringBuilder();
    for (int i = 0; i < passwordLength; i++) {
      char selection = pool[rng.nextInt(pool.length)];
      builder.append(selection);
    }
    return builder.toString();
  }


  /**
   * Get maximum password length.
   * 
   * @return the maxLength
   */
  public int getMaxLength() {
    return maxLength;
  }


  /**
   * Set maximum password length.
   * 
   * @param maximum password length
   */
  protected void setMaxLength(int maxLength) {
    this.maxLength = maxLength;
  }


  /**
   * Get minimum password length.
   * 
   * @return the minLength
   */
  public int getMinLength() {
    return minLength;
  }


  /**
   * Set minimum password length.
   * 
   * @param minimum password length
   */
  protected void setMinLength(int minLength) {
    this.minLength = minLength;
  }

}
