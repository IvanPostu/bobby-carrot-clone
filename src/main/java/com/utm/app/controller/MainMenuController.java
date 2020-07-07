package com.utm.app.controller;

import com.utm.app.game.CurrentAppStateEnum;
import com.utm.app.state.ApplicationState;
import com.utm.core.InjectByType;
import com.utm.core.Singleton;

@Singleton(lazy = false)
public class MainMenuController {

  @InjectByType
  private ApplicationState appState;
  
  public void exitFromApplication(){
    System.exit(0);
  }

  public void playGame(){
    appState.setApplicationState(CurrentAppStateEnum.GAME);
  }

}