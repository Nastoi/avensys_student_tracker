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
				<h2>Add Student Form</h2>
			</div>
		</div>
		
		<div id="container" >
			<h3>Add Student</h3>
			<form action="StudentControllerServlet" method="GET">
			<input type="hidden" name="command" value="ADD" />
			
			<table>
				<tr>
					<td>First Name</td>
					<td><input type="text" name="firstname" ></td>
				</tr>
				<tr>
					<td>Last Name</td>
					<td><input type="text" name="lastname" ></td>
				</tr>
				<tr>
					<td>Email</td>
					<td><input type="text" name="email" ></td>
				</tr>
				<tr>
					<td></td>
					<td><input class="save" type="submit" value="SAVE" ></td>
				</tr>
			
			</table>
				
			</form>
			
		</div>
		
		<div style="clear:both;">
			<a href="StudentControllerServlet">Back to list</a>
		</div>
	</body>

</html>