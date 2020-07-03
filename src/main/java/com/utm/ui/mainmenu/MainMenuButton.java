package com.utm.ui.mainmenu;

import java.awt.Color;

import javax.swing.JButton;

public abstract class MainMenuButton extends JButton {


  /**
   *
   */
  private static final long serialVersionUID = -4089722191928840149L;
  protected final int BUTTON_WIDTH = 100;
  protected final int BUTTON_HEIGHT = 40;
  protected final int X = MainMenuUIConfig.WINDOW_WIDTH / 2 - BUTTON_WIDTH / 2;
  protected final int Y = 45;
  protected final Color COLOR = Color.ORANGE;

  public MainMenuButton(String name) {
    super(name);
  }
}