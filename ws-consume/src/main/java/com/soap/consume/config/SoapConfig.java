package com.soap.consume.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import com.soap.consume.client.SoapClient;

@Configuration
public class SoapConfig {
	
	@Value("${soap.server.url}")
	String URL;
	
	@Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("com.soap.consume.stub");
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
}
