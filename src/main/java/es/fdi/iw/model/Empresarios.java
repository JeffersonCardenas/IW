package es.fdi.iw.model;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Random;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@Entity
@NamedQueries({
    @NamedQuery(name="empresarioPorId",
    	query="select u from Empresarios u where u.id = :idParam"),
    @NamedQuery(name="empresarioByNombre",
        query="select u from Empresarios u where u.nombre = :nombreParam"),
    @NamedQuery(name="listaEmpresarios",
    	query="select u from Empresarios u")
})
public class Empresarios {
	
	private long id;
	private String nombre;
	private String email;
	private String hashedAndSalted;
	private String salt;
	private String rol;
	private List<Local> locales;
	
	public Empresarios(){}
	
	public static Empresarios createEmpresarios(String nombre, String email, String pass) {
		Empresarios u = new Empresarios();
		u.nombre = nombre;
		u.email = email;
		Random r = new Random();
		
		// generate new, random salt; build hashedAndSalted
		byte[] saltBytes = new byte[16];
		r.nextBytes(saltBytes);
		u.salt = byteArrayToHexString(saltBytes);
		u.hashedAndSalted = generateHashedAndSalted(pass, u.salt);
		return u;
	}
	
	public boolean isPassValid(String pass) {
		return generateHashedAndSalted(pass, this.salt).equals(hashedAndSalted);		
	}
	
	public static String generateHashedAndSalted(String pass, String salt) {
		byte[] saltBytes = hexStringToByteArray(salt);
		byte[] passBytes = pass.getBytes();
		byte[] toHash = new byte[saltBytes.length + passBytes.length];
		System.arraycopy(passBytes, 0, toHash, 0, passBytes.length);
		System.arraycopy(saltBytes, 0, toHash, passBytes.length, saltBytes.length);
		return byteArrayToHexString(hash(toHash));
	}
	
	public static String byteArrayToHexString(byte[] b) {
		StringBuilder sb = new StringBuilder();
		for (int i=0; i<b.length; i++) {
			sb.append(Integer.toString((b[i]&0xff) + 0x100, 16).substring(1));
		}
		return sb.toString();
	}
	
	public static byte[] hexStringToByteArray(String hex) {
		byte[] r = new byte[hex.length()/2];
		for (int i=0; i<r.length; i++) {
			String h = hex.substring(i*2, (i+1)*2);
			r[i] = (byte)Integer.parseInt(h, 16);
		}
		return r;
	}
	
	public static byte[] hash(byte[] bytes) {
		MessageDigest md = null;
	    try {
	        md = MessageDigest.getInstance("SHA-1");
	    } catch(NoSuchAlgorithmException e) {
	        e.printStackTrace();
	    } 
	    return md.digest(bytes);
	}
	
	@Id
    @GeneratedValue
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	

	public String getHashedAndSalted() {
		return hashedAndSalted;
	}

	public void setHashedAndSalted(String hashedAndSalted) {
		this.hashedAndSalted = hashedAndSalted;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}
	

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	@OneToMany(targetEntity=Local.class)
	@JoinColumn(name="propietario_id")
	public List<Local> getListaLocales() {
		return locales;
	}

	public void setListaLocales(List<Local> listaLocales) {
		this.locales = listaLocales;
	}		
}
