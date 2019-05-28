package com.kafka.AtelierStreams;

public class CityWeather {
	
	private String country;
	private String state;
	private String city;
	private double currTemp;
	private double maxTemp;
	private double minTemp;
	private double avgTemp;
	
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
	public double getCurrTemp() {
		return currTemp;
	}
	public void setCurrTemp(double currTemp) {
		this.currTemp = currTemp;
	}
	public double getMaxTemp() {
		return maxTemp;
	}
	public void setMaxTemp(double maxTemp) {
		this.maxTemp = maxTemp;
	}
	public double getMinTemp() {
		return minTemp;
	}
	public void setMinTemp(double minTemp) {
		this.minTemp = minTemp;
	}
	public double getAvgTemp() {
		return avgTemp;
	}
	public void setAvgTemp(double avgTemp) {
		this.avgTemp = avgTemp;
	}
	
	@Override
	public String toString() {
		return "CityMetrics [country=" + country + ", state=" + state + ", city=" + city + ", temp=" + currTemp +", Maxtemp="+maxTemp +", minTemp="+ minTemp + "]";
	}

}
