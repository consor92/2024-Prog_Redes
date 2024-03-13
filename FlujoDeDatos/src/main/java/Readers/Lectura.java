package Readers;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Lectura {
	// Stream  
	// Stream  IN
	// Stream  OUT
	// Stream  ERR
	
	PrintStream ps;
	
	public Lectura()
	{
		//Intermediario entre la escritura y el canal
		ps = new PrintStream( System.out );
		
		System.out.println("Byte:");
		//Scanner s = new Scanner(System.in);
		//System.err.println();
		
		
		//byte a byte
		System.out.write(  13  );  // 
		System.out.write(  10  );  // enter 
		System.out.write(  62 );
		System.out.write(  27 );
		
		
		//ASCII    UNICODE
		byte[] array = { 78 , 64 , 119 , 97 , 19 };
		try {
			
			System.out.write( array );
			
		} catch (IOException e) {
			Logger.getLogger( Readers.Lectura.class.getName())
			.log( Level.SEVERE , null , e );
			//e.printStackTrace();
		}
		
		System.err.println("ERROR");
		
		System.out.flush();
		//System.out.println();
		
		
		
		ps.println("holaa");
		ps.printf("El numero elejido es: %b %d y su nombre: %s en su cuenta tiene: %.2f $ "
				,true, 8 , "PEPE" ,  655.7);
		
		
		
		File archivo = new File( "error.log" );
		
		PrintStream error = new PrintStream(  );
		System.setErr( error );
		
		System.err.println("esto es un error critico");
	}
	
	
	
	
	
}
