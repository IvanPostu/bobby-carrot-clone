package com.utm.app.game.round;

import java.util.Arrays;
import java.util.List;

import com.utm.app.game.element.ElementNotation;

import org.junit.Test;


public class RoundInitializerValidatorTest {
  
  private final RoundInitializerValidator validator = new RoundInitializerValidator();;
  private final String a = ElementNotation.EMPTY.getNotation();
  private final String r = ElementNotation.RABBIT.getNotation();
  private final String e = ElementNotation.APPLE.getNotation();
  private final String b = ElementNotation.BAD_ENEMY.getNotation();
  
  @Test
  public void validateWithSuccessTest() throws Exception {

    final String[] l1 = {a, a, r, e, e};
    final String[] l2 = {a, a, a, e, e};
    final String[] l3 = {a, a, a, a, a};

    final List<String[]> rawRoundObjectsFromFile = Arrays.asList(l1, l2, l3);

    validator.validate(rawRoundObjectsFromFile, 1);
  }

  @Test(expected = Exception.class)
  public void validateMultipleRabbitNotations() throws Exception {

    final String[] l1 = {a, r, r, e, e};
    final String[] l2 = {a, a, a, e, e};
    final String[] l3 = {a, a, a, a, a};

    final List<String[]> rawRoundObjectsFromFile = Arrays.asList(l1, l2, l3);
    validator.validate(rawRoundObjectsFromFile, 1);

  }

  @Test(expected = Exception.class)
  public void validateZeroRabbitNotations() throws Exception {

    final String[] l1 = {a, a, a, e, e};
    final String[] l2 = {a, a, a, e, e};
    final String[] l3 = {a, a, a, a, a};

    final List<String[]> rawRoundObjectsFromFile = Arrays.asList(l1, l2, l3);
    validator.validate(rawRoundObjectsFromFile, 1);

  }

  @Test(expected = Exception.class)
  public void validateWithInvalidNotationForRoundObject() throws Exception {

    final String[] l1 = {"ZO", "P_", r, e, e};
    final String[] l2 = {a, a, a, e, e};
    final String[] l3 = {"00", "g7", a, a, "LL"};

    final List<String[]> rawRoundObjectsFromFile = Arrays.asList(l1, l2, l3);
    validator.validate(rawRoundObjectsFromFile, 1);

  }

  @Test(expected = Exception.class)
  public void validateWithZeroEatableObjects() throws Exception {

    final String[] l1 = {a, a, r, a, a};
    final String[] l2 = {a, a, a, a, a};
    final String[] l3 = {a, a, a, a, a};

    final List<String[]> rawRoundObjectsFromFile = Arrays.asList(l1, l2, l3);
    validator.validate(rawRoundObjectsFromFile, 1);

  }

}