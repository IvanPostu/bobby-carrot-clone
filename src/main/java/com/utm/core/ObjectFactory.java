package com.utm.core;

import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;


public class ObjectFactory {
  
  private final List<ObjectConfigurator> configurators = new LinkedList<>();
  private final ApplicationContext context;

  public ObjectFactory(ApplicationContext context) {

    this.context = context;

    for (Class<? extends ObjectConfigurator> aClass : context.getConfig().getScanner()
        .getSubTypesOf(ObjectConfigurator.class)) {

      try {
        configurators.add(aClass.getDeclaredConstructor().newInstance());
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }

  public <T> T createObject(Class<T> implClass) {

    T t = null;

    try {
      t = create(implClass);
    } catch (Exception e) {
      e.printStackTrace();
    }



    return t;
  }

  public void callInvokeInitChainOnInheritanceHierarchy(Class<?> implClass, Object t) {

    List<Class<?>> inheritanceHierarchy = Utils.getClassInheritanceHierarchy(implClass);
    Iterator<Class<?>> linkedListIterator = inheritanceHierarchy.iterator();

    while(linkedListIterator.hasNext()){
      Class<?> temp = linkedListIterator.next();
      
      for (Method method : temp.getDeclaredMethods()) {
        if (method.isAnnotationPresent(PostConstruct.class)) {
          try {
            method.setAccessible(true);
            method.invoke(t);
          } catch (Exception e) {
            e.printStackTrace();
          }
        }
      }
    }

  }

  private <T> T create(Class<T> implClass) throws Exception {
    return implClass.getDeclaredConstructor().newInstance();
  }

  public <T> void configure(T t){
    configurators.forEach(objectConfigurator -> objectConfigurator.configure(t, context));
  }
}