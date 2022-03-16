package com.example.demo.eventhandling;

import com.example.demo.eventhandling.NeedEventHandling.EventType;
import com.example.demo.eventhandling.persistence.User;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class EventAdvisor {

  @Autowired
  ApplicationEventPublisher eventPublisher;

  @AfterReturning(value = "@annotation(com.example.demo.eventhandling.NeedEventHandling)", returning = "retValue")
  public void afterReturning(JoinPoint joinPoint, User retValue) throws Throwable {
    MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
    NeedEventHandling eventInfo = methodSignature.getMethod()
        .getAnnotation(NeedEventHandling.class);
    log.info("after returning : {}", retValue.toString());
    UserEvent event;
    if (EventType.CREATE == eventInfo.eventType()) {
      event = new UserEvent(retValue, UserEventType.REGISTERED);
    } else {
      event = new UserEvent(retValue, UserEventType.VISITED);
    }
    eventPublisher.publishEvent(event);
  }

  @Getter
  public static class UserEvent {

    private final User user;
    private final UserEventType userEventType;

    public UserEvent(User user,
        UserEventType userEventType) {
      this.userEventType = userEventType;
      this.user = user;
    }
  }

  enum UserEventType {
    REGISTERED, VISITED
  }
}
