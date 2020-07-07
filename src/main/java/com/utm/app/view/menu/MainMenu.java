package com.utm.app.view.menu;


import javax.swing.*;

import com.utm.app.controller.MainMenuController;
import com.utm.core.InjectByType;
import com.utm.core.InjectProperty;
import com.utm.core.PostConstruct;

import java.awt.event.*;
import java.awt.Color;
import java.awt.event.ActionListener;


public class MainMenu extends JPanel implements ActionListener {

  private static final long serialVersionUID = 4997689702344591993L;

  @InjectProperty("application.window.width")
  private int width;
  
  @InjectProperty("application.window.height")
  private int height;

  @InjectByType
  private PlayButton playButton;

  @InjectByType
  private ExitButton exitButton;

  @InjectByType
  private MainMenuController mainMenuController;

  public MainMenu() {
    super();
  }

  @PostConstruct
  public void postConstruct(){
    setBackground(Color.DARK_GRAY);
    setLayout(null);
    setBounds(0, 0, width, height);
    setFocusable(true);
    requestFocus();
    add(playButton);
    add(exitButton);

    exitButton.addActionListener(l -> {
      mainMenuController.exitFromApplication();
    });

    playButton.addActionListener(l -> {
      mainMenuController.playGame();
    });
  }

  @Override
  public void actionPerformed(ActionEvent arg0) {
    repaint();
  }
  
}