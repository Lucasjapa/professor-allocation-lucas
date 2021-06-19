package com.project.professorallocation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.professorallocation.entity.Allocation;

@Repository
public interface AllocationRepository extends JpaRepository<Allocation, Long> {

	// É uma opção, mas o que foi solicitado é pelo ID de professor
	// De qualquer forma, mostra que você entendeu
	//Search allocations for professor
	List<Allocation> findByProfessorNameIgnoreCase(String professorName);
	
	// É uma opção, mas o que foi solicitado é pelo ID de curso
	// De qualquer forma, mostra que você entendeu
	//Search allocations for course
	List<Allocation> findByCourseNameIgnoreCase(String courseName);
	
}
