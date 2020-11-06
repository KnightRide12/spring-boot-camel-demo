package io.fabric8.quickstarts.camel;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.hl7.HL7;
import org.apache.camel.component.hl7.HL7MLLPNettyDecoderFactory;
import org.apache.camel.component.hl7.HL7MLLPNettyEncoderFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class CamelArtemisRouteBuilder extends RouteBuilder {

	//@Autowired
	//JmsTemplate jmsTemplate;
	
	//@Value("${jms.queue.destination}")
	//String destinationQueue;
	
	  @Bean private HL7MLLPNettyEncoderFactory hl7Encoder() {
	      HL7MLLPNettyEncoderFactory encoder = new HL7MLLPNettyEncoderFactory();
	      encoder.setCharset("iso-8859-1"); //encoder.setConvertLFtoCR(true); 
	      return encoder;
	  }
	  
	  @Bean private HL7MLLPNettyDecoderFactory hl7Decoder() {
	      HL7MLLPNettyDecoderFactory decoder = new HL7MLLPNettyDecoderFactory();
	      decoder.setCharset("iso-8859-1"); 
	      return decoder;
	  }
	 
	  
	
	@Override
	public void configure() throws Exception {
		//from("netty4:tcp://0.0.0.0:3280?sync=true&decoder=#hl7Decoder&encoder=#hl7Encoder")
    	//  .process(new Processor() {

		//	@Override
		//	public void process(Exchange arg0) throws Exception {
		//		log.info("Received: " + arg0.getIn().getBody(String.class));
		//	}
    	//  })
		//.convertBodyTo(String.class)
    	from("timer://t1?period=10s")
		.to("jms:queue:demoQueue")
        .log("Delivered to jms:queue:demoQueue");
        //.transform(HL7.ack());
		
		/**
		from("timer://h2?period=10s")
		.transform(constant("Hello2"))
          .to("jms:topic:demoTopic")
          .log("Delivered to jms:topic:demoTopic");
		from("timer://h3?period=10s")
		  .transform(constant("Hello3"))
    	  //.convertBodyTo(String.class)
    	  .to("jms:queue:demoTopic")
          .log("Delivered to jms:queue:demoTopic");
		from("timer://h4?period=10s")
          //.transform(HL7.ack());
		  .transform(constant("Hello4"))
          .to("jms:topic:demoQueue")
          .log("Delivered to jms:topic:demoQueue");
		from("timer://h5?period=10s")
		  .transform(constant("Hello5"))
          .to("jms:topic:demoTopic.demoQueue")
          .log("Delivered to jms:topic:demoTopic.demoQueue");
		from("timer://h6?period=10s")
		  .transform(constant("Hello6"))
          .to("jms:queue:demoTopic.demoQueue")
          .log("Delivered to jms:queue:demoTopic.demoQueue");
        */

	}
}
