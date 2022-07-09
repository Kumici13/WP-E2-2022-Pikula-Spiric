package dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;

import beans.Adresa;
import beans.Lokacija;
import beans.Menadzer;
import beans.SportskiObjekat;
import beans.Trener;
import beans.Trening;
import enums.TipObjektaEnum;
import enums.TipTreninga;

public class TreningDAO {
	
private HashMap<Integer, Trening> treninzi;

	private int TreningId = 0;
	
	public TreningDAO()
	{
		treninzi = new HashMap<Integer, Trening>();

		ucitajTreninge();
	}
	
	public void ucitajSportskeObjekteiTrenerer(SportskiObjektiDAO sportskiObjektiDAO, KorisniciDAO korisniciDAO)
	{
		for (Trening trening : treninzi.values())	
		{
			if(trening.isAktivan()) 
			{
				if(!trening.getSportskiObjekatid().equals("null"))
				{
					trening.setSportskiObjekat(sportskiObjektiDAO.getSportskiObjekatById(trening.getSportskiObjekatid()));
				}
				
				if(!trening.getTrenerid().equals("null"))
				{
					trening.setTrener((Trener)korisniciDAO.getKorisnikByKorisnickoIme(trening.getTrenerid()));
				}
			}
		}
		
	}
	
	public ArrayList<Trening> getAllTreninzi()
	{
		ArrayList<Trening> trenings = new ArrayList<>();
		
		for (Trening trening : treninzi.values())	
		{
			if(trening.isAktivan()) 
			{
				trenings.add(trening);
			}
		}
		
		return trenings;
	}
	
	public ArrayList<Trening> getTreninziBySportskiObjekatId(String sportskiObjekatId)
	{
		ArrayList<Trening> trenings = new ArrayList<>();
		
		for (Trening trening : treninzi.values())	
		{
			if(trening.getSportskiObjekatid().equals(sportskiObjekatId) && trening.isAktivan()) 
			{
				trenings.add(trening);
			}
		}
		
		return trenings;
	}
	
	public ArrayList<Trener> getTreneriBySportskiObjekatId(String sportskiObjekatId)
	{
		ArrayList<Trener> treneri = new ArrayList<>();
		
		for (Trening trening : treninzi.values())	
		{
			if(trening.getSportskiObjekatid().equals(sportskiObjekatId) && trening.isAktivan()) 
			{
				if(trening.getTrener()!=null) 
				{
					if(!treneri.contains(trening.getTrener())) 
					{
						treneri.add(trening.getTrener());
					}
				}
			}
		}
		
		return treneri;
	}
	
	private void ucitajTreninge() 
	{
		String putanja = "./static/podaci/Treninzi.txt";
		
		BufferedReader bafer;
		try	
		{
			bafer = new BufferedReader(new FileReader(putanja));
			String row;
			while ((row = bafer.readLine()) != null)	
			{
				Gson gson =  new GsonBuilder().excludeFieldsWithoutExposeAnnotation().registerTypeAdapter(Date.class, (JsonDeserializer) (json, typeOfT, context) -> new Date(json.getAsLong())).create();
				Trening trening = gson.fromJson(row, Trening.class);
				trening.setSlikaNaziv(trening.getSlikaNaziv()); // UCITAJ SLIKA
				treninzi.put(Integer.parseInt(trening.getId()), trening);
				TreningId = Integer.parseInt(trening.getId());
			}
			
			bafer.close();
		} 
		catch (Exception e)	
		{
			e.printStackTrace();
			System.out.println("Fajl " + putanja + " nije pronadjen.\r\n");
		}
	}
	
	public Trening dodajNoviTrening(Trening noviSportskiObjekat) 
	{
		TreningId++;
		noviSportskiObjekat.setId(""+TreningId);
		treninzi.put(TreningId, noviSportskiObjekat);
		
		sacuvajNoviTrening(noviSportskiObjekat);
		
		return noviSportskiObjekat;
	}
	
	private void sacuvajNoviTrening(Trening noviTrening) 
	{
		String putanja = "./static/podaci/Treninzi.txt";
		try 
		{
			FileWriter writer = new FileWriter(putanja, true);
			writer.append(noviTrening.toSaveFormat());
			writer.close();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
			System.out.println("Fajl " + putanja + " nije pronadjen!\r\n");
		}
	}
	
	public void dodajSliku(String imeSlike, String idTreninga)	
	{
		for (Trening trening : treninzi.values())	
		{
			if(trening.getId().equals(idTreninga))
			{
				trening.setSlikaNaziv(imeSlike);
				azurirajBazu();
				return;
			}
		}
	}

	private void azurirajBazu()	
	{
		String putanja = "./static/podaci/Treninzi.txt";
		try 
		{
			FileWriter writer = new FileWriter(putanja, false);
			for (Trening trening : treninzi.values()) 
			{
				writer.write(trening.toSaveFormat());
			}
			writer.close();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			System.out.println("Fajl " + putanja + " nije pronadjen!\r\n");
		}
	}
	
	public Trening getTreningById(String idTreninga)	
	{
		for (Trening trening : treninzi.values())	
		{
			if(trening.getId().equals(idTreninga))
			{
				return trening;
			}
		}
		
		return null;
	}
	
	public void changeTreningActivityById(String idTreninga)
	{
		for (Trening trening : treninzi.values())	
		{
			if(trening.getId().equals(idTreninga))
			{
				trening.setAktivan(!trening.isAktivan());
			}
		}
		
		azurirajBazu();
		
	}
	
	
	
}
