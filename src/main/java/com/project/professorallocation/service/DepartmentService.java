package com.project.professorallocation.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.project.professorallocation.entity.Department;
import com.project.professorallocation.repository.DepartmentRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DepartmentService {
	
	public final DepartmentRepository departmentRepository;
	
	//---------------CREATE---------------
	public Department create(Department department) {
		department.setId(null);
		return departmentRepository.save(department);
	}
	//------------------------------------

	//---------------UPDATE---------------
	public Department update(Department department) {
		return departmentRepository.save(department);
	}
	//------------------------------------


	//----------------READ----------------
	public List<Department> findAll(){
		return departmentRepository.findAll();
	}

	public Department findById(Long id) {
		Optional<Department> department = departmentRepository.findById(id);
		return department.orElse(null);
	}
	//------------------------------------

	//----------------DELETE----------------
	public void deleteById(Long id) {
		departmentRepository.deleteById(id);
	}

	public void deleteALL() {
		departmentRepository.deleteAll();
	}
	//--------------------------------------

}
