<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Account Information</title>
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
					<li><a href = "/order">Order</a></li>
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
			<h2>Update you Information</h2>
			<div class="form-background">	
				<form:form action="/${user.id}/edit" method="post" modelAttribute="user" > 
				<div class="row">
	   				<div class="col-first">				
						<form:errors path="firstName"></form:errors>
						<form:input class="form-control" placeholder="First Name" path="firstName"></form:input>
					</div>
					<div class="col-last">						
						<form:errors path="lastName"></form:errors>
						<form:input class="form-control" placeholder="Last Name" path="lastName"></form:input>
					</div>
				</div>
				<div class="col-email">					
					<form:errors path="email"></form:errors>
					<form:input class="form-control" placeholder="Email" path="email"></form:input>
				</div>
	   			<div class="col-address">					
						<form:errors path="address"></form:errors>
						<form:input class="form-control" placeholder="Address" path="address"></form:input>
				</div>
				<div class="row">
					<div class="col-city">					
					<form:errors path="city"></form:errors>
					<form:input class="form-control" placeholder="City" path="city"></form:input>
					</div>
					<div class="col-state">					
				        <form:errors path="state"/>
				        <form:select class="form-control" path="state">
				        	<form:option value="">State</form:option>
				      		<form:option value="AL">AL</form:option>
				      		<form:option value="Ak">AK</form:option>
				      		<form:option value="AS">AS</form:option>
				      		<form:option value="AZ">AZ</form:option>
				      		<form:option value="CA">CA</form:option>
				      		<form:option value="CO">CO</form:option>
				      		<form:option value="CT">CT</form:option>
				      		<form:option value="DE">DE</form:option>
				      		<form:option value="DC">DC</form:option>
				      		<form:option value="FL">FL</form:option>
				      		<form:option value="GA">GA</form:option>
				      		<form:option value="GU">GU</form:option>
				      		<form:option value="HI">HI</form:option>
				      		<form:option value="ID">ID</form:option>
				      		<form:option value="IL">IL</form:option>
				      		<form:option value="IN">IN</form:option>
				      		<form:option value="IA">IA</form:option>
				      		<form:option value="KS">KS</form:option>
				      		<form:option value="KY">KY</form:option>
				      		<form:option value="LA">LA</form:option>
				      		<form:option value="ME">ME</form:option>
				      		<form:option value="MD">MD</form:option>
				      		<form:option value="MA">MA</form:option>
				      		<form:option value="MI">MI</form:option>
				      		<form:option value="MN">MN</form:option>
				      		<form:option value="MS">MS</form:option>
				      		<form:option value="MO">MO</form:option>
				      		<form:option value="MT">MT</form:option>
				      		<form:option value="NE">NE</form:option>
				      		<form:option value="NV">NV</form:option>
				      		<form:option value="NH">NH</form:option>
				      		<form:option value="NJ">NJ</form:option>
				      		<form:option value="NM">NM</form:option>
				      		<form:option value="NY">NY</form:option>
				      		<form:option value="NC">NC</form:option>
				      		<form:option value="ND">ND</form:option>
				      		<form:option value="MP">MP</form:option>
				      		<form:option value="OH">OH</form:option>
				      		<form:option value="OK">OK</form:option>
				      		<form:option value="OR">OR</form:option>
				      		<form:option value="PA">PA</form:option>
				      		<form:option value="PR">PR</form:option>
				      		<form:option value="RI">RI</form:option>
				      		<form:option value="SC">SC</form:option>
				      		<form:option value="SD">SD</form:option>
				      		<form:option value="TN">TN</form:option>
				      		<form:option value="TX">TX</form:option>
				      		<form:option value="UT">UT</form:option>
				      		<form:option value="VT">VT</form:option>
				      		<form:option value="VA">VA</form:option>
				      		<form:option value="VI">VI</form:option>
				      		<form:option value="WA">WA</form:option>
				      		<form:option value="WV">WV</form:option>
				      		<form:option value="WI">WI</form:option>
				      		<form:option value="WY">WY</form:option>
				        </form:select>
			   		</div>
			   	</div>
				<div class="button-row">	   				
					<div class="edit-button">
						<form:button class="reg-button">Update</form:button>
					</div>
				</div>
			</form:form>
		</div>
	</div>
		
	<div class="footer">
		<p>Contact Us</p>
			<ul class="footer-ul">
				<li class="footer-li"><img class="icon" src="https://brandpalettes.com/wp-content/uploads/2018/05/facebook_color_codes.png" alt="icon"><a href="/">FaceBook</a></li>
				<li class="footer-li"><img class="icon" src="https://th.bing.com/th/id/Rfb6ca24911710c6cc8355e4e1a574fc2?rik=erOGCNR4oREMXA&riu=http%3a%2f%2f1000logos.net%2fwp-content%2fuploads%2f2017%2f06%2fLogo-Twitter.jpg&ehk=CWjaAxmMAN774TK9cjhZMOaDU96b7oh4srAScg5nOf0%3d&risl=&pid=ImgRaw" alt="icon"><a href="/">Twitter</a></li>
				<li class="footer-li"><img class="icon" src="https://upload.wikimedia.org/wikipedia/commons/thumb/e/e7/Instagram_logo_2016.svg/1200px-Instagram_logo_2016.svg.png" alt="icon"><a href="/">Instagram</a></li>
			</ul>
		</div>			
	</div>	
</body>
</html>