package com.utm.app.view.game;

import javax.swing.*;
import java.awt.*;

import com.utm.app.game.Camera;
// import com.utm.app.game.round.RoundSize;
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

  // @InjectByType
  // private RoundSize roundSize;

  public MainGame() {
    super();
  }

  @PostConstruct
  public void postConstruct() {
    setLayout(null);
    setBounds(0, 0, width, height);
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
    image = createVolatileImage(gameWorld.getRoundWidth(),gameWorld.getRoundHeight());
    this.timer = new Timer(1000 / 70, this);
    this.timer.start();
    
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);

    Graphics2D g2 = image.createGraphics();
    g2.setBackground(Color.LIGHT_GRAY);

    gameWorld.render(g2);
    g.drawImage(
      this.image.getScaledInstance(
        gameWorld.getRoundWidth(),gameWorld.getRoundHeight(), 
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