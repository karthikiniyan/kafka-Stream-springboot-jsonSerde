package com.kafka.AtelierStreams;

import java.util.HashMap;

import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.serialization.Serializer;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.Aggregator;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.Initializer;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KTable;
import org.apache.kafka.streams.kstream.Produced;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.springframework.stereotype.Component;

import com.kafka.producer.service.Weather;




@Component
public class App1 {
  @SuppressWarnings("unchecked")
  @Bean("app1StreamTopology")
  public KStream<String, WeatherMetrics> startProcessing(@Qualifier("app1StreamBuilder") StreamsConfig sc) {
	  
	  StreamsBuilder builder = new StreamsBuilder();
	  final Serde<String> stringSerde = Serdes.String();
      System.out.println("insided startprocessing");
		
		
		
		final JsonSerializer<Weather> volvoSerializer = new JsonSerializer<Weather>();
		final JsonDeserializer<Weather> volvoDeserializer = new JsonDeserializer<Weather>();
		volvoDeserializer.addTrustedPackages("*");
		final Serde<Weather> countryMessageSerd = Serdes.serdeFrom(volvoSerializer, volvoDeserializer);
		
		
		
		final JsonSerializer<WeatherMetrics> metricser = new JsonSerializer<WeatherMetrics>();
		final JsonDeserializer<WeatherMetrics> metricdeser = new JsonDeserializer<WeatherMetrics>();
		
		final Serde<WeatherMetrics> mertricserde = Serdes.serdeFrom(metricser, metricdeser);
		
	  
	  final KStream<String,Weather > source = builder.stream("Streams_input", Consumed.with(stringSerde, countryMessageSerd));
	
	  
	  
	  KTable<String,WeatherMetrics> ktab = source.groupByKey().aggregate( 
			  new Initializer<WeatherMetrics>() {

				@Override
				public WeatherMetrics apply() {
					// TODO Auto-generated method stub
					WeatherMetrics wm = new WeatherMetrics();
					wm.setMaxTemp(0d);
					
					return wm;
				} },new Aggregator<String ,Weather,WeatherMetrics>() {

					@Override
					public WeatherMetrics apply(String key, Weather value, WeatherMetrics aggregate) {
						// TODO Auto-generated method stub
						System.out.println(  value.toString());
						//aggregate.setCity(value.getCity());
						aggregate.setCount(1 + aggregate.getCount());
						
						System.out.println(value.getCity());
						
						HashMap<String, CityWeather> citymap = aggregate.getMap();
						
						if(citymap.get(value.getCity()) != null) {
							
							System.out.println("key"+ value.getCity());
							
							CityWeather cw = citymap.get(value.getCity());
							
							cw.setCurrTemp(value.getTemp());
							
							if(value.getTemp() > cw.getMaxTemp()) {
								cw.setMaxTemp(value.getTemp());
							}
							
							if(value.getTemp() < cw.getMaxTemp()) {
								cw.setMinTemp(value.getTemp());
							}
							
							System.out.println(cw.toString());
							
							citymap.put(value.getCity(), cw);
							
						}else {
							
							CityWeather cw = new CityWeather();
							cw.setCity(value.getCity());
							cw.setCountry(value.getCountry());
							cw.setCurrTemp(value.getTemp());
							cw.setMaxTemp(value.getTemp());
							cw.setMinTemp(value.getTemp());
							
							System.out.println(cw.toString());
							
							citymap.put(value.getCity(), cw);
							
						}
						
						aggregate.setMap(citymap);
						//aggregate.setMaxTemp(aggregate.getMaxTemp() > value.getTemp()?aggregate.getMaxTemp():value.getTemp());
					
						
						
					
						
						return aggregate;
					}
					
				});
	  
	  KStream<String,WeatherMetrics> kreturn = ktab.toStream();
	  kreturn.to("Streams_output", Produced.with(stringSerde, mertricserde));
	  
	  KafkaStreams streams = new KafkaStreams(builder.build(), sc);

	  streams.setUncaughtExceptionHandler((Thread t, Throwable e) -> {
	        // TODO Auto-generated method stub
	        System.out.println(e.getMessage());
	    });
	    streams.start();
	  
		/*
		 * final KStream<String, Long> toSquare = builder.stream("toSquare",
		 * Consumed.with(Serdes.String(), Serdes.Long())); toSquare.map((key, value) ->
		 * { // do something with each msg, square the values in our case return
		 * KeyValue.pair(key, value * value); }).to("squared",
		 * Produced.with(Serdes.String(), Serdes.Long())); // send downstream to another
		 * topic
		 */
	    return kreturn;
    
  }

}