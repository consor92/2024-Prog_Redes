package socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class ClienteCli implements Runnable {
	
    String nickName = "";
    Socket sock;
    Thread hilo;
    

	final DataInputStream disCliente;
    final DataOutputStream dosCliente;
    boolean isConected;
    
    public ClienteCli(Socket sock, String nick, DataInputStream in, DataOutputStream out) {
        this.nickName = nick;
        this.sock = sock;
        this.disCliente = in;
        this.dosCliente = out;    
        
        this.isConected = true;
        this.hilo = new Thread(this, nickName);
    }

	@Override
	public void run() {
		
	}
    
	void notificarClientes(boolean b) {
		
	}
	
    public String getNickName() {
		return nickName;
	}

	public Socket getSock() {
		return sock;
	}

	public Thread getHilo() {
		return hilo;
	}

	public boolean isConected() {
		return isConected;
	}	
	
}
