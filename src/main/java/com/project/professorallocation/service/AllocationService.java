package com.project.professorallocation.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.professorallocation.entity.Allocation;
import com.project.professorallocation.entity.Course;
import com.project.professorallocation.entity.Professor;
import com.project.professorallocation.repository.AllocationRepository;

import lombok.RequiredArgsConstructor;
import validations.AllocationValidator;

@Service
@RequiredArgsConstructor
public class AllocationService {
	
	private final AllocationRepository allocationRepository;
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

		return allocationRepository.findById(allocationId).orElseThrow(() -> new Exception("Allocation does not exist"));

	}
	
	public List<Allocation> findAllocationByProfessorId(Long professorId) throws Exception {
		
		List<Allocation> allocations = allocationRepository.findByProfessorId(professorId);
		
		return allocations;
	}
	
	public List<Allocation> findAllocationByCourseId(Long courseId) throws Exception {
		
		List<Allocation> allocations = allocationRepository.findByCourseId(courseId);
		
		return allocations;
	}
	//------------------------------------
	
	//----------------DELETE----------------
	public void deleteById(Long allocationId) throws Exception {

		allocationRepository.findById(allocationId).orElseThrow(() -> new Exception("Allocation does not exist"));
		allocationRepository.deleteById(allocationId);
	}

	public void deleteALL() {
		allocationRepository.deleteAllInBatch();
	}
	//--------------------------------------
	
	private Allocation saveInternal(Allocation allocation) throws Exception {
		allocation = allocationRepository.save(allocation);
		allocation.setProfessor(professorService.findById(allocation.getProfessor().getId()));
		allocation.setCourse(courseService.findById(allocation.getCourse().getId()));
		
		return allocation;
	}
	
	private Allocation validation(Allocation newAllocation) throws Exception {
		Professor professor = professorService.findById(newAllocation.getProfessor().getId());
		Course course = courseService.findById(newAllocation.getCourse().getId());
		List<Allocation> allocations = allocationRepository.findByProfessorId(newAllocation.getProfessor().getId());
		
		AllocationValidator.validateAllocation(newAllocation, professor, course, allocations);
			
		return newAllocation;
	}
}
