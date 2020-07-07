package com.utm.app.view;

import java.awt.Dimension;

import javax.swing.JFrame;

import com.utm.app.state.ApplicationState;
import com.utm.core.InjectByType;
import com.utm.core.InjectProperty;
import com.utm.core.PostConstruct;
import com.utm.core.Singleton;

@Singleton(lazy = false)
public class MainWindow extends JFrame {
  
  private static final long serialVersionUID = -1962554833167584496L;

  @InjectProperty("application.window.title")
  private String title;

  @InjectProperty("application.window.width")
  private int width;
  
  @InjectProperty("application.window.height")
  private int height;

  @InjectByType
  private ApplicationState stateController;

  public MainWindow() {
    super();
  }

  @PostConstruct
  public void postConstruct(){
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setTitle(title);
    setSize(new Dimension(width, height));
    setResizable(false);
    setLocationRelativeTo(null);
    setLayout(null);
    setVisible(true);
  }

}