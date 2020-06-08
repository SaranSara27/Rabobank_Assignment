package com.rabobank.service;

import java.io.File;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabobank.model.Record;
import com.rabobank.model.Records;

/**
 * 
 * @author Saranya
 *	class to extract the uploaded file and convert to list of record java object.
 */
public class ExtractorServiceImpl implements ExtractorService {


	/**
	 * @return List<Records>
	 */
	public List<Record> extractStatmentFromJSON(File file) throws Exception {
		
		ObjectMapper objectMapper = new ObjectMapper();
		Records rootRecord = 
				  objectMapper.readValue(file, Records.class);
		return rootRecord.getRecord();
	}

}
