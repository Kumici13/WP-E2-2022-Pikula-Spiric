package beans;

import enums.Pol;
import enums.Uloga;

public class Menadzer extends Korisnik {
	
	private SportskiObjekat sportskiobjekat;
	
	public Menadzer() {
		super();
		this.uloga = Uloga.Menadzer;
	}
	
	public Menadzer(String korisnickoIme, String lozinka, String ime, String prezime, Pol pol, String datumRodjenja, SportskiObjekat sportskiobjekat) {
		super(korisnickoIme, lozinka, ime, prezime, pol, datumRodjenja, Uloga.Menadzer);
		this.uloga = Uloga.Menadzer;
		this.sportskiobjekat = sportskiobjekat;
	}

	public SportskiObjekat getSportskiobjekat() {
		return sportskiobjekat;
	}

	public void setSportskiobjekat(SportskiObjekat sportskiobjekat) {
		this.sportskiobjekat = sportskiobjekat;
	}
	
	
	@Override
	public String toSaveFormat() {
		return this.getKorisnickoIme() + ";" + this.getSifra() + ";" + this.getIme() + ";" + this.getPrezime() + ";" + this.getPol().name() + ";" + this.getDatumRodjenja() + ";"+ "clanarina" + ";"+ this.sportskiobjekat + "\n";
	}


	

}
