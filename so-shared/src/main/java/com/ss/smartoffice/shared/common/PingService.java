package com.ss.smartoffice.shared.common;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PingService {
	@GetMapping("/")
	public Object ping() {
		return "service works";
	}
	
}
