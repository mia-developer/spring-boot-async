package org.example.service;

import static java.lang.Thread.sleep;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class SampleService {

	private final Executor taskExecutor;

	public void execute() {
		CompletableFuture.runAsync(this::logging, taskExecutor);
	}

	private void logging()  {
		try {
			for (int i=0; i<10000; i++){
				log.info(String.valueOf(i));
				sleep(1);
			}
		} catch (InterruptedException e){

		}
	}
}
