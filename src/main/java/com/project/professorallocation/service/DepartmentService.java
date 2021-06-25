package com.project.professorallocation.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.professorallocation.entity.Department;
import com.project.professorallocation.repository.DepartmentRepository;

import lombok.RequiredArgsConstructor;
import validations.DepartmentValidator;

@Service
@RequiredArgsConstructor
public class DepartmentService {
	
	public final DepartmentRepository departmentRepository;
	
	//---------------CREATE---------------
	public Department create(Department department) throws Exception {
		
		department.setId(null);
		DepartmentValidator.validateName(department);
		
		return saveInternal(department);
	}
	//------------------------------------

	//---------------UPDATE---------------
	public Department update(Department department) throws Exception {
		
		DepartmentValidator.checkDepartmentExistById(department, departmentRepository.existsById(department.getId()));
		DepartmentValidator.validateName(department);
		
		return saveInternal(department);
	}
	//------------------------------------


	//----------------READ----------------
	public List<Department> findAll(){
		return departmentRepository.findAll();
	}

	public Department findById(Long departmentId) throws Exception {
		
		return departmentRepository.findById(departmentId).orElseThrow(() -> new Exception("Department does not exist"));
	}
	
	public List<Department> findDepartmentByName(String name){

		List<Department> departments = departmentRepository.findByNameContainingIgnoreCase(name);
		return departments;
	}
	//------------------------------------

	//----------------DELETE----------------
	public void deleteById(Long departmentId) throws Exception {
		
		departmentRepository.findById(departmentId).orElseThrow(() -> new Exception("Department does not exist"));
		departmentRepository.deleteById(departmentId);
	}

	public void deleteALL() {
		departmentRepository.deleteAllInBatch();
	}
	//--------------------------------------

	private Department saveInternal(Department department) {
        return departmentRepository.save(department);
    }
}
