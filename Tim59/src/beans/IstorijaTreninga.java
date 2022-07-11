package beans;

import java.util.Date;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.google.gson.annotations.Expose;

public class IstorijaTreninga 
{
	@Expose
	private String id;	
	@Expose
	private String datumVreme;
	@Expose
	private String treningId;
	private Trening trening;
	@Expose
	private String kupacId;
	private Kupac kupac;
	@Expose
	private boolean active = true;
	
	private Trener trener;
	private SportskiObjekat sportskiObjekat;
	
	public IstorijaTreninga()
	{
		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDatumVreme() {
		return datumVreme;
	}

	public void setDatumVreme(String datumVreme) {
		this.datumVreme = datumVreme;
	}

	public String getTreningId() {
		return treningId;
	}

	public void setTreningId(String treningId) {
		this.treningId = treningId;
	}

	public String getKupacId() {
		return kupacId;
	}

	public void setKupacId(String kupacId) {
		this.kupacId = kupacId;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public IstorijaTreninga(String id, String datumVreme, String treningId, String kupacId, boolean active) {
		super();
		this.id = id;
		this.datumVreme = datumVreme;
		this.treningId = treningId;
		this.kupacId = kupacId;
		this.active = active;
	}

	public IstorijaTreninga(String id, String datumVreme, String treningId, Trening trening, String kupacId,
			Kupac kupac, Trener trener, SportskiObjekat sportskiObjekat, boolean active) {
		super();
		this.id = id;
		this.datumVreme = datumVreme;
		this.treningId = treningId;
		this.trening = trening;
		this.kupacId = kupacId;
		this.kupac = kupac;
		this.trener = trener;
		this.sportskiObjekat = sportskiObjekat;
		this.active = active;
	}

	public Trening getTrening() {
		return trening;
	}

	public void setTrening(Trening trening) {
		this.trening = trening;
	}

	public Kupac getKupac() {
		return kupac;
	}

	public void setKupac(Kupac kupac) {
		this.kupac = kupac;
	}

	public Trener getTrener() {
		return trener;
	}

	public void setTrener(Trener trener) {
		this.trener = trener;
	}

	public SportskiObjekat getSportskiObjekat() {
		return sportskiObjekat;
	}

	public void setSportskiObjekat(SportskiObjekat sportskiObjekat) {
		this.sportskiObjekat = sportskiObjekat;
	}

	public String toSaveFormat() 
	{

		Gson gson =  new GsonBuilder().excludeFieldsWithoutExposeAnnotation().registerTypeAdapter(Date.class, (JsonDeserializer) (json, typeOfT, context) -> new Date(json.getAsLong())).create();
		
		return gson.toJson(this)+"\n";
	}

	
	

}
