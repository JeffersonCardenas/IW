<div class = "row">
	<div id = "user_content">
		<div class = "large-12 columns panel">
			<h2>Perfil</h2>
			<form method = "post" action = "updateProfile" enctype="multipart/form-data">
				<div class = "row">
					<div class = "large-6 columns">
						<label>Nombre
						<input type = "text" name = "name" placeholder="Nuevo Nombre" required autofocus>
						</label>
					</div>
					<div class = "large-6 columns">
						<label>Imagen de perfil
						<input type="file" name="logo" accept="image/*">
						</label>
					</div>
				</div>
				<div class = "row">
					<div class = "large-6 columns">
						<label>Email
						<input type = "email" name = "email" placeholder="ejemplo@ejemplo.com">
						</label>
					</div>
					<div class = "large-6 columns">
						<label>Teléfono
						<input type = "number" name = "telf" placeholder="Nuevo Teléfono">
						</label>
					</div>
				</div>
				<input class = "button success expand" type = "submit" name = "submit" value = "GUARDAR">
			</form>
		</div>
	</div>
</div>