package com.project.professorallocation.repository;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;

import com.project.professorallocation.entity.Professor;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
@TestPropertySource(locations = "classpath:application.properties")
public class ProfessorRepositoryTest {
	
	@Autowired
	private ProfessorRepository professorRepository;
	
	@Test
	void testCreate()
	{
		//FindAll Read (CRUD)
		List<Professor> professors = professorRepository.findAll();
		
		System.out.println(professors);
	}
	
	@Test
	void testCourse()
	{
		//FindAll Read (CRUD)
		List<Professor> professors = professorRepository.findAll();
		
		System.out.println(professors);
	}
	
	@Test
	void test2()
	{
		//Find byID (CRUD)
		Long id = 1L;
		
		Optional<Professor> optional = professorRepository.findById(id);
		
		Professor p = optional.orElse(null); 
	}

}
