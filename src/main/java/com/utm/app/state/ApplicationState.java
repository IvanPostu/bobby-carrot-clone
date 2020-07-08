package com.utm.app.state;

import javax.swing.JPanel;

import com.utm.app.view.MainWindow;
import com.utm.app.view.game.MainGame;
import com.utm.app.view.menu.MainMenu;
import com.utm.app.view.message.NextRoundMessage;
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

  @InjectByType
  private NextRoundMessage nextRoundMessage;

  private CurrentAppStateEnum stateEnum; 
  
  @PostConstruct
  public void postConstruct(){
    this.setApplicationState(CurrentAppStateEnum.MAIN_MENU);
  }
  
  public void setApplicationState(CurrentAppStateEnum gameState){
    if(gameState.equals(CurrentAppStateEnum.MAIN_MENU)){
      mainWindow.getContentPane().removeAll();
      mainWindow.repaint();
      addUIComponentToMainWindow(mainMenuJFrame);
    }

    if(gameState.equals(CurrentAppStateEnum.GAME)){
      mainWindow.getContentPane().removeAll();
      mainWindow.repaint();
      addUIComponentToMainWindow(mainGameJFrame);
    }

    if(gameState.equals(CurrentAppStateEnum.NEXT_ROUND_MSG)){
      mainWindow.getContentPane().removeAll();
      mainWindow.repaint();
      addUIComponentToMainWindow(nextRoundMessage);
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