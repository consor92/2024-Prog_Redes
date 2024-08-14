package Cajero_Supermercado;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CajaHilo extends Thread {

	private int numCaja;
	private String nombreCajero;
	private long tiempoInicial;
	private ArrayList<Persona> filaClientes; // cola de gente a PAGAR
	private boolean matar = false;


	public CajaHilo(int nro, String nom, long ti, ArrayList<Persona> listCli) {
		this.numCaja = nro;
		this.nombreCajero = nom;
		this.tiempoInicial = ti;
		this.filaClientes = listCli;
	}

	/////////////////////////////////
	// esta es la TAREA A EJECUTAR POR EL HILO
	@Override
	public void run() {
		while( !matar ) {
			for( Persona p : filaClientes ) {
				ps.printf("Cliente: %s (%d) \n", p.getNombre() , p.getDni());
				procesarCompra( p.getChanguito() );	
			}
			ps.printf("Caja No. %d --finaliza ventas-- \n", this.getNumCaja());
			this.matarHilo();
		}
	}
	////////////////////////////////

	PrintStream ps = new PrintStream(System.out);

	private void procesarCompra(Map<String, Integer> changuito) {
		// recorremo un diccionario
		//ps.println(this.getNombreCajero());
		ps.printf("Caja No. %d - (%s)\n", this.getNumCaja(), this.getNombreCajero());
		for (Map.Entry<String, Integer> c : changuito.entrySet()) {
			cobrarProducto(c.getValue());
			ps.printf("\t Producto: %s --Cant: %d-- \n", c.getKey(), c.getValue());
			// agregar tiempos de espera
		}
	}

	private void cobrarProducto(int cant) {
		try {
			Thread.sleep(cant * 300);
		} catch (InterruptedException e) {
			Logger.getLogger(CajaHilo.class.getName()).log(Level.SEVERE, null, e);
		}
	}

	public void matarHilo()
	{
		this.matar = true;
	}
	
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
