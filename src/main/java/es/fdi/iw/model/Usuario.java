package es.fdi.iw.model;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Random;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@Entity
@NamedQueries({
    @NamedQuery(name="usuarioByNombre",
        query="select u from Usuario u where u.nombre = :nombreParam"),
    @NamedQuery(name="usuarioById",
    query="select u from Usuario u where u.id = :idParam"),
    @NamedQuery(name="listaFavoritos",
    query="select l from Usuario u, in (u.favoritos) l where u.id = :idParam"),
    @NamedQuery(name="listaReservas",
    query="select l from Usuario u, in (u.reservas) l where u.id = :idParam"),
    @NamedQuery(name="delReseva",
    query="delete from Reserva r where r.id= :idParam")
})

public class Usuario {
	
	private long id;
	private String nombre;
	private String email;
	private String telefono;
	private String hashedAndSalted;
	private String salt;
	private String rol;
	private List<Local> favoritos;
	private List<Reserva> reservas;
	private List<Voto> puntuacion;
	
	public Usuario() {}
	
	public static Usuario createUsuario(String nombre, String email, String pass, String role) {
		Usuario u = new Usuario();
		u.nombre = nombre;
		u.email = email;
		u.rol = role;
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

	@Column(unique=true)
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

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	@ManyToMany(targetEntity=Local.class, mappedBy="forofos")
	public List<Local> getFavoritos() {
		return favoritos;
	}

	public void setFavoritos(List<Local> favoritos) {
		this.favoritos = favoritos;
	}
	
	@OneToMany(targetEntity=Reserva.class)
	@JoinColumn(name="asistente_id")	//Evita crear una tabla intermedia
	public List<Reserva> getReservas() {
		return reservas;
	}

	public void setReservas(List<Reserva> reservas) {
		this.reservas = reservas;
	}
	
	@OneToMany(targetEntity=Voto.class)
	@JoinColumn(name="votante_id")
	public List<Voto> getPuntuacion() {
		return puntuacion;
	}

	public void setPuntuacion(List<Voto> puntuacion) {
		this.puntuacion = puntuacion;
	}

	public String toString() {
		return "" + id + " " + nombre + " " + email + " " + hashedAndSalted + " " + salt;
	}	
}