package com.utm.app.view.game;


import java.awt.event.*;
import java.awt.Color;

import com.utm.core.InjectProperty;
import com.utm.core.PostConstruct;
import com.utm.core.Singleton;
import com.utm.app.Point;

import javax.swing.JPanel;
import javax.swing.Timer;

import java.awt.*;

@Singleton(lazy = false)
public class TopPanel extends JPanel implements ActionListener{
  
  private static final long serialVersionUID = 6116031675210364255L;

  @InjectProperty("application.window.width")
  private int width;

  /**
   * Set on init method.
   */
  private int height;

  @InjectProperty("application.isdebug")
  private boolean isDebug;

  private Timer timer;

  private int roundTime = 0;
  private int currentRound = 0;
  private int eatableOnRound = 0;

  private Font font = new Font ("Consolas", 1, 17);

  private Point rabbitPos = new Point(0,0);
  private Color panelColor = new Color(192,192,192,96);

  @PostConstruct
  public void postConstruct() {
    this.height = isDebug ? 40 : 20;
    setLayout(null);
    setBounds(0, 0, width, height);
    setBackground(panelColor);
    setFocusable(true);
    requestFocus();
    this.timer = new Timer(1000 / 60, this);
  }

  @Override
  public void addNotify() {
    super.addNotify();
    this.timer.start();
  }

  @Override
  public void removeNotify() {
    super.removeNotify();
    this.timer.stop();
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    g.setFont(font);
    g.drawString(String.format("Round time: %d", this.roundTime), 15, 15);
    g.drawString(String.format("Current round: %d", this.currentRound), 190, 15);
    g.drawString(String.format("Food on round: %d", this.eatableOnRound), 410, 15);
    if(isDebug){
      g.drawString(String.format("Rabbit position X:%d Y:%d", 
        rabbitPos.getX(), rabbitPos.getY()),15, 35);
    }
  }


  @Override
  public void actionPerformed(ActionEvent arg0) {
    repaint();
  }

  public void setCurrentRound(int currentRound) {
    this.currentRound = currentRound;
  }

  public void setEatableOnRound(int eatableOnRound) {
    this.eatableOnRound = eatableOnRound;
  }

  public void setRabbitPos(Point pos){
    this.rabbitPos = pos;
  }

  public void setRoundTime(int roundTime) {
    this.roundTime = roundTime;
  }
}