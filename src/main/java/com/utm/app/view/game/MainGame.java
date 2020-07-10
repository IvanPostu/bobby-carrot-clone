package com.utm.app.view.game;

import javax.swing.*;
import java.awt.*;

import com.utm.app.game.Camera;
import com.utm.app.game.round.RoundSize;
import com.utm.core.InjectByType;
import com.utm.core.InjectProperty;
import com.utm.core.PostConstruct;

import java.awt.event.*;
import java.awt.image.VolatileImage;

public class MainGame extends JPanel implements ActionListener, KeyListener {

  private static final long serialVersionUID = 4997689702344591993L;

  private Timer timer;

  private VolatileImage image;

  @InjectByType
  private GameWorld gameWorld;

  @InjectProperty("application.window.width")
  private int width;

  @InjectProperty("application.window.height")
  private int height;

  @InjectByType
  private Camera camera;

  @InjectByType
  private RoundSize roundSize;

  @InjectByType
  private TopPanel topPanel;

  public MainGame() {
    super();
  }

  @PostConstruct
  public void postConstruct() {
    this.timer = new Timer(1000 / 60, this);
    setLayout(null);
    setBounds(0, 0, width, height);
    add(topPanel);
    setFocusable(true);
    requestFocus();
    addKeyListener(this);
  }

  private void update(){
    repaint();
  }
  
  @Override
  public void addNotify() {
    super.addNotify();
    this.resetGameRoundSize(roundSize.getWidth(),roundSize.getHeight());
    this.timer.start();
    gameWorld.addNotify();
  }

  @Override
  public void removeNotify(){
    super.removeNotify();
    this.timer.stop();
    gameWorld.removeNotify();
  }

  public void resetGameRoundSize(int width, int height){
    image = createVolatileImage(width, height);
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    
    Graphics2D g2 = image.createGraphics();
    g2.setBackground(Color.BLACK);

    gameWorld.render(g2);
    g.drawImage(
      this.image.getScaledInstance(
        roundSize.getWidth(), roundSize.getHeight(),
        Image.SCALE_FAST
      ),
      camera.getX(), camera.getY(), null
    );
  }

  @Override
  public void actionPerformed(ActionEvent event) {
    update();
  }

  @Override
  public void keyPressed(KeyEvent event) {
    gameWorld.keyPressed(event);
    update();
  }

  @Override
  public void keyReleased(KeyEvent event) {

  }

  @Override
  public void keyTyped(KeyEvent event) {

  }
  
}