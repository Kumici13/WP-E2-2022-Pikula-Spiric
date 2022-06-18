package beans;

import enums.Pol;
import enums.Uloga;

public class Korisnik {
	
	private String korisnickoIme;
	private String sifra;
	private String ime;
	private String prezime;
	private Pol pol;
	private String datumRodjenja;
	protected Uloga uloga;
	protected String JWTToken;

	
	public Korisnik()
	{
		
	}
	
	public Korisnik(String korisnickoIme, String sifra, String ime, String prezime, Pol pol, String datumRodjenja,
			Uloga uloga) {
		super();
		this.korisnickoIme = korisnickoIme;
		this.sifra = sifra;
		this.ime = ime;
		this.prezime = prezime;
		this.pol = pol;
		this.datumRodjenja = datumRodjenja;
		this.uloga = uloga;
		
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
	
	public String toSaveFormat() {
		return this.korisnickoIme + ";" + this.sifra + ";" + this.ime + ";" + this.prezime + ";" + this.pol.name() + ";" + this.datumRodjenja +"\n";
	}

}
