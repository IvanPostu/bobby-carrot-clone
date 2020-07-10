package com.utm.app.view.message;

import java.awt.Color;

import com.utm.app.state.CurrentAppStateEnum;
import com.utm.core.InjectProperty;

public class LoseGameMessage extends FullScreenMessageWithTimeout {

  private static final long serialVersionUID = 1343141341L;

  @InjectProperty("application.game.loosemessage.time")
  private int timeInSecondsFromResource;

  @Override
  protected String getMessage() {
    final String msg1 = "You lose !!!";
    final String msg2 = String.format("Restart round after %d second(s)", 
      this.timeInSeconds);

    return msg1 + '\n' + msg2;
  }

  @Override
  protected Color getScreenColor() {
    return Color.RED;
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