package com.project.professorallocation.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.project.professorallocation.entity.Department;
import com.project.professorallocation.entity.Professor;
import com.project.professorallocation.repository.DepartmentRepository;
import com.project.professorallocation.repository.ProfessorRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProfessorService {

	private final ProfessorRepository professorRepository;
	private final DepartmentRepository departmentRepository;
	
	
	//---------------CREATE---------------
	public Professor create(Professor professor) {
		professor.setId(null);
		professor = professorRepository.save(professor);
		
		Long departmentId = professor.getDepartment().getId();
		Department department = new Department();
		department = departmentRepository.findById(professor.getDepartment().getId()).orElse(null);
		//professor.setDepartment(departmentRepository.findById(professor.getDepartment().getId()).orElse(null));
		
		return professor;
		//return saveInternal(professor);
	}
	//------------------------------------
	
	
	//---------------UPDATE---------------
	public Professor update(Professor professor) {
		
		if(!professorRepository.existsById(professor.getId())) {

		}
		
		
		return saveInternal(professor);
	}
	//------------------------------------
	
	
	//----------------READ----------------
	public List<Professor> findAll(){
		return professorRepository.findAll();
	}
	
	public Professor findById(Long id) {
		Optional<Professor> professor = professorRepository.findById(id);
		return professor.orElse(null);
	}
	//------------------------------------
	
	//----------------DELETE----------------
	public void deleteById(Long professorId) {
		
		if(!professorRepository.existsById(professorId)) {

		}
		
		professorRepository.deleteById(professorId);
	}
	
	public void deleteALL() {
		professorRepository.deleteAllInBatch();
	}
	//--------------------------------------
	
	private Professor saveInternal(Professor professor) {
		professor = professorRepository.save(professor);
		professor.setDepartment(departmentRepository.findById(professor.getDepartment().getId()).orElse(null));
		
		return professor;
	}
}
