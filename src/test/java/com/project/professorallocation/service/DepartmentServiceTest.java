package com.project.professorallocation.service;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import com.project.professorallocation.entity.Department;

@SpringBootTest
@TestPropertySource(locations = "classpath:application.properties")
public class DepartmentServiceTest {

	@Autowired
	private DepartmentService departmentService;
	
	@Test
	public void create() throws Exception {
		try {
		Department department = new Department();
		department.setName("correr");
		departmentService.create(department);
		
		// Print
		System.out.println(department);
		
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	@Test
	public void update() throws Exception {
		try {
		Department department = new Department();
		department.setId(1L);
		department.setName("futebol");
		departmentService.update(department);
		
		// Print
		System.out.println(department);
		
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	@Test
	public void readyById() throws Exception {
		try {
		Department department = new Department();
		
		department = departmentService.findById(1L);
		
		// Print
		System.out.println(department);
		
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	@Test
	public void readyAll(){
		
		List<Department> department = departmentService.findAll();
		
		// Print
		System.out.println(department);
		
	}
	
	@Test
	public void readyDepartmentByName(){
		
		List<Department> departments = departmentService.findDepartmentByName("e");
		
		// Print
		System.out.println(departments);
		
	}
	
	@Test
	public void deletedById(){
		
		try {
			departmentService.deleteById(1L);

			// Print
			System.out.println("Deletado com sucesso");

		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}	
	}
	
	@Test
	public void deletedALL(){
		
		departmentService.deleteALL();
	}

}

