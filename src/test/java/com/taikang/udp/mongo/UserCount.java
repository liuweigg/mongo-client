package com.taikang.udp.mongo;

public class UserCount {
	private long userCount ;
	
	private String company;
	
	private long ageSum ;
	
	private long maxAge ;

	private long minAge ;
	
	private String firstName;
	
	private String lastName;
	
	private int avgAge;
	
	private String column ; 
	
	private String height ;

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public long getUserCount() {
		return userCount;
	}

	public void setUserCount(long userCount) {
		this.userCount = userCount;
	}

	public long getAgeSum() {
		return ageSum;
	}

	public void setAgeSum(long ageSum) {
		this.ageSum = ageSum;
	}

	public long getMaxAge() {
		return maxAge;
	}

	public void setMaxAge(long maxAge) {
		this.maxAge = maxAge;
	}

	public long getMinAge() {
		return minAge;
	}

	public void setMinAge(long minAge) {
		this.minAge = minAge;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getAvgAge() {
		return avgAge;
	}

	public void setAvgAge(int avgAge) {
		this.avgAge = avgAge;
	}

	public String getColumn() {
		return column;
	}

	public void setColumn(String column) {
		this.column = column;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}
	
}
