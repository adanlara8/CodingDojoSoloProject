<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Past Orders</title>
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
			<h2>Past orders and Favorites</h2>
			<div class="past-orders-background">
			<div class="past-order-header">
					<p>Your Favorites</p>
				</div>	
					
				<div class="past-orders-table">
			 		<table class="past-order"> 
			 			<thead>
			 				
			 			</thead>
			 			<tbody>
			 				<c:forEach items="${user_favorites}" var="favorite">
			 					<tr>
									<td>${favorite.quantity} ${favorite.meat} ${favorite.item} and a ${favorite.drink}</td> 			
								</tr>
							</c:forEach>
			 			</tbody>	 		
			 		</table>
		 		</div>
					 
			</div>
			<div class="past-orders-background">
				<div class="past-order-header">
					<p>Past Orders</p>
					<p>Date</p>
					<p>Add Favorites</p>
				</div>
				<div class="past-orders-table">
			 		<table class="past-order"> 
			 			<thead>
			 				
			 			</thead>
			 			<tbody>
			 				<c:forEach items="${past_orders}" var="past_order">
			 					<tr>
									<td>${past_order.quantity} ${past_order.meat} ${past_order.item} and a ${past_order.drink}</td>
									
									<c:choose>
										<c:when test="${user.favorites.contains(past_order)}">
											<td><a href="/pastOrders/${user.id}/unlike/${past_order.id}">Unlike</a></td>
										</c:when>
										<c:otherwise>
											<td><a href="/pastOrders/${user.id}/like/${past_order.id}">Like</a></td>
										</c:otherwise>	
									</c:choose>			
								</tr>
							</c:forEach>
			 			</tbody>	 		
			 		</table>
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