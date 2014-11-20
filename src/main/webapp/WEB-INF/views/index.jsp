<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html class="no-js" lang="en">
  <head>
    <%@ include file="../fragments/header.jspf" %>
    <link rel="stylesheet" href="resources/css/style.css" />
    <script>
    $('document').ready(function(){
    	var height = $( window ).height();
    	document.getElementById('iconos_container').style.height = height + 'px';
    	document.getElementById('about').style.height = height + 'px';
    	document.getElementById('team').style.height = height + 'px';
    	document.getElementById('contact').style.height = height + 'px';
    });
    </script>
    <script>
    $('document').foundation({
    	"magellan-expedition": {
    	  threshold: 100, // how many pixels until the magellan bar sticks, 0 = auto
    	  destination_threshold: 100, // pixels from the top of destination for it to be considered active
    	  throttle_delay: 50, // calculation throttling to increase framerate
    	  fixed_top: 0, // top distance in pixels assigned to the fixed element on scroll
    	}
    });
    </script>
  </head>
  <body>
  	<a name="welcome"></a>
    <div data-magellan-expedition="fixed">
    	<div class = "row">
	  		<div class = "large-4 columns">	
	  			<a href="#welcome"><img src = "resources/img/logo.png" alt = "reserving" height = "70px" width = "70px"></a>
	  		</div>
	  		<div class = "large-8 columns">
	  			<dl class="sub-nav menu">
				    <dd data-magellan-arrival="about"><a href="#about">Sobre Reserving</a></dd>
				    <dd data-magellan-arrival="team"><a href="#team">El equipo</a></dd>
				    <dd data-magellan-arrival="contact"><a href="#contact">Escríbenos</a></dd>
				    <dd data-magellan-arrival="login"><a href="#" onclick = "document.getElementById('login').style.display = 'block'">Login</a></dd>
				    <dd data-magellan-arrival="register"><a href="#" onclick = "document.getElementById('register').style.display = 'block'">Registro</a></dd>
				</dl>
	  		</div>
	  	</div>
	</div>
	
	<div id = "iconos_container">
		<div id = "iconos">
			<div class = "row">
				<div class = "large-3 columns icono"><a href = "search"><img src = "resources/img/restaurantes.png" alt = "restaurantes"><br><h3 onclick = "alert ('Restaurantes')">Restaurantes</h3></a></div>
				<div class = "large-3 columns icono"><a href = "search"><img src = "resources/img/peluquerias.png" alt = "perluquerias"><br><h3>Peluquerías</h3></a></div>
				<div class = "large-3 columns icono"><a href = "search"><img src = "resources/img/deportes.png" alt = "deportes"><br><h3>Deportes</h3></a></div>
				<div class = "large-3 columns icono"><a href = "search"><img src = "resources/img/eventos.png" alt = "eventos"><br><h3>Eventos</h3></a></div>
			</div>
		</div>
	</div>
	
	<a name="about"></a>
	<div id = "about">
		<div id = "about_content">
		    <div class = "row">
				<h3 data-magellan-destination="about">Sobre Reserving</h3>
				<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer porta volutpat massa. Integer venenatis cursus consectetur. Nulla at posuere augue. Fusce mattis vitae orci ut condimentum. Praesent egestas, sem ut tempus mollis, risus turpis aliquet enim, a molestie nunc dolor ut nisl. Duis tristique turpis dui, pharetra iaculis sapien aliquam vitae. In eleifend rutrum sapien. Phasellus tincidunt odio nec velit luctus gravida. Nunc lobortis volutpat ligula at laoreet. Ut posuere tortor diam, non scelerisque tellus semper ut. Praesent quis eros et orci maximus viverra nec non mauris. Praesent congue neque nisi, id vulputate nulla sollicitudin ac. Phasellus tincidunt mi consequat ipsum consectetur facilisis.</p>
				<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer porta volutpat massa. Integer venenatis cursus consectetur. Nulla at posuere augue. Fusce mattis vitae orci ut condimentum. Praesent egestas, sem ut tempus mollis, risus turpis aliquet enim, a molestie nunc dolor ut nisl. Duis tristique turpis dui, pharetra iaculis sapien aliquam vitae. In eleifend rutrum sapien. Phasellus tincidunt odio nec velit luctus gravida. Nunc lobortis volutpat ligula at laoreet. Ut posuere tortor diam, non scelerisque tellus semper ut. Praesent quis eros et orci maximus viverra nec non mauris. Praesent congue neque nisi, id vulputate nulla sollicitudin ac. Phasellus tincidunt mi consequat ipsum consectetur facilisis.</p>
		    </div>
		</div>
	</div>
    
    <div id = "team">
    	<a name="team"></a>
    	<div id = "team_content">
		    <div class = "row">
				<h3 data-magellan-destination="team">El equipo</h3>
				<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer porta volutpat massa. Integer venenatis cursus consectetur. Nulla at posuere augue. Fusce mattis vitae orci ut condimentum. Praesent egestas, sem ut tempus mollis, risus turpis aliquet enim, a molestie nunc dolor ut nisl. Duis tristique turpis dui, pharetra iaculis sapien aliquam vitae. In eleifend rutrum sapien. Phasellus tincidunt odio nec velit luctus gravida. Nunc lobortis volutpat ligula at laoreet. Ut posuere tortor diam, non scelerisque tellus semper ut. Praesent quis eros et orci maximus viverra nec non mauris. Praesent congue neque nisi, id vulputate nulla sollicitudin ac. Phasellus tincidunt mi consequat ipsum consectetur facilisis.</p>
				<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer porta volutpat massa. Integer venenatis cursus consectetur. Nulla at posuere augue. Fusce mattis vitae orci ut condimentum. Praesent egestas, sem ut tempus mollis, risus turpis aliquet enim, a molestie nunc dolor ut nisl. Duis tristique turpis dui, pharetra iaculis sapien aliquam vitae. In eleifend rutrum sapien. Phasellus tincidunt odio nec velit luctus gravida. Nunc lobortis volutpat ligula at laoreet. Ut posuere tortor diam, non scelerisque tellus semper ut. Praesent quis eros et orci maximus viverra nec non mauris. Praesent congue neque nisi, id vulputate nulla sollicitudin ac. Phasellus tincidunt mi consequat ipsum consectetur facilisis.</p>
		    </div>
		</div>
	</div>
    
    <div id = "contact">
    	<a name="contact"></a>
    	<div id = "contact_content">
		    <div class = "row">
				<h3 data-magellan-destination="contact">Escríbenos</h3>
				<form>
					<div class="row">
						<div class="large-6 columns">
							<label>Nombre
								<input type="text" placeholder="Tu nombre" required/>
							</label>
						</div>
						<div class="large-6 columns">
							<div class="row collapse">
								<label>Asunto</label>
									<input type="text" placeholder="Un titulito" />
							</div>
						</div>
					</div>
					<div class="row">
						<div class="large-12 columns">
							<label>Mensaje
								<textarea rows="10" placeholder="Tu mensaje"></textarea>
							</label>
						</div>
					</div>
					<input class = "button expand" type = "submit" name = "submit" value = "Enviar">
				</form>
		    </div>
		</div>
	</div>
	
	<div id = "footer">
		<div id = "contact_content">
			<div class = "row">
				<p>Reserving</p>
			</div>
		</div>
	</div>
	
	<div id = "login" class = "panel">
		<h3 class = "text-center">Login</h3>
		<form method = "post" action = "company" data-abide id = "entrar">
			<div class="row">
    			<div class="large-12 columns">
    				<div class="name-field">
						<label>Usuario <small>requerido</small>
							<input type = "text" name = "user" placeholder="Nombre de usuario" required autofocus>
						</label>
						<small class="error">El nombre es necesario.</small>
					</div>
				</div>
			</div>
			<div class="row">
    			<div class="large-12 columns">
    				<div class="name-field">
						<label>Contraseña <small>requerido</small>
							<input type = "password" name = "pass" required pattern="[a-zA-Z]+">
						</label>
						<small class="error">La contraseña es necesaria.</small>
					</div>
				</div>
			</div>
			<input class = "button expand success" type = "submit" name = "submit" value = "Entrar">
		</form>
		<span class = "button expand" onclick = "document.getElementById('login').style.display = 'none'">Cerrar</span>
	</div>
	
	<div id = "register" class = "panel">
		<h3 class = "text-center">Registro</h3>
		<form method = "post" action = "" data-abide id = "registro">
			<div class="row">
    			<div class="large-12 columns">
    				<div class="name-field">
						<label>Usuario <small>requerido</small>
							<input type = "text" name = "user" placeholder="Nombre de usuario" required autofocus>
						</label>
						<small class="error">El nombre es necesario.</small>
					</div>
				</div>
			</div>
			<div class="row">
    			<div class="large-12 columns">
					<div class="password-field">
    					<label>Contraseña <small>requerido</small>
							<input type = "password" id = "password" name = "pass" required pattern="[a-zA-Z]+">
						</label>
						<small class="error">La contraseña de ser correcta.</small>
					</div>
				</div>
			</div>
			<div class="row">
    			<div class="large-12 columns">
    				<div class="password-confirmation-field">
						<label>Confirma Contraseña <small>required</small>
							<input type = "password" name = "pass" required pattern="[a-zA-Z]+" data-equalto="password">
						</label>
						<small class="error">Las contraseñas no coinciden.</small>
					</div>
				</div>
			</div>
			<input class = "button expand success" type = "submit" name = "submit" value = "Entrar">
		</form>
		<span class = "button expand" onclick = "document.getElementById('register').style.display = 'none'">Cerrar</span>
	</div>
<%@ include file="../fragments/footer.jspf" %>