package com.utm.app.game.element;

import java.util.List;
import java.util.Random;

import com.utm.app.Point;

public interface Enemy {
  
  default Point pointForMove(List<Point> possibleDirections){
    Random rand = new Random();
    int randDir = rand.nextInt(possibleDirections.size());

    return possibleDirections.get(randDir);
  }

}