package dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Collection;
import java.util.HashMap;
import java.util.StringTokenizer;

import beans.SportskiObjekat;



/***
 * Klasa namenjena da uèita proizvode iz fajla i pruža operacije nad njima (poput pretrage).
 * Proizvodi se nalaze u fajlu WebContent/products.txt u obliku: <br>
 * id;status;tipObjekta;sadrzaj;lokacija;slika;prosecnaOcena;
 * @author Lazar
 *
 */
public class SportskiObjekatDAO {
	
	private HashMap<String, SportskiObjekat> sportski = new HashMap<String, SportskiObjekat>();
	
	public SportskiObjekatDAO() {
		
	}
	
	/***
	 * @param contextPath Putanja do aplikacije u Tomcatu. Može se pristupiti samo iz servleta.
	 */
	public SportskiObjekatDAO(String contextPath) {
		loadSportskiObjekat(contextPath);
	}

	/***
	 * Vraæa sve proizvode.
	 * @return
	 */
	public Collection<SportskiObjekat> findAll() {
		return sportski.values();
	}

	/***
	 *  Vraca proizvod na osnovu njegovog id-a. 
	 *  @return Proizvod sa id-em ako postoji, u suprotnom null
	 */
	public SportskiObjekat findSportskiObjekat(String id) {
		return sportski.containsKey(id) ? sportski.get(id) : null;
	}
	
	/***
	 * Dodaje proizvod u mapu proizvoda. Id novog proizvoda æe biti postavljen na maxPostojeciId + 1.
	 * id;status;tipObjekta;sadrzaj;lokacija;slika;prosecnaOcena;
	 * @param product
	 */
	public SportskiObjekat save(SportskiObjekat sport) {
		Integer maxId = -1;
		for (String id : sportski.keySet()) {
			int idNum =Integer.parseInt(id);
			if (idNum > maxId) {
				maxId = idNum;
			}
		}
		maxId++;
		sport.setId(maxId.toString());
		sportski.put(sport.getId(), sport);
		return sport;
	}

	/**
	 * Uèitava korisnike iz WebContent/users.txt fajla i dodaje ih u mapu {@link #products}.
	 * Kljuè je id proizovda.
	 * id;status;tipObjekta;sadrzaj;lokacija;slika;prosecnaOcena;
	 * @param contextPath Putanja do aplikacije u Tomcatu
	 */
	private void loadSportskiObjekat(String contextPath) {
		BufferedReader in = null;
		try {
			File file = new File(contextPath + "/products.txt");
			System.out.println(file.getCanonicalPath());
			in = new BufferedReader(new FileReader(file));
			String line, id = "", name ="", status = "" , type = "" , sadrzaj = "" , lokacija = "" , ocena ="" , vreme = "";
			StringTokenizer st;
			while ((line = in.readLine()) != null) {
				line = line.trim();
				if (line.equals("") || line.indexOf('#') == 0)
					continue;
				st = new StringTokenizer(line, ";");
				while (st.hasMoreTokens()) {
					id = st.nextToken().trim();	
					name = st.nextToken().trim();
					status = st.nextToken().trim();
					type = st.nextToken().trim();
					sadrzaj = st.nextToken().trim();
					lokacija = st.nextToken().trim();
					ocena = st.nextToken().trim();
					vreme = st.nextToken().trim();
				}
				sportski.put(id, new SportskiObjekat(id, name, status, type, sadrzaj, lokacija,  Double
						.parseDouble(ocena), vreme));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if ( in != null ) {
				try {
					in.close();
				}
				catch (Exception e) { }
			}
		}
		
	}
	
	public SportskiObjekat change(SportskiObjekat sport) {
		sportski.put(sport.getId(), sport);
		return sport;
	}
	
	public SportskiObjekat delete(String id) {
		return sportski.remove(id);
	}
	
}
