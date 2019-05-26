package com.kafka.AtelierStreams;

import java.util.Map;

import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.serialization.Serializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

public class MyOwnSerde implements Serde<WeatherMetrics>{
	
	private JsonSerializer<WeatherMetrics> metricser = new JsonSerializer<WeatherMetrics>();
	private JsonDeserializer<WeatherMetrics> metricdeser = new JsonDeserializer<WeatherMetrics>();
	
	
	@Override
	public void configure(Map configs, boolean isKey) {
		metricser.configure(configs, isKey);
		metricdeser.configure(configs, isKey);
		
	}

	@Override
	public void close() {
		metricser.close();
		metricdeser.close();
		
	}

	@Override
	public Serializer serializer() {
		return metricser;
	}

	@Override
	public Deserializer deserializer() {
		return metricdeser;
	}

}
