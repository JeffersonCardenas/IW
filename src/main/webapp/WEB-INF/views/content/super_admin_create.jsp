<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="row">
	<div class="large 12 columns panel">
		<form method="post" action="addLocal">
		<div class="row"> 
			<div class="large-4 columns"> 
		    	<label>Nombre
		    		<input type="text" name="nombre" />
		    	</label> 
		    </div>
		    <div class="large-4 columns">
		    	<label>Horario
		    		<input type="text" name="horario" placeholder="ej: De Lunes a Viernes de 9 a 20h"/>
		    	</label>
		    </div>
		</div>
		<div class="row">
			<div class="large-8 columns">
		    	<label>Direccion
		    		<input type="text" placeholder="Desde maps embed code" name="dir"/>
		    	</label> 
		    </div>
		</div>
		<div class="row">
		 	<div class="large-6 columns">
		 		<label>Tipo
		 			<select name="tipo">
		 				<option value="peluqueria">Peluqueria</option>
		 				<option value="restaurante">Restaurante</option>
		 				<option value="evento">Evento</option>
		 				<option value="deportes">Deportes</option>
		 			</select>
		 		</label>
		 	</div>
		 	<div class="large-6 columns">
		 		<label>Propietario
		 			<select name="owner">
		 				<c:forEach items="${empresarios}" var="e">
		 					<option value="${e.nombre}">${e.nombre}</option>
		 				</c:forEach>
		 			</select>
		 		</label>
		 	</div>
		</div>       
		<div class="row"> 
			<div class="large-12 columns">
		 		<label>Descripción
		 			<textarea name="descripcion"></textarea>
		 		</label> 
		    </div> 
		</div>		
		<div class = "row">
			<div class = "large-12 columns">
				<input class = "button" type = "submit" name = "addLocal" value = "AÑADIR NUEVO">
			</div>
		</div>
		</form>
	</div>
</div>