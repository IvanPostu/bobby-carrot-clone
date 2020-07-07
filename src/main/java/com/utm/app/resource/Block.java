package com.utm.app.resource;

public enum Block {
  RABBIT_IMAGE("/images/bobby48.png"),
  ROCK_IMAGE("/images/rock48.png"),
  CARROT_IMAGE("/images/carrot48.png");

  private String path;

  private Block(String path){
    this.path = path;
  }

  public String getPath(){
    return this.path;
  }
}