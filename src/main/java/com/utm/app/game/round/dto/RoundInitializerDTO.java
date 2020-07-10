package com.utm.app.game.round.dto;

import java.util.List;
import java.util.Map;

import com.utm.app.Point;
import com.utm.app.game.element.GameObject;

public class RoundInitializerDTO {
  
  
  private Map<Point, List<GameObject>> roundObjects;
  private int roundTime;


  public RoundInitializerDTO(Map<Point, List<GameObject>> roundObjects, int roundTime) {
    this.roundObjects = roundObjects;
    this.roundTime = roundTime;
  }


  public Map<Point, List<GameObject>> getRoundObjects() {
    return roundObjects;
  }


  public int getRoundTime() {
    return roundTime;
  }

}