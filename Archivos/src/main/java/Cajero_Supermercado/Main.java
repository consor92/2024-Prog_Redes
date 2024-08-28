package Cajero_Supermercado;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

	public static void main(String[] args) {

		// --------//

		// --- Aca despues vamos a aplicar Patrones de Dise�o FACTORY ---
		Persona cli1 = new Persona("Pepito", 45678952,
				new HashMap<String, Integer>(Map.of("Arroz", 10, "Coca-Cola", 5, "Vela Cumple", 2, "Globos", 3)));
		Persona cli2 = new Persona("Juan", 36589741,
				new HashMap<String, Integer>(Map.of("Taza", 1, "Cuchara", 1, "Cafe", 1)));
		Persona cli3 = new Persona("Enrique", 69874521,
				new HashMap<String, Integer>(Map.of("Pan", 2, "Manteca", 1, "Cuchillo de Untar", 1)));
		Persona cli4 = new Persona("Maria", 45534578, new HashMap<String, Integer>(
				Map.of("Vodka", 2, "Fernet", 2, "Speed", 8, "Jugo de Naranja", 5, "Coca-Cola", 4)));
		Persona cli5 = new Persona("Sofia", 63642158,
				new HashMap<String, Integer>(Map.of("Pritty-Limon", 3, "Esponja", 2, "Guante", 2, "Detergente", 1)));
		Persona cli6 = new Persona("Hector", 25632568,
				new HashMap<String, Integer>(Map.of("Mandioca", 3, "Papayas", 2, "Biscochitos", 2)));
		Persona cli7 = new Persona("Maxi", 68574125,
				new HashMap<String, Integer>(Map.of("yerba", 2, "Azucar", 1, "Edulcurante", 2, "Yuyos varios", 3)));
		Persona cli8 = new Persona("Laura", 25632568,
				new HashMap<String, Integer>(Map.of("Tomates", 3, "Budin", 2, "Nesquik", 1, "Leche", 3)));
		Persona cli9 = new Persona("Matias", 12458524,
				new HashMap<String, Integer>(Map.of("Medialuna", 12, "Pepino", 3, "Bagio", 2)));
		Persona cli10 = new Persona("Renzo", 25632568, new HashMap<String, Integer>(
				Map.of("Test Embarazo", 1, "Lubricante", 3, "Hojas A4", 2, "Pa�ales", 3)));

		// Crear un Array con Datos iniciales
		// Crear un Map con datos iniciales
		// new ArrayList<>( List.of( elem1, elem2, elem3, ...) )
		// new HashMap<String, Integer>( Map.of( k1,v2 ,k2,v2 , ... ) )

		ArrayList<Persona> colaCaja1 = new ArrayList<>();
		colaCaja1.add(cli10);
		colaCaja1.add(cli9);
		colaCaja1.add(cli5);
		colaCaja1.add(cli4);

		ArrayList<Persona> colaCaja2 = new ArrayList<>();
		colaCaja2.add(cli1);
		colaCaja2.add(cli8);
		colaCaja2.add(cli2);
		colaCaja2.add(cli3);

		// Obtenemos la marca de tiempo nical del S.O para
		// generar comparacione en las tareas.
		long tiempoInicial = System.currentTimeMillis();

		// esta clase heredaba de Thread, asi que es
		// un Hilo y tiene actividades para hacer.
		CajaHilo Caja1 = new CajaHilo(7, "Raul", tiempoInicial, colaCaja1);
		Caja1.setName("Caja_1");
		Caja1.start();
		
		tiempoInicial = System.currentTimeMillis();
		CajaHilo Caja2 = new CajaHilo(7, "Raul", tiempoInicial, Caja1 , colaCaja2);
		Caja2.setName("Caja_2");
		Caja2.start();
		
		
		PrintStream ps = new PrintStream(System.out);
		ps.println("Seguimos con las tareas principales (HILOS EN PARALELOS)");
		/*
		 * Thread Caja2 = new Thread( new CajaHilo(8, "Roberto", tiempoInicial,
		 * colaCaja2), "Caja_2" //nombre del HILO ); Caja2.start();
		 */

		// esta clase implementa Runnable asi que
		// no es un Hilo es solamente una tarea a realizar.
		Seguridad tareasDeSeguridad = new Seguridad();
		Thread guardia1 = new Thread(tareasDeSeguridad, "Guardia1");
		guardia1.setPriority(Thread.MAX_PRIORITY); // 0-10 Enum
		guardia1.start();

		// cuando la tarea es muy cortita para realizar
		// conviene armar el Hilo directo sin crear Class nuevas
		Thread empleado1 = new Thread(new Runnable() {
			@Override
			public void run() {

			}
		});
		empleado1.setName("Empleado1");
		empleado1.setPriority(Thread.MIN_PRIORITY);
		empleado1.start();

		//fin hilo principal MAIN
	}

	public void metodosHilo(long tiempoInicial) {
		// Ciclo de vida: NEW
		Thread caja_1 = new Thread();
		caja_1.getName();
		caja_1.setName("Caja_1");
		caja_1.getPriority();
		caja_1.setPriority(Thread.MAX_PRIORITY);
		caja_1.getState(); // en que parte del CICLO DE VIDA esta
		caja_1.getThreadGroup();
		caja_1.isAlive();
		caja_1.isInterrupted();

		try {
			caja_1.interrupted();
			caja_1.interrupt();
			caja_1.sleep(tiempoInicial);
			caja_1.join();
			caja_1.wait(tiempoInicial);
			caja_1.suspend();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		caja_1.resume();// reactiva la tarea de un Thread
		caja_1.notify();
		caja_1.notifyAll();
		caja_1.start();
		caja_1.stop();

		caja_1.currentThread();
	}

}
