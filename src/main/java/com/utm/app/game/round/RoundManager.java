package com.utm.app.game.round;

import java.util.List;
import java.util.Map;

import com.utm.app.Point;
import com.utm.app.game.element.GameObject;
import com.utm.app.state.ApplicationState;
import com.utm.app.state.CurrentAppStateEnum;
import com.utm.app.view.game.MainGame;
import com.utm.app.view.game.TopPanel;
import com.utm.core.InjectByType;
import com.utm.core.InjectProperty;
import com.utm.core.Singleton;

@Singleton
public class RoundManager {
  
  @InjectProperty("application.game.totalrounds")
  private int totalRounds;

  @InjectByType
  private RoundInitializer roundInitializer;

  @InjectByType
  private TopPanel topPanel;

  @InjectByType 
  private MainGame mainGame;

  @InjectByType
  private ApplicationState applicationState;

  private int currentRound = 0;

  Map<Point, List<GameObject>> nextRound(){
    currentRound++;
    topPanel.setCurrentRound(currentRound);
    topPanel.resetTimerToZero();
    return roundInitializer.initGameObjects(currentRound);
    
  }

  void manageRoundCompleteEvent(){
    if(hasNextRound()){
      applicationState.setApplicationState(CurrentAppStateEnum.NEXT_ROUND_MSG);
    }else{
      applicationState.setApplicationState(CurrentAppStateEnum.WIN_GAME_MSG);
    }
  }

  boolean hasNextRound(){
    return currentRound<totalRounds;
  }

}