package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.service.SampleService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/samples")
@RequiredArgsConstructor
public class SampleController {

	private final SampleService service;

	@GetMapping
	public void get() {
		service.execute();
	}
}
