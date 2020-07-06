package com.utm.app.configurators;

import java.lang.reflect.Field;

import com.utm.core.ApplicationContext;
import com.utm.core.InjectByType;
import com.utm.core.ObjectConfigurator;

public class InjectByTypeAnnotationObjectConfigurator implements ObjectConfigurator {

  @Override
  public void configure(Object t, ApplicationContext context) {

    for (Field field : t.getClass().getDeclaredFields()) {
      

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