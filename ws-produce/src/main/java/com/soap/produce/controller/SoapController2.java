package com.soap.produce.controller;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.time.LocalDate;
import java.util.Base64;
import java.util.Iterator;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.Node;
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
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.soap.produce.client.SoapClient;
import com.soap.produce.stub.ZStorageResultMessage;
import com.soap.produce.stub.ZStorageResultMessageResponse;
import com.soap.produce.stub.ZwsStSrmRequest;
import com.soap.produce.stub.ZwsStSrmResponse;



@RestController
@RequestMapping("ws-produce/v2/bk")
public class SoapController2 {
	private static Logger logger = LogManager.getLogger(SoapController2.class);
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

	@GetMapping(value = "/srm", produces = MediaType.APPLICATION_XML_VALUE)
	public ZwsStSrmResponse getStoragePlanMessageResponse(@RequestBody String request) {
		ZStorageResultMessage message = new ZStorageResultMessage();
		ZwsStSrmResponse response = new ZwsStSrmResponse();

		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(ZwsStSrmRequest.class);
			InputStream soap = new ByteArrayInputStream(request.getBytes());
			message = (ZStorageResultMessage) jaxbUnmarshaller(jaxbContext, soap);
			//response = client.getStoragePlanMessageResponse(message);
			
		} catch (JAXBException | SOAPException e) {
			logger.error("JAXBException | SOAPException =" + e.getMessage(), e);
			e.printStackTrace();
		} catch (IOException e) {
			logger.error("IOException =" + e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error("Exception =" + e.getMessage(), e);
			e.printStackTrace();
		}

		return response;
	}

