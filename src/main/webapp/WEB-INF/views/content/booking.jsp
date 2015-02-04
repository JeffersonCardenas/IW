<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="https://www.owasp.org/index.php/OWASP_Java_Encoder_Project" prefix="e" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--
<script>
	$("#date").datepicker();
</script>
 --%>
 <script>
 jQuery(function() {
		${infoHomeController}
	});
 </script>
<div class = "row">
	<div class = "large-12 columns">
		<h2>Reserva</h2>
	</div>
	<div class = "large-12 columns">
		<c:choose>
			<c:when test="${not empty usuario}">
			   	<form method = "post" action = "reservar">
				<div class="row">
				   	<div class="large-6 columns">
						<label>Hola ${e:forHtmlContent(usuario.nombre)}</label>
					</div>
					<div class= "large-12 columns">
						<div class="row">
							<div class="large-2 columns">
								<label>Día
									<select name="dia" id="dia">
										<c:forEach items="${dia}" var="d">
											<option value="${d}">${d}</option>
										</c:forEach>									
									</select>
								</label>
							</div>
							<div class="large-2 columns">
								<label>Hora
									<select name="hora" id="hora">
										<c:forEach items="${hora}" var="h">
											<option value="${h}">${h}</option>
										</c:forEach>
									</select>
								</label>
							</div>
							<div class="large-2 columns end">
								<label>Minutos
									<select name="min" id="min">
										<c:forEach items="${min}" var="mm">
											<option value="${mm}">${mm}</option>
										</c:forEach>
									</select>
								</label>
							</div>
						</div>
					</div>
					<div class= "large-12 columns">
						<div class = "row">								
							<div class="large-4 columns">
								<label>Mes
									<select name="mes" id="mes">
										<c:forEach items="${mes}" var="m">
											<option value="${m}">${m}</option>
										</c:forEach>
									</select>
								</label>
							</div>
							<div class="large-4 columns">
								<label>Servicio
									<select name="servicio" id="servicio">
										<c:forEach items="${servicios}" var="s">
											<option value="${s.id}">${s.nombre}</option>
										</c:forEach>
									</select>
								</label>
							</div>
						</div>
					</div>
				<input class = "button expand" type = "submit" name = "submit" value = "Reservar">
			</div>
			</form>	
		</c:when>
		<c:otherwise>
			<div class="large-6 columns">
				<div class="row">
					<h1>Debes loguearte para reservar</h1>
					<p>Pulsa <a href="index">aquí</a></p>
				</div>
			</div>		
		</c:otherwise>
		</c:choose>
	</div>		
</div>