package dao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;

import beans.Administrator;
import beans.Clanarina;
import beans.Korisnik;
import beans.Kupac;
import beans.Menadzer;
import beans.Trener;
import enums.Pol;
import enums.Uloga;

public class KorisniciDAO {
	
	private HashMap<String, Korisnik> korisnici;
	
	public KorisniciDAO(String putanja)	{
		korisnici = new HashMap<String, Korisnik>();

		ucitajKorisnike(putanja + "Administratori.txt", Uloga.Administrator);
		ucitajKorisnike(putanja + "Kupci.txt", Uloga.Kupac);
		ucitajKorisnike(putanja + "Menadzeri.txt", Uloga.Menadzer);
		ucitajKorisnike(putanja + "Treneri.txt", Uloga.Trener);

	}
	
	private void ucitajKorisnike(String putanja, Uloga uloga)	{
		BufferedReader bafer;
		try	{
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
				System.out.println("KorisniciDAO: " + korisnici.get(tokeni[0]).toString() + "\r\n");
			}
			bafer.close();
		} catch (Exception e)	{
			e.printStackTrace();
			System.out.println("Fajl " + putanja + " nije pronadjen.\r\n");
		}
	}


	public Korisnik getKorisnikByKorisnickoIme(String korisnickoIme)	{
		return korisnici.get(korisnickoIme);
	}


}
