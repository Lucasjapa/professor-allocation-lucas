package com.project.professorallocation.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.professorallocation.entity.Professor;
import com.project.professorallocation.repository.ProfessorRepository;

import lombok.RequiredArgsConstructor;
import validations.ProfessorValidator;

@Service
@RequiredArgsConstructor
public class ProfessorService {

	private final ProfessorRepository professorRepository;
	private final DepartmentService departmentService;
	
	
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
		
		return professorRepository.findById(professorId).orElseThrow(() -> new Exception("Professor does not exist"));
	}
	
	public List<Professor> findProfessorByName(String name){

		List<Professor> professors = professorRepository.findByNameContainingIgnoreCase(name);
		return professors;
	}
	//------------------------------------
	
	//----------------DELETE----------------
	public void deleteById(Long professorId) throws Exception {
		
		professorRepository.findById(professorId).orElseThrow(() -> new Exception("Professor does not exist"));		
		professorRepository.deleteById(professorId);
	}
	
	public void deleteALL() {
		professorRepository.deleteAllInBatch();
	}
	//--------------------------------------
	
	private Professor saveInternal(Professor professor) throws Exception {
		professor = professorRepository.save(professor);
		professor.setDepartment(departmentService.findById(professor.getDepartment().getId()));
		
		return professor;
	}
	
	private Professor validation(Professor professor) throws Exception {
		
		departmentService.findById(professor.getDepartment().getId());
		ProfessorValidator.validateProfessor(professor);
		
		return professor;
	}
}
