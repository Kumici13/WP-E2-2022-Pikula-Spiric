package app;

import static spark.Spark.port;
import static spark.Spark.post;
import static spark.Spark.staticFiles;
import java.io.File;
import java.security.Key;
import java.util.Date;

import com.google.gson.Gson;

import beans.Korisnik;
import dao.KorisniciDAO;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
public class Main 
{
	private static Gson gson;
	private static KorisniciDAO korisnici = null;
	private static Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
	public static void main(String[] args) throws Exception 
	{
		port(8080);

		staticFiles.externalLocation(new File("./static").getCanonicalPath());
		korisnici = new KorisniciDAO("./static/baza/");
		
		
		//HTTP
			
		post("/app/login", (req, res) -> {
			String body = req.body();
			System.out.println("LOGIN: " + body);
			
			String[] tokeni = body.split("&");
			
			String korisnickoIme = tokeni[0];
			String lozinka = tokeni[1];
			
			Korisnik korisnik = korisnici.getKorisnikByKorisnickoIme(korisnickoIme);
			
			if (korisnik != null)	
			{
				if (korisnik.getLozinka().equals(lozinka))	
				{
					// Uspesno logovanje
					LogovanjeKorisnika(korisnik);
					return gson.toJson(korisnik);
				} 
				else	
				{
					// Neuspesno logovanje
					res.status(400);	// Status 400 Bad Request
					System.out.println("LOGIN: Pogresna lozinka za korisnicko ime " + korisnik.getKorisnickoIme() + "\r\n");
					return gson.toJson(("Lozinka za nalog " + korisnik.getKorisnickoIme() + " nije ispravna. Pokušajte ponovo."));
				}
			} 
			else	
			{
				// Korisnik ne postoji
				res.status(400);	// Status 400 Bad Request
				System.out.println("LOGIN: Korisnik " + korisnickoIme + " nije pronadjen u bazi.\r\n");
				return gson.toJson(("Ne postoji registrovan korisnik sa korisnickim imenom: " + korisnickoIme));
			}
		});
	}
	
	private static void LogovanjeKorisnika(Korisnik korisnik) 
	{
		String jwt = Jwts.builder().setSubject(korisnik.getKorisnickoIme()).setIssuedAt(new Date()).signWith(key).compact();
		korisnik.setJWTToken(jwt);
	}
	
}
