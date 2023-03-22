package com.march.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppController {
	@GetMapping("/hi")
	public String getMsg(@RequestParam String name) {
		System.out.println(name);
		return "hi "+name;
	}
}
