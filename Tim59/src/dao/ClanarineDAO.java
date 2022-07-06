package dao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import beans.Clanarina;
import enums.TipClanarine;


public class ClanarineDAO {
	
private HashMap<Integer, Clanarina> clanarine;
	
	public ClanarineDAO()
	{
		clanarine = new HashMap<Integer, Clanarina>();

		ucitajClanarine();
	}
	
	public ArrayList<Clanarina> getAllClanarine()
	{
		ArrayList<Clanarina> clanarinas = new ArrayList<>();
		
		for (Clanarina clanarina : clanarine.values())	
		{
			clanarinas.add(clanarina);
		}
		
		return clanarinas;
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
				
				Clanarina clanarina = new Clanarina(tokeni[0],TipClanarine.valueOf(tokeni[1]), tokeni[2], tokeni[3], Double.parseDouble(tokeni[4]), tokeni[5], Boolean.parseBoolean(tokeni[6]), Integer.parseInt(tokeni[7]));
				clanarine.put(Integer.parseInt(tokeni[0]), clanarina);
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
