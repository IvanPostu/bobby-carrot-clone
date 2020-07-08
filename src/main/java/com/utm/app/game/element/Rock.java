package com.utm.app.game.element;

import java.awt.Graphics2D;
import java.awt.image.*;

import com.utm.app.Point;

public class Rock extends GameObject {
  Rock(Point p, BufferedImage texture) {
    super(p, texture);
    this.walkable = false;
  }

  @Override
  public void render(Graphics2D g) {
    g.drawImage(this.texture, getXCalculatedPos(), getYCalculatedPos(), null);
  }


}