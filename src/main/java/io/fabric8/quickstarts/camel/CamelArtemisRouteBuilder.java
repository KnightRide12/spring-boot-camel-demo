package io.fabric8.quickstarts.camel;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class CamelArtemisRouteBuilder extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		from("netty4-http:http://0.0.0.0:3180?sync=false")
    	  .process(new Processor() {

			@Override
			public void process(Exchange arg0) throws Exception {
				log.info("Received: " + arg0.getIn().getBody(String.class));
			}
    	  })
          .to("jms:queue:demoTopic.demoQueue");

	}
}
