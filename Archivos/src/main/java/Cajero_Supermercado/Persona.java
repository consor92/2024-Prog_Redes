package Cajero_Supermercado;

import java.util.ArrayList;
import java.util.Map;

public class Persona {

	private String nombre;
	private int dni;
	private Map<String,Integer> changuito;
	
	/**
	 * 
	 * @param nom
	 * @param dni
	 * @param chan
	 * @param strings
	 * @param js
	 */
	public Persona(String nom, int dni, Map<String,Integer> chan ) {
		this.nombre = nom;
		this.dni = dni;
		this.changuito = chan;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getDni() {
		return dni;
	}

	public void setDni(int dni) {
		this.dni = dni;
	}

	public Map<String, Integer> getChanguito() {
		return changuito;
	}

	public void setChanguito(Map<String, Integer> changuito) {
		this.changuito = changuito;
	}

	
	
	
}
