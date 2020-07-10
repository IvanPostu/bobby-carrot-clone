package com.utm.app.game.round;

import com.utm.app.game.round.dto.RoundLoaderDTO;

public abstract interface RoundLoader {

  static enum RoundDataNotation {
    ROUND_TIME_NOTATION("RoundTime");

    private String notation;

    private RoundDataNotation(String notation){
      this.notation = notation;
    }

    public String getNotation() {
      return notation;
    }

  }

  RoundLoaderDTO loadRoundObjectNotations(final int currentRound);

}