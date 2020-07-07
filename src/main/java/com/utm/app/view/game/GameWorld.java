package com.utm.app.view.game;


import com.utm.app.state.GameRoundState;
import com.utm.core.InjectByType;
import com.utm.core.InjectProperty;

import java.awt.*;
import java.util.List;

public class GameWorld {

  private static final int BLOCK_SIZE = 32;

  @InjectProperty("application.window.width")
  private int width;
  
  @InjectProperty("application.window.height")
  private int height;

  @InjectByType
  private GameRoundState gameRoundState;

  public int getRoundHeight(){
    return (gameRoundState.currentRoundState().size() * BLOCK_SIZE) + 2;
  }

  public int getRoundWidth(){
    return (gameRoundState.currentRoundState().get(0).length() * BLOCK_SIZE) + 2;
  }

  public void render(Graphics2D g) {
    g.clearRect(0, 0, getRoundWidth(), getRoundHeight());

    List<String> round = gameRoundState.currentRoundState();

    for (int i = 0; i < round.size(); i++) {
      String line = round.get(i);
      for (int j = 0; j < line.length(); j++) {
        g.drawRect(BLOCK_SIZE*j, BLOCK_SIZE*i, BLOCK_SIZE, BLOCK_SIZE);
      }
    }
  }
  
}