package com.utm.app.resource;

public enum GameBlockResourcesEnum {
  RABBIT("/images/bobby48.png"),
  ROCK("/images/rock48.png"),
  CARROT("/images/carrot48.png"),
  GRASS_A("/images/grass48_A.png"),
  GRASS_B("/images/grass48_B.png"),
  GRASS_C("/images/grass48_C.png"),
  TRAP_A_OFF("/images/trap_A_off.png"),
  TRAP_A_ON("/images/trap_A_on.png");

  private String path;

  private GameBlockResourcesEnum(String path){
    this.path = path;
  }

  public String getPath(){
    return this.path;
  }

}