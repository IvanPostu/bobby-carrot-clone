package com.utm;

import java.util.HashMap;
import java.util.Map;

import com.utm.app.game.round.RoundLoader;
import com.utm.app.game.round.RoundLoaderFromFS;
import com.utm.core.Application;

public class Main 
{
  public static void main( String ...args )
  {
    /**
     * Если интерфейс имеет больше одной имплементации,
     * нужно в мэпу передать как ключ интерфейс 
     * и как значение нужную реализацию. 
     */
    Application.run(
      "com.utm.app", 
      new HashMap<>(Map.of(RoundLoader.class, RoundLoaderFromFS.class)) 
    );

  }
}
