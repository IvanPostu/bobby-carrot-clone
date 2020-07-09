package com.utm.app.view.message;

import java.awt.Color;

import com.utm.app.state.CurrentAppStateEnum;
import com.utm.core.InjectProperty;


public class NextRoundMessage extends FullScreenMessageWithTimeout {

  private static final long serialVersionUID = 795643169384749507L;

  @InjectProperty("application.game.nextroundmessage.time")
  private int timeInSecondsFromResource;

  @Override
  protected String getMessage() {
    return String.format("NEXT ROUND AFTER %d second(s)", this.timeInSeconds);
  }

  @Override
  protected Color getScreenColor() {
    return Color.ORANGE;
  }

  @Override
  protected void onTimeExpiredHandle() {
    applicationState.setApplicationState(CurrentAppStateEnum.GAME);
  }

  @Override
  protected int getTimeInSecondsFromResource() {
    return timeInSecondsFromResource;
  }

}