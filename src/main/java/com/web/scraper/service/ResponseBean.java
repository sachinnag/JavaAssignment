package com.web.scraper.service;

public class ResponseBean {
	private Object result;
	private String status;
	
	public ResponseBean() {
		
	}
	
	public ResponseBean(Object result, String status) {
		this.result = result;
		this.status = status;
	}

	public Object getResult() {
		return result;
	}
	
	public void setResult(Object result) {
		this.result = result;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
}
