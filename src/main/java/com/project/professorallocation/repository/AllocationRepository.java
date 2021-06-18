package com.project.professorallocation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.professorallocation.entity.Allocation;

@Repository
public interface AllocationRepository extends JpaRepository<Allocation, Long> {

	//Search allocations for professor
	List<Allocation> findByProfessorNameIgnoreCase(String professorName);
	
	//Search allocations for course
	List<Allocation> findByCourseNameIgnoreCase(String courseName);
	
}
