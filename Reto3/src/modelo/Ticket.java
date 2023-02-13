package modelo;

import java.util.Arrays;
import java.util.Objects;

public class Ticket {
	private int cdTicket;
	private float costeTotal;
	private Cliente cliente;
	private Entrada[] entradas;

	public int getCdTicket() {
		return cdTicket;
	}
	public void setCdTicket(int cdTicket) {
		this.cdTicket = cdTicket;
	}
	public float getCosteTotal() {
		return costeTotal;
	}
	public void setCosteTotal(float costeTotal) {
		this.costeTotal = costeTotal;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public Entrada[] getEntradas() {
		return entradas;
	}
	public void setEntradas(Entrada[] entradas) {
		this.entradas = entradas;
	}
	
	@Override
	public String toString() {
		return "[" + cdTicket + "," + costeTotal + "," + cliente + ","
				+ Arrays.toString(entradas) + "]";
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ticket other = (Ticket) obj;
		return cdTicket == other.cdTicket ;
	}
	
}