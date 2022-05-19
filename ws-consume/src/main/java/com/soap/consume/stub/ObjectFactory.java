//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.0 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.11.20 at 12:48:48 PM SGT 
//


package com.soap.consume.stub;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.soap.consume.stub package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _StoragePlanMessageSpmHeader_QNAME = new QName("http://server.webservice.wms7.daifuku.co.jp", "spmHeader");
    private final static QName _StoragePlanMessageResponseReturn_QNAME = new QName("http://server.webservice.wms7.daifuku.co.jp", "return");
    private final static QName _ResponseMessageResponseCode_QNAME = new QName("http://msg.webservice.wms7.daifuku.co.jp/xsd", "responseCode");
    private final static QName _ResponseMessageResponseDescription_QNAME = new QName("http://msg.webservice.wms7.daifuku.co.jp/xsd", "responseDescription");
    private final static QName _WMS7ResponseMessageHeader_QNAME = new QName("http://msg.webservice.wms7.daifuku.co.jp/xsd", "messageHeader");
    private final static QName _WMS7ResponseResponseMessageHeader_QNAME = new QName("http://msg.webservice.wms7.daifuku.co.jp/xsd", "responseMessageHeader");
    private final static QName _SPMDetailBatchNo_QNAME = new QName("http://msg.webservice.wms7.daifuku.co.jp/xsd", "batchNo");
    private final static QName _SPMDetailDeleteFlag_QNAME = new QName("http://msg.webservice.wms7.daifuku.co.jp/xsd", "deleteFlag");
    private final static QName _SPMDetailExpiryDate_QNAME = new QName("http://msg.webservice.wms7.daifuku.co.jp/xsd", "expiryDate");
    private final static QName _SPMDetailHu_QNAME = new QName("http://msg.webservice.wms7.daifuku.co.jp/xsd", "hu");
    private final static QName _SPMDetailItemCode_QNAME = new QName("http://msg.webservice.wms7.daifuku.co.jp/xsd", "itemCode");
    private final static QName _SPMDetailSapDocumentLineNo_QNAME = new QName("http://msg.webservice.wms7.daifuku.co.jp/xsd", "sapDocumentLineNo");
    private final static QName _SPMHeaderMsgId_QNAME = new QName("http://msg.webservice.wms7.daifuku.co.jp/xsd", "msgId");
    private final static QName _SPMHeaderSapDocumentNo_QNAME = new QName("http://msg.webservice.wms7.daifuku.co.jp/xsd", "sapDocumentNo");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.soap.consume.stub
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link SPMHeader }
     * 
     */
    public SPMHeader createSPMHeader() {
        return new SPMHeader();
    }

    /**
     * Create an instance of {@link SPMDetail }
     * 
     */
    public SPMDetail createSPMDetail() {
        return new SPMDetail();
    }

    /**
     * Create an instance of {@link WMS7Response }
     * 
     */
    public WMS7Response createWMS7Response() {
        return new WMS7Response();
    }

    /**
     * Create an instance of {@link ResponseMessageHeader }
     * 
     */
    public ResponseMessageHeader createResponseMessageHeader() {
        return new ResponseMessageHeader();
    }

    /**
     * Create an instance of {@link ResponseMessage }
     * 
     */
    public ResponseMessage createResponseMessage() {
        return new ResponseMessage();
    }

    /**
     * Create an instance of {@link StoragePlanMessage }
     * 
     */
    public StoragePlanMessage createStoragePlanMessage() {
        return new StoragePlanMessage();
    }

    /**
     * Create an instance of {@link StoragePlanMessageResponse }
     * 
     */
    public StoragePlanMessageResponse createStoragePlanMessageResponse() {
        return new StoragePlanMessageResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SPMHeader }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link SPMHeader }{@code >}
     */
    @XmlElementDecl(namespace = "http://server.webservice.wms7.daifuku.co.jp", name = "spmHeader", scope = StoragePlanMessage.class)
    public JAXBElement<SPMHeader> createStoragePlanMessageSpmHeader(SPMHeader value) {
        return new JAXBElement<SPMHeader>(_StoragePlanMessageSpmHeader_QNAME, SPMHeader.class, StoragePlanMessage.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link WMS7Response }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link WMS7Response }{@code >}
     */
    @XmlElementDecl(namespace = "http://server.webservice.wms7.daifuku.co.jp", name = "return", scope = StoragePlanMessageResponse.class)
    public JAXBElement<WMS7Response> createStoragePlanMessageResponseReturn(WMS7Response value) {
        return new JAXBElement<WMS7Response>(_StoragePlanMessageResponseReturn_QNAME, WMS7Response.class, StoragePlanMessageResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://msg.webservice.wms7.daifuku.co.jp/xsd", name = "responseCode", scope = ResponseMessage.class)
    public JAXBElement<String> createResponseMessageResponseCode(String value) {
        return new JAXBElement<String>(_ResponseMessageResponseCode_QNAME, String.class, ResponseMessage.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://msg.webservice.wms7.daifuku.co.jp/xsd", name = "responseDescription", scope = ResponseMessage.class)
    public JAXBElement<String> createResponseMessageResponseDescription(String value) {
        return new JAXBElement<String>(_ResponseMessageResponseDescription_QNAME, String.class, ResponseMessage.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://msg.webservice.wms7.daifuku.co.jp/xsd", name = "responseCode", scope = ResponseMessageHeader.class)
    public JAXBElement<String> createResponseMessageHeaderResponseCode(String value) {
        return new JAXBElement<String>(_ResponseMessageResponseCode_QNAME, String.class, ResponseMessageHeader.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://msg.webservice.wms7.daifuku.co.jp/xsd", name = "responseDescription", scope = ResponseMessageHeader.class)
    public JAXBElement<String> createResponseMessageHeaderResponseDescription(String value) {
        return new JAXBElement<String>(_ResponseMessageResponseDescription_QNAME, String.class, ResponseMessageHeader.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Object }{@code >}
     */
    @XmlElementDecl(namespace = "http://msg.webservice.wms7.daifuku.co.jp/xsd", name = "messageHeader", scope = WMS7Response.class)
    public JAXBElement<Object> createWMS7ResponseMessageHeader(Object value) {
        return new JAXBElement<Object>(_WMS7ResponseMessageHeader_QNAME, Object.class, WMS7Response.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ResponseMessageHeader }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ResponseMessageHeader }{@code >}
     */
    @XmlElementDecl(namespace = "http://msg.webservice.wms7.daifuku.co.jp/xsd", name = "responseMessageHeader", scope = WMS7Response.class)
    public JAXBElement<ResponseMessageHeader> createWMS7ResponseResponseMessageHeader(ResponseMessageHeader value) {
        return new JAXBElement<ResponseMessageHeader>(_WMS7ResponseResponseMessageHeader_QNAME, ResponseMessageHeader.class, WMS7Response.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://msg.webservice.wms7.daifuku.co.jp/xsd", name = "batchNo", scope = SPMDetail.class)
    public JAXBElement<String> createSPMDetailBatchNo(String value) {
        return new JAXBElement<String>(_SPMDetailBatchNo_QNAME, String.class, SPMDetail.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://msg.webservice.wms7.daifuku.co.jp/xsd", name = "deleteFlag", scope = SPMDetail.class)
    public JAXBElement<String> createSPMDetailDeleteFlag(String value) {
        return new JAXBElement<String>(_SPMDetailDeleteFlag_QNAME, String.class, SPMDetail.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://msg.webservice.wms7.daifuku.co.jp/xsd", name = "expiryDate", scope = SPMDetail.class)
    public JAXBElement<String> createSPMDetailExpiryDate(String value) {
        return new JAXBElement<String>(_SPMDetailExpiryDate_QNAME, String.class, SPMDetail.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://msg.webservice.wms7.daifuku.co.jp/xsd", name = "hu", scope = SPMDetail.class)
    public JAXBElement<String> createSPMDetailHu(String value) {
        return new JAXBElement<String>(_SPMDetailHu_QNAME, String.class, SPMDetail.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://msg.webservice.wms7.daifuku.co.jp/xsd", name = "itemCode", scope = SPMDetail.class)
    public JAXBElement<String> createSPMDetailItemCode(String value) {
        return new JAXBElement<String>(_SPMDetailItemCode_QNAME, String.class, SPMDetail.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://msg.webservice.wms7.daifuku.co.jp/xsd", name = "sapDocumentLineNo", scope = SPMDetail.class)
    public JAXBElement<String> createSPMDetailSapDocumentLineNo(String value) {
        return new JAXBElement<String>(_SPMDetailSapDocumentLineNo_QNAME, String.class, SPMDetail.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://msg.webservice.wms7.daifuku.co.jp/xsd", name = "msgId", scope = SPMHeader.class)
    public JAXBElement<String> createSPMHeaderMsgId(String value) {
        return new JAXBElement<String>(_SPMHeaderMsgId_QNAME, String.class, SPMHeader.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://msg.webservice.wms7.daifuku.co.jp/xsd", name = "sapDocumentNo", scope = SPMHeader.class)
    public JAXBElement<String> createSPMHeaderSapDocumentNo(String value) {
        return new JAXBElement<String>(_SPMHeaderSapDocumentNo_QNAME, String.class, SPMHeader.class, value);
    }

}
