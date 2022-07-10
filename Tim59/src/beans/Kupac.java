package beans;

import java.util.Date;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.google.gson.annotations.Expose;

import enums.Pol;
import enums.Uloga;

public class Kupac extends Korisnik {
	@Expose
	private Clanarina clanarina;
	@Expose
	private String poseceniObjekti;
	@Expose
	private double sakupljeniBodovi;
	
	public Kupac() {
		super();
		this.uloga = Uloga.Kupac;
	}
	
	public Kupac(String korisnickoIme, String lozinka, String ime, String prezime, Pol pol, String datumRodjenja, Clanarina clanarina, String poseceniObjekti, double sakupljeniBodovi, boolean aktivan) {
		super(korisnickoIme, lozinka, ime, prezime, pol, datumRodjenja, Uloga.Kupac, aktivan);
		this.uloga = Uloga.Kupac;
		this.clanarina = clanarina;
		this.poseceniObjekti = poseceniObjekti;
		this.sakupljeniBodovi = sakupljeniBodovi;
	}

	public Clanarina getClanarina() {
		return clanarina;
	}

	public void setClanarina(Clanarina clanarina) {
		this.clanarina = clanarina;
	}

	public String getPoseceniObjekti() {
		return poseceniObjekti;
	}

	public void setPoseceniObjekti(String poseceniObjekti) {
		this.poseceniObjekti = poseceniObjekti;
	}

	public double getSakupljeniBodovi() {
		return sakupljeniBodovi;
	}

	public void setSakupljeniBodovi(double sakupljeniBodovi) {
		this.sakupljeniBodovi = sakupljeniBodovi;
	}
	
	@Override
	public String toSaveFormat() 
	{
		Gson gson =  new GsonBuilder().excludeFieldsWithoutExposeAnnotation().registerTypeAdapter(Date.class, (JsonDeserializer) (json, typeOfT, context) -> new Date(json.getAsLong())).create();
		
		return gson.toJson(this)+"\n";
	}


}
