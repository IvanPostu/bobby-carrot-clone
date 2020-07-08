package com.utm.app.game.round;

import java.util.List;
import java.util.Map;

import com.utm.app.Point;
import com.utm.app.game.element.GameObject;
import com.utm.core.InjectByType;
import com.utm.core.InjectProperty;
import com.utm.core.Singleton;

@Singleton
public class RoundManager {
  
  @InjectProperty("application.game.totalrounds")
  private int totalRounds;

  @InjectByType
  private RoundInitializer roundInitializer;

  private int currentRound = 0;

  Map<Point, List<GameObject>> nextRound(){
    currentRound++;
    return roundInitializer.initGameObjects(currentRound);
  }

  boolean hasNextRound(){
    return currentRound<totalRounds;
  }

}