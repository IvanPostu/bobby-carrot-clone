package com.utm.app.view.menu;

import javax.swing.JButton;

import com.utm.core.InjectProperty;
import com.utm.core.PostConstruct;

import java.awt.Color;

public class ExitButton extends JButton {
  /**
   *
   */
  private static final long serialVersionUID = -7038556113049046504L;

  protected final Color COLOR = Color.ORANGE;

  @InjectProperty("application.mainmenu.button.width")
  private int width;
  
  @InjectProperty("application.mainmenu.button.height")
  private int height;

  @InjectProperty("application.window.width")
  private int windowWidth;
  
  @InjectProperty("application.window.height")
  private int windowHeight;

  public ExitButton() {
    super("Exit");
  }

  @PostConstruct
  public void init(){
    int y = 95;
    int x = windowWidth / 2 - width / 2;
    this.setBounds(x, y, width, height);
    this.setBackground(this.COLOR); 
  }
}