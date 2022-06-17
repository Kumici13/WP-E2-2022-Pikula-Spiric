package beans;

import enums.Pol;
import enums.Uloga;

public class Administrator extends Korisnik {
	
	public Administrator() {
		super();
		this.uloga = Uloga.Administrator;
	}
	
	public Administrator(String korisnickoIme, String lozinka, String ime, String prezime, Pol pol, String datumRodjenja) {
		super(korisnickoIme, lozinka, ime, prezime, pol, datumRodjenja, Uloga.Administrator);
		this.uloga = Uloga.Administrator;
	}

}
