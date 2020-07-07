package com.utm.app.game;

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
  
  private List<char[]> round = new ArrayList<>();
  private float scale = 1.0f;

  @PostConstruct
  public void init(){

    try(InputStream in = this.getClass().getResourceAsStream("/rounds/round1.txt")) {
      BufferedReader reader = new BufferedReader(new InputStreamReader(in));
      Stream<String> lines = reader.lines();
      Iterator<String> iter = lines.iterator();

      while(iter.hasNext()){
        String s = iter.next();
        this.round.add(s.toCharArray());
      }

    } catch (Exception e1) {
      e1.printStackTrace();
    }
  }

  public List<char[]> currentRoundState(){
    return this.round;
  }

  public float getScale() {
    return scale;
  }

  public void setScale(float scale) {
    this.scale = scale;
  }

  public int getRoundWidth(){
    return this.round.stream()
      .mapToInt(v -> v.length)
      .max()
      .orElseThrow(()->new RuntimeException());
  }

  public int getRoundHeight(){
    return this.round.size();
  }

}