<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="row">
	<div class="panel">
		<h5>Estas son tus reservas</h5>
	</div>
</div>
<div class = "row">
	<div id = "user_content">
		<div class = "large-12 columns" id="reservas">
			<c:forEach items="${reservas}" var="r">
			<div class = "large-3 columns end">
				<div class = "panel" id="panel_${r.id}">
					<img class = "logo" src = "local/photo?id=${r.id}">
					<h1>${r.local.nombre}</h1>
					<h3>${r.horario}</h3>
					<button class="button alert borraReserva"
						 id="borraReserva_${r.id}">Eliminar</button>
				</div>
			</div>
			</c:forEach>
		</div>
	</div>
</div>