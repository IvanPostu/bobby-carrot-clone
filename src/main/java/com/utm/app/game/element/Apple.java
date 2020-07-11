package com.utm.app.game.element;


import java.awt.Graphics2D;
import java.awt.image.*;

import com.utm.app.Point;

public class Apple extends GameObject {
  public static final String TEXTURE_PATH = "/images/apple48.png";
  private BufferedImage texture;

  Apple(Point p, BufferedImage texture) {
    super(p);
    this.texture = texture;
    this.walkable = true;
    this.eatable = true;
  }

  @Override
  public void render(Graphics2D g) {
    g.drawImage(this.texture, getXCalculatedPos(), getYCalculatedPos(), null);
  }
}