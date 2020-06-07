package dz.selma.springboot.cruddemo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dz.selma.springboot.cruddemo.entity.Employee;
import dz.selma.springboot.cruddemo.service.EmployeeService;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

	// inject employee dao
	@Autowired
	private EmployeeService employeeService;

	// use constructor injection
	@Autowired
	public EmployeeRestController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	// expose "/employees" and return list of employees
	@GetMapping("/employees")
	public List<Employee> getListEmployees(){
		return employeeService.findAll();
	}

	// expose "/employees/{employeeId}" and return a single employee
	@GetMapping("/employees/{employeeId}")
	public Employee getEmployee(@PathVariable int employeeId){
		
		Employee employee = employeeService.findById(employeeId);
		if (employee == null)
			throw new RuntimeException("Employee not found : " + employeeId);
		
		return employeeService.findById(employeeId);
		
	}
	
	// add a new employee
	@PostMapping("/employees")
	public Employee addEmployee(@RequestBody Employee employee) {
		
		// if they pass id to JSON, set up id to 0
		// to force save employee
		
		employee.setId(0);
		
		employeeService.save(employee);
		
		return employee;
	}

	// update existing employee
	@PutMapping("/employees")
	public Employee updateEmployee(@RequestBody Employee employee) {
		
		employeeService.save(employee);
		
		return employee;
	}
	
	@DeleteMapping("/employees/{employeeId}")
	public String deleteEmployee(@PathVariable int employeeId) {
		
		Employee employee = employeeService.findById(employeeId);
		
		if (employee == null)
			throw new RuntimeException("Employee not found : " + employeeId);
		
		employeeService.deleteById(employeeId);
		
		return "Deleted employee : " + employeeId;
	}
}
