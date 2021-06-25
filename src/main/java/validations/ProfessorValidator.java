package validations;

import com.project.professorallocation.entity.Professor;
import com.project.professorallocation.repository.ProfessorRepository;

public class ProfessorValidator {

	public static ProfessorRepository professorRepository;
	
	public static void validateProfessor(Professor professor) throws Exception {

		if(professor.getName() == null || professor.getName().equals("")) {
			throw new Exception("The name entered is not valid.");
		}
		if(professor.getCpf().length() != 11) {
			throw new Exception("Invalid Cpf, incorrect data.");
		}
	}
	
	public static void checkProfessorExistById(Professor professor, Boolean exist) throws Exception {

		if(professor.getId() == null || !exist) {
			throw new Exception("Professor does not exist");
		}
	}
}
