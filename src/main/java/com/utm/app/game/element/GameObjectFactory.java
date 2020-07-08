package com.utm.app.game.element;

import java.util.Random;

import com.utm.app.Point;
import com.utm.app.resource.BufferedImageContext;
import com.utm.app.resource.GameBlockEnum;
import com.utm.core.InjectByType;
import com.utm.core.Singleton;

@Singleton
public class GameObjectFactory {

  @InjectByType
  BufferedImageContext imageContext;

  public static enum GameObjectType {
    RABBIT('Z'),
    ROCK('R', ' '),
    CARROT('C'),
    EMPTY_RANDOM_PLACE('o');

    char[] idChars;

    private GameObjectType(char ...idChars){
      this.idChars = idChars;
    }

    public char[] getIdChars() {
      return idChars;
    }
  } 
  
  public GameObject createGameObject(char charId, Point p){
    
    for (GameObjectType type : GameObjectType.values()) {
      for (char c : type.getIdChars()) {
        if(charId == c){
          return createGameObjectByType(type, p);
        }
      }
    }

    throw new RuntimeException(String.format("Invalid char id for game object: (%c)", charId));

  }

  private GameObject createGameObjectByType(GameObjectType type, Point p){
    switch(type){
      case RABBIT:
        return new Rabbit(p, imageContext.getBufferedImage(GameBlockEnum.RABBIT));
      case ROCK:
        return new Rock(p, imageContext.getBufferedImage(GameBlockEnum.ROCK));
      case CARROT:
        return new Carrot(p, imageContext.getBufferedImage(GameBlockEnum.CARROT));
      case EMPTY_RANDOM_PLACE:
        return generateEmptyPlaceWithRandomTexture(p);
        
      default:
      throw new RuntimeException("Invalid game object type.");
    }
  }

  private GameObject generateEmptyPlaceWithRandomTexture(Point p){
    Random random = new Random();
    int randVal = random.nextInt(3);
    if(randVal==0){
      return new EmptyPlace(p, imageContext.getBufferedImage(GameBlockEnum.GRASS_A));
    }
    if(randVal==1){
      return new EmptyPlace(p, imageContext.getBufferedImage(GameBlockEnum.GRASS_B));
    }

    return new EmptyPlace(p, imageContext.getBufferedImage(GameBlockEnum.GRASS_C));
  }
}