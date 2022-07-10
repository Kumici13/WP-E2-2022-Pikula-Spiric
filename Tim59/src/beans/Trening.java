package beans;

import java.io.File;
import java.nio.file.Files;
import java.util.Base64;
import java.util.Date;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.google.gson.annotations.Expose;

import enums.TipTreninga;

public class Trening {
	@Expose
	private String id;
	@Expose
	private String naziv;
	@Expose
	private TipTreninga tip;
	private SportskiObjekat sportskiObjekat;
	@Expose
	private String sportskiObjekatid;
	@Expose
	private RadnoVreme radnoVreme;
	private Trener trener;
	@Expose
	private String trenerid;
	@Expose
	private String opis;
	@Expose
	private String slikaNaziv = "null";
	private String slika;
	@Expose
	private boolean aktivan = true;
	
	public Trening()
	{
		
	}

	public SportskiObjekat getSportskiObjekat() {
		return sportskiObjekat;
	}

	public void setSportskiObjekat(SportskiObjekat sportskiObjekat) {
		this.sportskiObjekat = sportskiObjekat;
	}

	public String getSlikaNaziv() {
		return slikaNaziv;
		
	}

	public void setSlikaNaziv(String slikaNaziv) {
		this.slikaNaziv = slikaNaziv;
		
		if(slikaNaziv != "null")
		{
			this.slika = ucitajSliku("./static/Images/Trening/" + this.slikaNaziv);
		}
	}

	public Trening(String id,String naziv, TipTreninga tip, String sportskiObjekatid, RadnoVreme radnoVreme, String trenerid, String opis,
			String slikaNaziv, boolean aktivan) {
		super();
		this.id = id;
		this.naziv = naziv;
		this.tip = tip;
		this.radnoVreme = radnoVreme;
		this.trenerid = trenerid;
		this.opis = opis;
		this.sportskiObjekatid = sportskiObjekatid;
		setSlikaNaziv(slikaNaziv);
		this.aktivan = aktivan;
	}

	
	public boolean isAktivan() {
		return aktivan;
	}

	public void setAktivan(boolean aktivan) {
		this.aktivan = aktivan;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public TipTreninga getTip() {
		return tip;
	}

	public void setTip(TipTreninga tip) {
		this.tip = tip;
	}

	public SportskiObjekat getIme() {
		return sportskiObjekat;
	}

	public void setIme(SportskiObjekat ime) {
		this.sportskiObjekat = ime;
	}

	public RadnoVreme getTrajanje() {
		return radnoVreme;
	}

	public void setTrajanje(RadnoVreme radnoVreme) {
		this.radnoVreme = radnoVreme;
	}

	public Trener getTrener() {
		return trener;
	}

	public void setTrener(Trener trener) {
		this.trener = trener;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public String getSlika() {
		return slika;
	}

	public void setSlika(String slika) {
		this.slika = slika;
	}

	public String getSportskiObjekatid() {
		return sportskiObjekatid;
	}

	public void setSportskiObjekatid(String sportskiObjekatid) {
		this.sportskiObjekatid = sportskiObjekatid;
	}

	public String getTrenerid() {
		return trenerid;
	}

	public void setTrenerid(String trenerid) {
		this.trenerid = trenerid;
	}
	private String ucitajSliku(String putanja)	
	{
		try 
		{
			File file = new File(putanja);
			String encodeImage = Base64.getEncoder().withoutPadding().encodeToString(Files.readAllBytes(file.toPath()));
			return encodeImage;
		} 
		catch (Exception e) 
		{
			System.out.println("Trening slika: "+putanja + " nije pronadjen.\r\n");
			return null;
		}
	}
	
	public String toSaveFormat()
	{
		Gson gson =  new GsonBuilder().excludeFieldsWithoutExposeAnnotation().registerTypeAdapter(Date.class, (JsonDeserializer) (json, typeOfT, context) -> new Date(json.getAsLong())).create();
	
		return gson.toJson(this)+"\n";
	}
	
	

}
