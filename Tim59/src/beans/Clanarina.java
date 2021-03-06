package beans;

import java.time.LocalDate;
import java.util.Date;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.google.gson.annotations.Expose;

import enums.TipClanarine;

public class Clanarina {

	@Expose
	private String identifikator;

	@Expose
	private TipClanarine tipClanarine;

	@Expose
	private String datumPlacanja;

	@Expose
	private String datumVazenja;

	@Expose
	private double cena;

	@Expose
	private String kupacid;

	@Expose
	private boolean status = true;

	@Expose
	private int brojTermina;
	
	public Clanarina()
	{
		
	}


	public Clanarina(String identifikator, TipClanarine tipClanarine, String datumPlacanja, String datumVazenja,
			         double cena,  String kupacid, boolean status, int brojTermina) {
		super();
		this.identifikator = identifikator;
		this.tipClanarine = tipClanarine;
		this.datumPlacanja = datumPlacanja;
		this.datumVazenja = datumVazenja;
		this.cena = cena;
		this.kupacid = kupacid;
		this.status = status;
		this.brojTermina = brojTermina;
	}


	public String getIdentifikator() {
		return identifikator;
	}

	public void setIdentifikator(String identifikator) {
		this.identifikator = identifikator;
	}

	public TipClanarine getTipClanarine() {
		return tipClanarine;
	}

	public void setTipClanarine(TipClanarine tipClanarine) {
		this.tipClanarine = tipClanarine;
	}

	

	public String getDatumPlacanja() {
		return datumPlacanja;
	}

	public void setDatumPlacanja(String datumPlacanja) {
		this.datumPlacanja = datumPlacanja;
	}

	public String getDatumVazenja() {
		return datumVazenja;
	}

	public void setDatumVazenja(String datumVazenja) {
		this.datumVazenja = datumVazenja;
	}

	public double getCena() {
		return cena;
	}

	public void setCena(double cena) {
		this.cena = cena;
	}

	public boolean isStatus() {
		return status;
	}

	public int setStatus(boolean status) 
	{
		int bodovi = 0;
		this.status = status;
		if(!this.status)
		{
			
			if(tipClanarine == TipClanarine.Godisnja)
			{
				if(brojTermina < 400)
				{
					bodovi = 50000 / 1000 * 600-brojTermina;
				}
				else 
				{
					bodovi = 50000 / (1000*133*4);
					bodovi *=-1;
				}
			}
			else if(tipClanarine == TipClanarine.Mesecna)
			{
				if(brojTermina < 11)
				{
					bodovi = 50000 / 1000 * 15-brojTermina;
				}
				else 
				{
					bodovi = 4500 / 1000*133*4;
					bodovi *=-1;
				}
			}
			else if(tipClanarine == TipClanarine.Nedeljna)
			{
				if(brojTermina < 4 )
				{
					bodovi = 50000 / 1000 * 4 -brojTermina;
				}
				else 
				{
					bodovi = 1500 / 1000*133*4;
					bodovi *=-1;
				}
			}
		}
		return bodovi;
	}

	public int getBrojTermina() {
		return brojTermina;
	}

	public void setBrojTermina(int brojTermina) {
		this.brojTermina = brojTermina;
	}

	public String getKupacid() {
		return kupacid;
	}

	public void setKupacid(String kupacid) {
		this.kupacid = kupacid;
	}


	public String toSaveFormat() 
	{
		Gson gson =  new GsonBuilder().excludeFieldsWithoutExposeAnnotation().registerTypeAdapter(Date.class, (JsonDeserializer) (json, typeOfT, context) -> new Date(json.getAsLong())).create();
		return gson.toJson(this)+"\n";
		
	}
	
	
	
	

}
