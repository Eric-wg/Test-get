package com.ebay.eric.xstream_test;

public class Birthday {

	private String birthday;
	
	public Birthday() {
		super();
	}

	public Birthday(String birthday){
		this.setBirthday(birthday);
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	@Override
	public String toString() {
		return birthday;
	}

	
}
