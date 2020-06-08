package com.rabobank.testcase;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.rabobank.constant.TestCaseUtil;
import com.rabobank.model.Record;
import com.rabobank.service.ExtractorService;
import com.rabobank.service.ExtractorServiceImpl;
import com.rabobank.service.ValidatorService;
import com.rabobank.service.ValidatorServiceImpl;

import junit.framework.Assert;

/**
 * 
 * @author Saranya 
 * 
 * This test cases covers all service class methods of this
 * application (100% code coverage)
 *
 */
public class TestCases {

	/**
	 * Type : Positive 
	 * scenario : Duplicate check in given Rabobank Customer
	 * Statement
	 */
	@Test
	public void getDuplicateRecordsTestCaseWithDuplilcate() {
		List<Record> inputList = Arrays.asList(
				new Record(172833, "NL69ABNA0433647324", 66.72, -41.74, "Tickets for Willem Theu�", 24.98),
				new Record(172833, "NL43AEGO0773393871", 16.52, +43.09, "Tickets for Willem Theu�", 59.61));
		ValidatorService validatorServiceImpl = new ValidatorServiceImpl();
		List<Record> duplicateRecords = validatorServiceImpl.getDuplicateRecords(inputList);
		assertEquals(inputList.size(), duplicateRecords.size());

	}

	/**
	 * Type : Negative 
	 * scenario : Duplicate check in given Rabobank Customer
	 * Statement
	 */
	@Test
	public void getDuplicateRecordsTestCaseWithOutDuplilcate() {
		List<Record> inputList = Arrays.asList(
				new Record(172823, "NL69ABNA0433647324", 66.72, -41.74, "Tickets for Willem Theu�", 24.98),
				new Record(172833, "NL43AEGO0773393871", 16.52, +43.09, "Tickets for Willem Theu�", 59.61));
		ValidatorService validatorServiceImpl = new ValidatorServiceImpl();
		List<Record> duplicateRecords = validatorServiceImpl.getDuplicateRecords(inputList);
		assertEquals(0, duplicateRecords.size());

	}

	/**
	 * Type : Positive 
	 * scenario : EndBalance validation in given Rabobank Customer
	 * Statement
	 */
	@Test
	public void getEndBalanceErrorRecordsTestCaseWithWrongValue() {
		List<Record> inputList = Arrays.asList(
				new Record(172833, "NL69ABNA0433647324", 66.72, -41.74, "Tickets for Willem Theu�", 24.98),
				new Record(172833, "NL43AEGO0773393871", 16.52, +43.09, "Tickets for Willem Theu�", 59.80));
		ValidatorService validatorServiceImpl = new ValidatorServiceImpl();
		List<Record> endBalanceErrorRecords = validatorServiceImpl.getEndBalanceErrorRecords(inputList);
		assertEquals(inputList.size(), endBalanceErrorRecords.size());

	}

	/**
	 * Type : Negative 
	 * scenario : EndBalance validation in given Rabobank Customer
	 * Statement
	 */
	@Test
	public void getEndBalanceErrorRecordsTestCaseWithCorrectValue() {
		List<Record> inputList = Arrays.asList(
				new Record(172833, "NL69ABNA0433647324", 66.72, -41.74, "Tickets for Willem Theu�", 108.46),
				new Record(172833, "NL43AEGO0773393871", 16.52, +43.09, "Tickets for Willem Theu�", -26.57));
		ValidatorService validatorServiceImpl = new ValidatorServiceImpl();
		List<Record> endBalanceErrorRecords = validatorServiceImpl.getEndBalanceErrorRecords(inputList);
		assertEquals(0, endBalanceErrorRecords.size());
	}
	

	/**
	 * Type : Positive 
	 * scenario : Processing the input json file and extracting
	 * values as POJO object for validation process
	 */
	@Test
	public void extractStatmentFromJSONTestCase() {
		ExtractorService extractorServiceImpl = new ExtractorServiceImpl();
		File inputFile = new File("records.json");
		try {
			int totalLineInInputJSON = 10; 
			List<Record> extractedRecords = extractorServiceImpl.extractStatmentFromJSON(inputFile);
			assertEquals(totalLineInInputJSON, extractedRecords.size());
		} catch (Exception e) {
			Assert.fail(e.getMessage());
			e.printStackTrace();
		}
	}
}
