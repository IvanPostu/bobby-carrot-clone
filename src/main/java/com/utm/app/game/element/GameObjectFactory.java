package com.utm.app.game.element;

import java.util.Random;

import com.utm.app.Point;
import com.utm.app.resource.BufferedImageContext;
import com.utm.core.InjectByType;
import com.utm.core.Singleton;

@Singleton
public class GameObjectFactory {

  @InjectByType
  BufferedImageContext imageContext;
  
  
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
        return new Rabbit(p, imageContext.getBufferedImage(Rabbit.TEXTURE_PATH));
      case ROCK:
        return new Rock(p, imageContext.getBufferedImage(Rock.TEXTURE_PATH));
      case CARROT:
        return new Carrot(p, imageContext.getBufferedImage(Carrot.TEXTURE_PATH));
      case GRASS_A:
        return new EmptyPlace(p, imageContext.getBufferedImage(EmptyPlace.TEXTURES_PATH[0]));
      case GRASS_B:
        return new EmptyPlace(p, imageContext.getBufferedImage(EmptyPlace.TEXTURES_PATH[1]));
      case GRASS_C:
        return new EmptyPlace(p, imageContext.getBufferedImage(EmptyPlace.TEXTURES_PATH[2]));
      case EMPTY:
        return generateEmptyPlaceWithRandomTexture(p);
      case TRAP_A_OFF:
        return new GroundSpikesTrap(false, p, imageContext.getBufferedImage(GroundSpikesTrap.ENABLED_TRAP_TEXTURE), imageContext.getBufferedImage(GroundSpikesTrap.DISABLED_TRAP_TEXTURE));
      case TRAP_A_ON:
        return new GroundSpikesTrap(false, p, imageContext.getBufferedImage(GroundSpikesTrap.ENABLED_TRAP_TEXTURE), imageContext.getBufferedImage(GroundSpikesTrap.DISABLED_TRAP_TEXTURE));
        
      default:
        throw new RuntimeException("Invalid game object type.");
    }
  }

  private GameObject generateEmptyPlaceWithRandomTexture(Point p){
    Random random = new Random();
    int randVal = random.nextInt(3);
    return new EmptyPlace(p, imageContext.getBufferedImage(EmptyPlace.TEXTURES_PATH[randVal]));
  }
}