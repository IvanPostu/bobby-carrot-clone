package com.utm.ui.game;

import javax.swing.*;

import java.awt.event.*;
import java.awt.Color;
import java.awt.event.ActionListener;


public class GameUI extends JPanel 
  implements ActionListener{

  private static final long serialVersionUID = 4997689702344591993L;

  private Timer timer;

  public GameUI() {
    super();


    setBackground(Color.GREEN);
    setLayout(null);
    setBounds(0, 0, GameUIConfig.WINDOW_WIDTH, GameUIConfig.WINDOW_HEIGHT);
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