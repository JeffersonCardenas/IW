<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class = "row" id="resulAdmin">
	<c:choose>
		<c:when test="${not empty local}">
			<c:forEach items="${local}" var="l">
				<div class = "large-3 columns end">	
					<div class = "panel" id="panel_${l.id}">
						<img class = "logo" src = "local/photo?id=${l.id}">
						<h3>${l.nombre}</h3>
						<p><img src = "resources/img/estrella.png" width = "20px"> <img src = "resources/img/estrella.png" width = "20px"> <img src = "resources/img/estrella.png" width = "20px"> <img src = "resources/img/estrella.png" width = "20px"></p>
						<span class = "label">${l.tipo}</span>
						<button class="button alert eliminaAdmin" id="eliminaAdmin_${l.id}">ELIMINAR</button>
					</div>
				</div>
			</c:forEach>
		</c:when>
		<c:otherwise>
			<div class = "row">
				<div class = "small-6 large-centered columns">
					<h2>No Hay Locales en la BBDD</h2>
				</div>
			</div>
		</c:otherwise>	
	</c:choose>
</div>