package com.utm.app.state;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

import com.utm.core.PostConstruct;
import com.utm.core.Singleton;

@Singleton
public class GameRoundState {
  
  private List<String> round = new ArrayList<>();

  @PostConstruct
  public void init(){
    try {

      Stream<String> lines = null;

      try(InputStream in = this.getClass().getResourceAsStream("/testround.txt")) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        lines = reader.lines();
        Iterator<String> iter = lines.iterator();

        while(iter.hasNext()){
          String s = iter.next();
          this.round.add(s);
        }
  

      } catch (Exception e1) {
        e1.printStackTrace();
      }

    } catch (Exception e) {
      e.printStackTrace();
    }
  }



  public List<String> currentRoundState(){
    return this.round;
  }
}