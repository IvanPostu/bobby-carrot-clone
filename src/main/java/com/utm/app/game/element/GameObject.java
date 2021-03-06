package com.utm.app.game.element;


import java.awt.*;

import com.utm.app.Point;

public abstract class GameObject {
  protected Point p;
  protected int width, height;
  protected boolean walkable;
  protected boolean eatable;

  public static int gameObjectSize = 32;

  public GameObject(Point p) {
    this.p = p;
  }

  public abstract void render(Graphics2D g);

  public boolean isWalkable(){
    return this.walkable;
  }

  public boolean isEatable() {
    return eatable;
  }

  public boolean isAggressive(){
    return false;
  }

  protected int getXCalculatedPos(){
    return p.getX() * gameObjectSize;
  }

  protected int getYCalculatedPos(){
    return p.getY() * gameObjectSize;
  }

  public Point getPoint() {
    return p;
  }

  public void setPoint(Point p) {
    this.p = p;
  }

}