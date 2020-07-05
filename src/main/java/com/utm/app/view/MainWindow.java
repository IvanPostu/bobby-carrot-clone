package com.utm.app.view;

import javax.swing.JFrame;

import java.awt.Dimension;

import com.utm.core.InjectProperty;
import com.utm.core.PostConstruct;
import com.utm.core.Singleton;

@Singleton(lazy = false)
public class MainWindow extends JFrame {
  
  /**
   *
   */
  private static final long serialVersionUID = -1962554833167584496L;

  @InjectProperty("applicationWindowTitle")
  private String applicationWindowTitle;

  public MainWindow() {
    super();
  }

  @PostConstruct
  public void postConstruct(){
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setTitle(applicationWindowTitle);
    setSize(new Dimension(640, 480));
    setResizable(false);
    setLocationRelativeTo(null);
    setLayout(null);
    setVisible(true);
  }

}