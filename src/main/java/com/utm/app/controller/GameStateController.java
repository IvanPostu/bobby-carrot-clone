package com.utm.app.controller;

import com.utm.app.state.CurrentGameState;
import com.utm.app.view.MainWindow;
import com.utm.app.view.game.MainGame;
import com.utm.app.view.menu.MainMenu;
import com.utm.core.InjectByType;
import com.utm.core.PostConstruct;
import com.utm.core.Singleton;

@Singleton(lazy = false)
public class GameStateController {
  
  @InjectByType
  private MainMenu mainMenuJFrame;

  @InjectByType
  private MainGame mainGameJFrame;

  @InjectByType
  private MainWindow mainWindow;

  private CurrentGameState gameState; 
  
  @PostConstruct
  public void postConstruct(){
    this.setGameState(CurrentGameState.MAIN_MENU);
  }
  
  public void setGameState(CurrentGameState gameState){
    if(gameState.equals(CurrentGameState.MAIN_MENU)){
      mainWindow.remove(mainGameJFrame);
      mainWindow.add(mainMenuJFrame);
    }

    if(gameState.equals(CurrentGameState.GAME)){
      mainWindow.remove(mainMenuJFrame);
      mainWindow.add(mainGameJFrame);
    }

    this.gameState = gameState;
  }


  public CurrentGameState getGameState(){
    return this.gameState;
  }

}