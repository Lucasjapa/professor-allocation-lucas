package com.project.professorallocation.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.project.professorallocation.entity.Department;
import com.project.professorallocation.entity.Professor;
import com.project.professorallocation.repository.DepartmentRepository;
import com.project.professorallocation.repository.ProfessorRepository;

import lombok.RequiredArgsConstructor;
import validations.ProfessorValidator;

@Service
@RequiredArgsConstructor
public class ProfessorService {

	private final ProfessorRepository professorRepository;
	private final DepartmentRepository departmentRepository;
	
	
	//---------------CREATE---------------
	public Professor create(Professor professor) throws Exception {
		
		professor.setId(null);
		return saveInternal(validation(professor));
	}
	//------------------------------------
	
	
	//---------------UPDATE---------------
	public Professor update(Professor professor) throws Exception {

		ProfessorValidator.checkProfessorExistById(professor, professorRepository.existsById(professor.getId()));
		return saveInternal(validation(professor));
	}
	//------------------------------------
	
	
	//----------------READ----------------
	public List<Professor> findAll(){
		return professorRepository.findAll();
	}
	
	public Professor findById(Long professorId) throws Exception {
		
		Optional<Professor> professor = professorRepository.findById(professorId);
		ProfessorValidator.checkProfessorExist(professor);
		
		return professor.orElse(null);
	}
	//------------------------------------
	
	//----------------DELETE----------------
	public void deleteById(Long professorId) throws Exception {
		
		Optional<Professor> professor = professorRepository.findById(professorId);
		ProfessorValidator.checkProfessorExist(professor);
		
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
	
	private Professor validation(Professor professor) throws Exception {
		
		Optional<Department> department = departmentRepository.findById(professor.getDepartment().getId());
		ProfessorValidator.validateProfessor(professor,department);
		
		return professor;
	}
}
