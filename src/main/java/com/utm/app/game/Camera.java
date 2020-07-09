package com.utm.app.game;

import com.utm.app.Point;
import com.utm.app.game.element.GameObject;
import com.utm.app.game.round.RoundSize;
import com.utm.core.InjectByType;
import com.utm.core.InjectProperty;
import com.utm.core.Singleton;

@Singleton
public class Camera {
  
  @InjectProperty("application.window.width")
  private int windowWidth;
  
  @InjectProperty("application.window.height")
  private int windowHeight;

  @InjectByType
  private RoundSize roundSize;

  private Point p = new Point(0, 0);

  public void setPointAprox(Point newPoint){
    final int BLOCK_SIZE = GameObject.gameObjectSize;
    final int LIMIT = BLOCK_SIZE*2;

    int newX = -newPoint.getX()*BLOCK_SIZE + windowWidth / 2 - BLOCK_SIZE / 2;
    int newY = -newPoint.getY()*BLOCK_SIZE + windowHeight / 2 - BLOCK_SIZE / 2;

    final int oldX = this.p.getX();
    final int oldY = this.p.getY();

    boolean xExtern = Math.max(Math.abs(newX), Math.abs(oldX)) - Math.min(Math.abs(newX), Math.abs(oldX)) > LIMIT;

    boolean yExtern = Math.max(Math.abs(newY), Math.abs(oldY)) - Math.min(Math.abs(newY), Math.abs(oldY)) > LIMIT;

    if(xExtern){
      newX = calculateXWithoutGoingBeyondTheRound(newX);
      this.p = new Point(newX, this.p.getY());
    }

    if(yExtern){
      newY = calculateYWithoutGoingBeyondTheRound(newY);
      this.p = new Point(this.p.getX(), newY);
    }

  }

  public void setPoint(Point newPoint){
    final int BLOCK_SIZE = GameObject.gameObjectSize;
    int newX = -newPoint.getX()*BLOCK_SIZE + windowWidth / 2 - BLOCK_SIZE / 2;
    int newY = -newPoint.getY()*BLOCK_SIZE + windowHeight / 2 - BLOCK_SIZE / 2;

    newX = newX>= -1*BLOCK_SIZE ? -1*BLOCK_SIZE : newX;
    newX = newX<= -(roundSize.getWidth()-windowWidth) 
      ? -(roundSize.getWidth()-windowWidth) : newX;


    // newY = newY<0 ? 0 : newY;
    // newY = newY>roundSize.getHeight()-windowHeight ? roundSize.getHeight()-windowWidth : newY;

    this.p = new Point(newX, newY);
  }

  private int calculateXWithoutGoingBeyondTheRound(final int x){
    final int BLOCK_SIZE = GameObject.gameObjectSize;
    int newX = x;

    newX = newX>= -1*BLOCK_SIZE ? -1*BLOCK_SIZE : newX;//left
    newX = newX<= -(roundSize.getWidth()-windowWidth) 
      ? -(roundSize.getWidth()-windowWidth) : newX; //right

    return newX;
  }

  private int calculateYWithoutGoingBeyondTheRound(final int y){
    final int BLOCK_SIZE = GameObject.gameObjectSize;
    int newY = y;

    newY = newY>= -1*BLOCK_SIZE ? -1*BLOCK_SIZE : newY; //top
    newY = newY<= -(roundSize.getHeight()-windowHeight+BLOCK_SIZE) 
      ? -(roundSize.getHeight()-windowHeight+BLOCK_SIZE) : newY;//bottom

    return newY;
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