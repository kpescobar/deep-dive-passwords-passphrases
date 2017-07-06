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
   * @return
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
   * @return the wordList
   */
  public String getWordList() {
    return wordList;
  }
  
  /**
   * @param wordList the wordList to set
   */
  public void setWordList(String wordList) {
    this.wordList = wordList;
  }
  
  /**
   * @return the delimiter
   */
  public String getDelimiter() {
    return delimiter;
  }
  
  /**
   * @param delimiter the delimiter to set
   */
  public void setDelimiter(String delimiter) {
    this.delimiter = delimiter;
  }
  
  /**
   * @return the length
   */
  public int getLength() {
    return length;
  }
  
  /**
   * @param length the length to set
   */
  public void setLength(int length) {
    this.length = length;
  }

  /**
   * @return the rng
   */
  protected Random getRng() {
    return rng;
  }

  /**
   * @param rng the rng to set
   */
  protected void setRng(Random rng) {
    this.rng = rng;
  }
  
  
}
