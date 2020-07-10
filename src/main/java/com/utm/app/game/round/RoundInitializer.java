package com.utm.app.game.round;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.utm.app.Point;
import com.utm.app.game.element.ElementNotation;
import com.utm.app.game.element.EmptyPlace;
import com.utm.app.game.element.GameObject;
import com.utm.app.game.element.GameObjectFactory;
import com.utm.app.game.round.dto.RoundInitializerDTO;
import com.utm.app.game.round.dto.RoundLoaderDTO;
import com.utm.app.view.game.MainGame;
import com.utm.core.InjectByType; 

public class RoundInitializer {

  @InjectByType
  private RoundSize roundSize;

  @InjectByType
  private GameObjectFactory gameObjectFactory;

  @InjectByType
  private MainGame mainGame;

  @InjectByType
  private RoundInitializerValidator validator;

  @InjectByType 
  private RoundLoader roundLoader;


  public RoundInitializerDTO initGameObjects(int currentRound){
    RoundLoaderDTO round = roundLoader.loadRoundObjectNotations(currentRound);
    List<String[]> roundObjectNotationsFromFile = round.getRoundObjectNotations();
    validator.validate(roundObjectNotationsFromFile, currentRound);
    roundObjectNotationsFromFile = addRoundBorder(roundObjectNotationsFromFile);
    calcRoundSize(roundObjectNotationsFromFile);
    Map<Point, List<GameObject>> roundObjects = generateGameObjects(roundObjectNotationsFromFile);

    return new RoundInitializerDTO(roundObjects, round.getRoundTime());
  }


  private List<String[]> addRoundBorder(final List<String[]> roundNotations){

    final int maxX = roundNotations
      .stream()
      .mapToInt(a -> a.length)
      .max()
      .getAsInt() + 2;

    List<String[]> result = new ArrayList<>();

    for (String[] roundLine : roundNotations) {
      String[] newRoundLine = new String[maxX];
      for (int i = 1; i < newRoundLine.length-1; i++) {
        if(i-1<roundLine.length){
          newRoundLine[i] = roundLine[i-1];
        }else{
          newRoundLine[i] = ElementNotation.ROCK.getNotation();
        }
      }
      newRoundLine[0] = ElementNotation.ROCK.getNotation();
      newRoundLine[newRoundLine.length-1] = ElementNotation.ROCK.getNotation();
      result.add(newRoundLine);
    }

    String[] arr1 = new String[result.get(0).length];
    String[] arr2 = new String[result.get(result.size()-1).length];

    for (int i = 0; i < arr1.length; i++) {
      arr1[i] = ElementNotation.ROCK.getNotation();
    }

    for (int i = 0; i < arr2.length; i++) {
      arr2[i] = ElementNotation.ROCK.getNotation();
    }

    result.add(0, arr1);
    result.add(arr2);

    return result;
  }


  private void calcRoundSize(List<String[]> roundFromFile){
    int width = roundFromFile
      .stream()
      .mapToInt(a -> a.length)
      .max()
      .getAsInt();

    int height = roundFromFile.size();

    width = (width + 1) * GameObject.gameObjectSize;
    height = (height + 1) * GameObject.gameObjectSize;

    this.roundSize.setSize(width, height);

  }


  private Map<Point, List<GameObject>> generateGameObjects(List<String[]> notations){

    Map<Point, List<GameObject>> result = new HashMap<>();

    int y = 0;
    int x = 0;

    for (String[] notation : notations) {
      y++;
      x = 0;

      for (String c : notation) {
        x++;

        Point p = new Point(x,y);
        List<GameObject> objectsOnPoint = new ArrayList<>(); 

        GameObject gameObject = gameObjectFactory.createGameObject(c, p);

        /**
         * If is game object, create empty place under him.
         */
        if(gameObject instanceof EmptyPlace == false){
          objectsOnPoint.add(gameObjectFactory
            .createGameObject(ElementNotation.EMPTY.getNotation(), p)
          );
        }

        objectsOnPoint.add(gameObject);

        result.put(p, objectsOnPoint);
        
      }
      
    }

    return result;
  }

  
}