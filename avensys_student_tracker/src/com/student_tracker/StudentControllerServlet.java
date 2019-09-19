package com.student_tracker;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class StudentControllerServlet
 */
@WebServlet("/StudentControllerServlet")
public class StudentControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private StudentDbUtil studentDbUtil;
	
	@Resource(name="jdbc/student_tracker")
	private DataSource dataSource;
	
	

	@Override
	public void init() throws ServletException {
		
		try {
		//Create our student dbutil and pass it to connection pool
		studentDbUtil = new StudentDbUtil(dataSource);
		}
		catch(Exception e)
		{
			throw new ServletException(e);  
		}
		
	}



	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
			try {
				//read the command parameter
				String theCommand = request.getParameter("command");
				
				//if the commad is missing then display listing
				if(theCommand==null)
				{
					theCommand="LOGINFORM";
				}
				
				//routing to appropriate method
				switch(theCommand)
				{
					case "LOGINFORM" :
						RequestDispatcher dispatch = request.getRequestDispatcher("/login.jsp");
						
						dispatch.forward(request, response);
							break;
					case "LOGIN":
							loginValidation(request,response);
							break;
					case "LIST" :
							listStudents(request, response);
							break;
					case "ADD" :
							addStudent(request,response);
							break;
					case "VIEW" :
							viewStudent(request,response);
							break;
					case "DELETE" :
							deleteStudent(request,response);
							break;
					case "UPDATE" :
							updateStudent(request,response);
					default:
						listStudents(request,response);
						
				}
				
			} catch (Exception e) {
				throw new ServletException(e);
			}
	
	}



	private void loginValidation(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		String tempRole = studentDbUtil.verifyUser(email,password);
		
		if(tempRole.equalsIgnoreCase("teacher"))
		{
			listStudents(request,response);
		}
		else{
			studentPage(request,response);
		}
		
	}



	private void studentPage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<Student> students = studentDbUtil.getStudents();
		request.setAttribute("Student_List", students);
			
		//get request dispacther
		RequestDispatcher dispatch = request.getRequestDispatcher("/studentPage.jsp");
		
		dispatch.forward(request, response);
	
	}



	private void viewStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {

		int id = Integer.parseInt(request.getParameter("id"));
		
		Student studentDetail =  studentDbUtil.viewStudent(id);
		
		request.setAttribute("userDetail",studentDetail);
		
		RequestDispatcher dispatch = request.getRequestDispatcher("update_student.jsp");
		dispatch.forward(request, response);
		
	}



	private void deleteStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
		int id = Integer.parseInt(request.getParameter("id"));
		
		studentDbUtil.deleteStudent(id);
		
		listStudents(request, response);
	
	}



	private void updateStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int id = Integer.parseInt(request.getParameter("id"));
		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		String email = request.getParameter("email");
		
		Student theStudent = new Student(id,firstname,lastname,email);
		
		studentDbUtil.updateStudent(theStudent);
		
		listStudents(request, response);
		
	}



	private void addStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//raed the student info from form data
		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		String email = request.getParameter("email");
		
		
		//create a new student object
		Student theStudent = new Student(firstname,lastname,email);
		
		//add the student to database
		studentDbUtil.addStudent(theStudent);
		
		//send back main page(the student list)
		listStudents(request, response);
		
		
	}



	private void listStudents(HttpServletRequest request, HttpServletResponse response) throws Exception{
			//get the students from StudentDbUtil
			List<Student> students = studentDbUtil.getStudents();
			request.setAttribute("Student_List", students);
				
			//get request dispacther
			RequestDispatcher dispatch = request.getRequestDispatcher("/list_student.jsp");
			
			dispatch.forward(request, response);
		
	}

}
