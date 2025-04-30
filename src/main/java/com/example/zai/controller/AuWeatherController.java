package com.example.zai.controller;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.zai.model.AuWeather;
import com.example.zai.service.AuWeatherService;

@RestController
@RequestMapping("/v1")
public class AuWeatherController {

	@Autowired
	AuWeatherService auService;

	@GetMapping("/weather")
	public  ResponseEntity <AuWeather> getData(@RequestParam String name) {
		AuWeather weatherDetails= auService.getWeather(name);
		 return ResponseEntity.ok()
	                .cacheControl(CacheControl.maxAge(3, TimeUnit.SECONDS)) // Cache for 3 seconds
	                .body(weatherDetails);

	}

}
