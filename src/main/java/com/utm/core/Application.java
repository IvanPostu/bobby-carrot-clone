package com.utm.core;

import java.util.Map;

import com.utm.app.configurators.JavaConfig;

public class Application {

  @SuppressWarnings("rawtypes")
  public static ApplicationContext run(String packageToScan, Map<Class, Class> ifc2ImplClass){
    JavaConfig config = new JavaConfig(packageToScan, ifc2ImplClass);
    ApplicationContext context = new ApplicationContext(config);
    ObjectFactory objectFactory = new ObjectFactory(context);
    
    context.setObjectFactory(objectFactory);

    var notLazySingletonInitializer = new NotLazySingletonInitializer(context);
    notLazySingletonInitializer.initializeNotLazySingletons();

    
    
    return context;
  }
 
}