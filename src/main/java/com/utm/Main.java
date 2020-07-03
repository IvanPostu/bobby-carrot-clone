package com.utm;


import com.utm.state.GameState;
import com.utm.ui.ApplicationUI;

public class Main 
{
  public static void main( String[] args )
  {
    ApplicationUI application = new ApplicationUI();
    application.setState(new GameState());

  }
}
