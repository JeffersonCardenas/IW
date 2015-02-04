<!DOCTYPE html>
<html>
<head>
	<%@ include file="../fragments/header.jspf" %>
    <link rel="stylesheet" href="resources/css/admin.css" />
    <script>
    $('document').ready(function(){
    	loadXMLDoc('super_admin_desktop');
    });
    </script>
    <script>
    $(function(){
    	$(".item").click(function() {
    		var dest = $(this).attr("id").substring("i_".length);
    		var url = 'content/' + dest;
    		var param = 'p='+${u.id};
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
	      	<li><div class="item" id="i_super_admin_desktop"><a href="#">Escritorio</a></div></li>
			<li><div class="item" id="i_super_admin_business"><a href="#">Negocios</a></div></li>
			<li><div class="item" id="i_super_admin_create"><a href="#">Insertar Local</a></div></li>
	      </ul>
	    </aside>
	
	    <section class="main-section">
	      <div id = "content"></div>
	    </section>
	
	  <a class="exit-off-canvas"></a>
	
	  </div>
	</div>
<%@ include file="../fragments/footer.jspf" %>