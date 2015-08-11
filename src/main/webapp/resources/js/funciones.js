/**
 * Carga busqueda interna
 */
jQuery(function() {
	jQuery("#submit").click(function(){
		var name = jQuery("#search").val();
		var tipo = jQuery('#type option:selected').text();
		intSearch(name,tipo);
	});
});
		
function intSearch(name,tipo){
	var v = jQuery("#resulBusqueda");
	jQuery("#panel").empty();
    v.empty();
    jQuery.ajax({
	        url:"searchnombre?p="+name+"&q="+tipo,
	        method:"GET",
	        success:function(datos){
	            jQuery("#resulBusqueda").html(datos);
	        },
	        error:function(e){
	            console.log(e);
	        }
	})
}

/**
 * Elimina la Reserva de un usuario
 */
jQuery(function() {
	jQuery('body').delegate('.borraReserva', 'click', function() {
		var id=jQuery(this).attr("id").substring("borraReserva_".length);
		borraReserva(id);
	})
});

function borraReserva(id){
	var v = $("#reservas");
	jQuery.ajax({
		url: "borrarReserva?id="+id,
		method:"POST",
		data: {"id": id},
		succes:function(datos){
			jQuery("#panel_"+id).remove();
			alert('Eliminado con éxito');
			v.empty();
			v.html(datos);
		},
		error:function(e){
			console.log(e);
		}
	})
}

/**
 * Elimina un local favorito
 */
jQuery(function(){
	jQuery('body').delegate('.eliminaFavorito','click',function(){
		var id=jQuery(this).attr("id").substring("eliminaFavorito_".length);
		eliminaFavorito(id);
	})
})

function eliminaFavorito(id){
	jQuery.ajax({
		url: "eliminaFavorito?id="+id,
		method:"POST",
		data: {"id": id},
		succes:function(datos){
			jQuery("#panel_"+id).remove();
			alert('Ya no es favorito');
		},
		error:function(e){
			console.log(e);
		}
	})
}

/**
* Añadir una fila a la tabla de servicios
*/
jQuery(function(){
	jQuery('body').delegate('.addRow','click',function(){
		jQuery('#tablaServicios > tBody:last').after('<tr><td>Nombre</td></tr><tr><td>Precio</td></tr><td><a href="#" class="button success">Editar</a></td><td><a href="#" class="button alert">Eliminar</a></td>');
	})
})

/**
* Agregar a favoritos un local
*/
jQuery(function(){
	jQuery('body').delegate('#favorito','click',function(){
		agregaFavorito();
	})
})

function agregaFavorito(nombre){
	jQuery.ajax({
		url:"addFavorite",
        method:"GET",
        success:function(datos){
        	alert('Local agregado a favoritos');
        	jQuery('#favorito').hide();
        },
        error:function(e){
            console.log(e);
        }		
	})
}

/**
* Eliminar un local de la base de datos en la vista admin
*/
jQuery(function(){
	jQuery('body').delegate('.eliminaAdmin','click',function(){
		var id=jQuery(this).attr("id").substring("eliminaAdmin_".length);
		eliminaLocal(id);
	})
})

function eliminaLocal(id){
	var v = jQuery("resulAdmin");
	//v.empty();
	jQuery.ajax({
		url:"deleteLocal?id="+id,
        method:"GET",
        success:function(datos){
        	alert('Peticion procesada');
        },
        error:function(e){
            console.log(e);
        }		
	})
}