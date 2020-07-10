package com.utm.app.view.game;

import com.utm.app.game.MoveDirection;
import com.utm.app.game.round.RoundManager;
import com.utm.app.game.round.RoundState;
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
  private RoundManager roundManager;

  public void keyPressed(KeyEvent event) {
    RoundState roundState = roundManager.getRoundState();

    if(event.getKeyCode() == KeyEvent.VK_RIGHT){
      roundState.moveRabbit(MoveDirection.RIGHT);
    }
    if(event.getKeyCode() == KeyEvent.VK_LEFT){
      roundState.moveRabbit(MoveDirection.LEFT);
    }
    if(event.getKeyCode() == KeyEvent.VK_UP){
      roundState.moveRabbit(MoveDirection.UP);
    }
    if(event.getKeyCode() == KeyEvent.VK_DOWN){
      roundState.moveRabbit(MoveDirection.DOWN);
    }
  }

  public void render(Graphics2D g) {
    roundManager.getRoundState().render(g);
  }


  
}