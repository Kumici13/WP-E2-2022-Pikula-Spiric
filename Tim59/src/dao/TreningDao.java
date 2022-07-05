package dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
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
				
				SportskiObjekat sportskiObjekat = new SportskiObjekat();
				Trener trener = new Trener();
			
				Trening trening = new Trening(tokeni[0],tokeni[1],TipTreninga.valueOf(tokeni[1]), sportskiObjekat, Double.parseDouble(tokeni[4]), trener, tokeni[5], ucitajSliku("./static/Images/" + tokeni[6]));
				treninzi.put(Integer.parseInt(tokeni[0]), trening);
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
