package in.Ineuron.servicefactory;

import in.Ineuron.service.IStudentService;
import in.Ineuron.service.StudentServiceImpl;


//Abstraction logic of implementation
public class StudentServiceFactory {

	//make constuctor private to avoid object creation
	private StudentServiceFactory() {

	}

	private static IStudentService studentService = null;

	public static IStudentService getStudentService() {
		
		//singleton pattern code
		if (studentService == null) {
			studentService = new StudentServiceImpl();
		}
		return studentService;
	}

}
