package beans;

import enums.Pol;
import enums.Uloga;
import javaxt.utils.string;

public class Menadzer extends Korisnik {
	
	private String sportskiObjekatId="null";
	private SportskiObjekat sportskiobjekat;
	
	public Menadzer() {
		super();
		this.uloga = Uloga.Menadzer;
	}
	
	public Menadzer(String korisnickoIme, String lozinka, String ime, String prezime, Pol pol, String datumRodjenja, String sportskiobjekatId) {
		super(korisnickoIme, lozinka, ime, prezime, pol, datumRodjenja, Uloga.Menadzer);
		this.uloga = Uloga.Menadzer;
		this.sportskiObjekatId = sportskiobjekatId;
	}

	public SportskiObjekat getSportskiobjekat() {
		return sportskiobjekat;
	}

	public void setSportskiobjekat(SportskiObjekat sportskiobjekat) 
	{
		this.sportskiobjekat = sportskiobjekat;
	}
	
	
	@Override
	public String toSaveFormat() 
	{
		return this.getKorisnickoIme() + ";" + this.getSifra() + ";" + this.getIme() + ";" + this.getPrezime() + ";" + this.getPol().name() + ";" + this.getDatumRodjenja() + ";"+ this.sportskiObjekatId  + "\n";
	}

	public String getSportskiObjekatId() {
		return sportskiObjekatId;
	}

	public void setSportskiObjekatId(String sportskiObjekatId) {
		this.sportskiObjekatId = sportskiObjekatId;
	}


	

}
