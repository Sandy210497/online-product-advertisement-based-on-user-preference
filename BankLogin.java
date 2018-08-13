package com.banklogic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import com.opensymphony.xwork2.ActionSupport;

public class BankLogin extends ActionSupport
{
	String adminusername="",adminpassword="";
	Random rand=new Random();
	ArrayList<String> bankaccntholdnames=new ArrayList<String>();
	String usernametext,phonetext,emailtext,addresstext,citytext,pincodetext,statetext;
	String firstselectaccountnotext,firstbankholdernames,firstamounttext,firstphonetext,firstmailtext,firstaddresstext,firstcitytext,firstpincodetext,firststatetext;
		
	public String getFirstselectaccountnotext() {
		return firstselectaccountnotext;
	}

	public void setFirstselectaccountnotext(String firstselectaccountnotext) {
		this.firstselectaccountnotext = firstselectaccountnotext;
	}

	public String getFirstbankholdernames() {
		return firstbankholdernames;
	}

	public void setFirstbankholdernames(String firstbankholdernames) {
		this.firstbankholdernames = firstbankholdernames;
	}

	public String getFirstamounttext() {
		return firstamounttext;
	}

	public void setFirstamounttext(String firstamounttext) {
		this.firstamounttext = firstamounttext;
	}

	public String getFirstphonetext() {
		return firstphonetext;
	}

	public void setFirstphonetext(String firstphonetext) {
		this.firstphonetext = firstphonetext;
	}

	public String getFirstmailtext() {
		return firstmailtext;
	}

	public void setFirstmailtext(String firstmailtext) {
		this.firstmailtext = firstmailtext;
	}

	public String getFirstaddresstext() {
		return firstaddresstext;
	}

	public void setFirstaddresstext(String firstaddresstext) {
		this.firstaddresstext = firstaddresstext;
	}

	public String getFirstcitytext() {
		return firstcitytext;
	}

	public void setFirstcitytext(String firstcitytext) {
		this.firstcitytext = firstcitytext;
	}

	public String getFirstpincodetext() {
		return firstpincodetext;
	}

	public void setFirstpincodetext(String firstpincodetext) {
		this.firstpincodetext = firstpincodetext;
	}

	public String getFirststatetext() {
		return firststatetext;
	}

	public void setFirststatetext(String firststatetext) {
		this.firststatetext = firststatetext;
	}

	public String getEmailtext() {
		return emailtext;
	}

	public void setEmailtext(String emailtext) {
		this.emailtext = emailtext;
	}

	public String getAdminusername() {
		return adminusername;
	}

	public void setAdminusername(String adminusername) {
		this.adminusername = adminusername;
	}

	public String getAdminpassword() {
		return adminpassword;
	}

	public void setAdminpassword(String adminpassword) {
		this.adminpassword = adminpassword;
	}

	public String getUsernametext() {
		return usernametext;
	}

	public void setUsernametext(String usernametext) {
		this.usernametext = usernametext;
	}

	public String getPhonetext() {
		return phonetext;
	}

	public void setPhonetext(String phonetext) {
		this.phonetext = phonetext;
	}

	public String getAddresstext() {
		return addresstext;
	}

	public void setAddresstext(String addresstext) {
		this.addresstext = addresstext;
	}

	public String getCitytext() {
		return citytext;
	}

	public void setCitytext(String citytext) {
		this.citytext = citytext;
	}

	public String getPincodetext() {
		return pincodetext;
	}

	public void setPincodetext(String pincodetext) {
		this.pincodetext = pincodetext;
	}

	public String getStatetext() {
		return statetext;
	}

	public void setStatetext(String statetext) {
		this.statetext = statetext;
	}	

	
	public ArrayList<String> getBankaccntholdnames() {
		return bankaccntholdnames;
	}

	public void setBankaccntholdnames(ArrayList<String> bankaccntholdnames) {
		this.bankaccntholdnames = bankaccntholdnames;
	}

	public String execute()
	{
		String status="error";
		try
		{
			if(adminusername.equals("admin") && adminpassword.equals("admin"))
			{
				XlApi xlap=new XlApi("webapps/BankAdmin/Pincode.csv",this);
				commonMetDb();				
				status="success";
			}
			else
			{
				addActionMessage("Login Failed!");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return status;
	}
	
	public String setNewHolder()
	{
		String status="success";
		try
		{
			String accno=rand.nextInt(10)+""+rand.nextInt(10)+""+rand.nextInt(10)+""+rand.nextInt(10)+""+rand.nextInt(10)+""+rand.nextInt(10)+""+rand.nextInt(10)+""+rand.nextInt(10);
			String pinpass=rand.nextInt(10)+""+rand.nextInt(10)+""+rand.nextInt(10)+""+rand.nextInt(10);
			CommonBean comb=new CommonBean();			
			comb.setAccountnumb(accno);
			comb.setUsername(usernametext);
			comb.setMobnum(phonetext);
			comb.setEmail(emailtext);
			comb.setAddress(addresstext);
			comb.setCity(citytext);
			comb.setPincode(pincodetext);
			comb.setState(statetext);
			comb.setPinpass(pinpass);
			comb.setAmount("10000");
			
			DbQueries db=new DbQueries();
			int dbst=db.insertUserDetails(comb);			
			commonMetDb();			
			addActionMessage("Added Successfully.");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return status;
	}
	
	public String updateAccountHold()
	{
		String status="success";
		try
		{
			DbQueries db=new DbQueries();
			CommonBean comb=new CommonBean();
			comb.setAccountnumb(firstselectaccountnotext);
			comb.setUsername(firstbankholdernames);
			comb.setMobnum(firstphonetext);
			comb.setEmail(firstmailtext);
			comb.setAddress(firstaddresstext);
			comb.setCity(firstcitytext);
			comb.setPincode(firstpincodetext);
			comb.setState(firststatetext);
			db.updateAccountHolderTab(comb);
			
			commonMetDb();
			addActionMessage("Updated Successfully.");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return status;
	}
	
	public String logOut()
	{
		return "success";
	}
	
	public String toAdminPage()
	{
		commonMetDb();
		return "success";
	}
	
	public String addAmount()
	{
		commonMetDb();
		return "success";
	}
	
	public String addAcctAmountHolder()
	{
		String status="success";
		try
		{
			DbQueries db=new DbQueries();
			CommonBean comb=new CommonBean();
			comb.setAccountnumb(firstselectaccountnotext);
			comb.setUsername(firstbankholdernames);
			comb.setAmount(firstamounttext);
			db.addAmountAccount(comb);
			commonMetDb();
			addActionMessage("Added Successfully.");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return status;
	}
	
	public void commonMetDb()
	{
		DbQueries db=new DbQueries();
		ArrayList arra=db.getUsersAccountId();
		bankaccntholdnames.addAll(arra);
		Collections.sort(bankaccntholdnames);
		if(bankaccntholdnames.isEmpty())
		{
			bankaccntholdnames.add("--Select--");
		}
	}
}
