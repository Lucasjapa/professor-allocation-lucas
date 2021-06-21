package com.project.professorallocation.service;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import com.project.professorallocation.entity.Department;
import com.project.professorallocation.entity.Professor;

@SpringBootTest
@TestPropertySource(locations = "classpath:application.properties")
public class ProfessorServiceTest {
	
	@Autowired
	private ProfessorService professorService;
	
	@Test
	public void findAll() {
		// Act
		List<Professor> professors = professorService.findAll();
		
		// Print
		System.out.println(professors);
	}
	
	@Test
	public void create() {
		Department dep = new Department();
		dep.setId(3L);
		Professor prof = new Professor();
		prof.setName("Amirton 1");
		prof.setCpf("11122233325");
		prof.setDepartment(dep);
		
		professorService.create(prof);
		
		// Print
		System.out.println(prof);
	}

}
