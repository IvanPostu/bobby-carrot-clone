package com.utm.app.game;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;
import java.awt.*;

import com.utm.app.game.element.GameObject;
import com.utm.app.game.element.GameObjectFactory;
import static com.utm.app.game.element.GameObjectFactory.ReturnType;
import com.utm.app.resource.GameBlockEnum;
import com.utm.core.InjectByType;
import com.utm.core.PostConstruct;
import com.utm.core.Singleton;

@Singleton
public class GameRoundState {

  @InjectByType
  private GameObjectFactory gameObjectFactory;
   
  private List<List<GameObject>> gameObjects;
  private List<GameObject> place;
  private float scale = 1.0f;

  @PostConstruct
  public void init(){

    try(InputStream in = this.getClass().getResourceAsStream("/rounds/round1.txt")) {
      BufferedReader reader = new BufferedReader(new InputStreamReader(in));
      Stream<String> lines = reader.lines();
      Iterator<String> iter = lines.iterator();

      List<char[]> round = new ArrayList<>();

      while(iter.hasNext()){
        String s = iter.next();
        round.add(s.toCharArray());
      }

      initGameObjects(round);
    } catch (Exception e1) {
      e1.printStackTrace();
    }
  }
  
  private void initGameObjects(List<char[]> roundFromFile){
    this.gameObjects = new ArrayList<>(roundFromFile.size());
    this.place = new LinkedList<>();

    int y = 0;
    int x = 0;

    for (char[] cs : roundFromFile) {
      List<GameObject> list = new ArrayList<>(cs.length);
      y++;
      x = 0;

      for (char c : cs) {
        x++;
        if(c == GameBlockEnum.RABBIT.getCharId()){
          list.add(gameObjectFactory.createGameObject(ReturnType.RABBIT, x, y));
        }

        if(c == GameBlockEnum.ROCK.getCharId() || c == GameBlockEnum.ROCK_A.getCharId()){
          list.add(gameObjectFactory.createGameObject(ReturnType.ROCK, x, y));
        }

        this.place.add(
          gameObjectFactory.createGameObject(ReturnType.EMPTY_RANDOM_PLACE, x, y));
      }
      

      this.gameObjects.add(list);
    }
  }

  public void render(Graphics2D g) {
    for (GameObject place : this.place) {
      place.render(g);
    }

    for (List<GameObject> list : gameObjects) {
      for (GameObject gameObject : list) {
        gameObject.render(g);
      }
    }
  }

  public float getScale() {
    return scale;
  }

  public void setScale(float scale) {
    this.scale = scale;
  }

  // private void createRoundBorder(){

  // }

}