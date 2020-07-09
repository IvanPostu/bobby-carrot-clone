package com.utm.app.game.round;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.utm.app.Point;
import com.utm.app.game.Camera;
import com.utm.app.game.MoveDirection;
import com.utm.app.game.element.GameObject;
import com.utm.app.game.element.GameObjectFactory;
import com.utm.app.game.element.Rabbit;
import com.utm.app.view.game.TopPanel;
import com.utm.core.InjectByType;
import com.utm.core.PostConstruct;
import com.utm.core.Singleton;

@Singleton
public class RoundState {

  @InjectByType
  private GameObjectFactory gameObjectFactory;

  @InjectByType
  private RoundManager roundManager;

  @InjectByType
  private Camera camera;

  private Map<Point, List<GameObject>> gameObjects;
  private short eatableCount;
  private Rabbit rabbit;

  @InjectByType
  private TopPanel topPanel;

  @PostConstruct
  public void postConstruct(){
    initNewRound();
  }

  private void initNewRound(){
    this.gameObjects = roundManager.nextRound();
    this.rabbit = null;
    this.findRabbit();
    camera.setPoint(this.rabbit.getPoint());
    this.eatableCount = 0;
    this.calculateEatableCount();
    topPanel.setEatableOnRound(this.eatableCount);
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
       * Move camera
       */
      camera.setPointAprox(this.rabbit.getPoint());

      /**
       * Eat all eatable game objects on new position.
       */
      nextLocObjects.removeIf(a -> {
        boolean remov = a.isEatable();
        if (remov){
          this.eatableCount--;
        }
        return remov;
      });

      topPanel.setEatableOnRound(this.eatableCount);
      topPanel.setRabbitPos(rabbit.getPoint());

      if(this.eatableCount==0){
        this.roundComplete();
      }
    }
  }

  private void roundComplete(){
    roundManager.manageRoundCompleteEvent();
    boolean hasNextRound = roundManager.hasNextRound();
    if(hasNextRound){
      initNewRound();
    }
  }

  private void findRabbit(){
    gameObjects.forEach((k, v) -> {
      for (GameObject gameObject : v) {
        if(gameObject instanceof Rabbit){
          if(this.rabbit != null){
            throw new RuntimeException("Max rabbits count is 1, please update your roundfile.");
          }
          this.rabbit = (Rabbit)gameObject;
          topPanel.setRabbitPos(rabbit.getPoint());
        }
      }
    });
  }

  private void calculateEatableCount(){
    gameObjects.forEach((k, v) -> {
      for (GameObject gameObject : v) {
        if(gameObject.isEatable()){
          this.eatableCount++;
        }
      }
    });
  }

}