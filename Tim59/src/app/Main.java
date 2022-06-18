package app;

import static spark.Spark.port;
import static spark.Spark.post;
import static spark.Spark.staticFiles;
import java.io.File;

import beans.Korisnik;
import dao.KorisniciDAO;
public class Main 
{
	private static KorisniciDAO korisnici = null;
	public static void main(String[] args) throws Exception 
	{
		port(8080);

		staticFiles.externalLocation(new File("./static").getCanonicalPath());
		korisnici = new KorisniciDAO("./static/baza/");
		
		
		//HTTP
			
			
	}
	
}
