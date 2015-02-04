<!DOCTYPE html>
<html>
<head>
	<%@ include file="../fragments/header.jspf" %>
	<%-- <script src="resources/js/funciones.js"></script> --%>
    <link rel="stylesheet" href="resources/css/search.css" />
</head>
<body>
	<div class = "row" id="panel">
		<div class = "large-12 columns panel">
			<div class = "row">
				<div class = "large-12 columns">
					<a href="index"><img src = "resources/img/logo.png" alt = "reserving" height = "70px" width = "70px"></a>
					<br>
				</div>
			</div>
			<div class = "row">
				<div class = "large-12 columns">
					<div class = "row">
						<div class = "large-4 columns">
							<label>Nombre
								<input type = "text" id="search" name = "search" placeholder="Nombre del sitio" autofocus>
							</label>
						</div>
						<div class = "large-4 columns">
							<label>Tipo
								<select name = "type" id="type">
						          <option value="0">Restaurante</option>
						          <option value="1">Peluqueria</option>
						          <option value="2">Evento</option>
						          <option value="3">Deportes</option>
						        </select>
							</label>
						</div>
						<div class = "large-4 columns">
							<a href="#" class = "button success expand" id = "submit">Buscar</a>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class = "space"></div>
	<div class = "row"  id="resulBusqueda">
		<c:choose>
			<c:when test="${not empty locales}">
				<c:forEach items="${locales}" var="u">
					<div class = "large-3 columns end">	
						<div class = "panel">
							<img class = "logo" src = "local/photo?id=${u.id}">
							<h3>${u.nombre} </h3>
							<p>${u.descripcion}</p>
							<a href="place?p=${u.id}" class="button expand">Ver</a>
						</div>
					</div>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<div class = "row">
					<div class = "small-6 large-centered columns">
						<h2>No Hay Locales de Este Tipo</h2>
					</div>
				</div>				
			</c:otherwise>
		</c:choose>
		<%-- 
			<p><img src = "resources/img/estrella.png" width = "20px"> <img src = "resources/img/estrella.png" width = "20px"> <img src = "resources/img/estrella.png" width = "20px"> <img src = "resources/img/estrella.png" width = "20px"></p>	
		--%>
	</div>
<%@ include file="../fragments/footer.jspf" %>