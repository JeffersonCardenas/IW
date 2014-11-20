package es.fdi.iw.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Reservan {
	
	private int id;
	private int idUsuario;
	private int idLocal;
	private Date fecha;
	private int duracion;
	
	public Reservan(){}
	
	public Reservan(int id, int idUsuario, int idLocal, Date fecha, int duracion) {
		this.id = id;
		this.duracion = duracion;
		this.fecha = fecha;
		this.idLocal = idLocal;
		this.idUsuario = idUsuario;
	}
	
	@Id
    @GeneratedValue
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public int getIdLocal() {
		return idLocal;
	}

	public void setIdLocal(int idLocal) {
		this.idLocal = idLocal;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public int getDuracion() {
		return duracion;
	}

	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}


		
}
