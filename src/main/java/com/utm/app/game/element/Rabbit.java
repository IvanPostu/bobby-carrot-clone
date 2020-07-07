package com.utm.app.game.element;

import java.awt.Graphics2D;
import java.awt.image.*;

public class Rabbit extends GameObject {

  Rabbit(int x, int y, BufferedImage texture) {
    super(x, y, texture);
  }

  @Override
  public void render(Graphics2D g) {
    g.drawImage(this.texture, x, y, null);
  }

}