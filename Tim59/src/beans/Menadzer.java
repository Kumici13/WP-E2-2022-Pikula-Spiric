package beans;

import java.util.Date;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.google.gson.annotations.Expose;

import enums.Pol;
import enums.Uloga;
import javaxt.utils.string;

public class Menadzer extends Korisnik {
	@Expose
	private String sportskiObjekatId="null";
	private SportskiObjekat sportskiobjekat;
	
	public Menadzer() {
		super();
		this.uloga = Uloga.Menadzer;
	}
	
	public Menadzer(String korisnickoIme, String lozinka, String ime, String prezime, Pol pol, String datumRodjenja, String sportskiobjekatId, boolean aktivan) {
		super(korisnickoIme, lozinka, ime, prezime, pol, datumRodjenja, Uloga.Menadzer, aktivan);
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
		Gson gson =  new GsonBuilder().excludeFieldsWithoutExposeAnnotation().registerTypeAdapter(Date.class, (JsonDeserializer) (json, typeOfT, context) -> new Date(json.getAsLong())).create();
		
		return gson.toJson(this)+"\n";
	}
	public String getSportskiObjekatId() {
		return sportskiObjekatId;
	}

	public void setSportskiObjekatId(String sportskiObjekatId) {
		this.sportskiObjekatId = sportskiObjekatId;
	}


	

}
