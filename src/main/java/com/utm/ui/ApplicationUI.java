package com.utm.ui;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.utm.state.ApplicationState;


public class ApplicationUI extends JFrame {

  private static final long serialVersionUID = 4269597159920155033L;
  private JPanel currentStatePanel;

  public ApplicationUI(){
    super();

    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setTitle(ApplicationUIConfig.APPLICATION_NAME);
    setSize(new Dimension(ApplicationUIConfig.WINDOW_WIDTH, ApplicationUIConfig.WINDOW_HEIGHT));
    setResizable(false);
    setLocationRelativeTo(null);
    setLayout(null);
    setVisible(true);
  }

  public void setState(ApplicationState state){
    this.currentStatePanel = state.applicationState();
    add(currentStatePanel);
  }

}