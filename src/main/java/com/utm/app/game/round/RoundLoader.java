package com.utm.app.game.round;

import java.util.List;

public abstract interface RoundLoader {

  List<String[]> loadRoundObjectNotations(final int currentRound);

}