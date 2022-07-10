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
import beans.Trening;
import beans.Clanarina;
import beans.Korisnik;
import dao.ClanarineDAO;
import dao.KorisniciDAO;
import dao.SportskiObjektiDAO;
import dao.TreningDAO;
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
	private static TreningDAO treninzi = null;
	private static ClanarineDAO clanarine = null;
	private static Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
	
	public static void main(String[] args) throws Exception 
	{
		port(8080);

		staticFiles.externalLocation(new File("./static").getCanonicalPath());
		korisnici = new KorisniciDAO();
		sportskiObjekti = new SportskiObjektiDAO();
		korisnici.ucitajSportskeObjekteUMenadzere(sportskiObjekti);
		treninzi = new TreningDAO();
		treninzi.ucitajSportskeObjekteiTrenerer(sportskiObjekti, korisnici);
		clanarine = new ClanarineDAO();
		gson =  new GsonBuilder().registerTypeAdapter(Date.class, (JsonDeserializer) (json, typeOfT, context) -> new Date(json.getAsLong())).create();
		
			
		post("app/login", (req, res) -> 
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
		
		post("app/registracija/kupac", (req, res) -> 
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
		
		post("app/registracija/trener", (req, res) -> 
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
		
		post("app/registracija/menadzer", (req, res) -> 
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
		
		
		post("app/changeActivityOfUser", (req, res) -> 
		{
			String body = req.body();	
			korisnici.changeActivityOfUser(body);
			return gson.toJson("Upesno ste izmenili aktivnost.");
		});
		
		post("app/addClanarina", (req, res) -> 
		{
			Korisnik korisnik = getKorisnikByJWT(req, res);
			if(korisnik != null)
			{
				String nazivClanarine = req.body();	
				clanarine.AddClanarina(nazivClanarine, korisnik.getKorisnickoIme());
				System.out.println("Clanarina dodata!");
				return gson.toJson("Upesno ste izmenili aktivnost.");
			}
			else 
			{					

				System.out.println("Clanarina dodata!");
				return gson.toJson("Upesno ste izmenili aktivnost.");
			}
			
		});
		
	
			
		
		get("app/getSportskiObjekti", (req, res) -> 
		{
			return gson.toJson(sportskiObjekti.getAllSportskiObjekti());
		});
		
		post("app/getClanarinaWithKorisnickoIme", (req, res) -> 
		{
			Korisnik korisnik = getKorisnikByJWT(req, res);
			
			if(korisnik != null) 
			{
				System.out.println(korisnik.getKorisnickoIme());
				Clanarina clanarina = clanarine.getClanarinaByKorisnickoIme(korisnik.getKorisnickoIme());
				if(clanarina != null)
				{
					return gson.toJson(clanarina);
				}
				else
				{
					System.out.println("Nema te clanarinu!");
					return gson.toJson("Nema te clanarinu!");
				}
			}
			else
			{
				System.out.println("Korisnik je null");
				return gson.toJson("KORISNIK JE NULL!");
			}
		});
		
		post("app/getSportskiObjekatWithID", (req, res) -> 
		{
			Korisnik korisnik = getKorisnikByJWT(req, res);
			
			if(korisnik != null) 
			{
				//if(korisnik.getUloga() == Uloga.Kupac) 
				{
					String idSportskogObjektaString = req.headers("idSporskogObjekta");
					SportskiObjekat sportskiObjekat = sportskiObjekti.getSportskiObjekatById(idSportskogObjektaString);
					if(sportskiObjekat != null)
					{
						return gson.toJson(sportskiObjekat);
					}
					else
					{
						System.out.println("Nema tog objekta");
						return gson.toJson("Nema tog objekta");
					}
				}
			}
			else
			{
				System.out.println("Korisnik je null");
				return gson.toJson("KORISNIK JE NULL!");
			}
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
		
		get("app/getKorisnik", (req, res) -> 
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
		
		put("app/izmeniKorisnika", (req, res) -> 
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
			System.out.println(payload);
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
		
		post("app/obrisiTreningById", (req, res) ->	
		{
			res.type("application/json");
			Korisnik korisnik = getKorisnikByJWT(req, res);

			String treningId = req.headers("TreningId");
			Trening stariTrening = treninzi.getTreningById(treningId);
			
			if (korisnik != null)	
			{
				if (korisnik.getUloga().equals(Uloga.Menadzer))	
				{
					if (stariTrening != null )
					{
						if(((Menadzer)korisnik).getSportskiObjekatId().equals(stariTrening.getSportskiObjekatid()))
						{
							treninzi.changeTreningActivityById(treningId);
							return gson.toJson("Trening obrisan");
						}
						else 
						{
							res.status(500);
							return gson.toJson("Doslo je do greske");
						}
						
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
		
		post("app/dodajTrening", (req, res) ->	
		{
			res.type("application/json");
			Korisnik korisnik = getKorisnikByJWT(req, res);

			String payload = req.body();
			
			Trening noviTrening = gson.fromJson(payload, Trening.class);
			
			if (korisnik != null)	
			{
				if (korisnik.getUloga().equals(Uloga.Menadzer))	
				{
					if (noviTrening != null)	
					{
						noviTrening.setAktivan(true);
						noviTrening.setSportskiObjekatid(((Menadzer)korisnik).getSportskiObjekatId());
						noviTrening.setTrener((Trener)(korisnici.getKorisnikByKorisnickoIme(noviTrening.getTrenerid())));
						noviTrening = treninzi.dodajNoviTrening(noviTrening);
						
						res.header("idnovogtreninga", noviTrening.getId());
						return gson.toJson("Trening dodat.");
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
		
		post("app/dodajSliku", (req, res) -> 
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
				Path out = Paths.get("static/Images/SportskiObjekat/" + fName);
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
	
		post("app/dodajSlikuTreningu", (req, res) -> 
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
				String fName = req.headers("idtreninga")+"."+tipSlikeString;
				
				Part uploadedFile = req.raw().getPart("file");
				Path out = Paths.get("static/Images/Trening/" + fName);
				try (final InputStream in = uploadedFile.getInputStream()) 
				{
				   Files.copy(in, out);
				   uploadedFile.delete();
				   treninzi.dodajSliku(fName, req.headers("idtreninga"));
				}
				multipartConfigElement = null;
				uploadedFile = null;
				
				return gson.toJson(treninzi.getTreningById(req.headers("idtreninga")));
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
		
		post("app/getTreningeZaObjekat", (req, res) -> 
		{
			Menadzer menadzer = (Menadzer) getKorisnikByJWT(req, res);
			if(menadzer.getSportskiObjekatId().equals("null"))
			{
				System.out.println("NEMAS SPORTSKI OBJEKAT");
				return gson.toJson("Morate biti vlasnik sportskog objekta!");
			}
			return gson.toJson(treninzi.getTreninziBySportskiObjekatId(menadzer.getSportskiObjekatId()));
		});
		
		post("app/getTreningByTreningId", (req, res) -> 
		{
			
			String tremningId = req.headers("TreningId");
		
			Trening trening = treninzi.getTreningById(tremningId);
			if(trening != null)
			{
				return gson.toJson(trening);	
			}
			return gson.toJson("ne postoji takav trening!");
		});
		
		post("app/gettrenerizaobjekat", (req, res) -> 
		{
			Menadzer menadzer = (Menadzer) getKorisnikByJWT(req, res);
			if(menadzer != null) 
			{
				if(menadzer.getSportskiObjekatId().equals("null"))
				{
					System.out.println("NEMAS SPORTSKI OBJEKAT");
					return gson.toJson("Morate biti vlasnik sportskog objekta!");
				}
			}
			else 
			{
				System.out.println("MENADZER JE NULL");
				return gson.toJson("MORATE SE ULOGOVATI!");
			}
			return gson.toJson(treninzi.getTreneriBySportskiObjekatId(menadzer.getSportskiObjekatId()));
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
