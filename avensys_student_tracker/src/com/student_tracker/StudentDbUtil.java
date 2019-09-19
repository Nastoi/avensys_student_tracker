package com.student_tracker;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.sql.DataSource;

public class StudentDbUtil {
	
	@Resource(name="jdbc/student_tracker")
	private DataSource dataSource;

	public StudentDbUtil(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public List<Student> getStudents() throws Exception
	{
		List<Student> students = new ArrayList<>();
		
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			//get connection
			con = dataSource.getConnection();
			
			//query
			String qry = "SELECT * FROM student_tracker.class ORDER BY FirstName";
			stmt = con.createStatement();
			
			//execute the query
			rs = stmt.executeQuery(qry);
			
			//process the result set
			while(rs.next())
			{
				//retrieve the data from result set
				int id = rs.getInt("Id");
				String firstname = rs.getString("FirstName");
				String lastname = rs.getString("lastname");
				String email = rs.getString("email");
				
				//create new objects and 
				Student tempStudent = new Student(id,firstname,lastname,email);
				
				//add it to the list
				students.add(tempStudent);
			}
			return students;
		}
		finally {
			//close connection
			close(con,stmt,rs);
		}
		
	}

	private void close(Connection con, Statement stmt, ResultSet rs) {
		try
		{
			if(rs!=null)
			{
				rs.close();
			}
			if(stmt!=null)
			{
				stmt.close();
			}
			if(con!=null)
			{
				con.close();
			}

		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public void addStudent(Student theStudent) throws Exception{
		
		
			Connection con = null;
			PreparedStatement pstmt = null;
			try {
				
				//create a connection
				con=dataSource.getConnection();
				
				//create the query
				String qry = "INSERT INTO student_tracker.class(FirstName,LastName,Email,Password,Role) VALUES(?,?,?,1,'student')";

				pstmt= con.prepareStatement(qry);
				
				//set parametes for the student
				pstmt.setString(1, theStudent.getFirstName());	
				pstmt.setString(2, theStudent.getLastName());		
				pstmt.setString(3, theStudent.getEmail());		
				
				//execute sql query
				pstmt.executeUpdate();
			}
			finally
			{
				close(con,pstmt,null);
			}
		
	}
	

	public void deleteStudent(int StudentId) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			
			//create a connection
			con=dataSource.getConnection();
			
			//create the query
			String qry = "DELETE FROM student_tracker.class WHERE id=?";
			
			pstmt= con.prepareStatement(qry);
			
			//set parametes for the student
			pstmt.setInt(1, StudentId);		
			
			//execute sql query
			pstmt.executeUpdate();
			
		}
		finally
		{
			close(con,pstmt,null);
		}
		
	}

	public Student viewStudent(int StudentId) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		Student theStudent = new Student();
		try {
			
			//create a connection
			con=dataSource.getConnection();
			
			//create the query
			String qry = "SELECT * FROM student_tracker.class WHERE id=?";
			
			pstmt= con.prepareStatement(qry);
			
			//set parametes for the student
			pstmt.setInt(1, StudentId);		
			
			//execute sql query
			rs = pstmt.executeQuery();
			
			if(rs.next())
			{
				theStudent.setId(rs.getInt("Id"));
				theStudent.setFirstName(rs.getString("FirstName"));
				theStudent.setLastName(rs.getString("LastName"));
				theStudent.setEmail(rs.getString("Email"));
			}
			
			return theStudent;
		}
		finally
		{
			close(con,pstmt,rs);
		}
		
	}

	public void updateStudent(Student theStudent) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			
			//create a connection
			con=dataSource.getConnection();
			
			//create the query
			String qry = "UPDATE student_tracker.class SET firstname=?, lastname=?, email=? WHERE id=?";
			
			pstmt= con.prepareStatement(qry);
			
			//set parameters for the student
			pstmt.setString(1, theStudent.getFirstName() );	
			pstmt.setString(2, theStudent.getLastName() );	
			pstmt.setString(3, theStudent.getEmail() );
			pstmt.setInt(4, theStudent.getId() );	
			
			//execute sql query
			pstmt.executeUpdate();
			
		}
		finally
		{
			close(con,pstmt,null);
		}
		
		
	}

	public String verifyUser(String email, String password) throws SQLException {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String role=null;
		try {
			
			//create a connection
			con=dataSource.getConnection();
			
			//create the query
			String qry = "SELECT * FROM student_tracker.class WHERE Email=? AND Password=?";
			
			pstmt= con.prepareStatement(qry);
			
			//set parameters for the student
			pstmt.setString(1, email);	
			pstmt.setString(2, password);		
			
			//execute sql query
			rs = pstmt.executeQuery();
			
			while(rs.next())
			{
				role = rs.getString("Role");
				
			}
			
			
			return role;
		}
		finally
		{
			close(con,pstmt,rs);
		}
		
	}

	
}
