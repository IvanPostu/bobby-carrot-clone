package com.utm.app.view.message;

import javax.swing.*;
import java.awt.*;

import com.utm.app.state.ApplicationState;
import com.utm.core.InjectByType;
import com.utm.core.InjectProperty;
import com.utm.core.PostConstruct;

import java.awt.event.*;
import java.awt.Color;
import java.awt.event.ActionListener;


abstract class FullScreenMessageWithTimeout extends JPanel implements ActionListener {

  private static final long serialVersionUID = -8059287014290049784L;

  @InjectByType
  protected ApplicationState applicationState;

  @InjectProperty("application.window.width")
  private int width;
  
  @InjectProperty("application.window.height")
  private int height;

  private Timer timer;
  private Timer secondTimer;

  private final Font font = new Font ("Consolas", 1, 26);

  protected int timeInSeconds;

  public FullScreenMessageWithTimeout() {
    super();
  }
  
  @PostConstruct
  private void postConstruct(){
    setBackground(getScreenColor());
    setLayout(null);
    setBounds(0, 0, width, height);
    setFocusable(true);
    requestFocus();
    this.timer = new Timer(1000 / 60, this);
    this.secondTimer = new Timer(1000, a -> everySecondHandle(a));
  }

  protected abstract String getMessage();
  protected abstract Color getScreenColor();
  protected abstract void onTimeExpiredHandle();
  protected abstract int getTimeInSecondsFromResource();


  private void everySecondHandle(ActionEvent actionEvent){
    if(timeInSeconds == 0){
      this.onTimeExpiredHandle();
    }
    timeInSeconds--;
  }

  protected int calcXForMessage(){
    int result = width / 2;
    return result - 200;
  }

  @Override
  public void addNotify() {
    super.addNotify();
    this.timeInSeconds = getTimeInSecondsFromResource();
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
  public void actionPerformed(ActionEvent e) {
    repaint();
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    g.setFont(font);

    final int fontSize = font.getSize();
    int offset = 0;
    String[] messages = getMessage().split("\n");
    for (String msg : messages) {
      g.drawString(msg, calcXForMessage(), height / 2 - fontSize + offset);
      offset += fontSize + 3;
    }
  }
}
