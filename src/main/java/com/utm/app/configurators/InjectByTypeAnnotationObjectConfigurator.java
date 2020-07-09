package com.utm.app.configurators;

import java.lang.reflect.Field;
import java.util.List;

import com.utm.core.ApplicationContext;
import com.utm.core.InjectByType;
import com.utm.core.ObjectConfigurator;
import com.utm.core.Utils;

public class InjectByTypeAnnotationObjectConfigurator implements ObjectConfigurator {

  @Override
  public void configure(Object t, ApplicationContext context) {

    List<Class<?>> inheritanceHierarchy = Utils.getClassInheritanceHierarchy(t.getClass());

    for(Class<?> cls : inheritanceHierarchy){
      for (Field field : cls.getDeclaredFields()) {
        if (field.isAnnotationPresent(InjectByType.class)) {
          field.setAccessible(true);
          Object object = null;
  
          object = context.getObject(field.getType());
          
          try {
            field.set(t, object);
          } catch (Exception e) {
            e.printStackTrace();
          }
        }
      }
    }
    

  }
  
}