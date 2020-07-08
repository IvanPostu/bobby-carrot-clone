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
  
  public void render(Graphics2D g) {
    gameRoundState.render(g);
  }
  
}