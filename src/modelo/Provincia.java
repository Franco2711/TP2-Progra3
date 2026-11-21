package modelo;

import java.util.Objects;

public class Provincia {
	
	private String nombre;
	private double latitud;
	private double longitud;
	
	public Provincia(String nombre, double latitud, double longitud) {
		this.nombre = nombre.trim();
		this.latitud = latitud;
		this.longitud = longitud;
	}
	
	private String clave() {
		return nombre.toLowerCase();
	}
	@Override
	public boolean equals(Object o) {
		if (this==o) 
			return true;
		if (!(o instanceof Provincia otra))
			return false;
		
		return Objects.equals(this.clave(), otra.clave());
	}
	@Override
	public int hashCode() {
		return Objects.hash(clave());
	}
	public String getNombre(){
		return this.nombre;
	}
}
