package com.utm.app.game.element;

import java.util.Random;

import com.utm.app.Point;
import com.utm.app.resource.BufferedImageContext;
import com.utm.app.resource.GameBlockResourcesEnum;
import com.utm.core.InjectByType;
import com.utm.core.Singleton;

@Singleton
public class GameObjectFactory {

  @InjectByType
  BufferedImageContext imageContext;

  // public static enum GameObjectType {
  //   RABBIT('Z'),
  //   ROCK('R', ' '),
  //   CARROT('C'),
  //   EMPTY_RANDOM_PLACE('o');

  //   char[] idChars;

  //   private GameObjectType(char ...idChars){
  //     this.idChars = idChars;
  //   }

  //   public char[] getIdChars() {
  //     return idChars;
  //   }
  // } 
  
  public GameObject createGameObject(String notation, Point p){
    
    for (ElementNotation type : ElementNotation.values()) {
      if(notation.equals(type.getNotation())){
        return createGameObjectByType(type, p);
      }
    }

    throw new RuntimeException(String.format("Invalid notation for game object: (%s)", notation));

  }

  private GameObject createGameObjectByType(ElementNotation type, Point p){
    switch(type){
      case RABBIT:
        return new Rabbit(p, imageContext.getBufferedImage(GameBlockResourcesEnum.RABBIT));
      case ROCK:
        return new Rock(p, imageContext.getBufferedImage(GameBlockResourcesEnum.ROCK));
      case CARROT:
        return new Carrot(p, imageContext.getBufferedImage(GameBlockResourcesEnum.CARROT));
      case GRASS_A:
        return new EmptyPlace(p, imageContext.getBufferedImage(GameBlockResourcesEnum.GRASS_A));
      case GRASS_B:
        return new EmptyPlace(p, imageContext.getBufferedImage(GameBlockResourcesEnum.GRASS_B));
      case GRASS_C:
        return new EmptyPlace(p, imageContext.getBufferedImage(GameBlockResourcesEnum.GRASS_C));
      case EMPTY:
        return generateEmptyPlaceWithRandomTexture(p);
        
      default:
        throw new RuntimeException("Invalid game object type.");
    }
  }

  private GameObject generateEmptyPlaceWithRandomTexture(Point p){
    Random random = new Random();
    int randVal = random.nextInt(3);
    if(randVal==0){
      return new EmptyPlace(p, imageContext.getBufferedImage(GameBlockResourcesEnum.GRASS_A));
    }
    if(randVal==1){
      return new EmptyPlace(p, imageContext.getBufferedImage(GameBlockResourcesEnum.GRASS_B));
    }

    return new EmptyPlace(p, imageContext.getBufferedImage(GameBlockResourcesEnum.GRASS_C));
  }
}