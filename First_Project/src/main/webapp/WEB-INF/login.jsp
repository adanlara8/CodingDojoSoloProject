<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login to Order</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css" integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">
<link rel="stylesheet" href="/css/style.css">

</head>
<body>
	<div id="wrapper">
	
		<div class="header">	
			<div class="nav-section">
			<h1>Taco Bus</h1>
				<ul class="nav-bar">
					<li><a href="/home">Home</a></li>
					<li><a href="/register">Don't Have an Account? Register Here</a></li>
				</ul>		
			</div>
			<img alt="logo" src="https://fee.org/media/17961/tacotruck.jpg?anchor=center&mode=crop&height=466&widthratio=2.0171673819742489270386266094&rnd=131303489940000000">
		</div>
		<div class="main-content">
			<h2>Login</h2>
			<div class="login-form-background">	
				<p>${error}</p>
			<form action="/login" method="post">				
		   		<div class="login-row">						
					<input class="form-control" placeholder="Email" name="loginEmail">
				</div>

				<div class="login-row">						
					<input type="password" class="form-control" placeholder="Password" name="loginPassword">
				</div>	
				<div class="login-row">						
				<button class="login-button">Login</button>
				</div>				
			</form>
		</div>
		<div class="specials">
			<h3>Try Our Daily Special</h3>
			<img alt="pic" src="https://b2303ztyja-flywheel.netdna-ssl.com/wp-content/uploads/2018/05/Burrito-Stock-Image.gif">
			<p>lorem ipsum, quia dolor sit, amet, consectetur, adipisci velit, sed quia non numquam eius modi tempora incidunt, ut labore et dolore magnam aliquam quaerat voluptatem.</p>
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