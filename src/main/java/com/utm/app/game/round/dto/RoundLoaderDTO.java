package com.utm.app.game.round.dto;

import java.util.List;

public class RoundLoaderDTO {
  private List<String[]> roundObjectNotations;
  private int roundTime;


  public List<String[]> getRoundObjectNotations() {
    return roundObjectNotations;
  }


  public int getRoundTime() {
    return roundTime;
  }


  public void setRoundObjectNotations(List<String[]> roundObjectNotations) {
    this.roundObjectNotations = roundObjectNotations;
  }


  public void setRoundTime(int roundTime) {
    this.roundTime = roundTime;
  }

}