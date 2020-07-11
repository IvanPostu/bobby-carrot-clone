package com.utm.app.game.element;
import java.awt.Graphics2D;
import java.awt.image.*;

import com.utm.app.Point;

public class VeryBadEnemy extends GameObject implements Enemy {
  public static final String TEXTURE_PATH = "/images/enemy48_B.png";
  private BufferedImage texture;

  public VeryBadEnemy(Point p, BufferedImage texture) {
    super(p);
    this.texture = texture;
    this.walkable = false;
    this.eatable = false;
  }

  @Override
  public void render(Graphics2D g) {
    g.drawImage(this.texture, getXCalculatedPos(), getYCalculatedPos(), null);
  }

  @Override
  public boolean isAggressive() {
    return true;
  }

}