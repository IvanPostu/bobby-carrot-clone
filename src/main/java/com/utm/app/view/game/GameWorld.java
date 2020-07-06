package com.utm.app.view.game;


import com.utm.app.state.GameRoundState;
import com.utm.core.InjectByType;
import com.utm.core.InjectProperty;

import java.awt.*;
import java.util.List;

public class GameWorld {

  @InjectProperty("application.window.width")
  private int width;
  
  @InjectProperty("application.window.height")
  private int height;

  @InjectByType
  private GameRoundState gameRoundState;

  public void render(Graphics2D g) {
    g.clearRect(0, 0, width, height);

    List<String> round = gameRoundState.currentRoundState();

    for (int i = 0; i < round.size(); i++) {
      String line = round.get(i);
      for (int j = 0; j < line.length(); j++) {
        g.drawRect(32*j, 32*i, 32, 32);
      }
    }
  }
  
}