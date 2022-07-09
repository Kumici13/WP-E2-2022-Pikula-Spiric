package dao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDate;
import java.util.ArrayList;

import java.util.HashMap;
import beans.Clanarina;
import beans.Korisnik;
import enums.TipClanarine;
import enums.Uloga;



public class ClanarineDAO {

	
private HashMap<Integer, Clanarina> clanarine;
	
	public ClanarineDAO()
	{
		clanarine = new HashMap<Integer, Clanarina>();
		AddClanarina("Classic");
		//ucitajClanarine();
	}
	
	public void AddClanarina(String NazivClanarine)
	{
		String putanja = "./static/podaci/Clanarine.txt";
		BufferedReader bafer;
		try	
		{
			bafer = new BufferedReader(new FileReader(putanja));
			String row;
			while ((row = bafer.readLine()) != null)	
			{
				
				if(NazivClanarine.equals("Classic"))
				{
					Clanarina clanarina = new Clanarina("0", TipClanarine.Godisnja, "/","/", 32, null, "lukap", true, 3);
					clanarine.put(412, clanarina);
				}
				else if(NazivClanarine.equals("StudioClassic"))
				{
					Clanarina clanarina = new Clanarina("0", TipClanarine.Mesecna, "/","/", 32, null, "lukap", true, 3);
					clanarine.put(412, clanarina);
				}
				else if(NazivClanarine.equals("StudioElite"))
				{
					Clanarina clanarina = new Clanarina("0", TipClanarine.Godisnja, "/","/", 32, null, "lukap", true, 3);
					clanarine.put(412, clanarina);
				}
				else
				{
					System.out.println("NAZIV CLANRINE NIJE DOBAR!!!!!!!!!!!");
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
	
	public Clanarina getClanarinaByKorisnickoIme(String korisnickoIme)
	{
		
		for (Clanarina clan : clanarine.values())	
		{					
			if(clan.getKupacid().equals(korisnickoIme))
			{
				System.out.println("uporedjujem " + clan.getKupacid());

				System.out.println(" sa korisnickim imenom: " + korisnickoIme);
				return clan;
			}
		}
		
		return null;
	}
	
	private void ucitajClanarine() 
	{
		String putanja = "./static/podaci/Clanarine.txt";
		
		BufferedReader bafer;
		try	
		{
			bafer = new BufferedReader(new FileReader(putanja));
			String row;
			while ((row = bafer.readLine()) != null)	
			{
				String[] tokeni = row.split(";");
				
				//Clanarina clanarina = new Clanarina(tokeni[0],TipClanarine.valueOf(tokeni[1]), tokeni[2], tokeni[3], Double.parseDouble(tokeni[4]), tokeni[5], Boolean.parseBoolean(tokeni[6]), Integer.parseInt(tokeni[7]));
				//clanarine.put(Integer.parseInt(tokeni[0]), clanarina);
			}
			
			bafer.close();
		} 
		catch (Exception e)	
		{
			e.printStackTrace();
			System.out.println("Fajl " + putanja + " nije pronadjen.\r\n");
		}
	}
	
	
	
	

}
