package com.utm.state;

import javax.swing.JPanel;

import com.utm.ui.mainmenu.MainMenuUI;

public class MainMenuState implements ApplicationState {

  @Override
  public JPanel applicationState() {
    return new MainMenuUI();
  }
  
}