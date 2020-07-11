package com.utm.app.game.element;

import java.util.Random;

import com.utm.app.Point;
import com.utm.app.resource.BufferedImageContext;
import com.utm.core.InjectByType;
import com.utm.core.Singleton;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Singleton
public class GameObjectFactory {

  static Logger logger = LogManager.getLogger(GameObjectFactory.class);
  
  @InjectByType
  BufferedImageContext imageContext;
  
  
  public GameObject createGameObject(String notation, Point p){
    
    try {
      for (ElementNotation type : ElementNotation.values()) {
        if(notation.equals(type.getNotation())){
          return createGameObjectByType(type, p);
        }
      }
      throw new Exception();

    } catch (Exception e) {
      final String errorMsg = String.format("Invalid notation (%s)", notation);
      logger.error(errorMsg);
      throw new RuntimeException(errorMsg);
    }
  }


  private GameObject createGameObjectByType(ElementNotation type, Point p) throws Exception {

    switch(type){
      case RABBIT:
        return new Rabbit(p, imageContext.getBufferedImage(Rabbit.TEXTURE_PATH));
      case BAD_ENEMY:
        return new BadEnemy(p, imageContext.getBufferedImage(BadEnemy.TEXTURE_PATH));
      case VERY_BAD_ENEMY:
        return new VeryBadEnemy(p, imageContext.getBufferedImage(VeryBadEnemy.TEXTURE_PATH));
      case ROCK:
        return new Rock(p, imageContext.getBufferedImage(Rock.TEXTURE_PATH));
      case CARROT:
        return new Carrot(p, imageContext.getBufferedImage(Carrot.TEXTURE_PATH));
      case APPLE:
        return new Apple(p, imageContext.getBufferedImage(Apple.TEXTURE_PATH));
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
        logger.error("Invalid ElementNotation \"{}\", "+
        "please modify switch statement on this method for this ElementNotation.", type.name());
        throw new Exception();
    }
  }

  private GameObject generateEmptyPlaceWithRandomTexture(Point p){
    Random random = new Random();
    int randVal = random.nextInt(3);
    return new EmptyPlace(p, imageContext.getBufferedImage(EmptyPlace.TEXTURES_PATH[randVal]));
  }
}