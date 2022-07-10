package dao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import javax.imageio.ImageTypeSpecifier;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.IconifyAction;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;

import beans.Administrator;
import beans.Korisnik;
import beans.Kupac;
import beans.Menadzer;
import beans.Trener;
import enums.Pol;
import enums.Uloga;

public class KorisniciDAO 
{
	private String kupacFajlIme = "Kupci.txt";
	private String menadzerFajlIme = "Menadzeri.txt";
	private String administratorFajlIme = "Administratori.txt";
	private String trenerFajlIme = "Treneri.txt";
	private String putanjaMain = "./static/podaci/";
	
	private int currentId = 0;
	private HashMap<String, Korisnik> korisnici;
	
	public KorisniciDAO()	
	{
		korisnici = new HashMap<String, Korisnik>();

		ucitajKorisnike(Uloga.Kupac);
		ucitajKorisnike(Uloga.Menadzer);
		ucitajKorisnike(Uloga.Administrator);
		ucitajKorisnike(Uloga.Trener);

	}
	
	public ArrayList<Korisnik> getAllRegKorisnici()
	{
		ArrayList<Korisnik> korisnics = new ArrayList<>();
		
		for (Korisnik kor : korisnici.values())	
		{
			korisnics.add(kor);
		}
		
		return korisnics;
	}

	public ArrayList<Korisnik> getAllRegKorisniciExceptAdmin()
	{
		ArrayList<Korisnik> korisnics = new ArrayList<>();
		
		for (Korisnik kor : korisnici.values())	
		{
			if(kor.getUloga() != Uloga.Administrator)
			{
				korisnics.add(kor);
			}
			
		}
		
		return korisnics;
	}
	
	public void ucitajSportskeObjekteUMenadzere(SportskiObjektiDAO sportskiObjektiDAO)
	{
		
		for (Korisnik kor : korisnici.values())	
		{
			if(kor.getUloga() == Uloga.Menadzer)
			{
					if (((Menadzer)kor).getSportskiObjekatId() != null)
					{
						((Menadzer)kor).setSportskiobjekat(sportskiObjektiDAO.getSportskiObjekatById(((Menadzer)kor).getSportskiObjekatId()));
					}
			}
		}
	}
	
	public ArrayList<Korisnik> getAllRegTreneri()
	{
			
		ArrayList<Korisnik> korisnics = new ArrayList<>();	
		for (Korisnik kor : korisnici.values())	
		{
			if(kor.getUloga().equals(Uloga.Trener))
			{
				korisnics.add(kor);
			}
			
		}
		
		return korisnics;
	}
	
	private void ucitajKorisnike(Uloga uloga)	
	{
		BufferedReader bafer;
		String putanja = napraviPutanju(uloga);
		try	
		{
			bafer = new BufferedReader(new FileReader(putanja));
			String red;
			while ((red = bafer.readLine()) != null)	
			{
				if (uloga == Uloga.Administrator)	
				{
					Gson gson =  new GsonBuilder().excludeFieldsWithoutExposeAnnotation().registerTypeAdapter(Date.class, (JsonDeserializer) (json, typeOfT, context) -> new Date(json.getAsLong())).create();
				
					Administrator admin = gson.fromJson(red, Administrator.class);
					korisnici.put(admin.getKorisnickoIme(), admin);
				} 
				else if (uloga == Uloga.Kupac)	
				{
					Gson gson =  new GsonBuilder().excludeFieldsWithoutExposeAnnotation().registerTypeAdapter(Date.class, (JsonDeserializer) (json, typeOfT, context) -> new Date(json.getAsLong())).create();
					//CLANARINA FALI UMESTO NULL
					Kupac kupac = gson.fromJson(red, Kupac.class);
					korisnici.put(kupac.getKorisnickoIme(), kupac);
				} 
				else if (uloga == Uloga.Menadzer)	
				{
					Gson gson =  new GsonBuilder().excludeFieldsWithoutExposeAnnotation().registerTypeAdapter(Date.class, (JsonDeserializer) (json, typeOfT, context) -> new Date(json.getAsLong())).create();
					//SPORTSKI OBJEKAT UMESTO NULL 
					Menadzer menadzer = gson.fromJson(red, Menadzer.class);
					korisnici.put(menadzer.getKorisnickoIme(), menadzer);
				}
				else if(uloga == Uloga.Trener)
				{
					Gson gson =  new GsonBuilder().excludeFieldsWithoutExposeAnnotation().registerTypeAdapter(Date.class, (JsonDeserializer) (json, typeOfT, context) -> new Date(json.getAsLong())).create();
					Trener trener = gson.fromJson(red, Trener.class);
					korisnici.put(trener.getKorisnickoIme(), trener);
				}
			}
			bafer.close();
		} 
		catch (Exception e)	
		{
			e.printStackTrace();
			System.out.println("Fajl " + putanja + " nije pronadjen.\r\n");
		}
	}


