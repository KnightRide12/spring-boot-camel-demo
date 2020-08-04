package io.fabric8.quickstarts.camel;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class CamelArtemisRouteBuilder extends RouteBuilder {

	//@Autowired
	//JmsTemplate jmsTemplate;
	
	//@Value("${jms.queue.destination}")
	//String destinationQueue;
	
	@Override
	public void configure() throws Exception {
		from("netty4:tcp://0.0.0.0:3180?sync=false")
    	  .process(new Processor() {

			@Override
			public void process(Exchange arg0) throws Exception {
				log.info("Received: " + arg0.getIn().getBody(String.class));
			}
    	  })
          //.setBody().constant("Hello1")
    	  //.to("jms:queue:demoQueue")
          //.log("Delivered to jms:queue:demoQueue")
          //.setBody().constant("Hello2")
          //.to("jms:topic:demoTopic")
          //.log("Delivered to jms:topic:demoTopic")
          //.setBody().constant("Hello3")
          .to("jms:queue:demoTopic")
          .log("Delivered to jms:queue:demoTopic");
          //.setBody().constant("Hello4")
          //.to("jms:topic:demoQueue")
          //.log("Delivered to jms:topic:demoQueue")
          //.setBody().constant("Hello5")
          //.to("jms:topic:demoTopic.demoQueue")
          //.log("Delivered to jms:topic:demoTopic.demoQueue")
          //.setBody().constant("Hello6")
          //.to("jms:queue:demoTopic.demoQueue")
          //.log("Delivered to jms:queue:demoTopic.demoQueue");
		
		  //jmsTemplate.convertAndSend(destinationQueue, "Hello!!");
          

	}
}
