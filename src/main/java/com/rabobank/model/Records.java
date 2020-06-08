package com.rabobank.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @author Saranya JAXB API json to POJO convention
 */
public class Records {

	public Records() {
	}

	public Records(List<Record> record) {
		super();
		this.record = record;
	}
	
	@JsonProperty("record")
	private List<Record> record;

	public List<Record> getRecord() {
		return record;
	}

	public void setRecord(List<Record> record) {
		this.record = record;
	}

}
