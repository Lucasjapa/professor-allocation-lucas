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

import com.project.professorallocation.entity.Department;
import com.project.professorallocation.entity.Professor;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
@TestPropertySource(locations = "classpath:application.properties")
public class ProfessorRepositoryTest {
	
	@Autowired
	private ProfessorRepository professorRepository;
	@Autowired
	private DepartmentRepository departmentRepository;
	
	@Test
	void testCreate()
	{
		//Arrange
		Department department = departmentRepository.getById(1L);
		
		Professor professor = new Professor();
		professor.setName("Amirton");
		professor.setCpf("11122233310");
		professor.setDepartment(department);
		
		// Act
		professor = professorRepository.save(professor);

		// Print
		System.out.println(professor);
	}
	
	@Test
	void testUpdate()
	{
		//Arrange
		Department department = departmentRepository.getById(2L);
		
		Professor professor = professorRepository.getById(1L);
		professor.setName("Pedro");
		professor.setCpf("11122233319");
		professor.setDepartment(department);
		
		// Act
		professor = professorRepository.save(professor);

		// Print
		System.out.println(professor);
	}
	
	@Test
	public void testRead(){
		
		//Arrange
		Long id = 1L;
				
		// Act
		List<Professor> professor = professorRepository.findAll();
		Optional<Professor> professorByID = professorRepository.findById(id);
		//Optional<Professor> professorByName = professorRepository.findByNameContainingIgnoreCase(name);
		
		// Print
		System.out.println("-------------");
		System.out.println(professorByID.orElse(null));
		System.out.println("-------------");
		System.out.println(professor);
	}
	
	@Test
	public void testDeleteById(){
		
		//Arrange
		Long id = 2L;
		
		// Act
		professorRepository.deleteById(id);
	
	}
	
	@Test
	public void testDeleteAll(){
		
		// Act
		professorRepository.deleteAll();
	
	}

}
