package com.utm.app.view.game;


import java.awt.event.*;
import java.awt.Color;

import com.utm.core.InjectProperty;
import com.utm.core.PostConstruct;
import com.utm.core.Singleton;

import javax.swing.JPanel;
import javax.swing.Timer;

import java.awt.*;

@Singleton(lazy = false)
public class TopPanel extends JPanel implements ActionListener{
  
  private static final long serialVersionUID = 6116031675210364255L;

  @InjectProperty("application.game.scorepanel.width")
  private int width;

  @InjectProperty("application.game.scorepanel.height")
  private int height;

  private Timer timer;
  private Timer secondTimer;

  private int gameSeconds = 0;
  private int currentRound = 0;
  private int eatableOnRound = 0;

  private Font font = new Font ("Consolas", 1, 16);

  @PostConstruct
  public void postConstruct() {
    setLayout(null);
    setBounds(0, 0, width, height);
    setBackground(Color.GRAY);
    setFocusable(true);
    requestFocus();
  }

  @Override
  public void addNotify() {
    super.addNotify();
    this.timer = new Timer(1000 / 60, this);
    this.secondTimer = new Timer(1000, (a) -> {
      gameSeconds++;
    });
    this.timer.start();
    this.secondTimer.start();
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    g.setFont(font);
    g.drawString(String.format("Round time: %d", this.gameSeconds), 15, 15);
    g.drawString(String.format("Current round: %d", this.currentRound), 190, 15);
    g.drawString(String.format("Food on round: %d", this.eatableOnRound), 410, 15);
  }


  @Override
  public void actionPerformed(ActionEvent arg0) {
    repaint();
  }

  public void resetTimerToZero(){
    this.gameSeconds = 0;
  }

  public void setCurrentRound(int currentRound) {
    this.currentRound = currentRound;
  }

  public void setEatableOnRound(int eatableOnRound) {
    this.eatableOnRound = eatableOnRound;
  }
}