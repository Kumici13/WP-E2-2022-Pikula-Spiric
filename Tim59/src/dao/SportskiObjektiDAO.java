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
import java.util.Iterator;

import beans.Adresa;
import beans.Lokacija;
import beans.SportskiObjekat;
import enums.TipObjektaEnum;
import javaxt.utils.string;

public class SportskiObjektiDAO {

	private HashMap<Integer, SportskiObjekat> sportskiObjekti;
	private int SportskiObjektiId =0;
	public SportskiObjektiDAO()
	{
		sportskiObjekti = new HashMap<Integer, SportskiObjekat>();

		ucitajSportskeObjekte();
	}
	
	public ArrayList<SportskiObjekat> getAllSportskiObjekti()
	{
		ArrayList<SportskiObjekat> sportskiObjekats = new ArrayList<>();
		
		for (SportskiObjekat sportskiObjekat : sportskiObjekti.values())	
		{
			sportskiObjekats.add(sportskiObjekat);
		}
		
		return sportskiObjekats;
	}
		
	
	
	private void ucitajSportskeObjekte() 
	{
		String putanja = "./static/podaci/SportskiObjekti.txt";
		
		BufferedReader bafer;
		try	
		{
			bafer = new BufferedReader(new FileReader(putanja));
			String row;
			while ((row = bafer.readLine()) != null)	
			{
				String[] tokeni = row.split(";");
				SportskiObjektiId = Integer.parseInt(tokeni[0]);
				String[] tokeniAdrese = tokeni[5].split(",");
				Adresa adresaSportskogObjekta = new Adresa(tokeniAdrese[0],tokeniAdrese[1],tokeniAdrese[2],Integer.parseInt(tokeniAdrese[3]));
				
				Lokacija lokacija = new Lokacija(Double.parseDouble(tokeni[6]), Double.parseDouble(tokeni[7]), adresaSportskogObjekta);
				
				String sadrzaj = tokeni[4];
				String[] sadrzaji = sadrzaj.split(",");
				
				SportskiObjekat sportskiObjekat = new SportskiObjekat(tokeni[0],tokeni[1],Boolean.parseBoolean(tokeni[2]),TipObjektaEnum.valueOf(tokeni[3]),sadrzaji,lokacija,tokeni[8],Double.parseDouble(tokeni[9]),tokeni[10]);
				sportskiObjekti.put(SportskiObjektiId, sportskiObjekat);
			}
			
			bafer.close();
		} 
		catch (Exception e)	
		{
			e.printStackTrace();
			System.out.println("Fajl " + putanja + " nije pronadjen.\r\n");
		}
	}
	
	public SportskiObjekat dodajNoviSportskiObjekat(SportskiObjekat noviSportskiObjekat) 
	{
		SportskiObjektiId++;
		noviSportskiObjekat.setId(""+SportskiObjektiId);
		noviSportskiObjekat.setStatus(false);
		sportskiObjekti.put(SportskiObjektiId, noviSportskiObjekat);
		
		sacuvajNoviSportskiObjekat(noviSportskiObjekat);
		
		return noviSportskiObjekat;
	}
	
	private void sacuvajNoviSportskiObjekat(SportskiObjekat noviSportskiObjekat) 
	{
		String putanja = "./static/podaci/SportskiObjekti.txt";
		try 
		{
			FileWriter writer = new FileWriter(putanja, true);
			writer.append(noviSportskiObjekat.toSaveFormat());
			writer.close();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
			System.out.println("Fajl " + putanja + " nije pronadjen!\r\n");
		}
	}
	
	public void dodajSliku(String imeSlike, String idSportskogObjekta)	
	{
		for (SportskiObjekat sportskiObjekat : sportskiObjekti.values())	
		{
			if(sportskiObjekat.getId().equals(idSportskogObjekta))
			{
				sportskiObjekat.setLogo(imeSlike);
				azurirajBazu();
				return;
			}
		}
	}
	
	private void azurirajBazu()	
	{
		String putanja = "./static/podaci/SportskiObjekti.txt";
		try 
		{
			FileWriter writer = new FileWriter(putanja, false);
			for (SportskiObjekat sportskiObjekat : sportskiObjekti.values()) 
			{
				writer.write(sportskiObjekat.toSaveFormat());
			}
			writer.close();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			System.out.println("Fajl " + putanja + " nije pronadjen!\r\n");
		}
	}
	
	public SportskiObjekat getSportskiObjekatById(String idSportskogObjekta)	
	{
		for (SportskiObjekat sportskiObjekat : sportskiObjekti.values())	
		{
			if(sportskiObjekat.getId() == idSportskogObjekta)
			{
				return sportskiObjekat;
			}
		}
		
		return null;
	}
	
}
