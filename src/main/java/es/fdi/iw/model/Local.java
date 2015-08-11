package es.fdi.iw.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;


@Entity
@NamedQueries({
    @NamedQuery(name="listaLocalesPorTipo",
        query="select u from Local u where u.tipo = :nombreParam"),
    @NamedQuery(name="localesPorNombre",
    	query="select u from Local u where u.tipo = :tipoParam and u.nombre like :nombreParam"),
    @NamedQuery(name="localPorId",
    	query="select u from Local u where u.id = :idParam"),
    @NamedQuery(name="listaLocales",
    	query="select u from Local u"),
    @NamedQuery(name="listaLocalesEmpresario",
    	query="select l from Local l where l.propietario.id = :nombreParam"),
    @NamedQuery(name="listaServicios",
        query="select l from Local u, in (u.servicios) l where u.id = :idParam"),
    @NamedQuery(name="borraLocal",
		query="delete from Local l where l.id = :idParam")
})
public class Local {
	
	private long id;
	private String tipo;
	private String nombre;
	private String horario;
	private String descripcion;
	private String direccion;
	private List<Usuario> forofos;
	private List<Reserva> reservas;
	private List<Voto> listaVotaciones;
	private List<Servicio> servicios;
	private Empresarios propietario;

	public Local(){}	
	
	@Id
    @GeneratedValue
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getTipo() {
		return tipo;
	}
	
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	@Column(length = 2048)
	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	@ManyToMany(targetEntity=Usuario.class)
	public List<Usuario> getForofos() {
		return forofos;
	}

	public String getHorario() {
		return horario;
	}

	public void setHorario(String horario) {
		this.horario = horario;
	}

	public void setForofos(List<Usuario> forofos) {
		this.forofos = forofos;
	}
	
	@ManyToMany(targetEntity=Servicio.class)
	public List<Servicio> getServicios() {
		return servicios;
	}

	public void setServicios(List<Servicio> servicios) {
		this.servicios = servicios;
	}

	@OneToMany(targetEntity=Reserva.class)
	@JoinColumn(name="local_id")
	public List<Reserva> getReservas() {
		return reservas;
	}

	public void setReservas(List<Reserva> reservas) {
		this.reservas = reservas;
	}

	@OneToMany(targetEntity=Voto.class)
	@JoinColumn(name="valorado_id")
	public List<Voto> getListaVotaciones() {
		return listaVotaciones;
	}

	public void setListaVotaciones(List<Voto> listaVotaciones) {
		this.listaVotaciones = listaVotaciones;
	}

	@ManyToOne(targetEntity=Empresarios.class)
	public Empresarios getPropietario() {
		return propietario;
	}

	public void setPropietario(Empresarios propietario) {
		this.propietario = propietario;
	}
}