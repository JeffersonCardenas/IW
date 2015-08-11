<!DOCTYPE html>
<html>
<head>
	<%@ include file="../fragments/header.jspf" %>
    <link rel="stylesheet" href="resources/css/place.css" />
    <script>
    $('document').ready(function(){
    		var url = 'content/about';
    		var param = "p="+${q.id};
        	loadXMLDocs(url, param);
    });
    </script>
    <script>
    $(function(){
    	$(".item").click(function() {
    		var dest = $(this).attr("id").substring("i_".length);
    		var url = 'content/' + dest;
    		var param = "p="+${q.id};
        	loadXMLDocs(url, param);    		
    	})
    });
    </script>
</head>
<body>
	<div class="icon-bar five-up sticky">
	  <a class="item" href="index">
	    <img src="resources/img/iconos_menu/welcome.png" >
	    <label>Inicio</label>
	  </a>
	  <div class="item" id="i_about">
	    <img src="resources/img/iconos_menu/about.png" >
	    <label>Sobre Nosotros</label>
	  </div>
	  <div class="item" id="i_services">
	    <img src="resources/img/iconos_menu/services.png" >
	    <label>Servicios</label>
	  </div>
	  <div class="item" id="i_map">
	    <img src="resources/img/iconos_menu/map.png" >
	    <label>Donde Estamos</label>
	  </div>
	  <div class="item" id="i_booking">
	    <img src="resources/img/iconos_menu/booking.png" >
	    <label>Reserva</label>
	  </div>
	</div>
	
	<div id = "content">
		
	</div>
<%@ include file="../fragments/footer.jspf" %>