	public Korisnik getKorisnikByKorisnickoIme(String korisnickoIme)	
	{
		return korisnici.get(korisnickoIme);
	}


	public boolean napraviKorisnika(Korisnik korisnik)	
	{
		if (!korisnici.containsKey(korisnik.getKorisnickoIme()))	
		{
			korisnici.put(korisnik.getKorisnickoIme(), korisnik);
			upisiKorisnika(korisnik);
			return true;
		}
		else	
		{
			System.out.println("Vec zauzeto.\r\n");
			return false;
		}
	}
	
	private void upisiKorisnika(Korisnik korisnik)
	{
		String putanja = napraviPutanju(korisnik.getUloga());
		
		try 
		{
			FileWriter writer = new FileWriter(putanja, true);
			writer.append(korisnik.toSaveFormat());
			writer.close();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
			System.out.println("Fajl " + putanja + " nije pronadjen!\r\n");
		}
	}
	
	private String napraviPutanju(Uloga uloga)	
	{
		String putanja = putanjaMain;
		switch(uloga)	
		{
			case Kupac:
				putanja += kupacFajlIme;
				break;
			case Menadzer:
				putanja += menadzerFajlIme;
				break;
			case Administrator:
				putanja += administratorFajlIme;
				break;
			case Trener:
				putanja += trenerFajlIme;
				break;
			default:
				System.out.println("LOSA PUTANJA!\r\n");
				break;
		}
		
		return putanja;
	}
	
	public boolean izmeniKorisnika(Korisnik izmenjenKorisnik)	
	{
		Korisnik korisnikZaIzmenu = korisnici.get(izmenjenKorisnik.getKorisnickoIme());
		if (korisnikZaIzmenu != null) 
		{
			korisnikZaIzmenu.setIme(izmenjenKorisnik.getIme());
			korisnikZaIzmenu.setPrezime(izmenjenKorisnik.getPrezime());
			korisnikZaIzmenu.setPol(izmenjenKorisnik.getPol());
			korisnikZaIzmenu.setSifra(izmenjenKorisnik.getSifra());
			azurirajPodatke(izmenjenKorisnik.getUloga());
			return true;
		} 
		else	
		{
			return false;
		}
	}
	
	public void azurirajPodatke(Uloga uloga) 
	{
		String putanja = napraviPutanju(uloga);
		try 
		{
			FileWriter writer = new FileWriter(putanja, false);
			for (Korisnik korisnik : korisnici.values())	
			{
				if (korisnik.getUloga().equals(uloga))	
				{
					writer.write(korisnik.toSaveFormat());
				}
			}
			writer.close();
		} 
		catch (IOException e)	
		{
			e.printStackTrace();
		}
	}


	public ArrayList<Menadzer> getSlobodniMenadzeri()
	{
		ArrayList<Menadzer> menadzeri = new ArrayList<>();
		
		for (Korisnik korisnik : korisnici.values())	
		{
			if(korisnik.getUloga()== Uloga.Menadzer) 
			{
				if((((Menadzer) korisnik).getSportskiobjekat() == null) && ((Menadzer) korisnik).getSportskiObjekatId().equals("null")) 
				{
					menadzeri.add((Menadzer) korisnik);
				}
			}
		}
		
		return menadzeri;
	}
	
	public ArrayList<Korisnik> changeActivityOfUser(String korisnickoIme)
	{
		ArrayList<Korisnik> korisnics = new ArrayList<>();
		
		for (Korisnik korisnik : korisnici.values())	
		{
			if(korisnik.getKorisnickoIme().equals(korisnickoIme)) 
			{
				korisnik.setAktivan(!(korisnik.isAktivan()));
				azurirajPodatke(korisnik.getUloga());
			}
		}
		
		return korisnics;
	}
}
