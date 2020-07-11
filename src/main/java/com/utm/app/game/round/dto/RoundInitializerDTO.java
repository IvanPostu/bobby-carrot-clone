package com.utm.app.game.round.dto;

import java.util.List;
import java.util.Map;

import com.utm.app.Point;
import com.utm.app.game.element.GameObject;
import com.utm.app.game.element.Rabbit;

public class RoundInitializerDTO {
  
  
  private Map<Point, List<GameObject>> roundObjects;
  private int roundTime;
  private Rabbit rabbit;


  public RoundInitializerDTO() {
  }


  public Map<Point, List<GameObject>> getRoundObjects() {
    return roundObjects;
  }


  public int getRoundTime() {
    return roundTime;
  }

  public Rabbit getRabbit() {
    return rabbit;
  }

  public void setRoundObjects(Map<Point, List<GameObject>> roundObjects) {
    this.roundObjects = roundObjects;
  }

  public void setRoundTime(int roundTime) {
    this.roundTime = roundTime;
  }

  public void setRabbit(Rabbit rabbit) {
    this.rabbit = rabbit;
  }

}