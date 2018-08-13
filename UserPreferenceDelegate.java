package com.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@javax.jws.WebService(targetNamespace = "http://service.com/", serviceName = "UserPreferenceService", portName = "UserPreferencePort", wsdlLocation = "WEB-INF/wsdl/UserPreferenceService.wsdl")
public class UserPreferenceDelegate {

	com.service.UserPreference userPreference = new com.service.UserPreference();

	public String userPref(String username) {
		return userPreference.userPref(username);
	}

	

}