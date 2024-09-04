package Banco;

import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Clase que representa un banco que gestiona cuentas y operaciones de transferencia.
 * Utiliza mecanismos de bloqueo para asegurar la consistencia en las operaciones.
 */
public class Banco {

    private String nombreBanco;
    private int sucursal;
    private Map<Integer, Integer> cuentas;
    private PrintStream ps = new PrintStream(System.out);
    
    /**
     * Objeto Lock utilizado para manejar la sincronización en las operaciones del banco.
     * Se utiliza para asegurar que solo un hilo pueda realizar operaciones críticas a la vez.
     */
    private Lock cierreDeBanco = new ReentrantLock();
    
    /**
     * Objeto Condition asociado al Lock {@code cierreDeBanco}.
     * Se utiliza para esperar y notificar condiciones específicas relacionadas con las operaciones de transferencia.
     */
    private Condition saldoSuficiente;
    
    /**
     * Constructor para inicializar el objeto Banco.
     * 
     * @param cantCuentas Número de cuentas a crear en el banco.
     * @param montoInicial Monto inicial de cada cuenta.
     * @param nombre Nombre del banco.
     */
    public Banco(int cantCuentas, int montoInicial, String nombre) {
        ps.printf("\n" + Colores.ANSI_WHITE_BACKGROUND + "\t\t\t" + Colores.ANSI_BLACK + "BANCO CREADO \t\t\t\n" + Colores.ANSI_RESET);

        cuentas = new HashMap<>();
        
        // Inicializa el objeto Condition asociado al Lock cierreDeBanco.
        // Esto permite que los hilos puedan esperar en una condición específica y ser notificados cuando se cumpla.
        saldoSuficiente = cierreDeBanco.newCondition();
        
        this.nombreBanco = nombre;
        
        // Inicializa las cuentas con el monto inicial proporcionado
        for (int nro = 0; nro < cantCuentas; nro++) {
            cuentas.put(nro, montoInicial);
        }
    }

    /**
     * Realiza una transferencia entre dos cuentas del banco.
     * Este método debería ser implementado para manejar la lógica de transferencia
     * y la sincronización necesaria para asegurar la consistencia de las cuentas.
     * 
     * @param cuentaOrigen Número de la cuenta de origen.
     * @param cuentaDestino Número de la cuenta destino.
     * @param monto Monto a transferir.
     */
    public void transferir(int cuentaOrigen, int cuentaDestino, int monto) {
        // Implementar lógica de transferencia aquí
    }

    /**
     * Obtiene el nombre del banco.
     * 
     * @return El nombre del banco.
     */
    public String getName() {
        return this.nombreBanco;
    }

}
