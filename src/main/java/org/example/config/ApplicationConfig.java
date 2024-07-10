package org.example.config;

import java.util.concurrent.Executor;
import org.example.support.MDCTaskDecorator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.security.concurrent.DelegatingSecurityContextExecutor;

@Configuration
public class ApplicationConfig {

	private static final int AWAIT_TERMINATION_SECONDS = 90;

	@Bean
	public ThreadPoolTaskExecutor threadPoolTaskExecutor() {
		ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();

		threadPoolTaskExecutor.setTaskDecorator(new MDCTaskDecorator());
		threadPoolTaskExecutor.setWaitForTasksToCompleteOnShutdown(true);
		threadPoolTaskExecutor.setAwaitTerminationSeconds(AWAIT_TERMINATION_SECONDS);
		threadPoolTaskExecutor.initialize();

		return threadPoolTaskExecutor;
	}

	@Bean
	public Executor taskExecutor(final ThreadPoolTaskExecutor threadPoolTaskExecutor) {
		return new DelegatingSecurityContextExecutor(threadPoolTaskExecutor);
	}
}
