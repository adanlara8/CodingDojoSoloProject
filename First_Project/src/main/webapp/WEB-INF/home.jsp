<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Welcome</title>
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
			<h2 class="welcome">Welcome Back ${user.firstName}</h2>
			<div class="sub-container">
				<h2>View Our Menu</h2>
				<img class="sub-image" src="https://b.zmtcdn.com/data/menus/894/16668894/19e59b36089dc8b203638ed8e133f3ee.jpg" alt="our menu">
				<p class="content">lorem ipsum, quia dolor sit, amet, consectetur, adipisci velit, sed quia non numquam eius modi tempora incidunt, ut labore et dolore magnamv </p>
				<c:choose>
					<c:when test="${user.id==null}"><a class="sub-content-button" href="/menu">Menu</a></c:when>
					<c:otherwise><a class="sub-content-button" href="/order">Order</a></c:otherwise>
				</c:choose>
			</div>
			<div class="sub-container">
				<h2>Stop By</h2>
				<img class="sub-image" src="https://th.bing.com/th/id/OIP.pU2dhcEfV-Wtdv5VAgphAQHaKa?pid=ImgDet&rs=1" alt="our Truck">
			</div>
			<div class="sub-container">
				<h2>View Past Orders</h2>
				<img class="sub-image" src="https://www.seasonsandsuppers.ca/wp-content/uploads/2018/08/tacos-al-pastor-A-1170x780.jpg" alt="picture">
				<p class="content">lorem ipsum, quia dolor sit, amet, consectetur, adipisci velit, sed quia non numquam eius modi tempora incidunt, ut labore et dolore magnam </p>
				<c:choose>
					<c:when test="${user.id==null}"><a class="sub-content-button" href="/login">Login</a></c:when>
					<c:otherwise><a class="sub-content-button" href="/pastOrders/${user.id}">Past orders</a></c:otherwise>
				</c:choose>
		
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