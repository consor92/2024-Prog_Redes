package Banco;

import java.io.PrintStream;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase que representa un hilo encargado de realizar transferencias entre cuentas del banco.
 * Hereda de la clase {@code Thread} para ejecutar las transferencias en un hilo separado.
 */
public class Transferencias extends Thread {

    private Banco banco;
    private int cuentaOrigen;
    private int monto;
    private Random rand;
    private boolean vivo = true;
    private int cuentaDestino;
    private Long tiempoActual;
    
    private PrintStream ps = new PrintStream(System.out);

    /**
     * Constructor para inicializar el objeto Transferencias.
     *
     * @param banco       Instancia del banco donde se realizarán las transferencias.
     * @param cuentaOrigen Número de la cuenta de origen de las transferencias.
     * @param monto       Monto a transferir en cada operación.
     * @param time        Tiempo actual utilizado para la inicialización del generador de números aleatorios.
     */
    public Transferencias(Banco banco, int cuentaOrigen, int monto, Long time) {
        this.banco = banco;
        this.cuentaOrigen = cuentaOrigen;
        this.monto = monto;
        this.tiempoActual = time;

        rand = new Random(System.currentTimeMillis());
    } // Constructor

    @Override
    public void run() {
        try {
            ps.printf("\n" + Colores.ANSI_BLUE_BACKGROUND + "\t\t\t" + Colores.ANSI_GREEN + "Inicio de Transferencia \t\t" + Colores.ANSI_RESET);
            ps.printf("\n" + Colores.ANSI_BLUE_BACKGROUND + "\t\t\t" + Colores.ANSI_GREEN + "Hilo: %s Banco:%s \t\t" + Colores.ANSI_RESET, this.currentThread().getName(), this.getName());

            while (vivo) {
                // Genera un número de cuenta destino diferente a la cuenta de origen
                cuentaDestino = rand.nextInt(100);
                while (cuentaOrigen == cuentaDestino) {
                    cuentaDestino = rand.nextInt(100);
                }

                ps.printf("\n" + Colores.ANSI_BLUE_BACKGROUND + "\t\t\t" + Colores.ANSI_GREEN + "C.C:%d a C.C:Cuenta-%d  Monto:%d$ \t\t\n" + Colores.ANSI_RESET, this.cuentaOrigen, this.cuentaDestino, this.monto);

                // Realiza la transferencia
                banco.transferir(cuentaOrigen, cuentaDestino, this.monto);
                
                // Duerme el hilo por un tiempo aleatorio
                Thread.sleep(rand.nextInt(100));
                
                // Termina el ciclo de transferencia
                this.matar();
            } // while
            
            ps.printf("\n" + Colores.ANSI_BLUE_BACKGROUND + "\t\t\t" + Colores.ANSI_GREEN + "TRANSFERENCIA: %s" + Colores.ANSI_RED + " FINALIZADA \t\t\n\n" + Colores.ANSI_RESET, this.currentThread().getName());

        } catch (InterruptedException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }
    } // run

    /**
     * Detiene el hilo de transferencia estableciendo la bandera {@code vivo} a {@code false}.
     */
    public void matar() {
        this.vivo = false;
    }

} // class
