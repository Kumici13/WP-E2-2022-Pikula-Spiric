package beans;

import enums.Pol;
import enums.Uloga;

public class Kupac extends Korisnik {
	
	private Clanarina clanarina;
	private String poseceniObjekti;
	private double sakupljeniBodovi;
	
	public Kupac() {
		super();
		this.uloga = Uloga.Kupac;
	}
	
	public Kupac(String korisnickoIme, String lozinka, String ime, String prezime, Pol pol, String datumRodjenja, Clanarina clanarina, String poseceniObjekti, double sakupljeniBodovi) {
		super(korisnickoIme, lozinka, ime, prezime, pol, datumRodjenja, Uloga.Kupac);
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
	public String toSaveFormat() {
		return this.getKorisnickoIme() + ";" + this.getSifra() + ";" + this.getIme() + ";" + this.getPrezime() + ";" + this.getPol().name() + ";" + this.getDatumRodjenja() + ";"+ "clanarina" + ";"+ this.poseceniObjekti + ";"+ this.sakupljeniBodovi +"\n";
	}


}
