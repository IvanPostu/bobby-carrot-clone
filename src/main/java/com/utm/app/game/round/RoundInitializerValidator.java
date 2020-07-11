package com.utm.app.game.round;

import java.util.List;

import com.utm.app.game.element.ElementNotation;


public class RoundInitializerValidator {

  public static final int RABBITS_COUNT = 1;

  private int rabbitsCount;
  private int eatablesCount;

  private void resetToZeroClassFields(){
    this.rabbitsCount = 0;
    this.eatablesCount = 0;
  }

  public void validate(final List<String[]> rawRoundObjectsFromFile, final int round) 
    throws Exception {
      
    this.resetToZeroClassFields();
    ElementNotation[] gameObjectTypes = ElementNotation.values();

    for (String[] strings : rawRoundObjectsFromFile) {
      for (String s : strings) {
        boolean isValid = false;
        for (int i = 0; i < gameObjectTypes.length; i++) {
          ElementNotation element = gameObjectTypes[i];

          if(s.equals(element.getNotation())){
            isValid = true;

            if(element.equals(ElementNotation.RABBIT)){
              this.rabbitsCount++;
            }

            if(element.equals(ElementNotation.APPLE) || element.equals(ElementNotation.CARROT)){
              this.eatablesCount++;
            }
          }
          
        }

        if(!isValid){
          final String errorMsg = String
            .format("Notation:'(%s)' is unknown, please rewrite roundfile nr. %d", s, round);
          
          throw new Exception(errorMsg);
        }
      }
    }

    if(!rabbitsCountIsValid()){
      final String errorMsg = String
        .format("Rabbits count is equal to %d, but need to be equal to %d.", 
          this.rabbitsCount, RABBITS_COUNT);

      throw new Exception(errorMsg);
    }

    if(!eatableCountIsValid()){
      final String errorMsg = String
        .format("Eatable count is equal to %d, this is not valid", this.eatablesCount);
      
        throw new Exception(errorMsg);
    }

  }

  private boolean rabbitsCountIsValid(){
    return this.rabbitsCount == RABBITS_COUNT;
  }

  private boolean eatableCountIsValid(){
    return this.eatablesCount >= 1;
  }
}