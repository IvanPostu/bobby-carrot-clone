package com.utm.ui.mainmenu;

import javax.swing.*;

import java.awt.event.*;
import java.awt.Color;
import java.awt.event.ActionListener;


public class MainMenuUI extends JPanel 
  implements ActionListener{

  private static final long serialVersionUID = 4997689702344591993L;

  private Timer timer;

  public MainMenuUI() {
    super();

    JButton play = new PlayButton();
    add(play);
    add(new ExitButton());

    setBackground(Color.DARK_GRAY);
    setLayout(null);
    setBounds(0, 0, MainMenuUIConfig.WINDOW_WIDTH, MainMenuUIConfig.WINDOW_HEIGHT);
    setFocusable(true);
    requestFocus();
  }

  @Override
  public void addNotify(){
      super.addNotify();
      this.timer = new Timer(1000/60, this);
      this.timer.start();
  }

  @Override
  public void actionPerformed(ActionEvent arg0) {
    repaint();
  }
  
}