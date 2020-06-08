package com.rabobank.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.rabobank.constant.PortalConstants;
import com.rabobank.model.AppResponse;
import com.rabobank.model.Record;
import com.rabobank.service.ExtractorService;
import com.rabobank.service.ValidatorService;

/**
 * 
 * @author Saranya
 *
 */

@RestController
@RequestMapping("/rabobank")
public class StatementProcessController {

	@Autowired
	private ValidatorService validatorService;

	@Autowired
	private ExtractorService extractorService;

	@GetMapping("/test")
	public @ResponseBody AppResponse test() throws Exception {
		AppResponse appResponse = new AppResponse();
		return appResponse;
	}

	@PostMapping("/processStatement")
	public @ResponseBody AppResponse handleFileUpload(@RequestParam("file") MultipartFile multipart) throws Exception {
		System.out.println("inside handleFileUpload");
		List<Record> errorRecords = new ArrayList<>();
		AppResponse appResponse = new AppResponse();
		if (!multipart.isEmpty()) {
			if (multipart.getContentType().equalsIgnoreCase(PortalConstants.FILE_TYPE_JSON)) {
				
				List<Record> duplicateRecords = null;
				List<Record> balanceErrorRecords = null;
				File jsonFile = new File(multipart.getOriginalFilename());
				multipart.transferTo(jsonFile);
				List<Record> extractedRecords = extractorService.extractStatmentFromJSON(jsonFile);
				duplicateRecords=validatorService.getDuplicateRecords(extractedRecords);
				balanceErrorRecords=validatorService.getEndBalanceErrorRecords(extractedRecords);
				errorRecords.addAll(duplicateRecords);
				errorRecords.addAll(balanceErrorRecords);
				if(duplicateRecords.isEmpty() && balanceErrorRecords.isEmpty()) {
					appResponse.setResult(PortalConstants.SUCCESSFUL);
				}
				else if(!duplicateRecords.isEmpty() && balanceErrorRecords.isEmpty()) {
					appResponse.setResult(PortalConstants.DUPLICATE_REFERENCE);
				}
				else if(duplicateRecords.isEmpty() && !balanceErrorRecords.isEmpty()) {
					appResponse.setResult(PortalConstants.INCORRECT_END_BALANCE);
				}
				else{
					appResponse.setResult(PortalConstants.BOTH_ERROR);
				}
			} 
		} else {
			appResponse.setResult(PortalConstants.BAD_REQUEST);
		}
		appResponse.setErrorRecords(errorRecords);
		return appResponse;
	}

	@ExceptionHandler(Exception.class)
	public @ResponseBody AppResponse handleException(HttpServletRequest request, Exception ex) {
		AppResponse appResponse = new AppResponse();
		appResponse.setResult(PortalConstants.BAD_REQUEST);
		return appResponse;
	}

}
