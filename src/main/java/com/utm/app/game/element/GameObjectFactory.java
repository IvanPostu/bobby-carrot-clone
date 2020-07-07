package com.utm.app.game.element;

import java.util.Random;

import com.utm.app.resource.BufferedImageContext;
import com.utm.app.resource.GameBlockEnum;
import com.utm.core.InjectByType;
import com.utm.core.Singleton;

@Singleton
public class GameObjectFactory {

  @InjectByType
  BufferedImageContext imageContext;

  public static enum ReturnType {
    RABBIT,
    ROCK,
    EMPTY_RANDOM_PLACE
  } 
  
  public GameObject createGameObject(ReturnType type, int x, int y){
    switch(type){
      case RABBIT:
        return new Rabbit(x, y, imageContext.getBufferedImage(GameBlockEnum.RABBIT));
      case ROCK:
        return new Rock(x, y, imageContext.getBufferedImage(GameBlockEnum.ROCK));
      case EMPTY_RANDOM_PLACE:
        Random random = new Random();
        int randVal = random.nextInt(3);
        if(randVal==0){
          return new EmptyPlace(x, y, imageContext.getBufferedImage(GameBlockEnum.GRASS_A));
        }
        if(randVal==1){
          return new EmptyPlace(x, y, imageContext.getBufferedImage(GameBlockEnum.GRASS_B));
        }
        if(randVal==2){
          return new EmptyPlace(x, y, imageContext.getBufferedImage(GameBlockEnum.GRASS_C));
        }
      default:
        throw new RuntimeException();
    }
  }
}