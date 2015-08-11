/**
 * Cambia el alto de una capa
 */
function changeBgHeight (div, height) {
	document.getElementById(div).style.height = height;
}

/**
 * Cargar contenido con AJAX
 */
function loadXMLDoc(menu) {
	var xmlhttp;
	var ref = ["about", "services", "map", "booking", "desktop", "admin_services", "admin_map" , "admin_profile", "user_desktop", "user_booking", "user_favorites", "user_profile", "super_admin_desktop", "super_admin_business", "super_admin_create"];
	var content = ["about", "services", "map", "booking", "desktop", "admin_services", "admin_map", "admin_profile", "user_desktop", "user_booking", "user_favorites", "user_profile", "super_admin_desktop", "super_admin_business", "super_admin_create"];
	var destiny = 'content/' + content[ref.indexOf(menu)];
	
	if (window.XMLHttpRequest) // code for IE7+, Firefox, Chrome, Opera, Safari
		xmlhttp=new XMLHttpRequest();
	else // code for IE6, IE5
		xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	
	xmlhttp.onreadystatechange=function() {
	if (xmlhttp.readyState==4 && xmlhttp.status==200)
		  document.getElementById("content").innerHTML=xmlhttp.responseText;
	}
	xmlhttp.open("GET",destiny,true);
	xmlhttp.send();
}

/**
 * Carga dinámica de contenido
 * @param url
 * @param params
 */
function loadXMLDocs(url,params){
	var req;
	
	if (window.XMLHttpRequest) 
		req = new XMLHttpRequest();
	else
		req = new ActiveXObject("Microsoft.XMLHTTP");
	
	req.onreadystatechange = function(){
	if (req.readyState==4 && req.status==200)
		document.getElementById("content").innerHTML=req.responseText;
	}		
	req.open("GET", url + '?' + params, true); 
	req.send();	
}

/**
* 
*/
function initScheduler() {
	scheduler.config.multi_day = true;
	scheduler.config.event_duration = 35;
	scheduler.config.xml_date = "%Y-%m-%d %H:%i";
	scheduler.config.occurrence_timestamp_in_utc = true;
	scheduler.config.include_end_by = true;
	scheduler.config.repeat_precise = true;

	scheduler.attachEvent("onLightbox", function(){
		var lightbox_form = scheduler.getLightbox(); // this will generate lightbox form
		var inputs = lightbox_form.getElementsByTagName('input');
		var date_of_end = null;
		for (var i=0; i<inputs.length; i++) {
			if (inputs[i].name == "date_of_end") {
				date_of_end = inputs[i];
				break;
			}
		}

		var repeat_end_date_format = scheduler.date.date_to_str(scheduler.config.repeat_date);
		var show_minical = function(){
			if (scheduler.isCalendarVisible())
				scheduler.destroyCalendar();
			else {
				scheduler.renderCalendar({
					position:date_of_end,
					date: scheduler.getState().date,
					navigation:true,
					handler:function(date,calendar) {
						date_of_end.value = repeat_end_date_format(date);
						scheduler.destroyCalendar()
					}
				});
			}
		};
		date_of_end.onclick = show_minical;
	});

	scheduler.config.lightbox.sections = [
		{ name:"description", height:200, map_to:"text", type:"textarea" , focus:true },
		{ name:"recurring", type:"recurring", map_to:"rec_type", button:"recurring" },
		{ name:"time", height:72, type:"calendar_time", map_to:"auto" }
	];

	scheduler.init('scheduler_here', new Date(2010, 0, 10), "week");

	scheduler.load("./data/events.xml", function() {
		scheduler.showLightbox(2);
	});

}