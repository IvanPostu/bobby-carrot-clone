package com.utm.core;

import java.util.LinkedList;

public class Utils {

  /**
   * For class return inheritance hierarchy.
   * 
   * Example:
   * [Object, BaseClassExample1, BaseClassExample2, cls]
   * 
   * @param someClass
   * @return
   */
  public static LinkedList<Class<?>> getClassInheritanceHierarchy(Class<?> cls){
    LinkedList<Class<?>> result = new LinkedList<>();
    result.addFirst(cls);

    Class<?> tempClass = cls;
    while(tempClass.getSuperclass() != null){
      result.addFirst(tempClass.getSuperclass());
      tempClass = tempClass.getSuperclass();
    }

    return result;
  }
  
}