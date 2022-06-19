package dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.Iterator;

import beans.Adresa;
import beans.Lokacija;
import beans.SportskiObjekat;
import enums.TipObjektaEnum;

public class SportskiObjektiDAO {

	private HashMap<Integer, SportskiObjekat> sportskiObjekti;
	
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
				
				String[] tokeniAdrese = tokeni[5].split(",");
				Adresa adresaSportskogObjekta = new Adresa(tokeniAdrese[0],Integer.parseInt(tokeniAdrese[1]),tokeniAdrese[2],Integer.parseInt(tokeniAdrese[3]));
				
				Lokacija lokacija = new Lokacija(Double.parseDouble(tokeni[6]), Double.parseDouble(tokeni[7]), adresaSportskogObjekta);
				
				String sadrzaj = tokeni[4];
				String[] sadrzaji = sadrzaj.split(",");
				
				SportskiObjekat sportskiObjekat = new SportskiObjekat(tokeni[0],tokeni[1],Boolean.parseBoolean(tokeni[2]),TipObjektaEnum.valueOf(tokeni[3]),sadrzaji,lokacija,ucitajSliku("./static/Images/" + tokeni[8]),Double.parseDouble(tokeni[9]),tokeni[10]);
				sportskiObjekti.put(Integer.parseInt(tokeni[0]), sportskiObjekat);
			}
			
			bafer.close();
		} 
		catch (Exception e)	
		{
			e.printStackTrace();
			System.out.println("Fajl " + putanja + " nije pronadjen.\r\n");
		}
	}
	
	private String ucitajSliku(String putanja)	
	{
		try 
		{
			File file = new File(putanja);
			String encodeImage = Base64.getEncoder().withoutPadding().encodeToString(Files.readAllBytes(file.toPath()));
			return encodeImage;
		} 
		catch (Exception e) 
		{
			System.out.println(putanja + " nije pronadjen.\r\n");
			return null;
		}
	}
	
}
