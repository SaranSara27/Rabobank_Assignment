package com.rabobank.model;

import java.util.List;

/**
 * 
 * @author Saranya
 *
 */
public class AppResponse {
	private String result;
	private List<Record> errorRecords;
	@Override
	public String toString() {
		return "AppResponse [result=" + result + ", errorRecords=" + errorRecords + "]";
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public List<Record> getErrorRecords() {
		return errorRecords;
	}
	public void setErrorRecords(List<Record> errorRecords) {
		this.errorRecords = errorRecords;
	}

	
}
