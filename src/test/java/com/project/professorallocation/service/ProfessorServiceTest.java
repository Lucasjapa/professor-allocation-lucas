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
	public void create() {
		try {
			Department department = new Department();
			department.setId(6L);
			
			Professor professor = new Professor();
			professor.setName("bina");
			professor.setCpf("10023233322");
			professor.setDepartment(department);
			
			professorService.create(professor);
			
			// Print
			System.out.println(professor);
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	@Test
	public void update() throws Exception {
		try {
			//Arrange
			Department department = new Department();
			department.setId(5L);
			
			Professor professor = professorService.findById(16L);
			professor.setName("haha");
			professor.setCpf("11023233322");
			professor.setDepartment(department);

			// Act
			professorService.update(professor);

			// Print
			System.out.println(professor);

		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	@Test
	public void readyById() throws Exception {
		try {
		Professor professor = new Professor();
		professor = professorService.findById(13L);
		
		// Print
		System.out.println(professor);
		
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	@Test
	public void readyAll(){
		
		List<Professor> professor = professorService.findAll();
		
		// Print
		System.out.println(professor);
		
	}
	
	@Test
	public void deletedById(){
		try {
			professorService.deleteById(11L);

			// Print
			System.out.println("Deletado com sucesso");

		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}	
	}
	
	@Test
	public void deletedALL(){
		
		professorService.deleteALL();
	}

}
