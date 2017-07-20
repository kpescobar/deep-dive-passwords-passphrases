/**
 * 
 */
package edu.cnm.deepdive.passwords.passphrases;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Random;
import java.util.ResourceBundle;

/**
 * @author Kelly Escobar
 *
 */
public class PassphraseGenerator {
  
  /** Word list filed used as the default if an alternative is not specified. */
  public static final String DEFAULT_WORD_LIST = "resources/wordlist";
  
  /** Default delimiter if none is specified. */
  public static final String DEFAULT_DELIMITER = " ";
  
  /** Default length of passphrase if none is specified.*/
  public static final int DEFAULT_LENGTH = 6;
  
  private String wordList = DEFAULT_WORD_LIST;
  private String delimiter = DEFAULT_DELIMITER;
  private int length = DEFAULT_LENGTH;
  private Random rng = null;
  private ArrayList<String> pool = null;
  
  /**
   * Creates an instance of the Passphrase Generator.
   */
  public PassphraseGenerator() {
    super(); 
  }
  
  /**
   * Sets up Array List to hold keys of specified word list.
   */
  protected void setupPool() {
    ResourceBundle bundle = ResourceBundle.getBundle(wordList);
    pool = new ArrayList<>();
    Enumeration<String> keyEnum = bundle.getKeys();
    while (keyEnum.hasMoreElements()) {
      String key = keyEnum.nextElement();
      String word = bundle.getString(key);
      pool.add(word);
    }
  }
  
  /**
   * Set up a Secure rng.
   */
  protected void setupRng() {
    rng = new SecureRandom();
  }
  
  /**
   * Returns generated passphrase with set delimiters.
   * @return generated passsphrase.
   */
  public String generate() {
    if (pool == null) {
      setupPool();
    }
    if (rng == null) {
      setupRng();
    }
    StringBuilder builder = new StringBuilder();
    String word = pool.get(rng.nextInt(pool.size()));
    builder.append(word);
    for (int i = 0; i < length - 1; i++) {
      word = pool.get(rng.nextInt(pool.size()));
      builder.append(delimiter);
      builder.append(word);
    }
    return builder.toString().trim();
  }

  /**
   * Return default word list if an alternative is not specified.
   * @return the wordList
   */
  public String getWordList() {
    return wordList;
  }
  
  /**
   * Set given/default word list.
   * @param wordList the wordList to set
   */
  public void setWordList(String wordList) {
    this.wordList = wordList;
  }
  
  /**
   * Get the delimiter.
   * @return the delimiter
   */
  public String getDelimiter() {
    return delimiter;
  }
  
  /**
   * Sets the delimiter.
   * @param delimiter set delimiter. 
   */
  public void setDelimiter(String delimiter) {
    this.delimiter = delimiter;
  }
  
  /**
   * Gets length.
   * @return the length
   */
  public int getLength() {
    return length;
  }
  
  /**
   * Sets passphrase length.
   * @param length sets length.
   */
  public void setLength(int length) {
    this.length = length;
  }

  /**
   * Gets random number generator.
   * @return the rng
   */
  protected Random getRng() {
    return rng;
  }

  /**
   * Sets random number generator.
   * @param rng sets rng.
   */
  protected void setRng(Random rng) {
    this.rng = rng;
  }
  
  
}
