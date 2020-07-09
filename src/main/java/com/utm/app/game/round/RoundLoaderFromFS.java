package com.utm.app.game.round;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

public class RoundLoaderFromFS implements RoundLoader {
  
  @Override
  public List<String[]> loadRoundObjectNotations(final int currentRound){
    
    List<String[]> round = new ArrayList<>();
    String roundfile = String.format("/rounds/round%d.txt", currentRound);


    try(InputStream in = this.getClass().getResourceAsStream(roundfile)) {
      BufferedReader reader = new BufferedReader(new InputStreamReader(in));
      Stream<String> lines = reader.lines();
      Iterator<String> iter = lines.iterator();

      while(iter.hasNext()){
        String s = iter.next();
        String[] arr = s.split(" ");
        round.add(arr);
      }

    } catch (Exception e1) {
      e1.printStackTrace();
    }

    return round;
  }

}