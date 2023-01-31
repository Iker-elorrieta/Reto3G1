package modelo;

import java.util.Objects;

public class Hora {
	private int horas;
	private int mins;
	public int getHoras() {
		return horas;
	}
	public void setHoras(int horas) {
		this.horas = horas;
	}
	public int getMins() {
		return mins;
	}
	public void setMins(int mins) {
		this.mins = mins;
	}
	@Override
	public int hashCode() {
		return Objects.hash(horas, mins);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Hora other = (Hora) obj;
		return horas == other.horas && mins == other.mins;
	}
	@Override
	public String toString() {
		return horas + ":" + mins;
	}

}
