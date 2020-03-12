package com.kafein.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kafein.constants.Constants;
import com.kafein.model.Employee;
import com.kafein.model.EmployeeDTO;
import com.kafein.service.EmployeeService;

@Controller
@RequestMapping(value = "/employee")
public class EmployeeController {
	
	ModelMapper mapper = new ModelMapper();
	
	@Autowired
	private EmployeeService employeeService;
	
	@GetMapping(value="/list")
	@ResponseBody
	private List <Employee> employeeList(){
		
		List<Employee> list = employeeService.getAllEmployee();
		if(list==null) {
			System.out.println("liste boş");
		}
		return list;
		
	}
	@PostMapping(value="/insert" , produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	private ResponseEntity<?> insertEmployee(@RequestBody Employee employee){
		if(employee==null) {
			return ResponseEntity.ok().body(Constants.getResponseModel("NOK", "-1", "Model is null"));
		}
		if(employeeService.checkMail(employee.getEmail())) {
			return ResponseEntity.ok().body(Constants.getResponseModel("NOK", "-1", "Email already inserted"));
		}
		else {
		employeeService.insertEmployee(employee);
		return ResponseEntity.ok().body(Constants.getResponseModel("OK", "200", "Successfuly inserted"));
	}
		}
	@PostMapping(value="/insertBulk" , produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	private Object bulkinsertEmployee(@RequestBody List<Employee> employeeList){
		if(employeeList==null) {
			return new ResponseEntity<List<Employee>>(employeeList,HttpStatus.NOT_FOUND);
		}
		List<EmployeeDTO> employeeListActive = new ArrayList<EmployeeDTO>(); // true
		List<EmployeeDTO> employeeListDeactive = new ArrayList<EmployeeDTO>();  // false
		for (int j = 0; j < employeeList.size(); j++) {
			
			EmployeeDTO dto = mapper.map(employeeList.get(j), EmployeeDTO.class);
			
			if(employeeService.checkMail(employeeList.get(j).getEmail())) {
				
				
				dto.setStatus("this mail already added");
				dto.setId(employeeList.get(j).getId());
				employeeListActive.add(dto);
			}
			else
			{
				employeeService.insertEmployee(employeeList.get(j));
				dto.setId(employeeList.get(j).getId());
				dto.setStatus("inserted");
				employeeListDeactive.add(dto);
			}
			
		}
		
		
		employeeListActive.addAll(employeeListDeactive);
		
		
		return new ResponseEntity<List<EmployeeDTO>>(employeeListActive , HttpStatus.OK);
		}
	
	
	@PostMapping(value="/delete/{id}" , produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	private ResponseEntity<?> deleteEmployee(@PathVariable Integer id){
		Boolean checkId = employeeService.checkId(id);
		if(checkId)
		employeeService.deleteEmployee(id);
		else 
			return ResponseEntity.ok().body(Constants.getResponseModel("NOK", "-1", "Id not null")); 
		return ResponseEntity.ok().body(Constants.getResponseModel("OK", "200", "Successfuly removed")); 
	}
	
	
	@PostMapping(value="/deleteBulk" , produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	private ResponseEntity<?> deleteBulkEmployee(@RequestBody List<Integer> idList){
		List<EmployeeDTO> employeeListDeleted = new ArrayList<EmployeeDTO>(); // true
		List<EmployeeDTO> employeeListNonDeleted = new ArrayList<EmployeeDTO>();  // false
		for (Integer i : idList) {
			EmployeeDTO dto = mapper.map(idList, EmployeeDTO.class);
			Boolean checkId = employeeService.checkId(i);
			if(checkId==true) {
				employeeService.deleteEmployee(i);
				dto.setStatus("true");
				dto.setId(i);
				employeeListDeleted.add(dto);
			}
			else {
				dto.setStatus("false");
				dto.setId(i);
				employeeListNonDeleted.add(dto);
			}
		}
		employeeListDeleted.addAll(employeeListNonDeleted);
		
		return new ResponseEntity<List<EmployeeDTO>>(employeeListDeleted , HttpStatus.OK);
	}
	
	//updateEmployee
	@PostMapping(value="/update" , produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	private ResponseEntity<?> updateEmployee(@RequestBody Employee employee){
		if (employee == null) {
			return ResponseEntity.ok().body(Constants.getResponseModel("NOK", "-1", "Model is null")); 
		}
		else {
		employeeService.updateEmployee(employee);
		return ResponseEntity.ok().body(Constants.getResponseModel("OK", "200", "Successfuly updated")); 
		}
	}
	

	@PostMapping(value="/updateBulk" , produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	private ResponseEntity<?> updateBulkEmployee(@RequestBody List<Employee> employeeList){
		List<EmployeeDTO> updatedList = new ArrayList<EmployeeDTO>();
		for (int i = 0; i < employeeList.size(); i++) {
			employeeService.updateEmployee(employeeList.get(i));
			EmployeeDTO dto = mapper.map(employeeList, EmployeeDTO.class);
			dto.setStatus("TRUE");
			dto.setId(employeeList.get(i).getId());
			dto.setEmail(employeeList.get(i).getEmail());
			dto.setFirstName(employeeList.get(i).getFirstName());
			dto.setLastName(employeeList.get(i).getLastName());
			updatedList.add(dto);
		}
		return new ResponseEntity<List<EmployeeDTO>>(updatedList , HttpStatus.OK);
		}
	
	
	
	
}
