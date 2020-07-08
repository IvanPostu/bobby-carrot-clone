package com.utm.app.game.round;

import com.utm.core.Singleton;

@Singleton
public class RoundSize {
  private int width = 0;
  private int height = 0;

  public int getWidth() {
    return width;
  }

  public int getHeight() {
    return height;
  }

  public void setSize(int width, int height){
    this.width = width;
    this.height = height;
  }
}