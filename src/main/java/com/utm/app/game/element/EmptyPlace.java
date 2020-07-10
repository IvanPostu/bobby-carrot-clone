package com.utm.app.game.element;

import java.awt.Graphics2D;
import java.awt.image.*;

import com.utm.app.Point;

public class EmptyPlace extends GameObject {  
  
  public static final String[] TEXTURES_PATH = {
    "/images/grass48_A.png",
    "/images/grass48_B.png",
    "/images/grass48_C.png"
  };
  
  private BufferedImage texture;

  EmptyPlace(Point p, BufferedImage texture) {
    super(p);
    this.texture = texture;
    this.walkable = true;
    this.eatable = false;
  }

  @Override
  public void render(Graphics2D g) {
    g.drawImage(this.texture, getXCalculatedPos(), getYCalculatedPos(), null);
  }

}