package com.kafein.constants;

import java.util.HashMap;
import java.util.Map;

import com.kafein.model.Employee;

public class Constants {
	public static final String STATUS = "status";
	public static final String STATUS_CODE = "statusCode";
	public static final String DESCRIPTION = "description";
	
	public static Map<String, Object> getResponseModel(String status,String statusCode,String desctription) {
		Map<String, Object> response = new HashMap<String, Object>();
		
		response.put(DESCRIPTION, desctription);
		response.put(STATUS_CODE, statusCode);
		response.put(STATUS, status);
		response.put("object", new Employee());
		return response;
	}
}
