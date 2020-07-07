package com.utm.app;

public class Point {
  private int x;
  private int y;

  public Point(int x, int y){
    this.x = x;
    this.y = y;
  }

  public void setX(int x) {
    this.x = x;
  }

  public void setY(int y) {
    this.y = y;
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
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
}