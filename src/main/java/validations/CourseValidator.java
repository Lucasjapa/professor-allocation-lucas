package validations;

import java.util.Optional;

import com.project.professorallocation.entity.Course;

public class CourseValidator {

	public static void validateName(Course course) throws Exception {
		
		if(course.getName() == null || course.getName().equals("")) {
			throw new Exception("The course entered is not valid.");
		}
	}
	
	public static void checkCourseExist(Optional<Course> course) throws Exception {
		
		// Na minha máquina, o método isEmpty não existe. Na minha máquina, o correto seria:
		// if(!course.isPresent()) {
		if(course.isEmpty()) {
			throw new Exception("No courses were found.");
		}
	}
	
	public static void checkCourseExistById(Course course, Boolean exist) throws Exception {
		
		if(course.getId() == null || !exist) {
			throw new Exception("Course not found.");
		}
	}
	
	
}
