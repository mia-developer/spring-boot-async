package org.example.config;

import java.util.concurrent.Executor;
import org.example.support.MDCTaskDecorator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.security.concurrent.DelegatingSecurityContextExecutor;

@Configuration
public class ApplicationConfig {

	@Bean
	public Executor threadPoolTaskExecutor(
		 @Value("${server.task-executor.thread-pool-size.core}") final Integer corePoolSize,
		 @Value("${server.task-executor.thread-pool-size.max}") final Integer maxPoolSize,
		 @Value("${server.task-executor.await-termination-seconds}")
		 final Integer awaitTerminationSeconds) {

		ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
		threadPoolTaskExecutor.setCorePoolSize(corePoolSize);
		threadPoolTaskExecutor.setMaxPoolSize(maxPoolSize);
		threadPoolTaskExecutor.setTaskDecorator(new MDCTaskDecorator());
		threadPoolTaskExecutor.setWaitForTasksToCompleteOnShutdown(true);
		threadPoolTaskExecutor.setAwaitTerminationSeconds(awaitTerminationSeconds);
		threadPoolTaskExecutor.initialize();

		return new DelegatingSecurityContextExecutor(threadPoolTaskExecutor);
	}

	@Bean
	public Executor taskExecutor(final ThreadPoolTaskExecutor threadPoolTaskExecutor) {
		return new DelegatingSecurityContextExecutor(threadPoolTaskExecutor);
	}
}
