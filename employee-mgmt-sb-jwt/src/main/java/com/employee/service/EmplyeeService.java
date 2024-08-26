package com.employee.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee.customException.BusinessException;
import com.employee.customException.EmployeeNotFoundException;
import com.employee.dao.EmployeeRepo;
import com.employee.entity.Employee;

@Service
public class EmplyeeService {
	
	
	
	@Autowired
	private EmployeeRepo employeeRepo;

	public List<Employee> getallEmployee() {
		 List<Employee> findAll = employeeRepo.findAll();
		return findAll;
	}

	public Employee getEmployeeById(int id) {
	 Optional<Employee> findById = employeeRepo.findById(id);
	 if(!findById.isPresent()) {
		 throw new EmployeeNotFoundException("Employee is not found in DB");
	 }
		return findById.get();
	}

	public Employee saveEmployee(Employee employee) {
		if(employee.getName().isEmpty() || employee.getName().length() == 0) {
			throw new BusinessException("Input Field are empty");
		}
		Employee save = employeeRepo.save(employee);
		
		return save;
	}

	public Employee updateEmployee(Employee employee, int id) {
		  Employee employee2 = employeeRepo.findById(id).get();
		  employee2.setName(employee.getName());
		  employee2.setAddress(employee.getAddress());
		  employee2.setAge(employee.getAge());
		  Employee update = employeeRepo.save(employee2);
		return update;
	}

	public boolean deleteEmployee(int id) {
		
		employeeRepo.deleteById(id);
		
		
		return true;
		 
		
	}
	
//This 1 option
	
	
//	public void deleteAllBYIds(List<Integer> integers) {
//		 for (Integer ids : integers) {
//			  Employee emp = employeeRepo.findById(ids).orElse(null);
//			  employeeRepo.delete(emp);
//			  System.out.println("hgudsgudgxhsgdhsag");
//			  }
//		
//	}

	 // 2 option 
	
	  public void deleteAllBYIds(List<Integer> integers) {
        employeeRepo.deleteByIdIn(new ArrayList<>(integers));
       // employeeRepo.softDeleteAllIds(integers);

        System.out.println("deleted adnlakdjakldlas");
    }
}
 


