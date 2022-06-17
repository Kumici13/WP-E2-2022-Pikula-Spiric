package beans;

import enums.Pol;
import enums.Uloga;

public class Trener extends Korisnik{
	
	private String istorijaTreninga;
	
	public Trener() {
		super();
		this.uloga = Uloga.Trener;
	}
	
	public Trener(String korisnickoIme, String lozinka, String ime, String prezime, Pol pol, String datumRodjenja, String istorijaTreninga) {
		super(korisnickoIme, lozinka, ime, prezime, pol,datumRodjenja, Uloga.Trener);
		this.uloga = Uloga.Trener;
		this.istorijaTreninga = istorijaTreninga;
	}

	public String getIstorijaTreninga() {
		return istorijaTreninga;
	}

	public void setIstorijaTreninga(String istorijaTreninga) {
		this.istorijaTreninga = istorijaTreninga;
	}
	
	

}
