package dao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;

import beans.Clanarina;
import beans.Korisnik;
import beans.Kupac;
import beans.SportskiObjekat;
import enums.TipClanarine;
import enums.Uloga;
import javaxt.utils.string;



public class ClanarineDAO {

	
private HashMap<Integer, Clanarina> clanarine;
	
	private int ClanarineId = 0;
	public ClanarineDAO(KorisniciDAO korisniciDAO)
	{
		clanarine = new HashMap<Integer, Clanarina>();
		ucitajClanarine(korisniciDAO);
	}
	
	public void AddClanarina(String nazivClanarine, String korisnickoIme)
	{
		
		String putanja = "./static/podaci/Clanarine.txt";
		ClanarineId++;
		TipClanarine tipClanarine = null;
		LocalDateTime now = LocalDateTime.now();
		LocalDateTime nowPlusDate = LocalDateTime.now();
		System.out.println("NOW: " + now.toString());  
		System.out.println("NOW: " + now.toLocalDate().toString());  
		int cena = 0;
		int brojTreninga = 0;
		
		if(nazivClanarine.equals("Classic"))
		{
			brojTreninga = 4;
			cena = 1500;
			nowPlusDate = LocalDateTime.now().plusDays(7);
			tipClanarine = TipClanarine.Nedeljna;
		}
		else if(nazivClanarine.equals("StudioClassic"))
		{
			brojTreninga = 15;
			cena = 4500;
			nowPlusDate = LocalDateTime.now().plusMonths(1);
			tipClanarine = tipClanarine.Mesecna;
		}
		else if(nazivClanarine.equals("StudioElite"))
		{
			brojTreninga = 600;
			cena = 50000;
			nowPlusDate = LocalDateTime.now().plusYears(1);
			tipClanarine =tipClanarine.Godisnja;
		}
		else
		{
			  System.out.println("NAZIV CLANRINE NIJE DOBAR!!!!!!!!!!!");
			  return;
		}
		
		if(getClanarinaByKorisnickoIme(korisnickoIme) != null)
		{
			obrisiClanarinaByKorisnickoIme(korisnickoIme);
		}
		
		Clanarina clanarina = new Clanarina(ClanarineId+"", tipClanarine, now.toLocalDate().toString(),nowPlusDate.toLocalDate().toString(), cena,  korisnickoIme, true, brojTreninga);
		clanarine.put(ClanarineId, clanarina);
		sacuvajNovuClanarinu(clanarina);
		System.out.println("Kreirao clanarinu!");
	}
	
	public void obrisiClanarinaByKorisnickoIme(String korisnickoIme)
	{
		for (Clanarina clan : clanarine.values())	
		{					
			if(clan.getKupacid().equals(korisnickoIme))
			{
				clan.setStatus(false);
				System.out.println("Obrisao clanarinu!");
			}
		}
		AzurirajBazu();
	}
	
	private void sacuvajNovuClanarinu(Clanarina clan) 
	{
		String putanja = "./static/podaci/Clanarine.txt";
		try 
		{
			FileWriter writer = new FileWriter(putanja, true);
			writer.append(clan.toSaveFormat());
			writer.close();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
			System.out.println("Fajl " + putanja + " nije pronadjen!\r\n");
		}
	}
	public void AzurirajBazu() 
	{
		String putanja = "./static/podaci/Clanarine.txt";
		try 
		{
			
			FileWriter writer = new FileWriter(putanja, false);
			for (Clanarina clan : clanarine.values()) 
			{
				writer.write(clan.toSaveFormat());
			}
			writer.close();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			System.out.println("Fajl " + putanja + " nije pronadjen!\r\n");
		}
		
	}
	
	public Clanarina getClanarinaByKorisnickoIme(String korisnickoIme)
	{
		
		for (Clanarina clan : clanarine.values())	
		{	
			if(clan.getKupacid().equals(korisnickoIme))
			{
				if(clan.isStatus()) 
				{
					return clan;
				}
			}
		}
		
		return null;
	}
	
	private void ucitajClanarine(KorisniciDAO korisniciDAO) 
	{
		String putanja = "./static/podaci/Clanarine.txt";
		
		BufferedReader bafer;
		try	
		{
			bafer = new BufferedReader(new FileReader(putanja));
			String row;
			while ((row = bafer.readLine()) != null)	
			{
				Gson gson =  new GsonBuilder().excludeFieldsWithoutExposeAnnotation().registerTypeAdapter(Date.class, (JsonDeserializer) (json, typeOfT, context) -> new Date(json.getAsLong())).create();

				LocalDate now = LocalDate.now();
				Clanarina clanarina = gson.fromJson(row, Clanarina.class);
				if(clanarina.isStatus()) 
				{
					if(clanarina.getBrojTermina() <= 0 || LocalDate.parse(clanarina.getDatumVazenja()).compareTo(now) <= 0) 
					{
							int bodovi = clanarina.setStatus(false);
							((Kupac)korisniciDAO.getKorisnikByKorisnickoIme(clanarina.getKupacid())).addBodovi(bodovi);
							
							System.out.println("Clanarina istekla!");
						
					}
					
					ClanarineId = Integer.parseInt(clanarina.getIdentifikator());
					clanarine.put(ClanarineId, clanarina);
				}
			}
			bafer.close();
			AzurirajBazu();
		} 
		catch (Exception e)	
		{
			e.printStackTrace();
			System.out.println("Fajl " + putanja + " nije pronadjen.\r\n");
		}
	}
}
