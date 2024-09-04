package Banco;

import java.io.PrintStream;
import java.util.Random;

/**
 * Clase principal que inicializa el banco y crea hilos para realizar transferencias entre cuentas.
 */
public class main {

    // Número total de cuentas a crear en el banco
    public static int cantCuentas = 100;
    
    /**
     * Método principal que ejecuta la aplicación.
     * 
     * @param args Argumentos de línea de comandos (no se utilizan en esta aplicación).
     */
    public static void main(String[] args) {
        Long tiempoActual;
        PrintStream ps = new PrintStream(System.out);
        Random rand = new Random(System.currentTimeMillis());
        
        // Crea una instancia del banco con un número de cuentas y un monto inicial aleatorio
        Banco bancoNacion = new Banco(cantCuentas, rand.nextInt(2000) + 3000, "Nacion");
        
        ps.printf(Colores.ANSI_WHITE_BACKGROUND + "\t\t\t" + Colores.ANSI_PURPLE + "C.C creadas: %d \t\t\n" + Colores.ANSI_RESET, cantCuentas);
        ps.printf(Colores.ANSI_WHITE_BACKGROUND + "\t\t\t" + Colores.ANSI_PURPLE + "Banco %s \t\t\t" + Colores.ANSI_RESET, bancoNacion.getName());
        
        // Crea y arranca 100 hilos para realizar transferencias
        for (int nroCuenta = 0; nroCuenta < 100; nroCuenta++) {
            int monto = rand.nextInt(5000 - 500) + 500; // Monto entre 500 y 5000
            tiempoActual = System.currentTimeMillis();
            Transferencias transfEnCurso = new Transferencias(bancoNacion, nroCuenta, monto, tiempoActual);
            
            // Configura el nombre y la prioridad del hilo de transferencia
            transfEnCurso.setName("Cuenta-" + nroCuenta);
            transfEnCurso.setPriority(rand.nextInt(10) + 1); // Prioridad entre 1 y 10
        
            // Inicia el hilo de transferencia
            transfEnCurso.start();
        } // for
    } // main
}
