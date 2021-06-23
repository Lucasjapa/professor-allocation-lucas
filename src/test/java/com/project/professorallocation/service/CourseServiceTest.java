package com.project.professorallocation.service;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import com.project.professorallocation.entity.Course;

@SpringBootTest
@TestPropertySource(locations = "classpath:application.properties")
public class CourseServiceTest {

	@Autowired
	private CourseService courseService;
	
	@Test
	public void create() throws Exception {
		try {
		Course course = new Course();
		course.setName("voar");
		courseService.create(course);
		
		// Print
		System.out.println(course);
		
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	@Test
	public void update() throws Exception {
		try {
		Course course = new Course();
		course.setId(4L);
		course.setName("test");
		courseService.update(course);
		
		// Print
		System.out.println(course);
		
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	@Test
	public void readyById() throws Exception {
		try {
		Course course = new Course();
		
		course = courseService.findById(4L);
		
		// Print
		System.out.println(course);
		
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	@Test
	public void readyAll(){
		
		List<Course> course = courseService.findAll();
		
		// Print
		System.out.println(course);
		
	}
	
	@Test
	public void readyCourseByName(){
		
		List<Course> courses = courseService.findCourseByName("O");
		
		// Print
		System.out.println(courses);
		
	}
	
	@Test
	public void deletedById(){
		
		try {
			courseService.deleteById(1L);

			// Print
			System.out.println("Deletado com sucesso");

		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}	
	}
	
	@Test
	public void deletedALL(){
		
		courseService.deleteALL();
	}

}
