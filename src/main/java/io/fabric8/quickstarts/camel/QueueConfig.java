package io.fabric8.quickstarts.camel;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "queue")
public class QueueConfig {

	private String demoQueue;
	
	public String getDemoQueue() {
		return this.demoQueue;
	}
	
	public void setDemoQueue(String queueName) {
		this.demoQueue = queueName;
	}
}
