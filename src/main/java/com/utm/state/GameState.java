package com.utm.state;

import javax.swing.JPanel;

import com.utm.ui.game.GameUI;

public class GameState implements ApplicationState {

  @Override
  public JPanel applicationState() {
    return new GameUI();
  }
  
}