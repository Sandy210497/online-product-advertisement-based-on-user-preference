package com.payment;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

/**
 * This object contains factory methods for each Java content interface and Java
 * element interface generated in the com.payment package.
 * <p>
 * An ObjectFactory allows you to programatically construct new instances of the
 * Java representation for XML content. The Java representation of XML content
 * can consist of schema derived interfaces and classes representing the binding
 * of schema type definitions, element declarations and model groups. Factory
 * methods for each of these are provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

	private final static QName _SetPaymentResponse_QNAME = new QName(
			"http://bankwebser.com/", "setPaymentResponse");
	private final static QName _SetPayment_QNAME = new QName(
			"http://bankwebser.com/", "setPayment");
	private final static QName _CommonMet_QNAME = new QName(
			"http://bankwebser.com/", "commonMet");
	private final static QName _CommonMetResponse_QNAME = new QName(
			"http://bankwebser.com/", "commonMetResponse");

	/**
	 * Create a new ObjectFactory that can be used to create new instances of
	 * schema derived classes for package: com.payment
	 * 
	 */
	public ObjectFactory() {
	}

	/**
	 * Create an instance of {@link SetPayment }
	 * 
	 */
	public SetPayment createSetPayment() {
		return new SetPayment();
	}

	/**
	 * Create an instance of {@link CommonMet }
	 * 
	 */
	public CommonMet createCommonMet() {
		return new CommonMet();
	}

	/**
	 * Create an instance of {@link CommonMetResponse }
	 * 
	 */
	public CommonMetResponse createCommonMetResponse() {
		return new CommonMetResponse();
	}

	/**
	 * Create an instance of {@link SetPaymentResponse }
	 * 
	 */
	public SetPaymentResponse createSetPaymentResponse() {
		return new SetPaymentResponse();
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}
	 * {@link SetPaymentResponse }{@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://bankwebser.com/", name = "setPaymentResponse")
	public JAXBElement<SetPaymentResponse> createSetPaymentResponse(
			SetPaymentResponse value) {
		return new JAXBElement<SetPaymentResponse>(_SetPaymentResponse_QNAME,
				SetPaymentResponse.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link SetPayment }
	 * {@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://bankwebser.com/", name = "setPayment")
	public JAXBElement<SetPayment> createSetPayment(SetPayment value) {
		return new JAXBElement<SetPayment>(_SetPayment_QNAME, SetPayment.class,
				null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link CommonMet }
	 * {@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://bankwebser.com/", name = "commonMet")
	public JAXBElement<CommonMet> createCommonMet(CommonMet value) {
		return new JAXBElement<CommonMet>(_CommonMet_QNAME, CommonMet.class,
				null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}
	 * {@link CommonMetResponse }{@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://bankwebser.com/", name = "commonMetResponse")
	public JAXBElement<CommonMetResponse> createCommonMetResponse(
			CommonMetResponse value) {
		return new JAXBElement<CommonMetResponse>(_CommonMetResponse_QNAME,
				CommonMetResponse.class, null, value);
	}

}
