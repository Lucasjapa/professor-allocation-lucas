package validations;

import java.util.List;

import com.project.professorallocation.entity.Allocation;
import com.project.professorallocation.entity.Course;
import com.project.professorallocation.entity.Professor;

public class AllocationValidator {

	public static void validateAllocation(Allocation newAllocation, Professor professor, Course course, List<Allocation> allocations) throws Exception {

		for(Allocation allocation: allocations) {
			if(!allocation.getId().equals(newAllocation.getId())
					&& allocation.getDayOfWeek().equals(newAllocation.getDayOfWeek())
					&& allocation.getStart().compareTo(newAllocation.getEnd()) < 0
					&& newAllocation.getStart().compareTo(allocation.getEnd()) < 0) {
				throw new Exception("Shock data, allocation cannot be recorded.");
			}
		}
	}
	
	public static void checkAllocationExistById(Allocation allocation, Boolean exist) throws Exception {

		if(allocation.getId() == null || !exist) {
			throw new Exception("Allocation does not exist");
		}
	}
}
