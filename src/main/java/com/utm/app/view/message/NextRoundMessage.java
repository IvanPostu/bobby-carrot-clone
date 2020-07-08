package com.utm.app.view.message;

import javax.swing.*;
import java.awt.*;

import com.utm.app.state.ApplicationState;
import com.utm.app.state.CurrentAppStateEnum;
import com.utm.core.InjectByType;
import com.utm.core.InjectProperty;
import com.utm.core.PostConstruct;

import java.awt.event.*;
import java.awt.Color;
import java.awt.event.ActionListener;


public class NextRoundMessage extends JPanel implements ActionListener {

  private static final long serialVersionUID = 4997689702344591993L;

  @InjectByType
  private ApplicationState applicationState;

  @InjectProperty("application.window.width")
  private int width;
  
  @InjectProperty("application.window.height")
  private int height;

  private Timer timer;
  private Timer secondTimer;

  private final Font font = new Font ("Consolas", 1, 26);

  private int timeInSeconds;

  public NextRoundMessage() {
    super();
  }

  @PostConstruct
  public void postConstruct(){
    setBackground(Color.ORANGE);
    setLayout(null);
    setBounds(0, 0, width, height);
    setFocusable(true);
    requestFocus();
    this.timer = new Timer(1000 / 60, this);
    this.secondTimer = new Timer(1000, a -> {
      if(timeInSeconds == 0){
        applicationState.setApplicationState(CurrentAppStateEnum.GAME);
      }
      timeInSeconds--;
    });
  }

  @Override
  public void addNotify() {
    super.addNotify();
    this.timeInSeconds = 4;
    this.timer.start();
    this.secondTimer.start();
  }

  @Override
  public void removeNotify() {
    super.removeNotify();
    this.timer.stop();
    this.secondTimer.stop();
  }

  @Override
  public void actionPerformed(ActionEvent arg0) {
    repaint();
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    g.setFont(font);
    g.drawString(String.format("NEXT ROUND AFTER %d second(s)", this.timeInSeconds), 
      width / 2 - 150, height / 2 - 50);
  }

}