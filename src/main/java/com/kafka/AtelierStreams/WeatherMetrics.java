package com.kafka.AtelierStreams;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map.Entry;


public class WeatherMetrics implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6872310476505347035L;

	private String id;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
		
	private HashMap<String, CityWeather> map = new HashMap<String, CityWeather>();
	
	private int count;
	
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public HashMap<String, CityWeather> getMap() {
		return map;
	}
	public void setMap(HashMap<String, CityWeather> map) {
		this.map = map;
	}
	
	@Override
	public String toString() {
		
		for(Entry<String, CityWeather> cw : this.map.entrySet()){
			
			System.out.println("Key : " +cw.getKey() +", "+ "value :" + cw.getValue().toString());
			
		}
		return "WeatherMetrics";
	}
	
	

}
