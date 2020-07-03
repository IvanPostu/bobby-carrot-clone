package com.utm.ui.mainmenu;

public class PlayButton extends MainMenuButton {
  /**
   *
   */
  private static final long serialVersionUID = -7038556113049046504L;

  public PlayButton() {
    super("Play");
    this.setBounds(this.X, this.Y, this.BUTTON_WIDTH, this.BUTTON_HEIGHT);
    this.setBackground(this.COLOR); 
  }
}