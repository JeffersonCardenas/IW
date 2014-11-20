package es.fdi.iw.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Favoritos {
	
	private int id;
	private int idUsuario;
	private int idLocal;
	
	public Favoritos(){}
	
	public Favoritos(int id, int idUsuario, int idLocal) {
		this.id = id;
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

		
}
