package com.utm.app.game.round;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.utm.app.Point;
import com.utm.app.game.MoveDirection;
import com.utm.app.game.element.GameObject;
import com.utm.app.game.element.GameObjectFactory;
import com.utm.app.game.element.Rabbit;
import com.utm.core.InjectByType;
import com.utm.core.PostConstruct;
import com.utm.core.Singleton;

@Singleton
public class GameRoundState {

  @InjectByType
  private GameObjectFactory gameObjectFactory;

  @InjectByType
  private RoundInitializer roundInitializer;

  private Map<Point, List<GameObject>> gameObjects;
  private float scale = 1.0f;

  private Rabbit rabbit;

  @PostConstruct
  public void init(){
    this.gameObjects = roundInitializer.initGameObjects(1);
    this.findRabbit();
  }

  private void addGameObjectToRound(Point p, GameObject o){
    List<GameObject> objectsOnPoint = this.gameObjects.get(p);

    if(objectsOnPoint == null){
      objectsOnPoint = new ArrayList<>();
      objectsOnPoint.add(o);
      this.gameObjects.put(p, objectsOnPoint);
    }else{
      objectsOnPoint.add(o);
    }
  }
  
  public void render(Graphics2D g) {
    gameObjects.forEach((k, v) -> {
      for (GameObject gameObject : v) {
        gameObject.render(g);
      }
    });
  }

  public float getScale() {
    return scale;
  }

  public void setScale(float scale) {
    this.scale = scale;
  }

  public void moveRabbit(MoveDirection dir){
    Point rabitLocation = rabbit.getPoint();
    Point nextLocation = null;

    if(dir==MoveDirection.RIGHT){
      nextLocation = new Point(rabitLocation.getX()+1, rabitLocation.getY());
    }
    if(dir==MoveDirection.LEFT){
      nextLocation = new Point(rabitLocation.getX()-1, rabitLocation.getY());
    }
    if(dir==MoveDirection.UP){
      nextLocation = new Point(rabitLocation.getX(), rabitLocation.getY()-1);
    }
    if(dir==MoveDirection.DOWN){
      nextLocation = new Point(rabitLocation.getX(), rabitLocation.getY()+1);
    }

    List<GameObject> nextLocObjects = this.gameObjects.get(nextLocation);

    final boolean moveIsPossible = nextLocObjects != null
      && nextLocObjects
          .stream()
          .allMatch(a -> a.isWalkable());

    if(moveIsPossible){

      /**
       * Detele rabit from last position.
       * Create rabbit on new position.
       */
      this.gameObjects.get(rabitLocation).removeIf(a -> a instanceof Rabbit);
      rabbit.setPoint(nextLocation);
      addGameObjectToRound(nextLocation, rabbit);

      /**
       * Eat all eatable game objects.
       */
      nextLocObjects.removeIf(a -> a.isEatable());

    }
  }

  private void findRabbit(){
    gameObjects.forEach((k, v) -> {
      for (GameObject gameObject : v) {
        if(gameObject instanceof Rabbit){
          if(this.rabbit != null){
            throw new RuntimeException("Max rabbits count: 1");
          }
          this.rabbit = (Rabbit)gameObject;
        }
      }
    });
  }

}