package es.fdi.iw.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
	@NamedQuery(name="borraReserva",
			query="delete from Reserva r where r.id = :idParam")
})
public class Reserva {
	
	private long id;
	private Usuario asistente;
	private Local local;	
	private String horario;
	
	public Reserva(){}
	
	@Id
    @GeneratedValue
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@ManyToOne(targetEntity=Usuario.class)
	public Usuario getAsistente() {
		return asistente;
	}

	public void setAsistente(Usuario asistente) {
		this.asistente = asistente;
	}

	@ManyToOne(targetEntity=Local.class)
	public Local getLocal() {
		return local;
	}

	public void setLocal(Local local) {
		this.local = local;
	}

	public String getHorario() {
		return horario;
	}

	public void setHorario(String horario) {
		this.horario = horario;
	}
		
}
