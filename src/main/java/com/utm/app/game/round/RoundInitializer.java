package com.utm.app.game.round;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
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
import com.utm.core.InjectProperty;

public class RoundInitializer {

  @InjectProperty("application.game.totalrounds")
  private int totalRounds;

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

    int y = 0;
    int x = 0;

    for (char[] cs : roundFromFile) {
      y++;
      x = 0;

      for (char c : cs) {
        x++;

        Point p = new Point(x,y);
        List<GameObject> objectsOnPoint = new ArrayList<>(); 

        /**
         * Создает землю для каждой точки
         */
        // objectsOnPoint.add(gameObjectFactory
        //   .createGameObject(GameObjectType.EMPTY_RANDOM_PLACE.getIdChars()[0], p)
        // );

        GameObject gameObject = gameObjectFactory.createGameObject(c, p);

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

}