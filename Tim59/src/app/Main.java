package app;

import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.post;
import static spark.Spark.put;
import static spark.Spark.staticFiles;
import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Key;
import java.util.Collection;
import java.util.Date;

import javax.servlet.MultipartConfigElement;
import javax.servlet.http.Part;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;

import beans.Kupac;
import beans.Menadzer;
import beans.SportskiObjekat;
import beans.Trener;
import beans.Korisnik;
import dao.KorisniciDAO;
import dao.SportskiObjektiDAO;
import dao.TreningDao;
import enums.Uloga;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import spark.Request;
import spark.Response;
public class Main 
{
	private static Gson gson;
	private static KorisniciDAO korisnici = null;
	private static SportskiObjektiDAO sportskiObjekti = null;
	private static TreningDao treninzi = null;
	private static Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
	@SuppressWarnings("unused")
	public static void main(String[] args) throws Exception 
	{
		port(8080);

		staticFiles.externalLocation(new File("./static").getCanonicalPath());
		korisnici = new KorisniciDAO();
		sportskiObjekti = new SportskiObjektiDAO();
		korisnici.ucitajSportskeObjekteUMenadzere(sportskiObjekti);
		treninzi = new TreningDao();
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
		
		post("/app/registracija/trener", (req, res) -> 
		{
			res.type("application/json");
			String body = req.body();
			Trener trener = gson.fromJson(body, Trener.class);
			if (trener != null)	
			{
				if (korisnici.napraviKorisnika(trener))	
				{
					LogovanjeKorisnika(trener);
					return gson.toJson(trener);
				} 
				else	
				{
					res.status(400);
					return gson.toJson("Korisnicko ime " + trener.getKorisnickoIme() + " je zauzeto. Pokusajte drugo korisnicko ime.");
				}
			} 
			else	
			{
				res.status(500);
				return gson.toJson("Greska prilikom registracije korisnika. Pokusajte ponovo.");
			}
		});
		
		post("/app/registracija/menadzer", (req, res) -> 
		{
			res.type("application/json");
			String body = req.body();
			Menadzer menadzer = gson.fromJson(body, Menadzer.class);
			if (menadzer != null)	
			{
				if (korisnici.napraviKorisnika(menadzer))	
				{
					LogovanjeKorisnika(menadzer);
					return gson.toJson(menadzer);
				} 
				else	
				{
					res.status(400);
					return gson.toJson("Korisnicko ime " + menadzer.getKorisnickoIme() + " je zauzeto. Pokusajte drugo korisnicko ime.");
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
		
		get("app/getSlobodniMenadzeri", (req, res) -> 
		{
			return gson.toJson(korisnici.getSlobodniMenadzeri());
		});
		
		get("app/getSadrzaji", (req, res) -> 
		{
			return gson.toJson(treninzi.getAllTreninzi());
		});
		

		get("app/getregKorisnici", (req, res) -> 
		{
			return gson.toJson(korisnici.getAllRegKorisniciExceptAdmin());
		});
		
		get("app/getTreneri", (req, res) -> 
		{
			return gson.toJson(korisnici.getAllRegTreneri());
		});
		
		get("/app/getKorisnik", (req, res) -> 
		{
			Korisnik korisnik = getKorisnikByJWT(req, res);
			
			if (korisnik != null)	
			{
				return gson.toJson(korisnik);
			} 
			else	
			{
				if (res.status() == 400)	
				{
					return gson.toJson("Morate biti ulogovati da biste pristupili.");
				}
				else if (res.status() == 500)	
				{ 
					return gson.toJson("Doslo je do greske.");
				}
				
				res.status(500);
				return gson.toJson("Doslo je do greske.");
			}
		});
		
		put("/app/izmeniKorisnika", (req, res) -> 
		{
			Korisnik korisnik = getKorisnikByJWT(req, res);
			
			if (korisnik != null)	
			{
				String payload = req.body();
				Korisnik izmenjenKorisnik = gson.fromJson(payload, Korisnik.class);
				
				if (korisnici.izmeniKorisnika(izmenjenKorisnik))	
				{
					return gson.toJson("Nalog izmenjen!");
				} 
				else	
				{
					return gson.toJson("Doslo je do greske.");
				}
			} 
			else	
			{
				if (res.status() == 400)	
				{
					return gson.toJson("Morate se ulogovati.");
				}
				else if (res.status() == 500)	
				{ 
					return gson.toJson("Doslo je do greske..");
				}
				
				res.status(500);
				return gson.toJson("Doslo je do greske.");
			}
		});
		
		post("app/dodajSportskiObjekat", (req, res) ->	
		{

			String menadzerUserName = req.headers("Menadzer");
			res.type("application/json");
			Korisnik korisnik = korisnici.getKorisnikByKorisnickoIme(menadzerUserName);

			String payload = req.body();
			SportskiObjekat noviSportskiObjekat = gson.fromJson(payload, SportskiObjekat.class);
			if (korisnik != null)	
			{
				if (korisnik.getUloga().equals(Uloga.Menadzer))	
				{

					if (noviSportskiObjekat != null)	
					{
						noviSportskiObjekat = sportskiObjekti.dodajNoviSportskiObjekat(noviSportskiObjekat);
						((Menadzer)korisnik).setSportskiobjekat(noviSportskiObjekat);
						((Menadzer)korisnik).setSportskiObjekatId(noviSportskiObjekat.getId());
						korisnici.azurirajPodatke(Uloga.Menadzer);
						res.header("idNovogSportskogObjekta", noviSportskiObjekat.getId());
						return gson.toJson("Sportski objekat dodat.");
					} 
					else	
					{
						res.status(500);
						return gson.toJson("Doslo je do greske");
					}
				} 
				else	
				{
					res.status(403);
					return gson.toJson("Niste ovlasceni.");
				}
			} 
			else	
			{
				if (res.status() == 400)	
				{
					return gson.toJson("Morate se ulogovati.");
				} 
				else if (res.status() == 500)	
				{
					return gson.toJson("Doslo je do greske.");
				}

				res.status(500);
				return gson.toJson("Doslo je do greske.");
			}

		});
		
		post("/app/dodajSliku", (req, res) -> 
		{
			Korisnik korisnik = getKorisnikByJWT(req, res);
			
			if (korisnik != null) 
			{
				String location = "image";  
				long maxFileSize = 100000000;       
				long maxRequestSize = 100000000;    
				int fileSizeThreshold = 1024;       

				MultipartConfigElement multipartConfigElement = new MultipartConfigElement(
				location, maxFileSize, maxRequestSize, fileSizeThreshold);
				req.raw().setAttribute("org.eclipse.jetty.multipartConfig",
				multipartConfigElement);

				Collection<Part> parts = req.raw().getParts();
				String tipSlikeString = ".png";
				for (Part part : parts) 
				{
					tipSlikeString= part.getContentType();
					tipSlikeString = tipSlikeString.replaceAll("image/","");
				}
				String fName = req.headers("IdSportskogObjekta")+"."+tipSlikeString;
				
				Part uploadedFile = req.raw().getPart("file");
				Path out = Paths.get("static/Images/" + fName);
				try (final InputStream in = uploadedFile.getInputStream()) 
				{
				   Files.copy(in, out);
				   uploadedFile.delete();
				   sportskiObjekti.dodajSliku(fName, req.headers("IdSportskogObjekta"));
				}
				multipartConfigElement = null;
				uploadedFile = null;
				
				return gson.toJson(sportskiObjekti.getSportskiObjekatById(req.headers("IdSportskogObjekta")));
			} 
			else	
			{
				if (res.status() == 400)	
				{
					return gson.toJson("Morate se ulogovati.");
				}
				else if (res.status() == 500)	
				{
					return gson.toJson("Doslo je do greske.");
				}
				
				res.status(500);
				return gson.toJson("Doslo je do greske.");
			}
		});
	
			
	}
	
	private static void LogovanjeKorisnika(Korisnik korisnik) 
	{
		String jwt = Jwts.builder().setSubject(korisnik.getKorisnickoIme()).setIssuedAt(new Date()).signWith(key).compact();
		korisnik.setJWTToken(jwt);
	}
	
	private static Korisnik getKorisnikByJWT(Request req, Response res) 
	{
		String autorizacija = req.headers("Autorizacija");
		
		if (autorizacija != null) 
		{	
			String jwt = autorizacija;
			try 
			{
				Jws<Claims> claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwt);
				String korisnickoIme = claims.getBody().getSubject();
				Korisnik korisnik = korisnici.getKorisnikByKorisnickoIme(korisnickoIme);
				return korisnik;
			} 
			catch (Exception e) 
			{
				res.status(500);
				return null;
			}
		} 
		else	
		{
			res.status(400);
			return null;
		}
	}
	
}
