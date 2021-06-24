package validations;

import java.util.Optional;

import com.project.professorallocation.entity.Department;
import com.project.professorallocation.entity.Professor;
import com.project.professorallocation.repository.ProfessorRepository;

public class ProfessorValidator {

	public static ProfessorRepository professorRepository;
	
	public static void validateProfessor(Professor professor, Optional<Department> department) throws Exception {

		if(professor.getName() == null || professor.getName().equals("")) {
			throw new Exception("The name entered is not valid.");
		}
		if(professor.getCpf().length() != 11) {
			throw new Exception("Invalid Cpf, incorrect data.");
		}
		DepartmentValidator.checkDepartmentExist(department);
	}
	
	public static void checkProfessorExist(Optional<Professor> professor) throws Exception {
		// Na minha máquina, o método isEmpty não existe. Na minha máquina, o correto seria:
		// if(!professor.isPresent()) {
		if(professor.isEmpty()) {
			throw new Exception("Professor does not exist");
		}
	}
	
	public static void checkProfessorExistById(Professor professor, Boolean exist) throws Exception {

		if(professor.getId() == null || !exist) {
			throw new Exception("Professor does not exist");
		}
	}
}
