package com.utm.app.game.element;

public enum ElementNotation {
  /**
   * Random grass texture. 
   */
  EMPTY("AA"),

  RABBIT("RA"),

  ROCK("RO"),

  CARROT("E1"),

  GRASS_A("G1"),

  GRASS_B("G2"),

  GRASS_C("G3"),

  TRAP_A_ON("T1"),

  TRAP_A_OFF("t1");

  private String notation;

  private ElementNotation(String notation){
    this.notation = notation;
  }

  public String getNotation() {
    return notation;
  }
}
