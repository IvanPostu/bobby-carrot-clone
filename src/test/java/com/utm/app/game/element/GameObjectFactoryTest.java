package com.utm.app.game.element;

import com.utm.app.Point;

import org.junit.Assert;

public class GameObjectFactoryTest {
  
  private GameObjectFactory gameObjectFactory;

  public void testCreateGameObjectUsingGameObjectFactory(){
    this.gameObjectFactory = new GameObjectFactory();

    ElementNotation[] notations = ElementNotation.values();

    for (int i = 0; i < notations.length; i++) {
      GameObject gameObject = gameObjectFactory
        .createGameObject(ElementNotation.RABBIT.getNotation(), new Point(0, 0));
        Assert.assertNotNull(gameObject);
    }
    
  }


}