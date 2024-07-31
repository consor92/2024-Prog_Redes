package Cajero_Supermercado;

import java.util.ArrayList;

public class CajaHilo extends Thread {

	private int numCaja;
	private String nombreCajero;
	private long tiempoInicial;
	private ArrayList<Persona> filaClientes;
	
	public CajaHilo(int nro, String nom, long ti , ArrayList<Persona> listCli) {
		this.numCaja = nro;
		this.nombreCajero = nom;
		this.tiempoInicial = ti;
		this.filaClientes = listCli;
	}
	
	/////////////////////////////////
	@Override
	public void run() {
		
	}
	////////////////////////////////
	
	public int getNumCaja() {
		return numCaja;
	}

	public void setNumCaja(int numCaja) {
		this.numCaja = numCaja;
	}

	public String getNombreCajero() {
		return nombreCajero;
	}

	public void setNombreCajero(String nombreCajero) {
		this.nombreCajero = nombreCajero;
	}

	public long getTiempoInicial() {
		return tiempoInicial;
	}

	public void setTiempoInicial(long tiempoInicial) {
		this.tiempoInicial = tiempoInicial;
	}

	public ArrayList<Persona> getFilaClientes() {
		return filaClientes;
	}

	public void setFilaClientes(ArrayList<Persona> filaClientes) {
		this.filaClientes = filaClientes;
	}
	
}
