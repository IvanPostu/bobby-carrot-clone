package com.utm.app.game.round;

import java.util.List;
import java.util.Map;

import javax.swing.Timer;

import com.utm.app.Point;
import com.utm.app.game.Camera;
import com.utm.app.game.element.GameObject;
import com.utm.app.game.element.GameObjectFactory;
import com.utm.app.game.round.dto.RoundInitializerDTO;
import com.utm.app.state.ApplicationState;
import com.utm.app.state.CurrentAppStateEnum;
import com.utm.app.view.game.MainGame;
import com.utm.app.view.game.TopPanel;
import com.utm.core.InjectByType;
import com.utm.core.InjectProperty;
import com.utm.core.PostConstruct;
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

  @InjectByType
  private GameObjectFactory gameObjectFactory;

  @InjectByType
  private Camera camera;

  private Timer roundTimer;

  private RoundState roundState;

  private short currentRound;

  private int roundTime;

  @PostConstruct
  private void postConstruct(){
    this.roundTimer = new Timer(1000, event -> {
      topPanel.setRoundTime(--this.roundTime);
    });
    setRound((short)1);
  }

  public void addNotify(){
    this.roundTimer.start();
  }

  public void removeNotify(){
    this.roundTimer.stop();
  }

  public RoundState getRoundState() {
    return roundState;
  }

  public short getCurrentRound() {
    return currentRound;
  }

  private Map<Point, List<GameObject>> generateGameObjects(){
    RoundInitializerDTO round = roundInitializer.initGameObjects(currentRound);
    this.roundTime = round.getRoundTime();
    topPanel.setCurrentRound(currentRound);
    topPanel.setRoundTime(this.roundTime);
    return round.getRoundObjects();
  }

  private void manageRoundCompleteEvent(){
    if(hasNextRound()){
      applicationState.setApplicationState(CurrentAppStateEnum.NEXT_ROUND_MSG);
      setRound(++currentRound);
    }else{
      applicationState.setApplicationState(CurrentAppStateEnum.WIN_GAME_MSG);
      setRound((short)1);
    }
  }

  private boolean hasNextRound(){
    return currentRound<totalRounds;
  }

  private void setRound(short round){
    currentRound = round;
    roundState = new RoundState(camera, generateGameObjects(), topPanel, this::onRoundComplet);
  }

  private void onRoundComplet(){
    manageRoundCompleteEvent();
  }

}