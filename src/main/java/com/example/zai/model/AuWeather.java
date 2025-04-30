package com.example.zai.model;

import org.springframework.stereotype.Component;

@Component
public class AuWeather {
	
	private int wind_speed;
	private int temperate_degrees;
	public int getWind_speed() {
		return wind_speed;
	}
	public void setWind_speed(int wind_speed) {
		this.wind_speed = wind_speed;
	}
	public int getTemperate_degrees() {
		return temperate_degrees;
	}
	public void setTemperate_degrees(int temperate_degrees) {
		this.temperate_degrees = temperate_degrees;
	}

}
