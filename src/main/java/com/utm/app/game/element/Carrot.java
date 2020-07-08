package com.utm.app.game.element;

import java.awt.Graphics2D;
import java.awt.image.*;

import com.utm.app.Point;

public class Carrot extends GameObject {

  Carrot(Point p, BufferedImage texture) {
    super(p, texture);
    this.walkable = true;
  }

  @Override
  public void render(Graphics2D g) {
    g.drawImage(this.texture, getXCalculatedPos(), getYCalculatedPos(), null);
  }
  
}