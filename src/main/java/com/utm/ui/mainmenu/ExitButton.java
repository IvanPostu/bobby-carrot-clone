package com.utm.ui.mainmenu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ExitButton extends MainMenuButton implements ActionListener {
  /**
   *
   */
  private static final long serialVersionUID = -7038556113049046504L;

  public ExitButton() {
    super("Exit");
    this.setBounds(this.X, this.Y + 50, this.BUTTON_WIDTH, this.BUTTON_HEIGHT);
    this.setBackground(this.COLOR);
    this.addActionListener(this);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    System.exit(0);
  }
}