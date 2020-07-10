package com.utm.app.game.element;

import java.awt.image.*;
import java.awt.Graphics2D;

import com.utm.app.Point;

public class Rabbit extends GameObject {

  public static final String TEXTURE_PATH = "/images/bobby48.png";
  private BufferedImage texture;

  Rabbit(Point p, BufferedImage texture) {
    super(p);
    this.texture = texture;
    this.walkable = false;
    this.eatable = false;
  }

  @Override
  public void render(Graphics2D g) {
    g.drawImage(this.texture, getXCalculatedPos(), getYCalculatedPos(), null);
  }

}