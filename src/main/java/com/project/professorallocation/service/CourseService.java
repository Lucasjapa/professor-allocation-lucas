package com.project.professorallocation.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.professorallocation.entity.Course;
import com.project.professorallocation.repository.CourseRepository;

import lombok.RequiredArgsConstructor;
import com.project.professorallocation.validations.CourseValidator;

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
	public List<Course> findAll(String name){
		if (name == null){
			return courseRepository.findAll();
		}else{
			return courseRepository.findByNameContainingIgnoreCase(name);
		}
	}

	public Course findById(Long courseId) throws Exception {

		return courseRepository.findById(courseId).orElseThrow(() -> new Exception("Course does not exist"));
	}
	//--------------------------------------

	//----------------DELETE----------------
	public void deleteById(Long courseId) throws Exception {
		
		courseRepository.findById(courseId).orElseThrow(() -> new Exception("Course does not exist"));
		courseRepository.deleteById(courseId);
	}

	public void deleteALL() {
		courseRepository.deleteAllInBatch();
	}
	//--------------------------------------
	
	private Course saveInternal(Course course) {
        return courseRepository.save(course);
    }
}
