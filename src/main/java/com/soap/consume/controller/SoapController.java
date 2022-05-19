package com.soap.consume.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.soap.consume.client.SoapClient;
import com.soap.consume.stub.StoragePlanMessage;
import com.soap.consume.stub.StoragePlanMessageResponse;


@RestController
@RequestMapping("ws-consume")
public class SoapController {
	private static Logger logger = LogManager.getLogger(SoapController.class);
	public LocalDate localDate = LocalDate.now();
	
	@Autowired
	public SoapClient client;
	
	@RequestMapping("/")
	public @ResponseBody String greeting() {
		return "Hello. Welcome message from Microservice";
	}
	
	@GetMapping(value = "/spm1")
	public String getStoragePlanMessageResponsePlain(){
		StoragePlanMessageResponse response = new StoragePlanMessageResponse();
		/*
		 * For testing. Production Delete 
		 * */
		return "Welcome. Get call without Body message";
	}
	
	@PostMapping(value = "/spm1")
	public String postStoragePlanMessageResponsePlain(){
		StoragePlanMessageResponse response = new StoragePlanMessageResponse();
		/*
		 * For testing. Production Delete 
		 * */
		return "Welcome. Post call without Body message";
	}
	
	@GetMapping(value = "/spm", produces=MediaType.APPLICATION_XML_VALUE)
	public StoragePlanMessageResponse getStoragePlanMessageResponse(@RequestBody String request){
		StoragePlanMessage message = new StoragePlanMessage();
		StoragePlanMessageResponse response = new StoragePlanMessageResponse();

		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(StoragePlanMessage.class);
		    InputStream soap = new ByteArrayInputStream(request.getBytes());
		    message=(StoragePlanMessage) jaxbUnmarshaller(jaxbContext,soap);
		    
		    response = client.getStoragePlanMessageResponse(message);
		} catch (JAXBException | SOAPException e) {
			logger.error("JAXBException | SOAPException ="+e.getMessage(), e);
			e.printStackTrace();
		} catch (IOException e) {
			logger.error("IOException ="+e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error("Exception ="+e.getMessage(), e);
			e.printStackTrace();
		}

		return response;
	}
	
	@PostMapping(value = "/spm", produces=MediaType.APPLICATION_XML_VALUE)
	public StoragePlanMessageResponse getStoragePlanMessageResponse2(@RequestBody String request){
		StoragePlanMessage message = new StoragePlanMessage();
		StoragePlanMessageResponse response = new StoragePlanMessageResponse();

		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(StoragePlanMessage.class);
		    InputStream soap = new ByteArrayInputStream(request.getBytes());
		    message=(StoragePlanMessage) jaxbUnmarshaller(jaxbContext,soap);
		    
		    response = client.getStoragePlanMessageResponse(message);
		} catch (JAXBException | SOAPException e) {
			logger.error("JAXBException | SOAPException ="+e.getMessage(), e);
			e.printStackTrace();
		} catch (IOException e) {
			logger.error("IOException ="+e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error("Exception ="+e.getMessage(), e);
			e.printStackTrace();
		}

		return response;
	}
	
	public Object jaxbUnmarshaller(JAXBContext jaxbContext, InputStream soap) throws JAXBException, SOAPException, IOException{
        SOAPMessage message = MessageFactory.newInstance().createMessage(null, soap);
    	Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
    	
	    return jaxbUnmarshaller.unmarshal(message.getSOAPBody().extractContentAsDocument());
	}
	
	/*
	@PostMapping(value = "/spm", produces=MediaType.APPLICATION_XML_VALUE)
	public StoragePlanMessageResponse getStoragePlanMessageResponse(@RequestBody String request){
		StoragePlanMessageResponse response = new StoragePlanMessageResponse();

		try {
			response = client.getStoragePlanMessageResponse(unmarshall(request));
		} catch (JAXBException | SOAPException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return response;
	}
	
	public StoragePlanMessage unmarshall(String request) throws JAXBException, SOAPException, IOException{
	    JAXBContext jaxbContext = JAXBContext.newInstance(StoragePlanMessage.class);
	    InputStream is = new ByteArrayInputStream(request.getBytes());
        SOAPMessage message = MessageFactory.newInstance().createMessage(null, is);
        
    	Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
    	StoragePlanMessage storagePlanMessage = (StoragePlanMessage) jaxbUnmarshaller.unmarshal(message.getSOAPBody().extractContentAsDocument());
	    return storagePlanMessage;
	}
	*/
}
