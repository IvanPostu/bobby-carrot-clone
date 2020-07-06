package com.utm.app.view.game;

import javax.swing.*;
import java.awt.*;

import com.utm.core.InjectByType;
import com.utm.core.InjectProperty;
import com.utm.core.PostConstruct;

import java.awt.event.*;
import java.awt.Color;
import java.awt.image.VolatileImage;

public class MainGame extends JPanel implements ActionListener {

  private static final long serialVersionUID = 4997689702344591993L;

  private Timer timer;

  private VolatileImage image;

  @InjectByType
  private GameWorld gameWorld;

  @InjectProperty("application.window.width")
  private int width;

  @InjectProperty("application.window.height")
  private int height;

  public MainGame() {
    super();
  }

  @PostConstruct
  public void postConstruct() {
    setLayout(null);
    setBounds(0, 0, width, height);
    setFocusable(true);
    requestFocus();
  }

  private void update(){
    
  }
  
  @Override
  public void addNotify() {
    super.addNotify();
    image = createVolatileImage((int)(width/1), (int)(height/1));
    this.timer = new Timer(1000 / 60, this);
    this.timer.start();
  }

  @Override
  public void paintComponent(Graphics g) {
      super.paintComponent(g);

      Graphics2D g2 = image.createGraphics();
      g2.setBackground(Color.WHITE);
      g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING	, RenderingHints.VALUE_ANTIALIAS_ON);
      g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, 
        RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
      gameWorld.render(g2);
      g.drawImage(this.image.getScaledInstance(width, height, Image.SCALE_FAST),
              0, 0, null
      );

  }


  @Override
  public void actionPerformed(ActionEvent arg0) {
    update();
    repaint();
  }
  
}