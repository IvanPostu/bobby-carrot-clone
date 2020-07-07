package com.utm.app.view.game;

import com.utm.app.game.GameRoundState;
import com.utm.app.resource.BufferedImageContext;
import com.utm.app.resource.GameBlockEnum;
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

  @InjectByType
  private BufferedImageContext imageContext;

  public int getRoundHeight(){
    return (gameRoundState.getRoundHeight() * BLOCK_SIZE) + 2;
  }

  public int getRoundWidth(){
    return (gameRoundState.getRoundWidth() * BLOCK_SIZE) + 2;
  }

  public void render(Graphics2D g) {
    g.clearRect(0, 0, getRoundWidth(), getRoundHeight());

    List<char[]> round = gameRoundState.currentRoundState();

    for (int i = 0; i < round.size(); i++) {
      char[] line = round.get(i);
      for (int j = 0; j < line.length; j++) {
        g.drawRect(BLOCK_SIZE*j, BLOCK_SIZE*i, BLOCK_SIZE, BLOCK_SIZE);
        this.drawBlockIfPresent(line[j], g, BLOCK_SIZE*j, BLOCK_SIZE*i);
      }
    }
  }

  private void drawBlockIfPresent(char c, Graphics2D g, int x, int y){
    x++;
    y++;

    switch(c){
      case 'Z':
        g.drawImage(imageContext.getBufferedImage(GameBlockEnum.RABBIT_IMAGE), x, y, null);
        break;
      case 'R':
        g.drawImage(imageContext.getBufferedImage(GameBlockEnum.ROCK_IMAGE), x, y, null);
        break;
      case 'C':
        g.drawImage(imageContext.getBufferedImage(GameBlockEnum.CARROT_IMAGE), x, y, null);
        break;
      default:
        return;
    }
  }
  
}