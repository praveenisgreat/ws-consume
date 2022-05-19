package com.soap.produce.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.web.client.RestTemplate;

import com.soap.produce.client.SoapClient;

@Configuration
public class SoapConfig {
	
	@Value("${soap.server.url}")
	String URL;
	
	/*
	@Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("com.soap.produce.stub");
        return marshaller;
    }
	*/
	
	@Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("com.soap.produce.stub");
        return marshaller;
    }
	
	@Bean
    public SoapClient soapClient(Jaxb2Marshaller marshaller) {
		SoapClient client = new SoapClient();
        client.setDefaultUri(URL);
        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);
        return client;
    }
	
	@Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
