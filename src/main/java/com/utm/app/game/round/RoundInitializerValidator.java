package com.utm.app.game.round;

import java.util.List;

import com.utm.app.game.element.ElementNotation;

public class RoundInitializerValidator {
  public static final int RABBITS_COUNT = 1;

  private int rabbitsCount;

  private void resetToZeroClassFields(){
    this.rabbitsCount = 0;
  }

  public void validate(List<String[]> rawRoundObjectsFromFile, final int round){
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
          }
          
        }

        if(!isValid){
          throw new RuntimeException(String
            .format("Notation:'(%s)' is unknown, please rewrite roundfile nr. %d", s, round)
          );
        }
      }
    }
    if(!rabbitsCountIsValid()){
      throw new RuntimeException(String
        .format("Rabbits count is equal to %d, but need to be equal to %d.", 
          this.rabbitsCount, RABBITS_COUNT));
    }
  }

  private boolean rabbitsCountIsValid(){
    return this.rabbitsCount == RABBITS_COUNT;
  }
}