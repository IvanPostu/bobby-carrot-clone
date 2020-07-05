package com.utm.app.configurators;


import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.reflect.Field;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.utm.core.ApplicationContext;
import com.utm.core.InjectProperty;
import com.utm.core.ObjectConfigurator;

public class InjectPropertyAnnotationObjectConfigurator implements ObjectConfigurator{

  private Map<String, String> propertiesMap;

  public InjectPropertyAnnotationObjectConfigurator() {
    String path = ClassLoader.getSystemClassLoader()
      .getResource("application.properties").getPath();
    
      Stream<String> lines = null;

    try(var buffer = new BufferedReader(new FileReader(path))) {
      
      lines = buffer.lines();

      propertiesMap = lines.map(line -> line.split("="))
        .collect(Collectors.toMap(arr -> arr[0], arr -> arr[1]));
    
      } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  public void configure(Object t, ApplicationContext context) {
    Class<?> implClass = t.getClass();

    for (Field field : implClass.getDeclaredFields()) {
      InjectProperty annotation = field.getAnnotation(InjectProperty.class);

      if (annotation != null) {
        String value;
        if (annotation.value().isEmpty()) {
          value = propertiesMap.get(field.getName());
        } else {
          value = propertiesMap.get(annotation.value());
        }
        field.setAccessible(true);

        try {
          field.set(t, value);
        } catch (IllegalArgumentException | IllegalAccessException e) {
          e.printStackTrace();
        }
      }

    }

  }
  
}