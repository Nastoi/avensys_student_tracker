<%@ page import="java.util.*,com.student_tracker.*"  %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
	<head>
		<title>Students</title>
		<link type="text/css" rel="stylesheet" href="css/style1.css" >
	</head>
	<body>
	
		<div id="wrapper">
			<div id="header">
				<h2>These are the students</h2>
			</div>
			</div>
		
		
	<div id="container">
	<div id="content">
	<!-- Add button -->
	<input class="add-student-button" type="button" Value="ADD STUDENT" onclick="window.location.href='add_student.jsp'">
	<input class="add-student-button" type="button" value="LOGOUT" onclick="window.location.href='login.jsp'">
		<table>
		<tr>
			<th>Firstname</th>
			<th>Lastname</th>
			<th>Email</th>
		</tr>
			<c:forEach var="tempStudent" items= "${ Student_List }">
				<tr>
					<td style="display:none"><input name="id" value="${ tempStudent.id }"></td>
					<td>${ tempStudent.firstName}</td>
					<td>${ tempStudent.lastName}</td>
					<td>${ tempStudent.email}</td>
						<td>
						<c:url var="updateLink" value="StudentControllerServlet">
							<c:param name="command" value="VIEW"/>
							<c:param name="id" value="${ tempStudent.id }" />
						</c:url>
							<!--  <a type="button" href="${ updateLink }">UPDATE</a> -->
							
							 <form action="StudentControllerServlet" method="GET">
								<input type="hidden" name="id" value="${ tempStudent.id }">
								<input type="hidden" name="command" value="VIEW">
								<input class="add-student-button" type="submit" value="UPDATE">
							</form>
						</td>
					<td>
						<c:url var="deleteLink" value="StudentControllerServlet">
							<c:param name="command" value="DELETE"/>
							<c:param name="id" value="${ tempStudent.id }" />
						</c:url>
						<!-- 	<a type="button" href="${ deleteLink }">DELETE</a>-->
						  <form action="StudentControllerServlet" method="GET">
							<input type="hidden" name="id" value="${ tempStudent.id }">
							<input class="add-student-button" type="submit" name="command" value="DELETE">
						</form> 
					</td>
					
				</tr>
			</c:forEach>
		
		</table>
		</div>
	</div>
	
	</body>



</html>