package com.utm.app.view.message;

import java.awt.Color;

import com.utm.app.state.CurrentAppStateEnum;
import com.utm.core.InjectProperty;


public class WinGameMessage extends FullScreenMessageWithTimeout {

  private static final long serialVersionUID = 1033834214702847590L;

  @InjectProperty("application.game.wingamemessage.time")
  private int timeInSecondsFromResource;

  @Override
  protected String getMessage() {
    final String msg1 = "Congratulations, you won!!!";
    final String msg2 = String.format("Back to menu after %d second(s)", 
      this.timeInSeconds);

    return msg1 + '\n' + msg2;
  }

  @Override
  protected Color getScreenColor() {
    return Color.ORANGE;
  }

  @Override
  protected void onTimeExpiredHandle() {
    applicationState.setApplicationState(CurrentAppStateEnum.MAIN_MENU);
  }

  @Override
  protected int getTimeInSecondsFromResource() {
    return timeInSecondsFromResource;
  }

}