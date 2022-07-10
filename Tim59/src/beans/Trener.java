package beans;

import java.util.Date;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.google.gson.annotations.Expose;

import enums.Pol;
import enums.Uloga;

public class Trener extends Korisnik{
	@Expose
	private String istorijaTreninga;
	
	public Trener() {
		super();
		this.uloga = Uloga.Trener;
	}
	
	public Trener(String korisnickoIme, String lozinka, String ime, String prezime, Pol pol, String datumRodjenja, String istorijaTreninga, boolean aktivan) {
		super(korisnickoIme, lozinka, ime, prezime, pol,datumRodjenja, Uloga.Trener, aktivan);
		this.uloga = Uloga.Trener;
		this.istorijaTreninga = istorijaTreninga;
	}

	public String getIstorijaTreninga() {
		return istorijaTreninga;
	}

	public void setIstorijaTreninga(String istorijaTreninga) {
		this.istorijaTreninga = istorijaTreninga;
	}
	
	@Override
	public String toSaveFormat() 
	{
		Gson gson =  new GsonBuilder().excludeFieldsWithoutExposeAnnotation().registerTypeAdapter(Date.class, (JsonDeserializer) (json, typeOfT, context) -> new Date(json.getAsLong())).create();
		
		return gson.toJson(this)+"\n";
	}

}
