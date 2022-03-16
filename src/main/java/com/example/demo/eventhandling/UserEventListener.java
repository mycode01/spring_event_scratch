package com.example.demo.eventhandling;

import com.example.demo.eventhandling.EventAdvisor.UserEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class UserEventListener {

  @Async
  @EventListener
  public void log(UserEvent userEvent) {
    log.info("in listener.log() {}, {} ", userEvent.getUserEventType().name(),
        userEvent.getUser().toString());
  }

  @Async
  @EventListener
  public void log2(UserEvent userEvent) {
    log.info("in listener.log2() {}, {} ", userEvent.getUserEventType().name(),
        userEvent.getUser().toString());
  }
}
