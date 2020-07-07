package com.utm.app.game.element;

import java.awt.image.*;
import java.awt.*;

public abstract class GameObject {
  protected int x, y;
  protected int width, height;
  protected boolean walkable;
  protected BufferedImage texture;

  public static int gameObjectSize = 32;

  public GameObject(int x, int y, BufferedImage texture) {
    this.x = x;
    this.y = y;
    this.texture = texture;
  }

  public abstract void render(Graphics2D g);

  public boolean isWalkable(){
    return this.walkable;
  }

  protected int getXCalculatedPos(){
    return x * gameObjectSize;
  }

  protected int getYCalculatedPos(){
    return y * gameObjectSize;
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

}