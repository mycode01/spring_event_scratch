package com.example.demo.eventhandling;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Inherited
@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = ElementType.METHOD)
public @interface NeedEventHandling {
  EventType eventType();

  enum EventType {
    CREATE, READ, UPDATE
  }
}
