package com.utm.app.view.game;

import com.utm.app.game.GameRoundState;
import com.utm.core.InjectByType;
import com.utm.core.InjectProperty;

import java.awt.*;

public class GameWorld {

  @InjectProperty("application.window.width")
  private int width;
  
  @InjectProperty("application.window.height")
  private int height;

  @InjectByType
  private GameRoundState gameRoundState;

  public int getRoundHeight(){
    // TODO Fix me!!!!!
    return width * 3;//(gameRoundState.getRoundHeight() * BLOCK_SIZE) + 1;
  }

  public int getRoundWidth(){
    // TODO Fix me!!!!!
    return height * 3;//(gameRoundState.getRoundWidth() * BLOCK_SIZE) + 1;
  }

  public void render(Graphics2D g) {
    g.clearRect(0, 0, getRoundWidth(), getRoundHeight());
    gameRoundState.render(g);
    // g.drawImage(imageContext.getBufferedImage(GameBlockEnum.RABBIT_IMAGE), 64, 64, null);
    // List<char[]> round = gameRoundState.currentRoundState();

    // for (int i = 0; i < round.size(); i++) {
    //   char[] line = round.get(i);
    //   for (int j = 0; j < line.length; j++) {
    //     g.setColor(Color.GRAY);
        
    //     g.drawImage(imageContext.getBufferedImage(GameBlockEnum.GRASS_A), 
    //       BLOCK_SIZE*j, BLOCK_SIZE*i, null);

    //     this.drawBlockIfPresent(line[j], g, BLOCK_SIZE*j, BLOCK_SIZE*i);
    //   }
    // }
  }

  
}