<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class = "row">
	<div id = "admin_content">
		<div class = "large-12 columns panel">
			<h2>Perfil</h2>
			<form method = "post" action = "updateProfileLocal" enctype="multipart/form-data">
				<div class = "row">
					<div class = "large-6 columns">
						<label>Nombre
						<input type = "text" name = "name" placeholder="Nuevo nombre del local">
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
						<textarea name="descripcion"></textarea>
						</label>
					</div>
				</div>
				<div class = "row">
					<div class="large-6 columns">
		    			<label>Horario
		    				<input type="text" name="horario" placeholder="ej: De Lunes a Viernes de 9 a 20h"/>
		    			</label>
		   			 </div>
					<div class="large-6 columns">
		    			<label>Direccion
		    				<input type="text" placeholder="Desde maps embed code" name="dir"/>
		    			</label> 
		    		</div>
				</div>
				<input class = "button success expand" type = "submit" name = "submit" value = "GUARDAR">
			</form>
		</div>
	</div>
</div>