package com.utm.app.game.round;

import com.utm.core.Singleton;

@Singleton
public class RoundSize {
  private int width = 0;
  private int height = 0;

  public void setWidth(int width) {
    this.width = width;
  }

  public void setHeight(int height) {
    this.height = height;
  }

  public int getWidth() {
    return width;
  }

  public int getHeight() {
    return height;
  }
}