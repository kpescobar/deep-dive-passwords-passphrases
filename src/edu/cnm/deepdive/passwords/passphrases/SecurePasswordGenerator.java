package edu.cnm.deepdive.passwords.passphrases;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class SecurePasswordGenerator extends PasswordGenerator {

 /**
 * Invokes superclass and uses cryptographically secure random number generator.
 */
  public SecurePasswordGenerator() {
    super();
    }
 
  /**
   * Uses an instance of Secure Random instead of Random.
   */
  @Override
  protected void setupRng() {
    try {
      setRng(SecureRandom.getInstanceStrong());
    } catch (NoSuchAlgorithmException ex) {
      throw new RuntimeException(ex);
      
    }
  }

}
