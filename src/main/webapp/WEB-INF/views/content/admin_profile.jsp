<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class = "row">
	<div id = "admin_content">
		<div class = "large-12 columns panel">
			<h2>Perfil</h2>
			<form method = "post" action = "#" enctype="multipart/form-data">
				<div class = "row">
					<div class = "large-6 columns">
						<label>Nombre
						<input type = "text" name = "name" value = "Marco Aldani" required autofocus>
						</label>
					</div>
					<div class = "large-6 columns">
						<label>Logo
						<input type="file" name="logo" accept="image/*">
						</label>
					</div>
				</div>
				<div class = "row">
					<div class = "large-12 columns">
						<label>Descripción corta
						<textarea>Marco Aldany es la cadena líder de peluquería y estética en España que cuenta con la experiencia de más de 400 aperturas en todo el mundo y más de 4000 estilistas.</textarea>
						</label>
					</div>
				</div>
				<div class = "row">
					<div class = "large-6 columns">
						<label>Email
						<input type = "email" name = "email" placeholder="ejemplo@ejemplo.com" required>
						</label>
					</div>
					<div class = "large-6 columns">
						<label>Teléfono
						<input type = "number" name = "telf" placeholder="910000000" required>
						</label>
					</div>
				</div>
				<div class = "row">
					<div class = "large-6 columns">
						<label>Web
						<input type = "text" name = "web" placeholder="http://www.marcoaldani.com">
						</label>
					</div>
					<div class = "large-6 columns">
						<label>Imagen de fondo
						<input type="file" name="bg" accept="image/*">
						</label>
					</div>
				</div>
				<input class = "button success expand" type = "submit" name = "submit" value = "GUARDAR">
			</form>
		</div>
	</div>
</div>