package in.Ineuron.service;

import in.Ineuron.dto.Student;

public interface IStudentService {
	
	// operations to be implemented
	public String addStudent(Student student);

	public Student searchStudent(Integer id);

	public String updateStudent(Student student);

	public String deleteStudent(Integer sid);

}
