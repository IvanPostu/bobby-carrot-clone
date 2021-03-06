package com.utm.app.configurators;

import java.util.Map;
import java.util.Set;

import com.utm.core.Config;

import org.reflections.Reflections;


@SuppressWarnings({"unchecked", "rawtypes"})
public class JavaConfig implements Config {

  private Reflections scanner;
  private Map<Class, Class> ifc2ImplClass;

  public JavaConfig(String packageToScan, Map<Class, Class> ifc2ImplClass) {
    this.scanner = new Reflections(packageToScan);
    this.ifc2ImplClass = ifc2ImplClass;
  }

  @Override
  public <T> Class<? extends T> getImplClass(Class<T> ifc) {
    return ifc2ImplClass.computeIfAbsent(ifc, aClass -> {
      Set<Class<? extends T>> classes = scanner.getSubTypesOf(ifc);
      if(classes.size() != 1){
        throw new RuntimeException(ifc + " has 0 or more than one impl please update your config.");
      }
      return classes.iterator().next();
    });
  }

  @Override
  public Reflections getScanner(){
    return scanner;
  }
  
}