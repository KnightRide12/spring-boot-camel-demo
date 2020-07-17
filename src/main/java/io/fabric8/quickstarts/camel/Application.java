/**
 *  Copyright 2005-2016 Red Hat, Inc.
 *
 *  Red Hat licenses this file to you under the Apache License, version
 *  2.0 (the "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 *  implied.  See the License for the specific language governing
 *  permissions and limitations under the License.
 */
package io.fabric8.quickstarts.camel;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

/**
 * A spring-boot application that includes a Camel route builder to setup the Camel routes
 */
@SpringBootApplication
@ImportResource({"classpath:spring/camel-context.xml"})
public class Application extends RouteBuilder {

    // must have a main method spring-boot can run
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void configure() throws Exception {
    	from("netty4-http:http://0.0.0.0:3180?sync=false")
    	//from("timer://foo?period=5000")
        //  .setBody().constant("Hello World")
    	  .process(new Processor() {

			@Override
			public void process(Exchange arg0) throws Exception {
				log.info("Received: " + arg0.getIn().getBody(String.class));
			}
    	  })
          .to("jms:queue:demoTopic.demoQueue");
	  //.log("Sent to internal service using netty tcp");
	    //  .to("netty:tcp://inbound-route-openshift.apps-crc.testing:80?sync=false");
    	//  .to("netty-http:http://inbound-route-openshift.apps-crc.testing:80?sync=false");
        //    .log(">>> ${body}");
    }
}
