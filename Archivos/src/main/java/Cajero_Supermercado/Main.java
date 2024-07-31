package Cajero_Supermercado;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main {

	public static void main(String[] args) {

		// --------//
		// Obtenemos la marca de tiempo nical del S.O para
		// generar comparacione en las tareas.
		long tiempoInicial = System.currentTimeMillis();

		// --- Aca despues vamos a aplicar Patrones de Diseño FACTORY ---
		Persona cli1 = new Persona("Javi", 25632568, new String[] { "Arroz", "Coca-Cola", "Vela Cumple", "Globos" },
				new int[] { 10, 5, 2, 3 });
		Persona cli2 = new Persona("Juan", 36589741, new String[] { "Taza", "Cuchara", "Cafe" }, new int[] { 1, 1, 1 });
		Persona cli3 = new Persona("Enrique", 69874521, new String[] { "Pan", "Manteca", "Cuchillo de Untar" },
				new int[] { 2, 1, 1 });
		Persona cli4 = new Persona("Maria", 45534578,
				new String[] { "Vodka", "Fernet", "Speed", "Jugo de Naranja", "Coca-Cola" },
				new int[] { 2, 2, 8, 5, 4 });
		Persona cli5 = new Persona("Sofia", 63642158,
				new String[] { "Pritty-Limon", "Esponja", "Guante", "Detergente" }, new int[] { 3, 2, 2, 1 });
		Persona cli6 = new Persona("Hector", 25632568, new String[] { "Mandioca", "Papayas", "Biscochitos" },
				new int[] { 3, 2, 2 });
		Persona cli7 = new Persona("Maxi", 68574125, new String[] { "yerba", "Azucar", "Edulcurante", "Yuyos varios" },
				new int[] { 2, 1, 2, 3 });
		Persona cli8 = new Persona("Laura", 25632568, new String[] { "Tomates", "Budin", "Nesquik", "Leche" },
				new int[] { 3, 2, 1, 3 });
		Persona cli9 = new Persona("Matias", 12458524, new String[] { "Medialuna", "Pepino", "Bagio" },
				new int[] { 12, 3, 2 });
		Persona cli10 = new Persona("Renzo", 25632568,
				new String[] { "Test Embarazo", "Lubricante", "Hojas A4", "Pañales" }, new int[] { 1, 3, 2, 3 });
		//new ArrayList<>(List.of( Map.of("key1", 1, "key2", 2, "key3", 3)));
		
		//esta clase heredaba de Thread, asi que es
		// un Hilo y tiene actividades para hacer.
		CajaHilo caja1 = new CajaHilo();
		caja1.start();
		
		// esta clase implementa Runnable asi que 
		// no es un Hilo es solamente una tarea a realizar.
		Seguridad tareasDeSeguridad = new Seguridad();
		Thread guardia1 = new Thread(tareasDeSeguridad,"Guardia1");
		guardia1.setPriority( Thread.MAX_PRIORITY ); //0-10 Enum
		guardia1.start();
		
		//cuando la tarea es muy cortita para realizar
		//conviene armar el Hilo directo sin crear Class nuevas
		Thread empleado1 = new Thread(
				new Runnable() {
					@Override
					public void run() {
						
					}
				});
		empleado1.setName("Empleado1");
		empleado1.setPriority(Thread.MIN_PRIORITY);
		empleado1.start();
		
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
