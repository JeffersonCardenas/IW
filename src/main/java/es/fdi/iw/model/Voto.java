package es.fdi.iw.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Voto {
	
	private long id;
	private Usuario votante;
	private Local valorado;
	private int numero;
	
	public Voto(){}

	@Id
    @GeneratedValue
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@ManyToOne(targetEntity=Usuario.class)
	public Usuario getVotante() {
		return votante;
	}

	@ManyToOne(targetEntity=Local.class)
	public Local getValorado() {
		return valorado;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public void setVotante(Usuario votante) {
		this.votante = votante;
	}

	public void setValorado(Local valorado) {
		this.valorado = valorado;
	}
}
