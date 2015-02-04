<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class = "row">
	<div class = "large-12 columns">
		<h2>Servicios</h2>
	</div>
	<div class = "large-12 columns">
		<c:choose>
			<c:when test="${not empty servicios}">
				<c:forEach items="${servicios}" var="s">
					<div class = "large-3 columns end">
						<ul class="pricing-table">
			  				<li class="title">${s.nombre}</li>
			  				<li class="price">${s.precio} euro/s</li>
			  				<%-- <li class="cta-button"><a class="button" href="#">Reservar</a></li> --%>
						</ul>
					</div>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<div class="row">
					<div class = "large-12 columns">
						<h1>Este Local Aún No Ofrece Servicios</h1>
					</div>
				</div>
			</c:otherwise>
		</c:choose>
	</div>
</div>