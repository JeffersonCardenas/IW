<div class = "row">
	<div id = "user_content">
		<div class = "large-12 columns panel">
			<div class = "large-3 columns">
				<%-- <img id="userfoto" class = "logo" src = "resources/img/user${q.id}.png" width = "100%">--%>
				<img id="userfoto" class = "logo" src = "user/photo?id=${q.id}" width = "100%">
			</div>
			<div class = "large-9 columns">
				<div class = "row">
					<h3>${q.nombre}</h3>
					<p>Email: ${q.email}</p>
					<p>Teléfono: ${q.telefono}</p>
				</div>
			</div>
		</div>
	</div>
</div>