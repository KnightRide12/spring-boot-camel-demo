package io.fabric8.quickstarts.camel;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.stereotype.Component;

@Component
public class CamelRouteRestProducer extends RouteBuilder {
	
	@Override
	public void configure() throws Exception {
		restConfiguration("servlet").host("localhost").port(8080).bindingMode(RestBindingMode.auto);
		
		rest("/messages")
		    .post().route()
		    .log("Received message: ${body}")
		    .multicast()
		    .inOut("jms:queue:demoQueue::restQueue")
		    .log("Delivered to restQueue")
		    .inOnly("jms:queue:demoQueue::fuseQueue")
		    .log("Delivered to fuseQueue");
	}

}
