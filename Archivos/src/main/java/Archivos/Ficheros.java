package Archivos;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class Ficheros {
	
	private File archivo;
	private PrintStream ps;
	
	public Ficheros()
	{
		String ruta = "";  //   " c:\\User\\ "
		String nombre = "mario";
		String extension = ".txt";
		
		archivo =  new File( ruta.concat(nombre.concat(extension))  );
	}
	
	public void createFilePrintStream(File a)
	{
		FileOutputStream fos = null;
		
		try {
			fos = new FileOutputStream(a);
			ps = new PrintStream(fos , true); //mode append true = no sobreescribe
			
			ps.println("holaa mundo");
			ps.println("Chauu mundo");
			
			ps.flush();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
}
