package validations;


import java.util.Optional;

import com.project.professorallocation.entity.Department;

public class DepartmentValidator {

	public static void validateName(Department department) throws Exception {

		if(department.getName() == null || department.getName().equals("")) {
			throw new Exception("The department entered is not valid.");
		}
	}
	
	public static void checkDepartmentExist(Optional<Department> department) throws Exception {

		if(department.isEmpty()) {
			throw new Exception("No departments were found.");
		}
	}
	
	public static void checkDepartmentExistById(Department department, Boolean exist) throws Exception {

		if(department.getId() == null || !exist) {
			throw new Exception("Department not found.");
		}
	}
	
}
