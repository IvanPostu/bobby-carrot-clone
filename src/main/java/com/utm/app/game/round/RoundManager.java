package com.utm.app.game.round;

import java.awt.event.ActionEvent;

import javax.swing.Timer;

import com.utm.app.game.Camera;
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

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Singleton
public class RoundManager {

  static Logger logger = LogManager.getLogger(RoundManager.class);
  
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
    this.roundTimer = new Timer(1000, this::everySecondEvent);
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

  private RoundInitializerDTO generateGameObjects(){
    RoundInitializerDTO round = roundInitializer.initGameObjects(currentRound);
    this.roundTime = round.getRoundTime();
    topPanel.setCurrentRound(currentRound);
    topPanel.setRoundTime(this.roundTime);

    logger.debug("Round objects successfully generated.");
    return round;
  }

  private void everySecondEvent(ActionEvent $_){
    topPanel.setRoundTime(this.roundTime==0?this.roundTime:--this.roundTime);

    this.roundState.everySecondHandler();

    if(this.roundTime==0){
      manageRoundLoseEvent();
    }
  }

  private void manageRoundLoseEvent(){
    applicationState.setApplicationState(CurrentAppStateEnum.LOSE_ROUND_MSG);
    setRound(currentRound);
    logger.debug("Round {} lost, restart process.", currentRound);
  }

  private void manageRoundCompleteEvent(){
    logger.debug("Round {} completed.", currentRound);
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
    RoundInitializerDTO roundData = generateGameObjects();

    roundState = new RoundState(camera, roundData.getRoundObjects(), roundData.getRabbit(),
      topPanel, this::manageRoundCompleteEvent, this::manageRoundLoseEvent
    );

    logger.debug("Round {} has been initialized.", round);
  }

}