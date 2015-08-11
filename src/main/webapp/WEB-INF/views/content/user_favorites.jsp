<%-- Aqui se llena la lista de locales favoritos del usuario --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class = "row">
	<div id = "user_content">
		<div class = "large-12 columns">
			<c:forEach items="${locales}" var="u">
				<div class = "large-3 columns end">
					<div class = "panel" id="panel_${u.id}">
						<img class = "logo" src = "local/photo?id=${u.id}">
						<h3>${u.nombre} </h3>
						<p>${u.descripcion}</p>
						<span class = "label">${u.tipo}</span>
						<button class="button alert eliminaFavorito" id="eliminaFavorito_${u.id}">Eliminar</button>
					</div>
				</div>
			</c:forEach>
		</div>
	</div>
</div>