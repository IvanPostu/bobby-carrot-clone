package com.utm.app;

/**
 * Immutable Point.
 */
public class Point {
  private final int x;
  private final int y;

  public Point(int x, int y){
    this.x = x;
    this.y = y;
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  @Override
  public String toString() {
    return String.format("Point { x:%s, y:%s }", this.x, this.y);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
		  return true;
    if (obj == null)
      return false;
    if (this.getClass() != obj.getClass())
      return false;

    Point point = (Point) obj;
  
    if (point.getX() != this.getX()) 
      return false;
    if (point.getY() != this.getY()) 
      return false;

	  return true;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    return x * prime + y;
  }

}