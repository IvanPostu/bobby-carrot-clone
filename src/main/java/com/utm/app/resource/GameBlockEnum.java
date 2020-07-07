package com.utm.app.resource;

public enum GameBlockEnum {
  RABBIT("/images/bobby48.png", 'Z'),
  ROCK("/images/rock48.png", 'R'),
  CARROT("/images/carrot48.png", 'C'),
  GRASS_A("/images/grass48_A.png", '1'),
  GRASS_B("/images/grass48_B.png", '2'),
  GRASS_C("/images/grass48_C.png", '3'),
  ROCK_A("/images/rock48.png", ' '),;

  private String path;
  private char charId;

  private GameBlockEnum(String path, char charId){
    this.path = path;
    this.charId = charId;
  }

  public String getPath(){
    return this.path;
  }

  public char getCharId() {
    return charId;
  }
}