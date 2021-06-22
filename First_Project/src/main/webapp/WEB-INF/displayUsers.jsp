<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>List Of All Users</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css" integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">
<link rel="stylesheet" href="/css/style.css">
</head>
<body>
	<div id="wrapper">
	
		<div class="header">	
			<div class="nav-section">
			<h1>Taco Bus</h1>
				<ul class="nav-bar">
					<li><a href="/home/${user.id}">Home</a></li>
					<c:choose>
						<c:when test="${user.id==null}"><li><a href="/register">Register</a></li></c:when>
						<c:otherwise><li><a href="/${user.id}/edit">Account</a></li></c:otherwise>	
					</c:choose>		
					<c:if test="${user.id==1}"> 
						<li><a href= "/allUsers">All Users</a></li>
					</c:if>
					<c:if test="${user.id==1}"> 
						<li><a href= "/addFoodItem">Add Food</a></li>
					</c:if>
					<c:choose>
						<c:when test="${user.id==null}"><li><a href="/login">Login</a></li></c:when>
						<c:otherwise><li><a href="/logout">Logout</a></li></c:otherwise>	
					</c:choose>
				</ul>		
			</div>
			<img alt="logo" src="https://fee.org/media/17961/tacotruck.jpg?anchor=center&mode=crop&height=466&widthratio=2.0171673819742489270386266094&rnd=131303489940000000">
		</div>
		<div class="main-content">
			<h2>Displaying All Users</h2>
			<div class=" table-div">
				<table class="user-list-table">
					<thead>
						<tr>
							<th>Name</th>
							<th>City</th>
							<th>State</th>
							<th>Email</th>
							<th>Role</th>
							<th>Action</th>
						</tr>
					</thead>	
					<tbody>
						<c:forEach items="${users}" var="user">
						<tr>
							<td>${user.firstName} ${user.lastName}</td>
							<td>${user.city}</td>
							<td>${user.state}</td>
							<td>${user.email}</td>
							<c:forEach items="${user.roles}" var="roles">
								<td>${roles.name}</td>
							</c:forEach>
							<td><a href="/${user.id}/deleteUser">Delete</a></td>
						</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</body>
</html>