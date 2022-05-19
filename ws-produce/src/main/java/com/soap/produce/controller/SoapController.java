package com.soap.produce.controller;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.time.LocalDate;
import java.util.Base64;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPConstants;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.soap.produce.client.SoapClient;
import com.soap.produce.stub.ZStorageResultMessage;
import com.soap.produce.stub.ZStorageResultMessageResponse;
import com.soap.produce.stub.ZwsStSrmRequest;
import com.soap.produce.stub.ZwsStSrmResponse;



@RestController
@RequestMapping("ws-produce/v2")
public class SoapController {
	private static Logger logger = LogManager.getLogger(SoapController.class);
	public LocalDate localDate = LocalDate.now();
	public static String ENDPOINT_URL;
	public static String ENDPOINT_AUTH_USER;
	public static String ENDPOINT_AUTH_PASSWORD;
	String tokenKey = "X-Requested-With";
	String tokenValue = "X";
	public static final String SOAP11_NAMESPACE = "http://schemas.xmlsoap.org/soap/envelope";
	
	@Autowired
	public SoapClient client;

	@Autowired
	private RestTemplate restTemplate;

	@RequestMapping("/")
	public @ResponseBody String greeting() {
		return "Hello. Welcome message from Microservice";
	}

	@PostMapping(value = "/rtx/srm", produces = MediaType.APPLICATION_XML_VALUE)
	public SOAPMessage getSpmResponseRestTemplateExchange(@RequestBody String request) throws IOException, SOAPException {
		logger.info("getSpmResponseRestTemplateExchange /rtx/srm");
		logger.info("*******************************************");
		HttpEntity<String> requestEntity = new HttpEntity<String>(request,getHeaders());
		// Call SAP to get result
		ResponseEntity<String> responseEntity = restTemplate.exchange(ENDPOINT_URL, HttpMethod.POST, requestEntity,
				String.class);
		logger.info("responseEntity*******"+responseEntity.getBody());
		return toSOAPMessage(responseEntity.getBody());
	}
	
	@PostMapping(value = "/rtx/srm2", produces = MediaType.APPLICATION_XML_VALUE)
	public String convertStringtoSoap(@RequestBody String request) throws IOException, SOAPException {
		logger.info("postManTest /rtx/srm/postManTest");

		HttpEntity<String> requestEntity = new HttpEntity<String>(request,getHeaders());
		// Call SAP to get result
		ResponseEntity<String> responseEntity = restTemplate.exchange(ENDPOINT_URL, HttpMethod.POST, requestEntity,
				String.class);
		
        SOAPMessage message = null;
        String result=null;
        try {
        	MessageFactory factory = null;

            // Force the usage of specific MesasgeFactories
            if (request.indexOf(SOAP11_NAMESPACE) >= 0) {
                factory = MessageFactory
                        .newInstance(SOAPConstants.SOAP_1_1_PROTOCOL);
            } else {
                factory = MessageFactory
                        .newInstance(SOAPConstants.SOAP_1_2_PROTOCOL);
            }
            message = factory.createMessage();
            message.getSOAPPart().setContent((Source) new StreamSource(new StringReader(responseEntity.getBody())));
            message.saveChanges();
            message.writeTo(System.out);
            //To Test
            
            ByteArrayOutputStream baos = null;
            baos = new ByteArrayOutputStream();
            message.writeTo(baos); 
            result = baos.toString();
            logger.info("baos.toString()======1"+result); 

        } catch (SOAPException e) {
        	logger.error("toSOAPMessage Exception encountered: " + e);
            e.printStackTrace();
        }
 
		//return message;
        return result;
	}
	
	@PostMapping(value = "/rtx/srm/test", produces = MediaType.APPLICATION_XML_VALUE)
	public String getSpmResponseRestTemplateExchangeForPostMan(@RequestBody String request) throws IOException, SOAPException {
		logger.info("String ResponseEntity /rtx/srm");
		logger.info("*********************");
		HttpEntity<String> requestEntity = new HttpEntity<String>(request,getHeaders());
		// Call SAP to get result
		ResponseEntity<String> responseEntity = restTemplate.exchange(ENDPOINT_URL, HttpMethod.POST, requestEntity,
				String.class);
		logger.info("responseEntity*******"+responseEntity.getBody());
		
        String result=null;
        ByteArrayOutputStream baos = null;
 
        baos = new ByteArrayOutputStream();
        toSOAPMessage(responseEntity.getBody()).writeTo(baos); 
        result = baos.toString(); 
		return result;
	}
	
	/**
     * Method used to convert Strings to SOAPMessages
     * 
     * @param msgString
     * @return
     */
	public static SOAPMessage toSOAPMessage(String msgString) {

        if (msgString == null)
            return null;

        SOAPMessage message = null;
        try {
            MessageFactory factory = null;

            // Force the usage of specific MesasgeFactories
            if (msgString.indexOf(SOAP11_NAMESPACE) >= 0) {
                factory = MessageFactory
                        .newInstance(SOAPConstants.SOAP_1_1_PROTOCOL);
            } else {
                factory = MessageFactory
                        .newInstance(SOAPConstants.SOAP_1_2_PROTOCOL);
            }
            message = factory.createMessage();
            message.getSOAPPart().setContent(
                    (Source) new StreamSource(new StringReader(msgString)));
            message.saveChanges();
        } catch (SOAPException e) {
            logger.error("toSOAPMessage Exception encountered: " + e);
            e.printStackTrace();
        }
        return message;
    }
	
