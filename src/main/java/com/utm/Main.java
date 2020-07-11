package com.utm;

import java.util.HashMap;
import java.util.Map;

import com.utm.app.game.round.RoundLoader;
import com.utm.app.game.round.RoundLoaderFromFS;
import com.utm.core.Application;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
  static Logger logger = LogManager.getLogger(Main.class);

  public static void main( String ...args ){

    /**
     * Если интерфейс имеет больше одной имплементации,
     * нужно в мэпу передать как ключ интерфейс 
     * и как значение нужную реализацию. 
     */
    Application.run(
      "com.utm.app", 
      new HashMap<>(Map.of(RoundLoader.class, RoundLoaderFromFS.class)) 
    );

    logger.debug("This Will Be Printed On Debug");
    logger.info("This Will Be Printed On Info");
    logger.warn("This Will Be Printed On Warn");
    logger.error("This Will Be Printed On Error");
    logger.fatal("This Will Be Printed On Fatal");


  }
}
