package com.utm.app.configurators;


import com.utm.core.InjectProperty;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.config.Configuration;


public class LoggerConfigImpl implements LoggerConfig {
  static Logger logger = LogManager.getLogger(LoggerConfigImpl.class);

  @InjectProperty("application.isdebug")
  private boolean isDebug;

  public void configureGlobalLogger(){
    LoggerContext ctx = (LoggerContext) LogManager.getContext(false);
    Configuration config = ctx.getConfiguration();
    org.apache.logging.log4j.core.config.LoggerConfig loggerConfig = config
        .getLoggerConfig(LogManager.ROOT_LOGGER_NAME);
    loggerConfig.setLevel(isDebug ? Level.DEBUG : Level.WARN);
    ctx.updateLoggers(); 

    logger.warn("Application is running in {} mode!!!", isDebug?"DEVELOPMENT":"PRODUCTION");
  }

}