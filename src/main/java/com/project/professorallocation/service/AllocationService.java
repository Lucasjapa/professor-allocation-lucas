package com.project.professorallocation.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.project.professorallocation.entity.Allocation;
import com.project.professorallocation.entity.Course;
import com.project.professorallocation.entity.Professor;
import com.project.professorallocation.repository.AllocationRepository;
import com.project.professorallocation.repository.CourseRepository;
import com.project.professorallocation.repository.ProfessorRepository;

import lombok.RequiredArgsConstructor;
import validations.AllocationValidator;

@Service
@RequiredArgsConstructor
public class AllocationService {
	
	private final AllocationRepository allocationRepository;
	private final ProfessorRepository professorRepository;
	private final CourseRepository courseRepository;
	
	// Usar o serviço é mais interessante pois o findById feito para a camada
	// de serviço já traria a entidade aplicando alguma regra de negócio (se fosse o caso)
	private final ProfessorService professorService;
	private final CourseService courseService;
	
	//---------------CREATE---------------
	public Allocation create(Allocation allocation) throws Exception {

		allocation.setId(null);
		return saveInternal(validation(allocation));
	}
	//------------------------------------
	
	//---------------UPDATE---------------
	public Allocation update(Allocation allocation) throws Exception {

		AllocationValidator.checkAllocationExistById(allocation, allocationRepository.existsById(allocation.getId()));
		return saveInternal(validation(allocation));
	}
	//------------------------------------
	
	//----------------READ----------------
	public List<Allocation> findAll(){
		return allocationRepository.findAll();
	}

	public Allocation findById(Long allocationId) throws Exception {

		Optional<Allocation> allocation = allocationRepository.findById(allocationId);
		
		// Dica: A checagem que você fez aqui você poderia usar:
		//allocation.orElseThrow(() -> ew Exception("Allocation does not exist"));
		// E substituir as duas linhas
		AllocationValidator.checkAllocationExist(allocation);

		return allocation.orElse(null);
	}
	
	public List<Allocation> findAllocationByProfessorId(Long professorId) throws Exception {
		// Linha sem necessidade
		//professorService.findById(professorId);
		List<Allocation> allocations = allocationRepository.findByProfessorId(professorId);
		
		return allocations;
	}
	
	public List<Allocation> findAllocationByCourseId(Long courseId) throws Exception {
		// Linha sem necessidade
		//courseService.findById(courseId);
		List<Allocation> allocations = allocationRepository.findByCourseId(courseId);
		
		return allocations;
	}
	//------------------------------------
	
	//----------------DELETE----------------
	public void deleteById(Long id) throws Exception {

		// O melhor é usar: allocationRepository.existsById(id);
		// Mas não está errado
		Optional<Allocation> allocation = allocationRepository.findById(id);
		AllocationValidator.checkAllocationExist(allocation);

		allocationRepository.deleteById(id);
	}

	public void deleteALL() {
		allocationRepository.deleteAllInBatch();
	}
	//--------------------------------------
	
	private Allocation saveInternal(Allocation allocation) {
		allocation = allocationRepository.save(allocation);
		allocation.setProfessor(professorRepository.findById(allocation.getProfessor().getId()).orElse(null));
		allocation.setCourse(courseRepository.findById(allocation.getCourse().getId()).orElse(null));
		
		return allocation;
	}
	
	private Allocation validation(Allocation newAllocation) throws Exception {
		Optional<Professor> professor = professorRepository.findById(newAllocation.getProfessor().getId());
		Optional<Course> course = courseRepository.findById(newAllocation.getCourse().getId());
		List<Allocation> allocations = allocationRepository.findByProfessorId(newAllocation.getProfessor().getId());
		
		AllocationValidator.validateAllocation(newAllocation, professor, course, allocations);
			
		return newAllocation;
	}
}
