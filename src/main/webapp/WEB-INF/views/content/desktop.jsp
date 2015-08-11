<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class = "row">
	<div id = "admin_content">
		<div class = "large-3 columns">
			<div class = "large-12 columns panel">
				<img class = "logo" src = "local/photo?id=${u.id}">
				<h3>${u.nombre }</h3>
				<p>${u.descripcion }</p>
			</div>
		</div>
		<div class = "large-9 columns">
			<div class = "large-12 columns panel">
				<div class = "row">
					<h2>AGENDA</h2>
					<br>
				</div>
				<c:choose>
				<c:when test="${not empty reservas}">
					<c:forEach items="${reservas}" var="r">
					<div class = "row">
						<div class = "large-9 large-offset-3 columns">
							<p>${r.horario}</p>
						</div>
					</div>
				</c:forEach>
				</c:when>
				<c:otherwise>
					<div class = "row">
						<div class = "large-9 large-offset-3 columns">
							<h1>No Hay Reservas</h1>
						</div>
				</div>
				</c:otherwise>
				</c:choose>
			</div>
		</div>
	</div>
</div>