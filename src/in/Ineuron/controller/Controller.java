package in.Ineuron.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.Ineuron.dto.Student;
import in.Ineuron.service.IStudentService;
import in.Ineuron.servicefactory.StudentServiceFactory;


@WebServlet("/controller/*")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 doService(request,response);
	}
	
		
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doService(request,response);
	}
	
	public void doService(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		  String operation = request.getRequestURI();
		  IStudentService service = StudentServiceFactory.getStudentService();
		  PrintWriter out = response.getWriter();
		  
		  if(operation.endsWith("/addform")) {
			  String name = request.getParameter("sname");
			  int age = Integer.parseInt(request.getParameter("sage"));
			  String address = request.getParameter("saddr");
			  Student std = new Student();
			  std.setSname(name);
			  std.setSage(age);
			  std.setSaddress(address);
			  String status = service.addStudent(std);
			  
			 
			  response.setContentType("text/html");
			  
			  if(status.equalsIgnoreCase("success")) {
				 RequestDispatcher requestDispatcher = request.getRequestDispatcher("../AddSuccess.html");
				 requestDispatcher.forward(request, response);
			  }
			  else {
				   RequestDispatcher requestDispatcher = request.getRequestDispatcher("../AddFail.html");
				   requestDispatcher.forward(request, response);
			  }
				   
		  }
		  
		  else if(operation.endsWith("searchform")) {
		       int id = Integer.parseInt(request.getParameter("sid"));
		       Student student = service.searchStudent(id);
		       if(student != null) {
		 
		    	     out.println("<body>");
		    	     out.println("<center>");
		    	     out.println("<table border='1'>");
		    	     out.println("<tr><th>ID</th><th>NAME</th><th>AGE</th><th>ADDRESS</th></tr>");
		    	     out.println("<tr><td>"+student.getSid()+"</td><td>"+student.getSname()+"</td><td>"+student.getSage()+"</td><td>"+student.getSage()+"</td></tr>");
		    	     out.println("</table>");
		    	     out.println("</center>");
		    	     out.println("</body>");
		       }
		       else {
		    	     RequestDispatcher requestDispatcher = request.getRequestDispatcher("../SearchFail.html");
		    	     requestDispatcher.forward(request, response);
		       }
	  }
		  
		  else if(operation.endsWith("deleteform")) {
			    int id = Integer.parseInt(request.getParameter("sid"));
			    String status = service.deleteStudent(id);
			   
			    if(status.equalsIgnoreCase("success")) {
			    	RequestDispatcher requestDispatcher = request.getRequestDispatcher("../Deleted.html");
			    	requestDispatcher.forward(request, response);
			    }
			    	
			    else if(status.equalsIgnoreCase("not found")) {
					RequestDispatcher requestDispatcher = request.getRequestDispatcher("../NotFound.html");
					requestDispatcher.forward(request, response);
				} else {
					RequestDispatcher requestDispatcher = request.getRequestDispatcher("../Fail.html");
					requestDispatcher.forward(request, response);
				}
			    	
			    
			    
		  }
		  
		  else if(operation.endsWith("editform")) {
			  int id = Integer.parseInt(request.getParameter("sid"));
			  Student student = service.searchStudent(id);
			  response.setContentType("text/html");
			  if(student != null) {
				  out.println("<form method='post' action='./controller/updateForm'>");
				  out.println("<table>");
				  out.println("<tr><th>ID</th><td>"+id+"</td></tr>");
				  out.println("<input type='hidden' name='sid' value='"+id+"'");
				  out.println("<tr><th>NAME</th><td><input type='text' name='sname' value='"+student.getSname()+"'></td></tr>");
				  out.println("<tr><th>AGE</th><td><input type='text' name='sage' value='"+student.getSage()+"'></td></tr>");
				  out.println("<tr><th>ADDRESS</th><td><input type='text' name='saddr' value='"+student.getSaddress()+"'></td></tr>");
				  out.println("<tr><td></td><td><input type='submit' value='update'></td></tr>");
				  out.println("</table>");
				  out.println("</form>");
			  }
			  else {
				  RequestDispatcher requestDispatcher = request.getRequestDispatcher("..EditFail.html");
				  requestDispatcher.forward(request, response);
			  }
		  }
		  else if(operation.endsWith("updateForm")) {
			    int id = Integer.parseInt(request.getParameter("sid"));
			    String name = request.getParameter("sname");
			    int age = Integer.parseInt(request.getParameter("sage"));
			    String address = request.getParameter("saddr");
			    Student student = new Student();
			    student.setSid(id);
			    student.setSname(name);
			    student.setSage(age);
			    student.setSaddress(address);
			   String status =  service.updateStudent(student);
			   if(status.equalsIgnoreCase("success")) {
				   RequestDispatcher requestDispatcher = request.getRequestDispatcher("../../UpdateSuccess.html");
				   requestDispatcher.forward(request, response);
			      
		  }
			  else {
				    RequestDispatcher requestDispatcher = request.getRequestDispatcher("../../UpdateFail.html");
				    requestDispatcher.forward(request, response); 
			      }
		  }
	}

}
