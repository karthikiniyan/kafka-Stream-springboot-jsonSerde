package com.Kafka.AtelierStreams;

import java.io.Serializable;

public class WeatherMetrics implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6872310476505347035L;
	private String country;
	private String state;
	private String city;
	private Double temp;
	
	private Double maxTemp;
	public Double getMaxTemp() {
		return maxTemp;
	}
	public void setMaxTemp(Double maxTemp) {
		this.maxTemp = maxTemp;
	}
	public Double getMinTemp() {
		return minTemp;
	}
	public void setMinTemp(Double minTemp) {
		this.minTemp = minTemp;
	}
	public Double getAvgTemp() {
		return avgTemp;
	}
	public void setAvgTemp(Double avgTemp) {
		this.avgTemp = avgTemp;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	private Double minTemp;
	private Double avgTemp;
	private int count;
	
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public Double getTemp() {
		return temp;
	}
	public void setTemp(Double temp) {
		this.temp = temp;
	}
	@Override
	public String toString() {
		return "Weather [country=" + country + ", state=" + state + ", city=" + city + ", temp=" + temp + "]";
	}
	
	

}
