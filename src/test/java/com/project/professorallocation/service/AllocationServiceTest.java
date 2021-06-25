package com.project.professorallocation.service;

import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import com.project.professorallocation.entity.Allocation;
import com.project.professorallocation.entity.Course;
import com.project.professorallocation.entity.Professor;

@SpringBootTest
@TestPropertySource(locations = "classpath:application.properties")
public class AllocationServiceTest {
	
	SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
	
	@Autowired
	private AllocationService allocationService;
	
	@Autowired
	private ProfessorService professorService;
	
	@Autowired
	private CourseService courseService;
	
	@Test
	public void create() {
		try {
			Professor professor = professorService.findById(21L);
			Course course = courseService.findById(7L);
			
			Allocation allocation = new Allocation();
			allocation.setDayOfWeek(DayOfWeek.SATURDAY);
			allocation.setStart(sdf.parse("10:00"));
			allocation.setEnd(sdf.parse("14:00"));
			allocation.setProfessor(professor);
			allocation.setCourse(course);
			
			// Act
			allocation = allocationService.create(allocation);
			
			// Print
			System.out.println(allocation);
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	@Test
	public void update() throws Exception {
		try {
			//Arrange
			//Para definir se algum dado não vai ser alterado,  
			//só comentar a linha de set que não deseja alterar.
			Professor professor= professorService.findById(21L);
			Course course = courseService.findById(6L);
			
			Allocation allocation = allocationService.findById(7L);
			allocation.setDayOfWeek(DayOfWeek.SATURDAY);
			allocation.setStart(sdf.parse("11:00"));
			allocation.setEnd(sdf.parse("12:00"));
			allocation.setProfessor(professor);
			allocation.setCourse(course);

			// Act
			allocation = allocationService.update(allocation);

			// Print
			System.out.println(allocation);

		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	@Test
	public void readById() throws Exception {
		try {
		Allocation allocation = new Allocation();
		allocation = allocationService.findById(3L);
		
		// Print
		System.out.println(allocation);
		
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	@Test
	public void readAll(){
		
		List<Allocation> allocations = allocationService.findAll();
		
		// Print
		System.out.println(allocations);
	}
	
	@Test
	public void readByProfessorId() throws Exception {
		try {
			List<Allocation> allocations = allocationService.findAllocationByProfessorId(16L);
		
		// Print
		System.out.println(allocations);
		
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	@Test
	public void readByCourseId() throws Exception {
		try {
			List<Allocation> allocations = allocationService.findAllocationByCourseId(1L);
		
		// Print
		System.out.println(allocations);
		
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	@Test
	public void deletedById(){
		try {
			allocationService.deleteById(5L);

			// Print
			System.out.println("Deletado com sucesso");

		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}	
	}
	
	@Test
	public void deletedALL(){
		
		allocationService.deleteALL();
	}
	
	
}
