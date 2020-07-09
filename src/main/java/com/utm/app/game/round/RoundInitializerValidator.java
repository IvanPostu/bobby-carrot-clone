package com.utm.app.game.round;

import java.util.List;

import com.utm.app.game.element.ElementNotation;

public class RoundInitializerValidator {
  public void validate(List<String[]> rawRoundObjectsFromFile, final int round){
    ElementNotation[] gameObjectTypes = ElementNotation.values();

    for (String[] strings : rawRoundObjectsFromFile) {
      for (String s : strings) {
        boolean isValid = false;
        for (int i = 0; i < gameObjectTypes.length; i++) {

          if(s.equals(gameObjectTypes[i].getNotation())){
            isValid = true;
            break;
          }
          
        }

        if(!isValid){
          throw new RuntimeException(String
            .format("Notation:'(%s)' is unknown, please rewrite roundfile nr. %d", s, round));
        }
        
      }
    }
    
  }
}