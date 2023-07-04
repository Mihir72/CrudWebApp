package in.Ineuron.daofactory;

import in.Ineuron.dao.IStudentDao;
import in.Ineuron.dao.StudentDaoImpl;

public class StudentDaoFactory {

	private StudentDaoFactory() {}

	private static IStudentDao studentDao = null;

	public static IStudentDao getStudentDao() {
		if (studentDao == null) {
			studentDao = new StudentDaoImpl();
		}
		return studentDao;
	}
}
