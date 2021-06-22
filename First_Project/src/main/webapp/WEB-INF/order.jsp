<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Place An Order</title>
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
			<div class ="order-form">
				<h1>What Would You Like ${user.firstName}?</h1>
				
				<form:form action="/order" method="post" modelAttribute="order" > 
				<div class="order-form-row">
					<div class="order-col-first">	
					<form:label path="item">Item</form:label>
					<form:errors path="item"></form:errors>
					<form:select class="form-control" path="item">
						<form:option value="">Choose</form:option>
						<c:forEach items="${dishes}" var="item">
							<form:option value="${item.name}"></form:option>
						</c:forEach>
					</form:select>
					</div>	
					
					
					
					<div class="order-col-first">	
					<form:label path="meat">Meat</form:label>
					<form:errors path="meat"></form:errors>
					<form:select class="form-control" path="meat">
						<form:option value="">Choose</form:option>
						<c:forEach items="${meats}" var="meat">						
							<form:option value="${meat.name}"></form:option>
						</c:forEach>
					</form:select>
					</div>									
					<div  class="order-col-first">
						<form:label path="quantity">Quantity</form:label>
						<form:errors path="quantity"></form:errors>
						<form:select class="form-control" path="quantity">
							<form:option value="0">0</form:option>
							<form:option value="1">1</form:option>
							<form:option value="2">2</form:option>
							<form:option value="3">3</form:option>
							<form:option value="4">4</form:option>
							<form:option value="5">5</form:option>
							<form:option value="6">6</form:option>
							<form:option value="7">7</form:option>
							<form:option value="8">8</form:option>
							<form:option value="9">9</form:option>
						<form:option value="10">10</form:option>
						</form:select>
					</div>
					<div class="order-col-first">	
					<form:label path="drink">Drink</form:label>
					<form:errors path="drink"></form:errors>
					<form:select class="form-control" path="drink">
						<form:option value="No Drink">No Drink</form:option>
						<c:forEach items="${drinks}" var="drink">						
							<form:option value="${drink.name}"></form:option>
						</c:forEach>
					</form:select>
					</div>									
					
				</div>	
				<form:button class="order-button">Add To Order</form:button>
				
			</form:form>
		</div>
		<h2 class="menu-h2">Menu</h2>
		<div class="menu-container">
			<div class="upper-menu">
				<div class="menu-items">
					<h3>Food</h3>
					<ul>
						<c:forEach items="${dishes}" var="dish">
							<li>${dish.name} ${dish.price}</li>
						</c:forEach>				
					</ul>
				</div>
				<div class="menu-items-m">
					<h3>Our Meats</h3>
					<ul>
						<c:forEach items="${meats}" var="meat">
							<li>${meat.name}</li>
						</c:forEach>		
					</ul>
				</div>
			</div>
		<div class="lower-menu">
			<div class="l-menu-items">			
				<h3>Drinks</h3>
				<ul>
					<c:forEach items="${drinks}" var="drink">
							<li> ${drink.name} ${drink.price}</li>
					</c:forEach>
				</ul>
			</div>		
		</div>	
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