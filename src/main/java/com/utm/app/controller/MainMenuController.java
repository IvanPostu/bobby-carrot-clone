package com.utm.app.controller;

import com.utm.core.Singleton;

@Singleton(lazy = false)
public class MainMenuController {
  
  public void exitFromApplication(){
    System.exit(0);
  }

}