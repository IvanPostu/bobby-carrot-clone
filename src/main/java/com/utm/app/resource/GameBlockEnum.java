package com.utm.app.resource;

public enum GameBlockEnum {
  RABBIT("/images/bobby48.png"),
  ROCK("/images/rock48.png"),
  CARROT("/images/carrot48.png"),
  GRASS_A("/images/grass48_A.png"),
  GRASS_B("/images/grass48_B.png"),
  GRASS_C("/images/grass48_C.png"),
  ROCK_A("/images/rock48.png"),;

  private String path;

  private GameBlockEnum(String path){
    this.path = path;
  }

  public String getPath(){
    return this.path;
  }

}