	public Object jaxbUnmarshaller(JAXBContext jaxbContext, InputStream soap) throws JAXBException, SOAPException, IOException{
        SOAPMessage message = MessageFactory.newInstance().createMessage(null, soap);
    	Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
    	logger.info("jaxbUnmarshaller jaxbUnmarshaller======1"+jaxbUnmarshaller);
	    return jaxbUnmarshaller.unmarshal(message.getSOAPBody().extractContentAsDocument());
	}

	private HttpHeaders getHeaders() {
		// MediaType XML
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.add("Content-Type", "text/xml");
		requestHeaders.add("Accept", "*/*");
		requestHeaders.add("User-Agent",
				"Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/59.0.3071.115 Safari/537.36");
		// Set Basic Auth
		String plainCreds = String.format("%s:%s", ENDPOINT_AUTH_USER, ENDPOINT_AUTH_PASSWORD);
		byte[] plainCredsBytes = plainCreds.getBytes();
		byte[] base64CredsBytes = Base64.getEncoder().encode(plainCredsBytes);

		String base64Creds = new String(base64CredsBytes);
		requestHeaders.add("Authorization", "Basic " + base64Creds);
		// requestHeaders.add(tokenKey, tokenValue);

		logger.info("requestHeaders-" + requestHeaders);
		return requestHeaders;
	}

	@PostMapping(value = "/rtx/srm/testAsString", produces = MediaType.APPLICATION_XML_VALUE)
	public String getSpmResponseRestTestAsString(@RequestBody String request) throws IOException, SOAPException {
		logger.info("getSpmResponseRestTemplateExchange /rtx/srm");
		String responseEntity = "<soap-env:Envelope xmlns:soap-env=\"http://schemas.xmlsoap.org/soap/envelope/\">\n"
				+ "    <soap-env:Header/>\n"
				+ "    <soap-env:Body>\n"
				+ "        <n0:ZStorageResultMessageResponse xmlns:n0=\"urn:sap-com:document:sap:soap:functions:mc-style\">\n"
				+ "            <StorageResultMsgResponse>\n"
				+ "                <MessageHeader>\n"
				+ "                    <MsgId>20210222102646</MsgId>\n"
				+ "                </MessageHeader>\n"
				+ "                <ResponseMessageHeader>\n"
				+ "                    <ResponseCode>0000</ResponseCode>\n"
				+ "                    <ResponseDescription>Success</ResponseDescription>\n"
				+ "                </ResponseMessageHeader>\n"
				+ "                <ResponseMessage/>\n"
				+ "            </StorageResultMsgResponse>\n"
				+ "        </n0:ZStorageResultMessageResponse>\n"
				+ "    </soap-env:Body>\n"
				+ "</soap-env:Envelope>";
		logger.info("responseEntity*******"+responseEntity);
		return responseEntity;
	}
	
	@PostMapping(value = "/rtx/srm/testAsSOAP", produces = MediaType.APPLICATION_XML_VALUE)
	public SOAPMessage getSpmResponseRestTestAsSoap(@RequestBody String request) throws IOException, SOAPException {
		logger.info("getSpmResponseRestTemplateExchange /rtx/srm");
		logger.info("*******************************************");
		SOAPMessage message =  null; 
		String responseEntity = "<soap-env:Envelope xmlns:soap-env=\"http://schemas.xmlsoap.org/soap/envelope/\">\n"
				+ "    <soap-env:Header/>\n"
				+ "    <soap-env:Body>\n"
				+ "        <n0:ZStorageResultMessageResponse xmlns:n0=\"urn:sap-com:document:sap:soap:functions:mc-style\">\n"
				+ "            <StorageResultMsgResponse>\n"
				+ "                <MessageHeader>\n"
				+ "                    <MsgId>20210222102646</MsgId>\n"
				+ "                </MessageHeader>\n"
				+ "                <ResponseMessageHeader>\n"
				+ "                    <ResponseCode>0000</ResponseCode>\n"
				+ "                    <ResponseDescription>Success</ResponseDescription>\n"
				+ "                </ResponseMessageHeader>\n"
				+ "                <ResponseMessage/>\n"
				+ "            </StorageResultMsgResponse>\n"
				+ "        </n0:ZStorageResultMessageResponse>\n"
				+ "    </soap-env:Body>\n"
				+ "</soap-env:Envelope>";
		logger.info("responseEntity*******"+responseEntity);
		message=toSOAPMessage(responseEntity);
		return message;
	}
	
	@Value("${sap.endpoint.url}")
	public void setENDPOINT_URL(String eNDPOINT_URL) {
		ENDPOINT_URL = eNDPOINT_URL;
	}

	@Value("${sap.endpoint.url.auth.user}")
	public void setENDPOINT_AUTH_USER(String eNDPOINT_AUTH_USER) {
		ENDPOINT_AUTH_USER = eNDPOINT_AUTH_USER;
	}

	@Value("${sap.endpoint.url.auth.pass}")
	public void setENDPOINT_AUTH_PASSWORD(String eNDPOINT_AUTH_PASSWORD) {
		ENDPOINT_AUTH_PASSWORD = eNDPOINT_AUTH_PASSWORD;
	}
}
