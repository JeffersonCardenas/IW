package es.fdi.iw.reserving;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.orm.hibernate3.LocalSessionFactoryBean;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import es.fdi.iw.ContextInitializer;
import es.fdi.iw.model.Empresarios;
import es.fdi.iw.model.Local;
import es.fdi.iw.model.Reserva;
import es.fdi.iw.model.Servicio;
import es.fdi.iw.model.Usuario;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@Transactional
	public String login(HttpServletRequest request, Model model, HttpSession session) {
		String formNombre = request.getParameter("user");
		String formPass = request.getParameter("pass");
		String formSource = request.getParameter("entrar");
		
		logger.info("Login attempt from '{}' while visiting '{}'", formNombre, formSource);
		// validate request
		if (formNombre == null || formNombre.length() < 3 || formPass == null || formPass.length() < 3) {
			model.addAttribute("loginError", "usuarios y contraseñas: 4 caracteres mínimo");
		} else {
			Empresarios e = null;
			List<Usuario> resul = entityManager.createNamedQuery("usuarioByNombre",Usuario.class)
					.setParameter("nombreParam", formNombre).getResultList();
			System.out.println(resul);
			if (resul.size() > 0){
				try {
					if (resul.get(0).isPassValid(formPass)) {
						logger.info("pass was valid");				
						session.setAttribute("usuario", resul.get(0));
					} else {
						logger.info("pass was NOT valid");
						model.addAttribute("errorHomeController","alert('Error en contraseña');");
					}
				}
				catch(NoResultException nre){
					model.addAttribute("errorHomeController","alert('Error en usuario o contraseña');");
				}
			}
			else{
				logger.info("Comprabamos si es empresario");
				try{
					e = (Empresarios)entityManager.createNamedQuery("empresarioByNombre")
						.setParameter("nombreParam", formNombre).getSingleResult();
					if(e.isPassValid(formPass)){
						logger.info("Contraseña Empresario válida");
						session.setAttribute("usuario", e);
					} else{
						logger.info("pass was NOT valid");
						model.addAttribute("errorHomeController","alert('Error en contraseña');");
					}
				}
				catch(NoResultException nr){
					model.addAttribute("errorHomeController","alert('Error en usuario o contraseña');");
				}			
			}
		}
		
		// redirects to view from which login was requested
		return "index";
	}
	
	/**
	* Logout (also returns to home view).
	*/
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session) {
	logger.info("User '{}' logged out", session.getAttribute("usuario"));
	session.invalidate();
	return "redirect:/index";
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	@Transactional
	public String register(HttpServletRequest request, Model model, HttpSession session){
		String formNombre = request.getParameter("user");
		String formEmail = request.getParameter("email");
		String formPass = request.getParameter("pass");
		String formSource = request.getParameter("registro");
		
		logger.info("Register attempt from '{}' while visiting '{}'", 
				new Object[] {formNombre, formEmail}, formSource);
		
		// validate request
		if (formNombre == null || formNombre.length() < 3 || formPass == null || formPass.length() < 3) {
				model.addAttribute("errorHomeController","alert('usuarios y contraseñas: 4 caracteres mínimo');");
		} else {
			int cuantos = entityManager.createNamedQuery("usuarioByNombre")
					.setParameter("nombreParam", formNombre).getResultList()
					.size();
			if (cuantos == 0) {
				logger.info("Creamos el usuario {}", formNombre);				
				Usuario user = Usuario.createUsuario(formNombre, formEmail, formPass, "user");
				entityManager.persist(user);		
				session.setAttribute("usuario", user);
			} else {
				logger.info("User already exist: {}", formNombre);
			}
		}		
		
		return "redirect:/index";
	}
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	//EMPIEZA INDEX
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		model.addAttribute("pageTitle", "Bienvenido");
		return "index";
	}
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(HttpSession session, Model model) {
		model.addAttribute("pageTitle", "Bienvenido");
		return "index";
	}
	
	//EMPIEZA ADMIN
	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String admin(HttpSession session, Model model) {
		model.addAttribute("pageTitle", "Administración");
		Usuario u = (Usuario)session.getAttribute("usuario");
		model.addAttribute("u", u);
		return "admin";
	}
	
	@RequestMapping(value = "/content/super_admin_desktop", method = RequestMethod.GET)
	public String super_admin_desktop(HttpSession session, Model model) {
		List<Integer> tam = new ArrayList<Integer>();
		List<String> ind = new ArrayList<String>();
		ind.add("restaurante");
		ind.add("peluqueria");
		ind.add("deportes");
		ind.add("evento");
		Iterator<String> it = ind.iterator();
		while(it.hasNext()){
			List<Local> l = entityManager.createNamedQuery("listaLocalesPorTipo",Local.class)
					.setParameter("nombreParam",it.next()).getResultList();
			tam.add(l.size());
		}
		List<Local> lt = entityManager.createNamedQuery("listaLocales",Local.class).getResultList();
		tam.add(lt.size());
		model.addAttribute("locales", tam);
		return "content/super_admin_desktop";
	}
	
	@RequestMapping(value = "/content/super_admin_business", method = RequestMethod.GET)
	public String super_admin_business(HttpSession session, Model model) {
		List<Local> l = entityManager.createNamedQuery("listaLocales",Local.class).getResultList();
		model.addAttribute("local", l);
		return "content/super_admin_business";
	}
	
	@RequestMapping(value = "/content/super_admin_create", method = RequestMethod.GET)
	public String super_admin_create(HttpSession session, Model model){
		List<Empresarios> l = entityManager.createNamedQuery("listaEmpresarios", Empresarios.class).getResultList();
		model.addAttribute("empresarios", l);
		return "content/super_admin_create";
	}
	
	//Agregar un local al sistema
	@RequestMapping(value = "addLocal", method = RequestMethod.POST)
	@Transactional
	public String addLocal(HttpServletRequest request, HttpSession session, Model model){
		String nombre = request.getParameter("nombre");
		String direccion = request.getParameter("dir");
		String tipo = request.getParameter("tipo");
		String propietario = request.getParameter("owner");
		String descripcion = request.getParameter("descripcion");
		String horario = request.getParameter("horario");
		
		try{
			Empresarios e = (Empresarios)entityManager.createNamedQuery("empresarioByNombre")
					.setParameter("nombreParam", propietario).getSingleResult();
			Local l = new Local();
			l.setDescripcion(descripcion);
			l.setDireccion(direccion);
			l.setHorario(horario);
			l.setNombre(nombre);
			l.setPropietario(e);
			l.setTipo(tipo);
			entityManager.persist(l);			
		}
		catch(NoResultException nre){
			model.addAttribute("Error insert", "Local no insertado");
		}
		
		return "redirect:/admin";
	}
	
	//Borrar un local del sistema
	@RequestMapping(value="deleteLocal" , method = RequestMethod.GET)
	@Transactional
	public String deleteLocal(HttpSession session, Model model,
			@RequestParam("id") long id){
		try{
			Local loc = (Local)entityManager.createNamedQuery("localPorId")
					.setParameter("idParam", id).getSingleResult();
			if (loc.getReservas().isEmpty() && loc != null){
				if (entityManager.createNamedQuery("borraLocal")
						.setParameter("idParam", id).executeUpdate() == 1){
					List<Local> l = entityManager.createNamedQuery("listaLocales",Local.class).getResultList();
					model.addAttribute("local", l);
				}
			}
			else logger.info("Este local tiene reservas");
		}
		catch(NoResultException nre){
			logger.error("Error al borrar o local no existente", nre);
		}
		return "redirect:/admin";
	}
	
	//Empieza BUSINESS
	@RequestMapping(value = "/business", method = RequestMethod.GET)
	public String business(HttpSession session, Model model) {
		model.addAttribute("pageTitle", "Negocios Empresario");
		try{
			List<Local> l = entityManager.createNamedQuery("listaLocalesEmpresario",Local.class)
					.setParameter("nombreParam",((Empresarios)session.getAttribute("usuario")).getId()).getResultList();
			model.addAttribute("locales", l);
		}
		catch (NoResultException nre){
			logger.error("Saltó excepcion business",nre);
			model.addAttribute("Error busqueda", "No hay locales de ese tipo");
		}
		return "business";
	}
	
	//EMPIEZA COMPANY
	@RequestMapping(value = "/content/desktop", method = RequestMethod.GET)
	@Transactional
	public String desktop(HttpSession session, Model model,@RequestParam("p") long id) {
		Local l = null;
		try{
			l = (Local)entityManager.createNamedQuery("localPorId")
					.setParameter("idParam", id).getSingleResult();
			model.addAttribute("u", l);
			System.out.println(l.getReservas().size());
			model.addAttribute("reservas", l.getReservas());
		}
		catch(NoResultException nre){
			model.addAttribute("Error id", "Local no encontrado");
		}
		return "content/desktop";
	}	
	
	@RequestMapping(value = "/content/admin_services", method = RequestMethod.GET)
	public String admin_services(HttpSession session, Model model,@RequestParam("p") long id) {
		List<Servicio> l;
		try{
			l = entityManager.createNamedQuery("listaServicios",Servicio.class)
					.setParameter("idParam", id).getResultList();
			model.addAttribute("servicios", l);
		}
		catch(NoResultException nre){
			model.addAttribute("Error id", "Local no encontrado");
		}
		return "content/admin_services";
	}
	
	@RequestMapping(value = "/content/admin_map", method = RequestMethod.GET)
	public String admin_map(HttpSession session, Model model,@RequestParam("p") long id) {
		Local l = null;
		try{
			l = (Local)entityManager.createNamedQuery("localPorId")
					.setParameter("idParam", id).getSingleResult();
			model.addAttribute("u", l);
		}
		catch(NoResultException nre){
			model.addAttribute("Error id", "Local no encontrado");
		}
		return "content/admin_map";
	}
	
	@RequestMapping(value = "/content/admin_profile", method = RequestMethod.GET)
	public String admin_profile(HttpSession session, Model model,@RequestParam("p") long id) {
		Local l = null;
		try{
			l = (Local)entityManager.createNamedQuery("localPorId")
					.setParameter("idParam", id).getSingleResult();
			model.addAttribute("u", l);
		}
		catch(NoResultException nre){
			model.addAttribute("Error id", "Local no encontrado");
		}
		return "content/admin_profile";
	}
	
	@RequestMapping(value = "/company", method = RequestMethod.GET)
	public String company(HttpSession session, Model model,@RequestParam("u") long id) {
		Local l = null;
		try {
			l = (Local)entityManager.createNamedQuery("localPorId")
					.setParameter("idParam", id).getSingleResult();
			session.setAttribute("u", l);
			model.addAttribute("pageTitle", l.getNombre());
		} catch (NoResultException nre) {
			model.addAttribute("Error Id", "Id no encontrado");
		}
		return "company";
	}
	
	//Añadir un servicio al local
	@RequestMapping(value="addService", method = RequestMethod.POST)
	@Transactional
	public String addService(HttpServletRequest request, HttpSession session, Model model){
		String nombre = request.getParameter("nombre");
		String precio = request.getParameter("precio");
		Servicio s = new Servicio();
		
		try{
			logger.info("Agregar un servicio al local");
			s.setNombre(nombre);
			if (precio != null)
				s.setPrecio(Float.parseFloat(precio));
			else s.setPrecio(0);
			entityManager.persist(s);
			
			s = entityManager.find(Servicio.class, s.getId());
			Local l = (Local)session.getAttribute("u");
			l = entityManager.find(Local.class, l.getId());
			l.getServicios().add(s);
		}
		catch(NoResultException nre){
			model.addAttribute("Error servicio", "No se pudo agregar el servicio");
		}
		catch(NumberFormatException nfe){
			s.setPrecio(0);
			entityManager.persist(s);
			s = entityManager.find(Servicio.class, s.getId());
			Local l = (Local)session.getAttribute("u");
			l = entityManager.find(Local.class, l.getId());
			l.getServicios().add(s);
		}
		
		return "redirect:/business";
		
	}
	
	//Actualizar los datos del local
	@RequestMapping(value="updateProfileLocal", method = RequestMethod.POST)
	@Transactional
	public String updateProfileLocal(HttpServletRequest request, HttpSession session, Model model,
			@RequestParam("logo") MultipartFile photo){
		String formNombre = request.getParameter("name");
		String formDescripcion = request.getParameter("descripcion");
		String formHorario = request.getParameter("horario");
		String formDireccion = request.getParameter("dir");
		
		Local l = (Local)session.getAttribute("u");
		
		if (!photo.isEmpty()) {
            try {
                byte[] bytes = photo.getBytes();
                BufferedOutputStream stream =
                        new BufferedOutputStream(
                        		new FileOutputStream(ContextInitializer.getFile("local", String.valueOf(l.getId()))));
                stream.write(bytes);
                stream.close();
                l = entityManager.find(Local.class, l.getId());			
                if (formDescripcion != null)
                	l.setDescripcion(formDescripcion);
				if (formDireccion != null)
					l.setDireccion(formDireccion);
				if (formHorario != null)
					l.setHorario(formHorario);
				if (formNombre != null)
					l.setNombre(formNombre);
    			session.setAttribute("u", l);
            } catch (Exception e) {
                model.addAttribute("Error upload photo", "Fallo al cargar imagen");
            }
        }
		else {
			try{
				l = entityManager.find(Local.class, l.getId());
				if (formDescripcion != null)
                	l.setDescripcion(formDescripcion);
				if (formDireccion != null)
					l.setDireccion(formDireccion);
				if (formHorario != null)
					l.setHorario(formHorario);
				if (formNombre != null)
					l.setNombre(formNombre);			
				session.setAttribute("u", l);	
			}catch(Exception e){
				model.addAttribute("Error update profile Local", "Error profile local");
			}
		}
		
		return "redirect:/business";		
	}
	
	//EMPIEZA PLACE
	@RequestMapping(value = "/place", method = RequestMethod.GET)
	@Transactional
	public String place(HttpSession session, @RequestParam("p") long id,Model model) {
		Local l = null;
		try {
			l = (Local)entityManager.createNamedQuery("localPorId")
					.setParameter("idParam", id).getSingleResult();
			model.addAttribute("q", l);
			model.addAttribute("pageTitle", l.getNombre());
			session.setAttribute("local", l);
		} catch (NoResultException nre) {
			model.addAttribute("Error Id", "Id no encontrado");
		}
		return "place";
	}
	
	@RequestMapping(value = "/content/about", method = RequestMethod.GET)
	@Transactional
	public String about(HttpSession session, @RequestParam("p") long id,Model model) {
		Local l = null;
		try {
			l = (Local)entityManager.createNamedQuery("localPorId")
					.setParameter("idParam", id).getSingleResult();
			model.addAttribute("q", l);
		} catch (NoResultException nre) {
			model.addAttribute("Error Id", "Id no encontrado");
		}
		return "content/about";
	}
	
	@RequestMapping(value = "/content/services", method = RequestMethod.GET)
	public String services(HttpSession session, Model model,@RequestParam("p") long id) {
		//Devolver los servicios que ofrece este local
		List<Servicio> l;
		try{
			l = entityManager.createNamedQuery("listaServicios",Servicio.class)
					.setParameter("idParam", id).getResultList();
			model.addAttribute("servicios", l);
		}
		catch(NoResultException nre){
			model.addAttribute("Error id", "Local no encontrado");
		}
		return "content/services";
	}
	
	@RequestMapping(value = "/content/map", method = RequestMethod.GET)
	public String map(HttpSession session, Model model, @RequestParam("p") long id) {
		Local l = null;
		try {
			l = (Local)entityManager.createNamedQuery("localPorId")
					.setParameter("idParam", id).getSingleResult();
			model.addAttribute("q", l);
			logger.info("content/about "+l.getNombre());
		} catch (NoResultException nre) {
			model.addAttribute("Error Id", "Id no encontrado");
		}
		return "content/map";
	}
	
	@RequestMapping(value = "/content/booking", method = RequestMethod.GET)
	public String booking(HttpSession session, Model model,@RequestParam("p") long id) {
		//Reservar en este local
		List<Servicio> l;
		List<Integer> dia = new ArrayList<Integer>();
		List<String> mes = new ArrayList<String>();
		List<Integer> hora = new ArrayList<Integer>();
		List<String> min = new ArrayList<String>();
		//Inicializar las listas
		String[] meses = {"Enero","Febrero","Marzo","Abril","Mayo","Junio","Julio","Agosto","Septiembre",
				"Octubre","Noviembre","Diciembre"};
		String[] minutos = {"00","15","30","45"};
		for (int i=1;i<32;i++){
			dia.add(i);
		}
		for(int i=0;i<12;i++){
			mes.add(meses[i]);
		}
		for(int i=9;i<24;i++){
			hora.add(i);
		}
		for(int i=0;i<4;i++){
			min.add(minutos[i]);
		}
		
		try{
			l = entityManager.createNamedQuery("listaServicios",Servicio.class)
					.setParameter("idParam", id).getResultList();
			System.out.println(l.size());
			model.addAttribute("servicios", l);
			model.addAttribute("dia", dia);
			model.addAttribute("mes", mes);
			model.addAttribute("hora", hora);
			model.addAttribute("min", min);
		}
		catch(NoResultException nre){
			model.addAttribute("Error id", "Local no encontrado");
		}
		return "content/booking";
	}
	
	//Procesar la reserva
	@RequestMapping(value = "reservar", method = RequestMethod.POST)
	@Transactional
	public String reserva(HttpServletRequest request, HttpSession session, Model model){
		logger.info("Realizamos la reserva");
		String dia = request.getParameter("dia");
		String mes = request.getParameter("mes");
		String hora = request.getParameter("hora");
		String min = request.getParameter("min");
		String horario = "El "+ dia + " de " + mes + " a las " + hora + ":" +min + " horas"  ;
		Integer day = Integer.valueOf(dia);
		Boolean val = true;
		if (day == 29 || day == 30 || day==31){
			val = this.horarioValido(dia, mes);
		}
		if (val){
			try{
				Local l = (Local)session.getAttribute("local");
				Usuario user = entityManager.find(Usuario.class, ((Usuario)session.getAttribute("usuario")).getId());			
				
				Reserva r = new Reserva();
				r.setHorario(horario);
				r.setAsistente(user);
				r.setLocal(l);
				entityManager.persist(r);
				user.getReservas().add(r);
				model.addAttribute("infoHomeController","alert('Reserva realizada');");
			}
			catch(NoResultException nre){
				model.addAttribute("Error reserva", "Reserva no realizada");
				logger.info("Fallo de reserva");
			}
			return "redirect:/user";
		}
		else {
			model.addAttribute("erroHomeController","alert('Fecha erronea');");
			logger.info("Fecha erronea");
			return "redirect:/user";
		}
	}
	
	/**
	 * Mira si una fecha es valida
	 * @param dia
	 * @param mes
	 * @return
	 */
	private boolean horarioValido(String dia,String mes){
		boolean valido=false;
		Integer day = Integer.parseInt(dia);
		String[] meses = {"Enero","Febrero","Marzo","Abril","Mayo","Junio","Julio","Agosto","Septiembre",
				"Octubre","Noviembre","Diciembre"};
		if (day == 31 && (meses[0].equals(mes) || meses[2].equals(mes) || meses[4].equals(mes) 
				|| meses[6].equals(mes) || meses[7].equals(mes) || meses[9].equals(mes)
				|| meses[11].equals(mes)) ){
			valido = true;
		}
		if (day == 30 && (meses[3].equals(mes) || meses[5].equals(mes) || meses[8].equals(mes)
				|| meses[10].equals(mes) )){
			valido = true;
		}
		if (day == 29 && meses[1].equals(mes)) valido = false;
		return valido;
	}
	
	//Agregar a favoritos
	@RequestMapping(value="addFavorite",method = RequestMethod.GET)
	@Transactional
	public String addFavorites(HttpSession session, Model model){
		try{
			Usuario u = entityManager.find(Usuario.class, ((Usuario)session.getAttribute("usuario")).getId() );
			Local l = entityManager.find(Local.class, ((Local)session.getAttribute("local")).getId() );
			//Ver si está en los favoritos ya insertado
			if (!u.getFavoritos().contains(l)){
				u.getFavoritos().add(l);
				l.getForofos().add(u);
			}
			else logger.info("El local ya está en favoritos del usuario");
		}
		catch(NoResultException nre){
			logger.error("Error en agregar favoritos",nre);
		}
		return "index";
	}
	
	//Empieza search
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	@Transactional
	public String search(HttpSession session, @RequestParam("q") String tipo ,Model model) {
		model.addAttribute("pageTitle", "Búsqueda");
		logger.info("Busqueda de locales");
		logger.info(tipo);
		try{
			List<Local> l = entityManager.createNamedQuery("listaLocalesPorTipo",Local.class)
					.setParameter("nombreParam",tipo).getResultList();
			System.out.println(l.size());
			model.addAttribute("locales", l);
		}
		catch (NoResultException nre){
			logger.error("Saltó excepcion busqueda",nre);
			model.addAttribute("Error busqueda", "No hay locales de ese tipo");
		}		
		return "search";
	}
	
	@RequestMapping(value = "/searchnombre", method = RequestMethod.GET)
	@Transactional
	public String searchNombre(HttpSession session, @RequestParam("q") String tipo ,
			@RequestParam("p") String nombre, Model model) {
		model.addAttribute("pageTitle", "Búsqueda");
		logger.info("Busqueda de locales por nombre");
		try{
			List<Local> l = entityManager.createNamedQuery("localesPorNombre",Local.class)
					.setParameter("tipoParam",tipo.toLowerCase(Locale.getDefault())).setParameter("nombreParam", nombre).getResultList();
			model.addAttribute("locales", l);
		}
		catch (NoResultException nre){
			logger.error("Saltó excepcion busqueda",nre);
			model.addAttribute("Error busqueda", "No hay locales de ese tipo");
		}		
		return "search";
	}
	
	//EMPIEZA USER
	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public String user(HttpSession session, Model model) {
		model.addAttribute("pageTitle", "Usuario");
		return "user";
	}
	
	@RequestMapping(value = "/content/user_desktop", method = RequestMethod.GET)
	@Transactional
	public String user_desktop(HttpSession session, Model model) {
		Usuario u = (Usuario)session.getAttribute("usuario");
		model.addAttribute("q", u);
		return "content/user_desktop";
	}
	
	@RequestMapping(value = "/content/user_booking", method = RequestMethod.GET)
	@Transactional
	public String user_booking(HttpSession session, Model model) {
		logger.info("Lista de reservas del usuario");				
		Usuario u = (Usuario)session.getAttribute("usuario");
		@SuppressWarnings("unchecked")
		List<Reserva> lo = (List<Reserva>)entityManager.createNamedQuery("listaReservas")
				  .setParameter("idParam", u.getId()).getResultList();
		model.addAttribute("reservas", lo);
		return "content/user_booking";
	}
	
	@RequestMapping(value = "/content/user_favorites", method = RequestMethod.GET)
	@Transactional
	public String user_favorites(HttpSession session, Model model) {
		logger.info("Lista de locales favoritos del usuario");				
		Usuario u = (Usuario)session.getAttribute("usuario");
		logger.info(u.toString());
		@SuppressWarnings("unchecked")
		List<Local> lo = (List<Local>)entityManager.createNamedQuery("listaFavoritos")
				  .setParameter("idParam", u.getId()).getResultList();
		model.addAttribute("locales", lo);
		return "content/user_favorites";
	}
	
	@RequestMapping(value = "/content/user_profile", method = RequestMethod.GET)
	public String user_profile(HttpSession session, Model model) {
		return "content/user_profile";
	}
	
	/**
	 * Elimina una reserva del usuario
	 * @return
	 */
	@RequestMapping(value="/borrarReserva", method = RequestMethod.POST)
	@Transactional
	public String borrarReserva(HttpSession session, Model model, @RequestParam("id") long id){
		Usuario u = (Usuario)session.getAttribute("usuario");
		//borrar reserva de la BBDD
		if (entityManager.createNamedQuery("borraReserva")
				.setParameter("idParam", id).executeUpdate() == 1){
			@SuppressWarnings("unchecked")
			List<Reserva> lo = (List<Reserva>)entityManager.createNamedQuery("listaReservas")
					  .setParameter("idParam", u.getId()).getResultList();
			model.addAttribute("reservas", lo);
		}
		else {
			model.addAttribute("Error", "Error al borrar la reserva");
			logger.info("Error al borrar la reserva");
		}
				
		return "redirect:/user";
	}
	
	/**
	 * Eliminar un local de los favoritos del usuario
	 */
	@RequestMapping(value="/eliminaFavorito", method = RequestMethod.POST)
	@Transactional
	public String eliminaFavorito(HttpSession session, Model model, @RequestParam("id") long id){
		Usuario u = entityManager.find(Usuario.class, ((Usuario)session.getAttribute("usuario")).getId() );
		Local l = entityManager.find(Local.class, id );
		try{
			if (u.getFavoritos().remove(l) && l.getForofos().remove(u) )
				logger.info("Relación eliminada");
			else logger.info("Error al eliminar");
		}
		catch(Exception e){
			logger.error("Error borrar favorito", e);
		}
		return "redirect:/user";
	}
	
	
	/**
	 * Actualizar el perfil del usuario
	 */
	@RequestMapping(value="updateProfile", method = RequestMethod.POST)
	@Transactional
	public String updateProfile(HttpServletRequest request, HttpSession session, Model model,
			@RequestParam("logo") MultipartFile photo){
		String formNombre = request.getParameter("name");
		String formEmail = request.getParameter("email");
		String formTelf = request.getParameter("telf");
		
		Usuario u = (Usuario)session.getAttribute("usuario");
		
		if (!photo.isEmpty()) {
            try {
                byte[] bytes = photo.getBytes();
                BufferedOutputStream stream =
                        new BufferedOutputStream(
                        		new FileOutputStream(ContextInitializer.getFile("user", String.valueOf(u.getId()))));
                stream.write(bytes);
                stream.close();
                u = entityManager.find(Usuario.class, u.getId());			
    			if (formNombre != null)
    				u.setNombre(formNombre);
    			if (formEmail != null)
    				u.setEmail(formEmail);
    			if (formTelf != null)
    				u.setTelefono(formTelf);
    			session.setAttribute("usuario", u);
            } catch (Exception e) {
                model.addAttribute("Error upload photo", "Fallo al cargar imagen");
            }
        } else {
        	try{
    			u = entityManager.find(Usuario.class, u.getId());			
    			if (formNombre != null)
    				u.setNombre(formNombre);
    			if (formEmail != null)
    				u.setEmail(formEmail);
    			if (formTelf != null)
    				u.setTelefono(formTelf);
    			session.setAttribute("usuario", u);
    		}catch(Exception e){
    			model.addAttribute("Error update profile", "Error profile");
    		}
        }		
		
		return "redirect:/user";		
	}
	
	/**
	 * Returns a users' photo
	 * @param id id of user to get photo from
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/user/photo", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
	public byte[] userPhoto(@RequestParam("id") String id) throws IOException {
	    File f = ContextInitializer.getFile("user", id);
	    InputStream in = null;
	    if (f.exists()) {
	    	in = new BufferedInputStream(new FileInputStream(f));
	    } else {
	    	in = new BufferedInputStream(
	    			this.getClass().getClassLoader().getResourceAsStream("unknown-user.jpg"));
	    }
	    
	    return IOUtils.toByteArray(in);
	}
	
	/**
	 * Devuelve la foto del local
	 * @param id id of local to get photo from
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/local/photo", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
	public byte[] localPhoto(@RequestParam("id") String id) throws IOException {
	    File f = ContextInitializer.getFile("local", id);
	    InputStream in = null;
	    if (f.exists()) {
	    	in = new BufferedInputStream(new FileInputStream(f));
	    } else {
	    	in = new BufferedInputStream(
	    			this.getClass().getClassLoader().getResourceAsStream("unknown-user.jpg"));
	    }
	    
	    return IOUtils.toByteArray(in);
	}
}
