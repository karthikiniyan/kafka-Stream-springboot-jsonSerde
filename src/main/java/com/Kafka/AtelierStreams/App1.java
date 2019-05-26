package com.Kafka.AtelierStreams;

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


@Component
public class App1 {
  @SuppressWarnings("unchecked")
  @Bean("app1StreamTopology")
  public void startProcessing(@Qualifier("app1StreamBuilder") StreamsBuilder builder) {
	  
	  final Serde<String> stringSerde = Serdes.String();
      System.out.println("insided startprocessing");
		
		final Serializer<Weather> volvoSerializer = new JsonSerializer<Weather>();
		final Deserializer<Weather> volvoDeserializer = new JsonDeserializer<Weather>();
		
		final Serde<Weather> countryMessageSerd = Serdes.serdeFrom(volvoSerializer, volvoDeserializer);
		
		
	  
	  final KStream<String,Weather > source = builder.stream("Streams_input", Consumed.with(stringSerde, countryMessageSerd));
	  
	  KTable<String,WeatherMetrics> ktab = source.groupByKey().aggregate( 
			  new Initializer<WeatherMetrics>() {

				@Override
				public WeatherMetrics apply() {
					// TODO Auto-generated method stub
					WeatherMetrics wm = new WeatherMetrics();
					return wm;
				} },new Aggregator<String ,Weather,WeatherMetrics>() {

					@Override
					public WeatherMetrics apply(String key, Weather value, WeatherMetrics aggregate) {
						// TODO Auto-generated method stub
						System.out.println("Heloo");
						return aggregate;
					}
					
				});
	  
	  

		/*
		 * final KStream<String, Long> toSquare = builder.stream("toSquare",
		 * Consumed.with(Serdes.String(), Serdes.Long())); toSquare.map((key, value) ->
		 * { // do something with each msg, square the values in our case return
		 * KeyValue.pair(key, value * value); }).to("squared",
		 * Produced.with(Serdes.String(), Serdes.Long())); // send downstream to another
		 * topic
		 */
    
  }

}