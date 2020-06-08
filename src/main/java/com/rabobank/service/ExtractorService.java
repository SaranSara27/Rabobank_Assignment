package com.rabobank.service;

import java.io.File;
import java.util.List;

import com.rabobank.model.Record;
/**
 * 
 * @author Saranya
 *
 */
public interface ExtractorService {

	public List<Record> extractStatmentFromJSON(File file) throws Exception;
	
}
