<!DOCTYPE html>
<html>
<head>
	<%@ include file="../fragments/header.jspf" %>
    <link rel="stylesheet" href="resources/css/user.css" />
    <script>
    $('document').ready(function(){
    	loadXMLDoc('user_desktop');
    	var height = $( window ).height();
    	document.getElementById('admin_content').style.minHeight = height + 'px';
    });
    </script>
</head>
<body>
	<div class="off-canvas-wrap" data-offcanvas>
	  <div class="inner-wrap">
	    <nav class="tab-bar">
	      <section class="left-small">
	        <a class="left-off-canvas-toggle menu-icon" href="#"><span></span></a>
	      </section>
	
	      <section class="middle tab-bar-section">
	        <h1 class="title">Reserving</h1>
	      </section>
	    </nav>
	
	    <aside class="left-off-canvas-menu">
	      <ul class="off-canvas-list">
	      	<li><a href="#" onclick = "loadXMLDoc('user_desktop'); initScheduler();">Escritorio</a></li>
	      	<li><a href="index">Home</a>
			<li><a href="#" onclick = "loadXMLDoc('user_booking')">Reservas</a></li>
			<li><a href="#" onclick = "loadXMLDoc('user_favorites')">Favoritos</a></li>
			<li><label>Configuracion</label></li>
			<li><a href="#" onclick = "loadXMLDoc('user_profile')">Perfil</a></li>
	      </ul>
	    </aside>
	
	    <section class="main-section">
	      <div id = "content"></div>
	    </section>
	
	  <a class="exit-off-canvas"></a>
	
	  </div>
	</div>
<%@ include file="../fragments/footer.jspf" %>