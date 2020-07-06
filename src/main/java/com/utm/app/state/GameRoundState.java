package com.utm.app.state;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.utm.Main;
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