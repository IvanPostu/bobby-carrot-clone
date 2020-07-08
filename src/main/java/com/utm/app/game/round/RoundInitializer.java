package com.utm.app.game.round;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import com.utm.app.Point;
import com.utm.app.game.element.EmptyPlace;
import com.utm.app.game.element.GameObject;
import com.utm.app.game.element.GameObjectFactory;
import com.utm.app.game.element.GameObjectFactory.GameObjectType;
import com.utm.core.InjectByType; 

public class RoundInitializer {



  @InjectByType
  private GameObjectFactory gameObjectFactory;

  private List<char[]> readRoundDataFromFile(final int currentRound){
    List<char[]> round = new ArrayList<>();
    String roundfile = String.format("/rounds/round%d.txt", currentRound);


    try(InputStream in = this.getClass().getResourceAsStream(roundfile)) {
      BufferedReader reader = new BufferedReader(new InputStreamReader(in));
      Stream<String> lines = reader.lines();
      Iterator<String> iter = lines.iterator();

      

      while(iter.hasNext()){
        String s = iter.next();
        round.add(s.toCharArray());
      }

    } catch (Exception e1) {
      e1.printStackTrace();
    }

    return round;
  }

  Map<Point, List<GameObject>> initGameObjects(int currentRound){
    Map<Point, List<GameObject>> result = new HashMap<>();
    List<char[]> roundFromFile = readRoundDataFromFile(currentRound);
    roundFromFile = addLimitRocks(roundFromFile);

    int y = 0;
    int x = 0;

    for (char[] cs : roundFromFile) {
      y++;
      x = 0;

      for (char c : cs) {
        x++;

        Point p = new Point(x,y);
        List<GameObject> objectsOnPoint = new ArrayList<>(); 

        GameObject gameObject = gameObjectFactory.createGameObject(c, p);

        /**
         * Если объект игры то создает пустое место под ним.
         */
        if(gameObject instanceof EmptyPlace == false){
          objectsOnPoint.add(gameObjectFactory
            .createGameObject(GameObjectType.EMPTY_RANDOM_PLACE.getIdChars()[0], p)
          );
        }

        objectsOnPoint.add(gameObject);

        result.put(p, objectsOnPoint);
        
      }
      
    }

    return result;
  }


  private List<char[]> addLimitRocks(List<char[]> rawRoundFromFile){

    final int maxX = rawRoundFromFile
      .stream()
      .mapToInt(a -> a.length)
      .max()
      .getAsInt() + 2;

    List<char[]> result = new ArrayList<>();

    for (char[] cs : rawRoundFromFile) {
      char[] newarr = new char[maxX];
      for (int i = 1; i < newarr.length-1; i++) {
        if(i-1<cs.length){
          newarr[i] = cs[i-1];
        }else{
          newarr[i] = GameObjectType.ROCK.getIdChars()[0];
        }
      }
      newarr[0] = GameObjectType.ROCK.getIdChars()[0];
      newarr[newarr.length-1] = GameObjectType.ROCK.getIdChars()[0];
      result.add(newarr);
    }

    char[] arr1 = new char[result.get(0).length];
    char[] arr2 = new char[result.get(result.size()-1).length];

    for (int i = 0; i < arr1.length; i++) {
      arr1[i] = GameObjectType.ROCK.getIdChars()[0];
    }

    for (int i = 0; i < arr2.length; i++) {
      arr2[i] = GameObjectType.ROCK.getIdChars()[0];
    }

    result.add(0 , arr1);
    result.add(arr2);


    return result;


  }

}