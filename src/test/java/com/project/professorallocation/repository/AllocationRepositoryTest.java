package com.project.professorallocation.repository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;

import com.project.professorallocation.entity.Allocation;
import com.project.professorallocation.entity.Course;
import com.project.professorallocation.entity.Professor;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
@TestPropertySource(locations = "classpath:application.properties")
public class AllocationRepositoryTest {

	SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
	
	@Autowired
	private AllocationRepository allocationRepository;
	@Autowired
	private ProfessorRepository professorRepository;
	@Autowired
	private CourseRepository courseRepository;
	
	@Test
	void testCreate() throws ParseException
	{
		//Arrange
		Professor professor = professorRepository.getById(4L);
		Course course = courseRepository.getById(1L);
		
		Allocation allocation = new Allocation();
		allocation.setDayOfWeek(DayOfWeek.MONDAY);
		allocation.setStart(sdf.parse("11:20"));
		allocation.setEnd(sdf.parse("12:20"));
		allocation.setProfessor(professor);
		allocation.setCourse(course);
		
		// Act
		allocation = allocationRepository.save(allocation);

		// Print
		System.out.println(allocation);
	}
	
	@Test
	void testUpdate() throws ParseException
	{
		//Arrange
		Professor professor = professorRepository.getById(5L);
		Course course = courseRepository.getById(1L);
		
		Allocation allocation = allocationRepository.getById(1L);
		allocation.setDayOfWeek(DayOfWeek.THURSDAY);
		allocation.setStart(sdf.parse("11:20"));
		allocation.setEnd(sdf.parse("12:20"));
		allocation.setProfessor(professor);
		allocation.setCourse(course);
		
		// Act
		allocation = allocationRepository.save(allocation);

		// Print
		System.out.println(allocation);
	}
	
	@Test
	public void testRead(){
		
		//Arrange
		Long id = 1L;
				
		// Act
		List<Allocation> allocation = allocationRepository.findAll();
		Optional<Allocation> allocationByID = allocationRepository.findById(id);
		//Optional<allocation> allocationByName = allocationRepository.findByNameContainingIgnoreCase(name);
		
		// Print
		System.out.println("-------------");
		System.out.println(allocationByID.orElse(null));
		System.out.println("-------------");
		System.out.println(allocation);
	}
	
	@Test
	public void testDeleteById(){
		
		//Arrange
		Long id = 1L;
		
		// Act
		allocationRepository.deleteById(id);
	
	}
	
	@Test
	public void testDeleteAll(){
		
		// Act
		allocationRepository.deleteAll();
	
	}
	
}
