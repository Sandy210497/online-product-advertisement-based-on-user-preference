package com.bankwebser;

import java.util.*;
import javax.mail.Session;
import javax.mail.Message.RecipientType;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import com.sun.mail.smtp.SMTPSSLTransport; 

public class Mail
{
	public Mail(String host,String userid,String password,String from,String to,String subject,String messagge)
	{
		try
		{
    		Properties props = System.getProperties();
    		props.put("mail.smtp.starttls.enable", "true");
    		props.put("mail.smtp.host", host);
    		props.setProperty("mail.transport.protocol", "smtps");
    		props.put("mail.smtp.user", userid);
    		props.put("mail.smtp.password", password);
    		props.put("mail.smtp.port", "465");
    		props.put("mail.smtps.auth", "true");
    		
    		Session session = Session.getDefaultInstance(props, null);
    		MimeMessage message = new MimeMessage(session);
    		InternetAddress fromAddress = null;
    		InternetAddress toAddress = null;
    		try
    		{
    			System.out.println("Address of the sender==="+from);
    			System.out.println("Address of the reciever==="+to);
	    		fromAddress = new InternetAddress(from);
	    		toAddress = new InternetAddress(to);
    		}
    		catch(AddressException e)
    		{
    			e.printStackTrace();
    		}
    		System.out.println("Content of the title displayed"+subject);
    		System.out.println("Content of the message displayed"+messagge);
    		message.setFrom(fromAddress);
    		message.setRecipient(RecipientType.TO, toAddress);
    		message.setSubject(subject);
    		message.setText(messagge);
    		SMTPSSLTransport transport =(SMTPSSLTransport)session.getTransport("smtps");
    		transport.connect(host, userid, password);
    		transport.sendMessage(message, message.getAllRecipients()); 
    		transport.close();
    		System.out.println("Mail Sent Successfully");
		}
		catch(Exception e1)
		{
			e1.printStackTrace();
		}
	}
}