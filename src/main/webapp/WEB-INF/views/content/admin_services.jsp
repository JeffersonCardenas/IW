<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class = "row">
	<div id = "admin_content">
		<div class = "large-12 columns panel">
			<h2>Servicios | Edición</h2>
			<c:choose>
				<c:when test="${not empty servicios }">
				<table id="tablaServicios">
			  		<thead>
			    		<tr>
			      			<th>Nombre</th>
			      			<th>Precio</th>
			    		</tr>
			  		</thead>
			  		<tbody>
			    	<c:forEach items="${servicios}" var="u">
			    		<tr>
			    			<td>${u.nombre}</td>
			    			<td>${u.precio}</td>
			    		</tr>
			    	</c:forEach>
			  		</tbody>
				</table>
				</c:when>
				<c:otherwise>
					<h1>No Hay Servicios</h1>
				</c:otherwise>
			</c:choose>
			<form action="addService" method="post">
				<fieldset>
					<legend>Nuevo Servicio</legend>
					<div class="row">
						<div class="large-4 columns">
							<label>Nombre
								<input type="text" name="nombre" />
							</label>
						</div>
						<div class="large-4 columns">
							<label>Precio
								<input type="number" name="precio" />
							</label>
						</div>
						<div class="large-4 columns">
							<button class="button addRow">Añadir Nuevo Servicio</button>
						</div>
					</div>
			</fieldset>
			</form>
		</div>
	</div>
</div>