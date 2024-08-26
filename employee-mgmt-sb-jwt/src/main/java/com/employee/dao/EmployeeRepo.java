package com.employee.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.employee.entity.Employee;


 
public interface EmployeeRepo extends JpaRepository<Employee, Integer> {

	 
	    @Modifying
	    @Transactional
	    @Query("delete from Employee e where e.id in(:integers)")
	    void deleteByIdIn(List<Integer> integers);
	    
	    public Employee findByUsername(String username);
	
//	@Modifying
//	@Transactional
//	@Query(nativeQuery = true, value ="delete from employee e where e.id in(:integers)")
//	void deleteByIdIn(List<Long> itemIds);

//	    @Modifying
//	    @Transactional
//	    @Query("update  Employee e set e.active = false where e.id in(:integers)")
//	    void softDeleteAllIds(List<Integer> integers);
	

}
