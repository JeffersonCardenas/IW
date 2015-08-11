<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<%@ include file="../fragments/header.jspf" %>
    <link rel="stylesheet" href="resources/css/business.css" />
<title>Locales</title>
</head>
<body>
	<div class="row">
		<div class = "large-12 columns panel">
			<div class = "row">
				<div class = "medium-3 columns">
					<a href="index"><img src = "resources/img/logo.png" alt = "reserving" height = "70px" width = "70px"></a>
					<br>
				</div>
			</div>
  			<div class="panel">
  				<h5>Hola ${usuario.nombre}</h5>
  				<p>Estos son tus locales</p>
  			</div>
		</div>
		
		<div class = "space"></div>
		
		<div class = "row">
		<c:choose>
			<c:when test="${not empty locales}">
				<c:forEach items="${locales}" var="u">
					<div class = "large-3 columns end">	
						<div class = "panel">
							<img class = "logo" src = "local/photo?id=${u.id}">
							<h3>${u.nombre} </h3>
							<p>${u.descripcion}</p>
							<a href="company?u=${u.id}" class="button expand">Ver</a>
						</div>
					</div>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<div class = "row">
					<div class = "small-6 large-centered columns">
						<h2>Tú imperio aún no ha empezado, tienes 0 locales</h2>
						<h3>Work Hard, Play Hard</h3>
					</div>
				</div>				
			</c:otherwise>
		</c:choose>
		</div>	
	</div>
<%@ include file="../fragments/footer.jspf" %>