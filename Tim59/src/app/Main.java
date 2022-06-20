package app;

import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.post;
import static spark.Spark.staticFiles;
import java.io.File;
import java.security.Key;
import java.util.Date;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;

import beans.Kupac;
import beans.Korisnik;
import dao.KorisniciDAO;
import dao.SportskiObjektiDAO;
import enums.Uloga;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
public class Main 
{
	private static Gson gson;
	private static KorisniciDAO korisnici = null;
	private static SportskiObjektiDAO sportskiObjekti = null;
	private static Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
	public static void main(String[] args) throws Exception 
	{
		port(8080);

		staticFiles.externalLocation(new File("./static").getCanonicalPath());
		korisnici = new KorisniciDAO();
		sportskiObjekti = new SportskiObjektiDAO();
		gson =  new GsonBuilder().registerTypeAdapter(Date.class, (JsonDeserializer) (json, typeOfT, context) -> new Date(json.getAsLong())).create();
		
			
		post("/app/login", (req, res) -> 
		{
			String[] tokeni = req.body().split("&");
			
			String korisnickoIme = tokeni[0];
			String sifra = tokeni[1];
			
			Korisnik korisnik = korisnici.getKorisnikByKorisnickoIme(korisnickoIme);
			
			if (korisnik != null)	
			{
				if (korisnik.getSifra().equals(sifra))	
				{
					LogovanjeKorisnika(korisnik);
					return gson.toJson(korisnik);
				} 
				else	
				{
					res.status(400);
					return gson.toJson(("Sifra za nalog " + korisnik.getKorisnickoIme() + " nije ispravna."));
				}
			} 
			else	
			{
				res.status(400);	
				return gson.toJson(("Ne postoji korisnik sa korisnickim imenom: " + korisnickoIme));
			}
		});
		
		post("/app/registracija/kupac", (req, res) -> 
		{
			res.type("application/json");
			String body = req.body();
			Kupac kupac = gson.fromJson(body, Kupac.class);
			if (kupac != null)	
			{
				if (korisnici.napraviKorisnika(kupac))	
				{
					LogovanjeKorisnika(kupac);
					return gson.toJson(kupac);
				} 
				else	
				{
					res.status(400);
					return gson.toJson("Korisnicko ime " + kupac.getKorisnickoIme() + " je zauzeto. Pokusajte drugo korisnicko ime.");
				}
			} 
			else	
			{
				res.status(500);
				return gson.toJson("Greska prilikom registracije korisnika. Pokusajte ponovo.");
			}
		});
		
		get("app/getSportskiObjekti", (req, res) -> 
		{
			return gson.toJson(sportskiObjekti.getAllSportskiObjekti());
		});
		
	}
	
	private static void LogovanjeKorisnika(Korisnik korisnik) 
	{
		String jwt = Jwts.builder().setSubject(korisnik.getKorisnickoIme()).setIssuedAt(new Date()).signWith(key).compact();
		korisnik.setJWTToken(jwt);
	}
	
}
