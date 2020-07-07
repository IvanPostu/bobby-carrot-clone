package com.utm.app.state;

import javax.swing.JPanel;

import com.utm.app.view.MainWindow;
import com.utm.app.view.game.MainGame;
import com.utm.app.view.menu.MainMenu;
import com.utm.core.InjectByType;
import com.utm.core.PostConstruct;
import com.utm.core.Singleton;

@Singleton(lazy = false)
public class ApplicationState {
  
  @InjectByType
  private MainMenu mainMenuJFrame;

  @InjectByType
  private MainGame mainGameJFrame;

  @InjectByType
  private MainWindow mainWindow;

  private CurrentAppStateEnum stateEnum; 
  
  @PostConstruct
  public void postConstruct(){
    this.setApplicationState(CurrentAppStateEnum.MAIN_MENU);
  }
  
  public void setApplicationState(CurrentAppStateEnum gameState){
    if(gameState.equals(CurrentAppStateEnum.MAIN_MENU)){
      mainWindow.remove(mainGameJFrame);
      addUIComponentToMainWindow(mainMenuJFrame);
    }

    if(gameState.equals(CurrentAppStateEnum.GAME)){
      mainWindow.remove(mainMenuJFrame);
      addUIComponentToMainWindow(mainGameJFrame);
    }

    this.stateEnum = gameState;
  }

  private void addUIComponentToMainWindow(JPanel component){
    mainWindow.add(component);
    component.setFocusable(true);
    component.requestFocus();
  }


  public CurrentAppStateEnum getAppState(){
    return this.stateEnum;
  }

}