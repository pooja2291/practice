package com.example.zai.serviceimpl;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.zai.model.AuWeather;
import com.example.zai.rest.DeserializeJson;
import com.example.zai.service.AuWeatherService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AuWeatherServiceImpl implements AuWeatherService {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	DeserializeJson deserialize;

	@Override
	@CircuitBreaker(name = "weather", fallbackMethod = "fallbackWeatherCall")
	
	public AuWeather getWeather(String countryName) {
		Boolean isfallback = false;
		String url = "https://api.weatherstack.com/current?access_key=b7602462d6e7d82b7bf2364a3ee1fc19&query=Melbourne";

		HttpHeaders httpHeaders = new HttpHeaders();

		// set the Accept-header as APPLICATION_JSON to get the
		// content in JSON format
		httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

		// building a HttpEntity using HttpHeaders to
		// customize the request
		HttpEntity<String> entity = new HttpEntity<>(httpHeaders);

		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
		String responseCode = response.getStatusCode().toString();
		log.info("response code is --->" + responseCode);

		return deserialize.setResponse(response.getBody(), isfallback);

	}

	public AuWeather fallbackWeatherCall(Exception e) {

		Boolean isfallback = true;

		String url = "http://api.openweathermap.org/data/2.5/weather?q=melbourne,AU&appid=2326504fb9b100bee21400190e4dbe6d";

		HttpHeaders httpHeaders = new HttpHeaders();

		// set the Accept-header as APPLICATION_JSON to get the
		// content in JSON format
		httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

		// building a HttpEntity using HttpHeaders to
		// customize the request
		HttpEntity<String> entity = new HttpEntity<>(httpHeaders);

		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
		String responseCode = response.getStatusCode().toString();
		log.info("response code is --->" + responseCode);

		return deserialize.setResponse(response.getBody(), isfallback);
	}
}