	@PostMapping(value = "/auth/srm", produces = MediaType.APPLICATION_XML_VALUE)
	public ZwsStSrmResponse getSpmResponseBasicAuth(@RequestBody String request) {
		ZStorageResultMessage message = new ZStorageResultMessage();
		ZStorageResultMessageResponse message2 = new ZStorageResultMessageResponse();
		
		logger.info("Endpoint-" + ENDPOINT_URL);
		JAXBContext jaxbContext;
		try {
			//jaxbContext = JAXBContext.newInstance(ZwsStSrmResponse.class);
			jaxbContext = JAXBContext.newInstance(ZStorageResultMessageResponse.class);
			System.out.println("Before:::::::::"+request);
			InputStream soap = new ByteArrayInputStream(request.getBytes());
			//message = (ZStorageResultMessage) jaxbUnmarshaller(jaxbContext, soap);
			message2 = (ZStorageResultMessageResponse) jaxbUnmarshaller(jaxbContext, soap);
			System.out.println("After:::::::::"+message.toString());
		} catch (JAXBException | SOAPException e) {
			logger.error("JAXBException | SOAPException =" + e.getMessage(), e);
			e.printStackTrace();
		} catch (IOException e) {
			logger.error("IOException =" + e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error("Exception =" + e.getMessage(), e);
			e.printStackTrace();
		}

		
		HttpEntity<ZStorageResultMessage> requestEntity = new HttpEntity<ZStorageResultMessage>(message, getHeaders());
		// Call SAP to get result
		ResponseEntity<ZwsStSrmResponse> responseEntity = restTemplate.exchange(ENDPOINT_URL, HttpMethod.POST,
				requestEntity, ZwsStSrmResponse.class);
		logger.info("Response XML from EBS: " + responseEntity);

		return responseEntity.getBody();
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
	public SOAPBody getSpmResponseRestTemplateExchangeSOAPBody(@RequestBody String request) throws IOException, SOAPException {
		logger.info("getSpmResponseRestTemplateExchangeSOAPBody /rtx/srm2");
		logger.info("****************************************************");
		HttpEntity<String> requestEntity = new HttpEntity<String>(request,getHeaders());
		// Call SAP to get result
		ResponseEntity<String> responseEntity = restTemplate.exchange(ENDPOINT_URL, HttpMethod.POST, requestEntity,
				String.class);
		logger.info("responseEntity*******"+responseEntity.getBody());
		return toSOAPMessage(responseEntity.getBody()).getSOAPBody();
	}
	
	@PostMapping(value = "/rtx/srm3", produces = MediaType.APPLICATION_XML_VALUE)
	public SOAPBody getSpmResponseRestTemplateExchangeChildElement(@RequestBody String request) throws IOException, SOAPException {
		logger.info("getSpmResponseRestTemplateExchangeChildElement /rtx/srm3");
		logger.info("********************************************************");
		HttpEntity<String> requestEntity = new HttpEntity<String>(request,getHeaders());
		// Call SAP to get result
		ResponseEntity<String> responseEntity = restTemplate.exchange(ENDPOINT_URL, HttpMethod.POST, requestEntity,
				String.class);
		logger.info("responseEntity*******"+responseEntity.getBody());
		return (SOAPBody) toSOAPMessage(responseEntity.getBody()).getSOAPBody().getChildElements();
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
	
	// Production Delete
	@PostMapping(value = "/rtx/srm/postManTest", produces = MediaType.APPLICATION_XML_VALUE)
	public ZStorageResultMessageResponse postManTest(@RequestBody String request) {
		logger.info("postManTest /rtx/srm/postManTest");
		ZStorageResultMessageResponse response = new ZStorageResultMessageResponse();

        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(ZStorageResultMessageResponse.class);
		    InputStream soap = new ByteArrayInputStream(request.getBytes());
		    response=(ZStorageResultMessageResponse) jaxbUnmarshaller(jaxbContext,soap);
		    logger.info("toSOAPMessage Exception encountered: SUCCESS");
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
	
	// Production Delete
	@PostMapping(value = "/rtx/srm/convertStringtoSoap", produces = MediaType.APPLICATION_XML_VALUE)
	public SOAPMessage convertStringtoSoap(@RequestBody String request) throws IOException, SOAPException {
		logger.info("postManTest /rtx/srm/postManTest");

		if (request == null)
            return null;

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
            message.getSOAPPart().setContent((Source) new StreamSource(new StringReader(request)));
            message.saveChanges();
            message.writeTo(System.out);
            //To Test
            
            ByteArrayOutputStream baos = null;
            baos = new ByteArrayOutputStream();
            message.writeTo(baos); 
            result = baos.toString("UTF-8");
              
            logger.info("baos.toString()======1"+result); 

        } catch (SOAPException e) {
        	logger.error("toSOAPMessage Exception encountered: " + e);
            e.printStackTrace();
        }
 
		//return message;
        return message;
	}
	
	// Production Delete
	@PostMapping(value = "/rtx/srm/test12", produces = MediaType.APPLICATION_XML_VALUE)
	public SOAPMessage test() {
		String soapText =
	            "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
	            +   "<soap:Envelope "
	            +           "xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\" "
	            +           "xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" "
	            +           "xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" "
	            +           "xmlns:mh=\"http://www.Monson-Haefel.com/jwsbook/BookQuote\">"
	            +       "<soap:Body>"
	            +           "<mh:getBookPrice>"
	            +               "<isbn xsi:type=\"xsd:string\">0321146182</isbn>"
	            +           "</mh:getBookPrice>"
	            +       "</soap:Body>"
	            +   "</soap:Envelope>";
		SOAPMessage message2=null;
	        try {
	            // Create SoapMessage
	            MessageFactory msgFactory     = MessageFactory.newInstance();
	            SOAPMessage message           = msgFactory.createMessage();
	            SOAPPart soapPart             = message.getSOAPPart();
	  
	            // Load the SOAP text into a stream source
	            byte[] buffer                 = soapText.getBytes();
	            ByteArrayInputStream stream   = new ByteArrayInputStream(buffer);
	            StreamSource source           = new StreamSource(stream);
	  
	            // Set contents of message 
	            soapPart.setContent(source);
	  
	            // -- DONE
	  
	            message.writeTo(System.out);
	            message2=message;
	 
	           
	  
	        } catch (SOAPException  e) {
	            System.out.println("SOAPException : " + e);
	  
	        } catch (IOException  e) {
	            System.out.println("IOException : " + e);
	        }
	   return message2;
	}
	
	@PostMapping(value = "/rtx/srm6", produces=MediaType.APPLICATION_XML_VALUE)
	public ZStorageResultMessageResponse getZStorageResultMessageResponse(@RequestBody String request){
		ZStorageResultMessageResponse response = new ZStorageResultMessageResponse();
		ZStorageResultMessage message = new ZStorageResultMessage();

		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(ZStorageResultMessage.class);
		    InputStream soap = new ByteArrayInputStream(request.getBytes());
		    message=(ZStorageResultMessage) jaxbUnmarshaller(jaxbContext,soap);
		    
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
	
	
	/*
	@SuppressWarnings("unchecked")
	@PostMapping(value = "/rtx/srm5", produces = MediaType.ALL_VALUE)
	public ZStorageResultMessageResponse getSpmResponseRestTemplateExchange5(@RequestBody String request) throws JAXBException, IOException, SOAPException {
		logger.info("ZStorageResultMessageResponse Unmarshaller /rtx/srm5");
		logger.info("*********************");
		HttpEntity<String> requestEntity = new HttpEntity<String>(request,getHeaders());
		// Call SAP to get result
		HttpEntity<String> responseEntity = restTemplate.exchange(ENDPOINT_URL, HttpMethod.POST, requestEntity,
				String.class);

			// Unmarshalling
			JAXBContext context= JAXBContext.newInstance(ZStorageResultMessageResponse.class);
			SOAPMessage message = MessageFactory.newInstance().createMessage(null, new ByteArrayInputStream(responseEntity.getBody().getBytes()));

			Unmarshaller jaxbUnmarshaller = context.createUnmarshaller();
			JAXBElement<ZStorageResultMessageResponse> result=
			    (JAXBElement<ZStorageResultMessageResponse>) jaxbUnmarshaller.unmarshal((message.getSOAPBody().extractContentAsDocument()));

			logger.info("Response message.getSOAPBody().getValue() " + message.getSOAPBody().getValue());
		    logger.info("Response result " + result.getValue());
		return result.getValue();
	} */
	
	public Object jaxbUnmarshaller(JAXBContext jaxbContext, InputStream soap) throws JAXBException, SOAPException, IOException{
        SOAPMessage message = MessageFactory.newInstance().createMessage(null, soap);
    	Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
    	logger.info("jaxbUnmarshaller jaxbUnmarshaller======1"+jaxbUnmarshaller);
	    return jaxbUnmarshaller.unmarshal(message.getSOAPBody().extractContentAsDocument());
	}
	
	public Object marshaller(JAXBContext jaxbContext, InputStream soap)
			throws JAXBException, SOAPException, IOException {
		Marshaller marshaller = jaxbContext.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		return marshaller(jaxbContext, soap);
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
