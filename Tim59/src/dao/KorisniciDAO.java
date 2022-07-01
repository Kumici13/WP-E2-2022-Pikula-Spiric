package dao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import beans.Administrator;
import beans.Clanarina;
import beans.Korisnik;
import beans.Kupac;
import beans.Menadzer;
import beans.SportskiObjekat;
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
				String[] tokeni = red.split(";");
				
				if (uloga == Uloga.Administrator)	
				{
					Administrator admin = new Administrator(tokeni[0], tokeni[1], tokeni[2], tokeni[3], Pol.valueOf(tokeni[4]), tokeni[5]);
					korisnici.put(tokeni[0], admin);
				} 
				else if (uloga == Uloga.Kupac)	
				{
					//CLANARINA FALI UMESTO NULL
					Kupac kupac = new Kupac(tokeni[0], tokeni[1], tokeni[2], tokeni[3], Pol.valueOf(tokeni[4]), tokeni[5], null, tokeni[7], Double.valueOf(tokeni[8]));
					korisnici.put(tokeni[0], kupac);
				} 
				else if (uloga == Uloga.Menadzer)	
				{
					//SPORTSKI OBJEKAT UMESTO NULL 
					Menadzer menadzer = new Menadzer(tokeni[0], tokeni[1], tokeni[2], tokeni[3], Pol.valueOf(tokeni[4]), tokeni[5], null);
					korisnici.put(tokeni[0], menadzer);
				}
				else if(uloga == Uloga.Trener)
				{
					Trener trener = new Trener(tokeni[0], tokeni[1], tokeni[2], tokeni[3], Pol.valueOf(tokeni[4]), tokeni[5], tokeni[6]);
					korisnici.put(tokeni[0], trener);
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
	
	private void azurirajPodatke(Uloga uloga) 
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
}
