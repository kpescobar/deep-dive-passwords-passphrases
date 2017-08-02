/**
 * 
 */
package edu.cnm.deepdive.passwords.passphrases.ui;

import edu.cnm.deepdive.passwords.passphrases.core.PassphraseGenerator;
import edu.cnm.deepdive.passwords.passphrases.core.SecurePasswordGenerator;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;


/**
 * @author Kelly Escobar
 *
 */
public class Controller {
  
  private SecurePasswordGenerator passwordGenerator = new SecurePasswordGenerator();
  private PassphraseGenerator passphraseGenerator = new PassphraseGenerator();
  
  @FXML
  private CheckBox upperIncluded;
  @FXML
  private CheckBox lowerIncluded;
  @FXML
  private CheckBox digitsIncluded;
  @FXML
  private CheckBox punctuationIncluded;
  @FXML
  private CheckBox ambiguousExcluded;
  @FXML
  private Label passwordLengthLabel;
  @FXML
  private Slider passwordLength;
  @FXML
  private Button generatePassword;
  @FXML
  private TextField password;
  
  @FXML
  private Label delimiterOptionLabel;
  @FXML
  private ChoiceBox delimiter;
  @FXML
  private Slider passphraseLength;
  @FXML
  private Label passphraseLengthLabel;
  @FXML
  private TextField passphrase;
  
  
  @FXML
  private void initialize() {    
    upperIncluded.setSelected(passwordGenerator.isUpperCaseIncluded());
    lowerIncluded.setSelected(passwordGenerator.isLowerCaseIncluded());
    digitsIncluded.setSelected(passwordGenerator.isNumbersIncluded());
    punctuationIncluded.setSelected(passwordGenerator.isPunctuationIncluded());
    ambiguousExcluded.setSelected(passwordGenerator.isAmbiguousExcluded());
    passwordLengthLabel.setLabelFor(passwordLength);
    passwordLength.setValue(passwordGenerator.getMinLength());
    checkPasswordOptions();
    delimiterOptionLabel.setLabelFor(delimiter);
//    delimiter.setValue(delimiter);
    passphraseLengthLabel.setLabelFor(passphraseLength);
    passphraseLength.setValue(passphraseGenerator.getLength());
  }

  @FXML
  private void checkPasswordOptions() {
    generatePassword.setDisable(!upperIncluded.isSelected()
        && !lowerIncluded.isSelected()
        && !digitsIncluded.isSelected()
        && !punctuationIncluded.isSelected());
  }
  
  @FXML
  private void generatePassword() {
    int length = (int) Math.round(passwordLength.getValue());
    passwordGenerator.setUpperCaseIncluded(upperIncluded.isSelected());
    passwordGenerator.setLowerCaseIncluded(lowerIncluded.isSelected());
    passwordGenerator.setNumbersIncluded(digitsIncluded.isSelected());
    passwordGenerator.setPunctuationIncluded(punctuationIncluded.isSelected());
    passwordGenerator.setAmbiguousExcluded(ambiguousExcluded.isSelected());
    if (length > passwordGenerator.getMaxLength()) {
      passwordGenerator.setMaxLength((int) Math.round(passwordLength.getValue()));
      passwordGenerator.setMinLength((int) Math.round(passwordLength.getValue()));
    } else {
      passwordGenerator.setMinLength((int) Math.round(passwordLength.getValue()));
      passwordGenerator.setMaxLength((int) Math.round(passwordLength.getValue()));
    }
    password.setText(passwordGenerator.generate());
  }
    
  @FXML
  private void generatePassphrase() {
    int length = (int) Math.round(passphraseLength.getValue());
    String d = (String) delimiter.getValue();
    passphraseGenerator.setLength(length);
    passphraseGenerator.setDelimiter(d);
    passphrase.setText(passphraseGenerator.generate());
  }
}














