<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class = "row">
	<div class = "large-12 columns">
		<h2>Reserva</h2>
	</div>
	<div class = "large-12 columns">
		<form method = "post" action = "">
			<div class="row">
				<div class="large-6 columns">
					<label>Nombre
					<input type="text" placeholder="Tu nombre" />
					</label>
				</div>
				<div class="large-6 columns">
					<label>Apellidos
					<input type="text" placeholder="Tus apellidos" />
					</label>
				</div>
			</div>
			<div class = "row">
				<div class="large-4 columns">
					<label>Hora
					<input type="time" placeholder="Tu nombre" />
					</label>
				</div>
				<div class="large-4 columns">
					<label>Dí­a
					<input type="date" placeholder="Tus apellidos" />
					</label>
				</div>
				<div class="large-4 columns">
					<label>Servicio
						<select>
							<option value="0">Lavar y peinar</option>
							<option value="1">Mechas</option>
							<option value="2">Lavar, cortar y peinar</option>
							<option value="3">Color</option>
						</select>
					</label>
				</div>
			</div>
			<input class = "button expand" type = "submit" name = "submit" value = "Reservar">
		</form>
	</div>
</div>