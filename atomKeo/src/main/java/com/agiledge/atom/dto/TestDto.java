package com.agiledge.atom.dto;

public class TestDto {
private String id;
public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
public String getSubscriptionStatus() {
	return subscriptionStatus;
}
public void setSubscriptionStatus(String subscriptionStatus) {
	this.subscriptionStatus = subscriptionStatus;
}
public String getEmpId() {
	return empId;
}
public void setEmpId(String empId) {
	this.empId = empId;
}
private String subscriptionStatus;
private String empId;
}
