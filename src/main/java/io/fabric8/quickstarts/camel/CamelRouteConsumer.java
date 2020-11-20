package io.fabric8.quickstarts.camel;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.hl7.HL7MLLPNettyDecoderFactory;
import org.apache.camel.component.hl7.HL7MLLPNettyEncoderFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class CamelRouteConsumer extends RouteBuilder {

	/*
	 * @Bean private HL7MLLPNettyEncoderFactory hl7Encoder() {
	 * HL7MLLPNettyEncoderFactory encoder = new HL7MLLPNettyEncoderFactory();
	 * encoder.setCharset("iso-8859-1"); return encoder; }
	 * 
	 * @Bean private HL7MLLPNettyDecoderFactory hl7Decoder() {
	 * HL7MLLPNettyDecoderFactory decoder = new HL7MLLPNettyDecoderFactory();
	 * decoder.setCharset("iso-8859-1"); return decoder; }
	 */
	  
	
	@Override
	public void configure() throws Exception {
		from("jms:queue:demoQueue::demoQueue")
    	  .log("Message received from JMS demoQueue: ${body}");
		
		from("jms:queue:demoQueue::restQueue")
  	      .log("Message received from JMS restQueue: ${body}");
		
	}
}
