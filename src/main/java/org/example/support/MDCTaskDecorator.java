package org.example.support;

import java.util.Map;
import org.slf4j.MDC;
import org.springframework.core.task.TaskDecorator;
import org.springframework.lang.NonNull;

public class MDCTaskDecorator implements TaskDecorator {

  @Override
  public Runnable decorate(final @NonNull Runnable task) {
    Map<String, String> contextMap = MDC.getCopyOfContextMap();

    return () -> {
      try {
        MDC.setContextMap(contextMap);
        task.run();
      } finally {
        MDC.clear();
      }
    };
  }
}
