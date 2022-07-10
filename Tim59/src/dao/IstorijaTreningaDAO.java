package dao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;

import beans.IstorijaTreninga;
import beans.Trener;
import beans.IstorijaTreninga;

public class IstorijaTreningaDAO {
	private HashMap<Integer, IstorijaTreninga> treninzi;

	private int TreningId = 0;
	
	public IstorijaTreningaDAO()
	{
		treninzi = new HashMap<Integer, IstorijaTreninga>();

		ucitajIstorijuTreninga();
	}
	
	public ArrayList<IstorijaTreninga> getAllIstorijaTreninga()
	{
		ArrayList<IstorijaTreninga> trenings = new ArrayList<>();
		
		for (IstorijaTreninga trening : treninzi.values())	
		{
			trenings.add(trening);
			
		}
		
		return trenings;
	}
	
	public boolean checkIfAlreadyExists(String treningId, String kupacId, String dateTime)
	{
		boolean found = false;
		for (IstorijaTreninga it : treninzi.values())	
		{
			if(it.getDatumVreme().equals(dateTime))
			{
				if(it.getKupacId().equals(kupacId))
				{
					if(it.getTreningId().equals(treningId))
					{
						found = true; //VEC POSTOJI
						break;
					}
					else 
					{
						continue;
					}
				}
				else 
				{
					continue;
				}
			}
			else 
			{
				continue;
			}
		}
		
		return found; 
		
	}
	
	private void ucitajIstorijuTreninga() 
	{
		String putanja = "./static/podaci/IstorijaTreninga.txt";
		
		BufferedReader bafer;
		try	
		{
			bafer = new BufferedReader(new FileReader(putanja));
			String row;
			while ((row = bafer.readLine()) != null)	
			{
				Gson gson =  new GsonBuilder().excludeFieldsWithoutExposeAnnotation().registerTypeAdapter(Date.class, (JsonDeserializer) (json, typeOfT, context) -> new Date(json.getAsLong())).create();
				IstorijaTreninga trening = gson.fromJson(row, IstorijaTreninga.class);
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
	
	public IstorijaTreninga dodajNoviTrening(IstorijaTreninga novaIstorijaTreninga) 
	{
		TreningId++;
		novaIstorijaTreninga.setId(""+TreningId);
		treninzi.put(TreningId, novaIstorijaTreninga);
		
		sacuvajNovuIstorijuTreninga(novaIstorijaTreninga);
		
		return novaIstorijaTreninga;
	}
	
	private void sacuvajNovuIstorijuTreninga(IstorijaTreninga novaIstorijaTreninga) 
	{
		String putanja = "./static/podaci/IstorijaTreninga.txt";
		try 
		{
			FileWriter writer = new FileWriter(putanja, true);
			writer.append(novaIstorijaTreninga.toSaveFormat());
			writer.close();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
			System.out.println("Fajl " + putanja + " nije pronadjen!\r\n");
		}
	}

	private void azurirajBazu()	
	{
		String putanja = "./static/podaci/IstorijaTreninga.txt";
		try 
		{
			FileWriter writer = new FileWriter(putanja, false);
			for (IstorijaTreninga itrening : treninzi.values()) 
			{
				writer.write(itrening.toSaveFormat());
			}
			writer.close();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			System.out.println("Fajl " + putanja + " nije pronadjen!\r\n");
		}
	}
	
	public IstorijaTreninga getIstorijaTreningById(String idIstorijeTreninga)	
	{
		for (IstorijaTreninga trening : treninzi.values())	
		{
			if(trening.getId().equals(idIstorijeTreninga))
			{
				return trening;
			}
		}
		
		return null;
	}
	

}
