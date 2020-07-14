package com.utm.app.game.round;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

import com.utm.app.game.round.dto.RoundLoaderDTO;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RoundLoaderFromFS implements RoundLoader {

  private final static Logger logger = LogManager.getLogger(RoundState.class);
  
  @Override
  public RoundLoaderDTO loadRoundObjectNotations(final int currentRound){
    List<String[]> round = new ArrayList<>();
    String roundfile = String.format("/rounds/round%d.txt", currentRound);
    RoundLoaderDTO roundLoaderDTO = new RoundLoaderDTO();


    try(InputStream in = this.getClass().getResourceAsStream(roundfile)) {
      BufferedReader reader = new BufferedReader(new InputStreamReader(in));
      Stream<String> lines = reader.lines();
      Iterator<String> iter = lines.iterator();

      while(iter.hasNext()){
        String s = iter.next();
        if(s.equals("[DATA]")){
          break;
        }
        String[] arr = s.split(" ");
        round.add(arr);
      }

      while(iter.hasNext()){
        String s = iter.next();
        String[] arr = s.split("=");

        if(arr[0].equals(RoundDataNotation.ROUND_TIME_NOTATION.getNotation())){
          roundLoaderDTO.setRoundTime(Integer.parseInt(arr[1]));
        }
      }

      roundLoaderDTO.setRoundObjectNotations(round);
    } catch (Exception e) {
      logger.error(e);
      logger.error("Round file is not valid!!!");
    }

    return roundLoaderDTO;
  }

}