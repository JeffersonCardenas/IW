<!DOCTYPE html>
<html>
<head>
	<%@ include file="../fragments/header.jspf" %>
    <link rel="stylesheet" href="resources/css/place.css" />
    <script>
    $('document').ready(function(){
    	loadXMLDoc('welcome');
    });
    </script>
</head>
<body>
	<div class="icon-bar five-up sticky">
	  <a class="item" onclick = "loadXMLDoc('welcome')">
	    <img src="resources/img/iconos_menu/welcome.png" >
	    <label>Bienvenido</label>
	  </a>
	  <a class="item" onclick = "loadXMLDoc('about')">
	    <img src="resources/img/iconos_menu/about.png" >
	    <label>Sobre Nosotros</label>
	  </a>
	  <a class="item" onclick = "loadXMLDoc('services')">
	    <img src="resources/img/iconos_menu/services.png" >
	    <label>Servicios</label>
	  </a>
	  <a class="item" onclick = "loadXMLDoc('map')">
	    <img src="resources/img/iconos_menu/map.png" >
	    <label>Donde Estamos</label>
	  </a>
	  <a class="item" onclick = "loadXMLDoc('booking')">
	    <img src="resources/img/iconos_menu/booking.png" >
	    <label>Reserva</label>
	  </a>
	</div>
	
	<div id = "content">
		
	</div>
<%@ include file="../fragments/footer.jspf" %>