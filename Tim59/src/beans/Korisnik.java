package beans;

import java.util.Date;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.google.gson.annotations.Expose;

import enums.Pol;
import enums.Uloga;

public class Korisnik {
	@Expose
	private String korisnickoIme;
	@Expose
	private String sifra;
	@Expose
	private String ime;
	@Expose
	private String prezime;
	@Expose
	private Pol pol;
	@Expose
	private String datumRodjenja;
	@Expose
	protected Uloga uloga;
	
	protected String JWTToken;
	@Expose
	protected boolean aktivan= true;

	
	public Korisnik()
	{
		
	}
	
	public Korisnik(String korisnickoIme, String sifra, String ime, String prezime, Pol pol, String datumRodjenja,
			Uloga uloga, boolean aktivan) {
		super();
		this.korisnickoIme = korisnickoIme;
		this.sifra = sifra;
		this.ime = ime;
		this.prezime = prezime;
		this.pol = pol;
		this.datumRodjenja = datumRodjenja;
		this.uloga = uloga;
		this.aktivan = aktivan;
		
	}
	
	public String getKorisnickoIme() {
		return korisnickoIme;
	}

	public void setKorisnickoIme(String korisnickoIme) {
		this.korisnickoIme = korisnickoIme;
	}

	public String getSifra() {
		return sifra;
	}

	public void setSifra(String sifra) {
		this.sifra = sifra;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public Pol getPol() {
		return pol;
	}

	public void setPol(Pol pol) {
		this.pol = pol;
	}

	public String getDatumRodjenja() {
		return datumRodjenja;
	}

	public void setDatumRodjenja(String datumRodjenja) {
		this.datumRodjenja = datumRodjenja;
	}

	public Uloga getUloga() {
		return uloga;
	}

	public void setUloga(Uloga uloga) {
		this.uloga = uloga;
	}

	public String getJWTToken() {
		return JWTToken;
	}

	public void setJWTToken(String jWTToken) {
		JWTToken = jWTToken;
	}
	
	
	
	public boolean isAktivan() {
		return aktivan;
	}

	public void setAktivan(boolean obrisan) {
		this.aktivan = obrisan;
	}

	public String toSaveFormat() 
	{
		Gson gson =  new GsonBuilder().excludeFieldsWithoutExposeAnnotation().registerTypeAdapter(Date.class, (JsonDeserializer) (json, typeOfT, context) -> new Date(json.getAsLong())).create();
		
		return gson.toJson(this)+"\n";
	}

}
