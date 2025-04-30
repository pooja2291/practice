package com.example.zai.rest;

import org.json.JSONObject;
import org.springframework.stereotype.Component;

import com.example.zai.model.AuWeather;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class DeserializeJson {

	public AuWeather setResponse(String apiResponse,boolean isFallback) {
		
		AuWeather weather=null;
		if(isFallback) {
			
		//we can add more attributes value here to add more and set them for response
			 JSONObject jsonObj = new JSONObject(apiResponse);
		        double wind = jsonObj.getJSONObject("wind").getInt("speed");
		        double temperature = jsonObj.getJSONObject("main").getInt("temp");
		       
		      //converting kelvin to celsius  
		        double temp = temperature - 273.15;
		        int tempDegree = (int)Math.round(temp);
		      //converting m/s to km/h  
		        int windKmh=(int) (3.6 * wind); 
		        
		        weather= new AuWeather();
		        weather.setWind_speed(windKmh);
		        weather.setTemperate_degrees(tempDegree);
		       System.out.println(windKmh);
		       System.out.println(tempDegree);
			
		}
		else {
           JSONObject jsonObj = new JSONObject(apiResponse);
	        int wind = jsonObj.getJSONObject("current").getInt("wind_speed");
	        int temperature = jsonObj.getJSONObject("current").getInt("temperature");
	         weather= new AuWeather();
	        weather.setWind_speed(wind);
	        weather.setTemperate_degrees(temperature);
	       System.out.println(wind);
	       System.out.println(temperature);
		  
		}
		return weather;

	}
}
