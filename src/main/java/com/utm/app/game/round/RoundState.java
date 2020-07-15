package com.utm.app.game.round;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.utm.app.Point;
import com.utm.app.Procedure;
import com.utm.app.game.Camera;
import com.utm.app.game.MoveDirection;
import com.utm.app.game.element.Enemy;
import com.utm.app.game.element.GameObject;
import com.utm.app.game.element.GroundSpikesTrap;
import com.utm.app.game.element.Rabbit;
import com.utm.app.view.game.TopPanel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RoundState {

  static Logger logger = LogManager.getLogger(RoundState.class);

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
    Rabbit rabbit,
    TopPanel topPanel,
    Procedure roundCompleteCallback,
    Procedure roundLoseCallback) 
  {
    this.roundLoseCallback = roundLoseCallback;
    this.roundCompleteCallback = roundCompleteCallback;
    this.camera = camera;
    this.topPanel = topPanel;
    this.gameObjects = gameObjects;
    this.rabbit = rabbit;
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
  
  public void everySecondHandler(){
    moveEnemies();
  }


  public void render(Graphics2D g) {
    gameObjects.forEach((k, v) -> {
      for (GameObject gameObject : v) {
        gameObject.render(g);
      }
    });
  }

  private void moveEnemies(){
    List<Enemy> cache = new ArrayList<>();
    for(Map.Entry<Point, List<GameObject>> entry : this.gameObjects.entrySet()){
      List<GameObject> currentObjects = entry.getValue();

      currentObjects.stream()
        .filter(a -> a instanceof Enemy && !cache.contains((Enemy)a))
        .findFirst()
        .ifPresent(enemy -> {
          Enemy e = (Enemy)enemy;
          final int x = enemy.getPoint().getX();
          final int y = enemy.getPoint().getY();
          List<Point> potentiallyDirs = Arrays.asList(
            new Point(x+1, y), new Point(x-1, y), new Point(x, y+1), new Point(x, y-1)
          );

          List<Point> possiblyDirs = new ArrayList<>();

          for (Point posDir : potentiallyDirs) {
            List<GameObject> nextLocObjects = this.gameObjects.get(posDir);
            final boolean moveIsPossible = nextLocObjects != null
            && nextLocObjects
                .stream()
                .allMatch(a -> a.isWalkable() || a instanceof Rabbit);

            if(moveIsPossible){
              possiblyDirs.add(posDir);
            }
          }

          if(possiblyDirs.size()!=0){
            Point newDir = e.pointForMove(possiblyDirs);
            logger.debug("Enemy point {} {}", enemy.getPoint(), newDir);
            
            /**
             * Detele enemy from last position.
             * Create enemy on new position.
             */
            currentObjects.removeIf(a -> a instanceof Enemy);
            enemy.setPoint(newDir);
            addGameObjectToRound(newDir, enemy);
            cache.add(e);

            if(newDir.equals(rabbit.getPoint())){
              roundLoseCallback.resolve();
            }

          }
        });
    }

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

    logger.debug("Attempt to take a step from {} to {}", rabitLocation, nextLocation);

    List<GameObject> objectsUnderRabbit = this.gameObjects.get(rabitLocation);
    List<GameObject> nextLocObjects = this.gameObjects.get(nextLocation);

    final boolean moveIsPossible = nextLocObjects != null
      && nextLocObjects
          .stream()
          .allMatch(a -> a.isWalkable() || a instanceof Enemy);

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
       * If stepped on aggresive object.
       * Lose round.
       */
      Iterator<GameObject> nextLocObjectsIterator = nextLocObjects.iterator();
      while(nextLocObjectsIterator.hasNext()){
        if(nextLocObjectsIterator.next().isAggressive()){
          this.roundLoseCallback.resolve();
          return;
        }
      }

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
          logger.debug(a.toString() + " eaten!!!");
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