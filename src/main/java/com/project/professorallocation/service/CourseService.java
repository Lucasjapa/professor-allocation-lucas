package com.project.professorallocation.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.project.professorallocation.entity.Course;
import com.project.professorallocation.repository.CourseRepository;

import lombok.RequiredArgsConstructor;
import validations.CourseValidator;

@Service
@RequiredArgsConstructor
public class CourseService {

	public final CourseRepository courseRepository;
	
	//---------------CREATE---------------
	public Course create(Course course) throws Exception {
		
		course.setId(null);
		CourseValidator.validateName(course);
		
		return saveInternal(course);
	}
	//------------------------------------
	
	//---------------UPDATE---------------
	public Course update(Course course) throws Exception {
		
		CourseValidator.checkCourseExistById(course, courseRepository.existsById(course.getId()));
		CourseValidator.validateName(course);
		
		return saveInternal(course);
	}
	//------------------------------------


	//----------------READ----------------
	public List<Course> findAll(){
		return courseRepository.findAll();
	}

	public Course findById(Long id) throws Exception {
		
		Optional<Course> course = courseRepository.findById(id);
		CourseValidator.checkCourseExist(course);
		
		return course.orElse(null);
	}
	
	public List<Course> findCourseByName(String name){
		
		List<Course> courses = courseRepository.findByNameContainingIgnoreCase(name);
		return courses;
	}
	//------------------------------------
	
	//----------------DELETE----------------
	public void deleteById(Long id) throws Exception {
		
		Optional<Course> course = courseRepository.findById(id);
		CourseValidator.checkCourseExist(course);
		
		courseRepository.deleteById(id);
	}

	public void deleteALL() {
		courseRepository.deleteAllInBatch();
	}
	//--------------------------------------
	
	private Course saveInternal(Course course) {
        return courseRepository.save(course);
    }
}
