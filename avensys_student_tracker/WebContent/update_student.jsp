<!DOCTYPE html>
<html>
	<head>
		<title>Add Student</title>
		<link type="text/css" rel="stylesheet" href="css/style.css">
		<link type="text/css" rel="stylesheet" href="css/add-student-style.css">
	</head>
	<body>
		<div id="wrapper">
			<div id="header">
				<h2>Update form</h2>
			</div>
		</div>
		
		<div id="container" >
			<h3>update Student</h3>
			<form action="StudentControllerServlet" method="GET">
			<input type="hidden" name="command" value="UPDATE" />
			<input type="hidden" name="id" value="${ userDetail.id }">
			<table>
				<tr>
					<td>First Name</td>
					<td><input type="text" name="firstname" value="${ userDetail.firstName }"></td>
				</tr>
				<tr>
					<td>Last Name</td>
					<td><input type="text" name="lastname" value="${ userDetail.lastName }"></td>
				</tr>
				<tr>
					<td>Email</td>
					<td><input type="text" name="email" value="${ userDetail.email }"></td>
				</tr>
				<tr>
					<td></td>
					<td><input class="save" type="submit" value="UPDATE" ></td>
				</tr>
			
			</table>
				
			</form>
			
		</div>
		
		<div style="clear:both;">
			<a href="StudentControllerServlet">Back to list</a>
		</div>
	</body>

</html>