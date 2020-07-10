package com.utm.app.game.element;

import java.awt.Graphics2D;
import java.awt.image.*;

import com.utm.app.Point;

public class Rock extends GameObject {
  
  public static final String TEXTURE_PATH = "/images/rock48.png";

  private BufferedImage texture;

  Rock(Point p, BufferedImage texture) {
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