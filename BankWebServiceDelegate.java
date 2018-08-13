package com.bankwebser;

import java.io.FileInputStream;
import java.net.InetAddress;
import java.util.Properties;
import java.util.Random;
import javax.servlet.http.HttpServletRequest;

@javax.jws.WebService(targetNamespace = "http://bankwebser.com/", serviceName = "BankWebServiceService", portName = "BankWebServicePort", wsdlLocation = "WEB-INF/wsdl/BankWebServiceService.wsdl")
public class BankWebServiceDelegate {

	com.bankwebser.BankWebService bankWebService = new com.bankwebser.BankWebService();

	public String setPayment(String mailid, String payment) {
		return bankWebService.setPayment(mailid, payment);
	}

	public String commonMet() {
		return bankWebService.commonMet();
	}

}