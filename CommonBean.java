package com.banklogic;

import java.io.Serializable;

public class CommonBean implements Serializable
{
	String accountnumb,username,mobnum,email,address,city,pincode,state;
	String amount,pinpass,opass;
	
	
	public String getOpass() {
		return opass;
	}

	public void setOpass(String opass) {
		this.opass = opass;
	}

	public String getPinpass() {
		return pinpass;
	}

	public void setPinpass(String pinpass) {
		this.pinpass = pinpass;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAccountnumb() {
		return accountnumb;
	}

	public void setAccountnumb(String accountnumb) {
		this.accountnumb = accountnumb;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getMobnum() {
		return mobnum;
	}

	public void setMobnum(String mobnum) {
		this.mobnum = mobnum;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}	
}