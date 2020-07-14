package com.utm.core;

import java.lang.reflect.Constructor;
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

  @SuppressWarnings({"unchecked"})
  private <T> T create(Class<T> implClass) throws Exception {

    /**
     * If is declared constructor with params and annotation @InjectByType
     * then use it.
     */
    Constructor<?>[] ctors = implClass.getConstructors();
    for(Constructor<?> ct : ctors){
      if(ct.isAnnotationPresent(InjectByType.class)){
        Class<?>[] paramTypes = ct.getParameterTypes();
        Object[] params = new Object[paramTypes.length];

        for (int i = 0; i < paramTypes.length; i++) {
          params[i] = context.getObject(paramTypes[i]);
        }

        return (T) ct.newInstance(params);
      }
    }

    /**
     * Or else use default constructor.
     */
    return implClass.getDeclaredConstructor().newInstance();
  }

  public <T> void configure(T t){
    configurators.forEach(objectConfigurator -> objectConfigurator.configure(t, context));
  }
}