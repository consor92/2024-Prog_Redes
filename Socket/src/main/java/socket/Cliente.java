package socket;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Cliente {

	InetAddress IP;
	int puerto = 7777;
	boolean nickEnviado = true;
	String nick = "";

	Socket sock;
	DataInputStream dis;
	DataOutputStream dos;
	InputStreamReader isr;
	BufferedReader buff;
	PrintStream ps;

	public Cliente() {

		try {
			IP = InetAddress.getByName("localhost");
			sock = new Socket(IP, puerto);

			dis = new DataInputStream(sock.getInputStream());
			dos = new DataOutputStream(sock.getOutputStream());
			isr = new InputStreamReader(System.in);
			buff = new BufferedReader(isr);
			ps = new PrintStream(System.out);

			if (sock.isConnected() && nickEnviado) {
				ps.println("Ingresa tu ID: ");
				String nickname = buff.readLine();
				dos.writeUTF(nickname);
				nickEnviado = false;

				ps.println("Bienvenido " + nickname);
			}
			ps.print("\t-> ");
			dos.writeUTF(nick);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Hilo que leer datos permanentemente y los envia por la red.
		Thread enviarMensajes = new Thread(new Runnable() {
			@Override
			public void run() {
				String msg = "";
				try {
					while (true && !msg.equalsIgnoreCase("/salir")) {
						msg = buff.readLine();

						dos.writeUTF(msg);
						ps.print("\n->");
					}
				} catch (IOException e) {
					Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, e);
				} // try
			}// run
		}// runnable
		);// thread
		enviarMensajes.setName("enviar");
		enviarMensajes.start();

		// Hilo que leer datos permanentemente y los envia por la red.

	}
}
