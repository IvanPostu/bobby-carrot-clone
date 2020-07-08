package com.utm.app.view.game;

import com.utm.app.game.MoveDirection;
import com.utm.app.game.round.GameRoundState;
import com.utm.core.InjectByType;
import com.utm.core.InjectProperty;


import java.awt.event.*;
import java.awt.*;

public class GameWorld {

  @InjectProperty("application.window.width")
  private int width;
  
  @InjectProperty("application.window.height")
  private int height;

  @InjectByType
  private GameRoundState gameRoundState;

  // @InjectByType
  // private RoundSize roundSize;

  public void keyPressed(KeyEvent event) {
    if(event.getKeyCode() == KeyEvent.VK_RIGHT){
      gameRoundState.moveRabbit(MoveDirection.RIGHT);
    }
    if(event.getKeyCode() == KeyEvent.VK_LEFT){
      gameRoundState.moveRabbit(MoveDirection.LEFT);
    }
    if(event.getKeyCode() == KeyEvent.VK_UP){
      gameRoundState.moveRabbit(MoveDirection.UP);
    }
    if(event.getKeyCode() == KeyEvent.VK_DOWN){
      gameRoundState.moveRabbit(MoveDirection.DOWN);
    }
  }

  public int getRoundHeight(){
    // TODO Fix me!!!!!
    return width * 3;//(gameRoundState.getRoundHeight() * BLOCK_SIZE) + 1;
  }

  public int getRoundWidth(){
    // TODO Fix me!!!!!
    return height * 3;//(gameRoundState.getRoundWidth() * BLOCK_SIZE) + 1;
  }

  public void render(Graphics2D g) {
    g.clearRect(0, 0, width*2, height*2);
    gameRoundState.render(g);
  }
  
}