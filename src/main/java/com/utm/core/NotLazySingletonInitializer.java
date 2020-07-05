package com.utm.core;

import java.util.Set;

public class NotLazySingletonInitializer {
  private ApplicationContext context;

  public NotLazySingletonInitializer(ApplicationContext context) {
    this.context = context;
  }

  public void initializeNotLazySingletons(){

    Set<Class<?>> singletonsClasses = context.getConfig()
      .getScanner()
      .getTypesAnnotatedWith(Singleton.class);

    for(Class<?> cl : singletonsClasses){
      Singleton singleton = cl.getAnnotation(Singleton.class);
      if(!singleton.lazy()){
        /**
         * Application context by default hashes this singleton object.
         */
        context.getObject(cl);
      }
    }
  }


}