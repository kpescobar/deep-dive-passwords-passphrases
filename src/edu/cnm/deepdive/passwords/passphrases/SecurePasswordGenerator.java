package edu.cnm.deepdive.passwords.passphrases;

import java.security.SecureRandom;

public class SecurePasswordGenerator extends PasswordGenerator {
/**
 * Invokes superclass and uses cryptographically secure random number generator.
 */
  public SecurePasswordGenerator() {
    super();
    rng = new SecureRandom();
  }
/**
 * Invokes {@link PasswordGenerator#PasswordGenerator(int, int) superclass constructor}
 * 
 * @param minLength
 * @param maxLength
 */
  public SecurePasswordGenerator(int minLength, int maxLength) {
    super(minLength, maxLength);
    // TODO Auto-generated constructor stub
  }

  /**
   * Invokes superclass constructor.
   * 
   * @param minLength
   * @param maxLength
   * @param includeUpperCase
   * @param includeLowerCase
   * @param includeNumbers
   * @param includePunctuation
   * @param excludeAmbiguous
   */
  public SecurePasswordGenerator(int minLength, int maxLength, boolean includeUpperCase,
      boolean includeLowerCase, boolean includeNumbers, boolean includePunctuation,
      boolean excludeAmbiguous) {
    super(minLength, maxLength, includeUpperCase, includeLowerCase, includeNumbers,
        includePunctuation, excludeAmbiguous);
 
  }

}
