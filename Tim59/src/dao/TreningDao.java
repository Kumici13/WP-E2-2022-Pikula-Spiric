package dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;

import beans.Adresa;
import beans.Lokacija;
import beans.SportskiObjekat;
import beans.Trener;
import beans.Trening;
import enums.TipObjektaEnum;
import enums.TipTreninga;

public class TreningDao {
	
private HashMap<Integer, Trening> treninzi;

	private int TreningId = 0;
	
	public TreningDao()
	{
		treninzi = new HashMap<Integer, Trening>();

		ucitajTreninge();
	}
	
	public ArrayList<Trening> getAllTreninzi()
	{
		ArrayList<Trening> trenings = new ArrayList<>();
		
		for (Trening trening : treninzi.values())	
		{
			trenings.add(trening);
		}
		
		return trenings;
	}
	
	public ArrayList<Trening> getTreninziBySportskiObjekatId(String sportskiObjekatId)
	{
		ArrayList<Trening> trenings = new ArrayList<>();
		
		for (Trening trening : treninzi.values())	
		{
			if(trening.getSportskiObjekatid() == sportskiObjekatId) 
			{
				trenings.add(trening);
			}
		}
		
		return trenings;
	}
	
	public ArrayList<Trener> getTreneriBySportskiObjekatId(String trenerId)
	{
		ArrayList<Trener> treneri = new ArrayList<>();
		
		for (Trening trening : treninzi.values())	
		{
			if(trening.getTrenerid() == trenerId) 
			{
				if(!treneri.contains(trening.getTrener())) 
				{
					treneri.add(trening.getTrener());
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
				String[] tokeni = row.split(";");
				
				Trening trening = new Trening(tokeni[0],tokeni[1],TipTreninga.valueOf(tokeni[2]), tokeni[3], Double.parseDouble(tokeni[4]), tokeni[5], tokeni[6], tokeni[7]);
				treninzi.put(Integer.parseInt(tokeni[0]), trening);
				TreningId = Integer.parseInt(tokeni[0]);
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
			if(trening.getId() == idTreninga)
			{
				return trening;
			}
		}
		
		return null;
	}
	
}
