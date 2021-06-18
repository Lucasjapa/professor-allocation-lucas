package com.project.professorallocation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.professorallocation.entity.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

	//Buscar courses por uma parte do nome;
	List<Course> findByNameContainingIgnoreCase(String partName);
}
