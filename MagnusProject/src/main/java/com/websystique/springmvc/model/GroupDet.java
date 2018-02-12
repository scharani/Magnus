package com.websystique.springmvc.model;

import java.util.ArrayList;
import java.util.List;



import org.hibernate.validator.constraints.NotEmpty;


public class GroupDet {
	@NotEmpty(message = "Please enter group name.")
	private String gname;
	@NotEmpty(message = "Please enter Volunteer Date.")
	private String vdate;
	private String fname;
	private String lname;
	private String address;
	private String city;
	private String state;
	private String zip;
//	private List<String> vlist = new ArrayList<String>();

	public GroupDet()
	{
	}
	public GroupDet(String gname, String vdate)
	{
		this.gname = gname;
		this.vdate = vdate;
		this.fname = null;
		this.lname = null;
		this.address = null;
		this.city = null;
		this.state = null;
		this.zip = null;
	}
 	public String getGname() {
		return gname;
	}

	public void setGname(String gname) {
		this.gname = gname;
	}

	public String getVdate() {
		return vdate;
	}

	public void setVdate(String vdate) {
		this.vdate = vdate;
	}
	
	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
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

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	 		
}
