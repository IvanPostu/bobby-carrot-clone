package com.utm.app.resource;

public enum GameBlockEnum {
  RABBIT_IMAGE("/images/bobby48.png"),
  ROCK_IMAGE("/images/rock48.png"),
  CARROT_IMAGE("/images/carrot48.png"),
  GRASS_A("/images/grass48_A.png"),
  GRASS_B("/images/grass48_B.png"),
  GRASS_C("/images/grass48_C.png");

  private String path;

  private GameBlockEnum(String path){
    this.path = path;
  }

  public String getPath(){
    return this.path;
  }
}