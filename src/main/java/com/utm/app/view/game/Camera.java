package com.utm.app.view.game;

import com.utm.app.Point;
import com.utm.app.game.element.GameObject;
import com.utm.core.InjectProperty;
import com.utm.core.Singleton;

@Singleton
public class Camera {
  
  @InjectProperty("application.window.width")
  private int windowWidth;
  
  @InjectProperty("application.window.height")
  private int windowHeight;

  private Point p = new Point(0, 0);

  public void setPoint(Point newPoint){
    final int BLOCK_SIZE = GameObject.gameObjectSize;
    final int LIMIT = BLOCK_SIZE*2;

    final int newX = -newPoint.getX()*BLOCK_SIZE + windowWidth / 2 - BLOCK_SIZE / 2;
    final int newY = -newPoint.getY()*BLOCK_SIZE + windowHeight / 2 - BLOCK_SIZE / 2;

    final int oldX = this.p.getX();
    final int oldY = this.p.getY();

    boolean xExtern = Math.max(Math.abs(newX), Math.abs(oldX)) - Math.min(Math.abs(newX), Math.abs(oldX)) > LIMIT;

    boolean yExtern = Math.max(Math.abs(newY), Math.abs(oldY)) - Math.min(Math.abs(newY), Math.abs(oldY)) > LIMIT;

    if(xExtern){
      this.p = new Point(newX, oldY);
    }

    if(yExtern){
      this.p = new Point(oldX, newY);
    }

  }

  public int getX(){
    return this.p.getX();
  }

  public int getY(){
    return this.p.getY();
  }

  public Point getPoint() {
    return p;
  }


}