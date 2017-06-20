package edu.cnm.deepdive.passwords.passphrases;

import java.security.SecureRandom;

public class SecurePasswordGenerator extends PasswordGenerator {

  public SecurePasswordGenerator() {
    super();
    rng = new SecureRandom();
  }

  public SecurePasswordGenerator(int minLength, int maxLength) {
    super(minLength, maxLength);
    // TODO Auto-generated constructor stub
  }

  public SecurePasswordGenerator(int minLength, int maxLength, boolean includeUpperCase,
      boolean includeLowerCase, boolean includeNumbers, boolean includePunctuation,
      boolean excludeAmbiguous) {
    super(minLength, maxLength, includeUpperCase, includeLowerCase, includeNumbers,
        includePunctuation, excludeAmbiguous);
    // TODO Auto-generated constructor stub
  }

}
