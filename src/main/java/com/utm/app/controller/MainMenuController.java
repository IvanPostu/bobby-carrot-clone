package com.utm.app.controller;

import com.utm.app.state.CurrentGameState;
import com.utm.core.InjectByType;
import com.utm.core.Singleton;

@Singleton(lazy = false)
public class MainMenuController {

  @InjectByType
  private GameStateController gameStateController;
  
  public void exitFromApplication(){
    System.exit(0);
  }

  public void playGame(){
    gameStateController.setGameState(CurrentGameState.GAME);
  }

}