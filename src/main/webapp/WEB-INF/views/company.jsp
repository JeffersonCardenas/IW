<!DOCTYPE html>
<html>
<head>
	<%@ include file="../fragments/header.jspf" %>
    <link rel="stylesheet" href="resources/css/company.css" />
    <script>
    $('document').ready(function(){
    	var url = 'content/desktop';
		var param = "p="+${u.id};
    	loadXMLDocs(url,param);
    });
    </script>
    <script>
    $(function(){
    	$(".item").click(function() {
    		var dest = $(this).attr("id").substring("i_".length);
    		var url = 'content/' + dest;
    		var param = "p="+${u.id};
        	loadXMLDocs(url, param);    		
    	})
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
	      	<li><a href="index">Home</a>
	      	<li><a href="business?u=${usuario.id}">Mis Locales</a>
	        <li><label>Paginas</label></li>
			<li><div class="item" id="i_desktop"><a href="#">Agenda</a></div></li>
			<li><div class="item" id="i_admin_services"><a href="#">Servicios</a></div></li>
			<li><div class="item" id="i_admin_map"><a href="#">Donde Estamos</a></div></li>
			<li><label>Configuracion</label></li>
			<li><div class="item" id="i_admin_profile"><a href="#">Perfil</a></div></li>
	      </ul>
	    </aside>
	
	    <section class="main-section">
	      <div id = "content"></div>
	    </section>
	
	  <a class="exit-off-canvas"></a>
	
	  </div>
	</div>
<%@ include file="../fragments/footer.jspf" %>