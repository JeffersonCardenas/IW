package es.fdi.iw.reserving;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import es.fdi.iw.ContextInitializer;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@PersistenceContext
	private EntityManager entityManager;
	
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
		return "admin";
	}
	
	@RequestMapping(value = "/content/super_admin_desktop", method = RequestMethod.GET)
	public String super_admin_desktop(HttpSession session, Model model) {
		model.addAttribute("pageTitle", "Super_admin_desktop");
		return "content/super_admin_desktop";
	}
	
	@RequestMapping(value = "/content/super_admin_business", method = RequestMethod.GET)
	public String super_admin_business(HttpSession session, Model model) {
		model.addAttribute("pageTitle", "Super_admin_business");
		return "content/super_admin_business";
	}
	
	//EMPIEZA COMPANY
	@RequestMapping(value = "/content/desktop", method = RequestMethod.GET)
	public String desktop(HttpSession session, Model model) {
		model.addAttribute("pageTitle", "Desktop");
		return "content/desktop";
	}
	
	@RequestMapping(value = "/content/admin_welcome", method = RequestMethod.GET)
	public String admin_welcome(HttpSession session, Model model) {
		model.addAttribute("pageTitle", "Admin_welcome");
		return "content/admin_welcome";
	}
	
	@RequestMapping(value = "/content/admin_about", method = RequestMethod.GET)
	public String admin_about(HttpSession session, Model model) {
		model.addAttribute("pageTitle", "admin_about");
		return "content/admin_about";
	}
	
	@RequestMapping(value = "/content/admin_services", method = RequestMethod.GET)
	public String admin_services(HttpSession session, Model model) {
		model.addAttribute("pageTitle", "Admin_services");
		return "content/admin_services";
	}
	
	@RequestMapping(value = "/content/admin_map", method = RequestMethod.GET)
	public String admin_map(HttpSession session, Model model) {
		model.addAttribute("pageTitle", "Admin_map");
		return "content/admin_map";
	}
	
	@RequestMapping(value = "/content/admin_booking", method = RequestMethod.GET)
	public String admin_booking(HttpSession session, Model model) {
		model.addAttribute("pageTitle", "Admin_booking");
		return "content/admin_booking";
	}
	
	@RequestMapping(value = "/content/admin_profile", method = RequestMethod.GET)
	public String admin_profile(HttpSession session, Model model) {
		model.addAttribute("pageTitle", "Admin_profile");
		return "content/admin_profile";
	}
	
	@RequestMapping(value = "/company", method = RequestMethod.GET)
	public String company(HttpSession session, Model model) {
		model.addAttribute("pageTitle", "Empresa");
		return "company";
	}
	
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String search(HttpSession session, Model model) {
		model.addAttribute("pageTitle", "Búsqueda");
		return "search";
	}
	
	//EMPIEZA PLACE
	@RequestMapping(value = "/place", method = RequestMethod.GET)
	public String place(HttpSession session, Model model) {
		model.addAttribute("pageTitle", "Web Empresa");
		model.addAttribute("content", "../fragments/content/welcome.jspf");
		return "place";
	}
	
	@RequestMapping(value = "/content/welcome", method = RequestMethod.GET)
	public String welcome(HttpSession session, Model model) {
		model.addAttribute("pageTitle", "Welcome");
		return "content/welcome";
	}
	
	@RequestMapping(value = "/content/about", method = RequestMethod.GET)
	public String about(HttpSession session, Model model) {
		model.addAttribute("pageTitle", "About");
		return "content/about";
	}
	
	@RequestMapping(value = "/content/services", method = RequestMethod.GET)
	public String services(HttpSession session, Model model) {
		model.addAttribute("pageTitle", "Services");
		return "content/services";
	}
	
	@RequestMapping(value = "/content/map", method = RequestMethod.GET)
	public String map(HttpSession session, Model model) {
		model.addAttribute("pageTitle", "Map");
		return "content/map";
	}
	
	@RequestMapping(value = "/content/booking", method = RequestMethod.GET)
	public String booking(HttpSession session, Model model) {
		model.addAttribute("pageTitle", "Booking");
		//model.addAttribute("content", "../fragments/content/welcome.jspf");
		return "content/booking";
	}
	
	//EMPIEZA USER
	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public String user(HttpSession session, Model model) {
		model.addAttribute("pageTitle", "Usuario");
		return "user";
	}
	
	@RequestMapping(value = "/content/user_desktop", method = RequestMethod.GET)
	public String user_desktop(HttpSession session, Model model) {
		model.addAttribute("pageTitle", "User_desktop");
		return "content/user_desktop";
	}
	
	@RequestMapping(value = "/content/user_booking", method = RequestMethod.GET)
	public String user_booking(HttpSession session, Model model) {
		model.addAttribute("pageTitle", "User_booking");
		return "content/user_booking";
	}
	
	@RequestMapping(value = "/content/user_favorites", method = RequestMethod.GET)
	public String user_favorites(HttpSession session, Model model) {
		model.addAttribute("pageTitle", "User_favorites");
		return "content/user_favorites";
	}
	
	@RequestMapping(value = "/content/user_profile", method = RequestMethod.GET)
	public String user_profile(HttpSession session, Model model) {
		model.addAttribute("pageTitle", "User_profile");
		return "content/user_profile";
	}

	/**
	 * Uploads a photo for a user
	 * @param id of user 
	 * @param photo to upload
	 * @return
	 */
	@RequestMapping(value="/user", method=RequestMethod.POST)
    public @ResponseBody String handleFileUpload(@RequestParam("photo") MultipartFile photo,
    		@RequestParam("id") String id){
        if (!photo.isEmpty()) {
            try {
                byte[] bytes = photo.getBytes();
                BufferedOutputStream stream =
                        new BufferedOutputStream(
                        		new FileOutputStream(ContextInitializer.getFile("user", id)));
                stream.write(bytes);
                stream.close();
                return "You successfully uploaded " + id + 
                		" into " + ContextInitializer.getFile("user", id).getAbsolutePath() + "!";
            } catch (Exception e) {
                return "You failed to upload " + id + " => " + e.getMessage();
            }
        } else {
            return "You failed to upload a photo for " + id + " because the file was empty.";
        }
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
}
