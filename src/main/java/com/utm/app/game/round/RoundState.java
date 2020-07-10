package com.utm.app.game.round;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.utm.app.Point;
import com.utm.app.Procedure;
import com.utm.app.game.Camera;
import com.utm.app.game.MoveDirection;
import com.utm.app.game.element.GameObject;
import com.utm.app.game.element.GroundSpikesTrap;
import com.utm.app.game.element.Rabbit;
import com.utm.app.view.game.TopPanel;

public class RoundState {

  private short eatableCount;
  private Camera camera;
  private Map<Point, List<GameObject>> gameObjects;
  private Rabbit rabbit;
  private TopPanel topPanel;
  private Procedure roundCompleteCallback;
  private Procedure roundLoseCallback;


  public RoundState(
    Camera camera, 
    Map<Point, List<GameObject>> gameObjects, 
    TopPanel topPanel,
    Procedure roundCompleteCallback,
    Procedure roundLoseCallback) 
  {
    this.roundLoseCallback = roundLoseCallback;
    this.roundCompleteCallback = roundCompleteCallback;
    this.camera = camera;
    this.topPanel = topPanel;
    this.gameObjects = gameObjects;
    this.rabbit = this.findRabbit();
    camera.setPoint(this.rabbit.getPoint());
    this.eatableCount = this.calculateEatableCount();

    topPanel.setEatableOnRound(this.eatableCount);
    topPanel.setRabbitPos(rabbit.getPoint());
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

    List<GameObject> objectsUnderRabbit = this.gameObjects.get(rabitLocation);
    List<GameObject> nextLocObjects = this.gameObjects.get(nextLocation);

    final boolean moveIsPossible = nextLocObjects != null
      && nextLocObjects
          .stream()
          .allMatch(a -> a.isWalkable());

    if(moveIsPossible){

      /**
       * Activate GroundSpikesTrap if exist.
       */
      objectsUnderRabbit.forEach(a -> {
        if(a instanceof GroundSpikesTrap && !((GroundSpikesTrap)a).trapIsEnabled()){
          GroundSpikesTrap trap = (GroundSpikesTrap)a;
          trap.setTrapEnabled();
        }
      });

      /**
       * Detele rabit from last position.
       * Create rabbit on new position.
       */
      objectsUnderRabbit.removeIf(a -> a instanceof Rabbit);
      rabbit.setPoint(nextLocation);
      addGameObjectToRound(nextLocation, rabbit);


      /**
       * If stepped on active GroundSpikesTrap
       * Lose round.
       */
      nextLocObjects.forEach(a->{
        if(a instanceof GroundSpikesTrap && ((GroundSpikesTrap)a).trapIsEnabled()){
          this.roundLoseCallback.resolve();
        }
      });

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
        this.roundCompleteCallback.resolve();
      }
    }
  }


  private Rabbit findRabbit(){
    for(Map.Entry<Point, List<GameObject>> entry : this.gameObjects.entrySet()){
      List<GameObject> list = entry.getValue();
      for (GameObject gameObject : list) {
        if(gameObject instanceof Rabbit){
          Rabbit rab = (Rabbit)gameObject;
          topPanel.setRabbitPos(rab.getPoint());
          return rab;
        }
      }
    }

    throw new RuntimeException();
  }


  private short calculateEatableCount(){
    short eatableCount = 0;

    for(Map.Entry<Point, List<GameObject>> entry : this.gameObjects.entrySet()){
      List<GameObject> list = entry.getValue();
      for (GameObject gameObject : list) {
        if(gameObject.isEatable()){
          eatableCount++;
        }
      }
    }
   
    return eatableCount;
  }

}