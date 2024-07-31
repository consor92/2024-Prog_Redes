package Cajero_Supermercado;

import java.util.ArrayList;
import java.util.Map;

public class Persona {

	private String nombre;
	private int dni;
	private ArrayList<Map<String,Integer>> changuito;
	
	public Persona(String nom, int dni, ArrayList<Map<String,Integer>> chan ,String[] strings, int[] js) {
		this.nombre = nom;
		this.dni = dni;
		this.changuito = chan;
	}

}
