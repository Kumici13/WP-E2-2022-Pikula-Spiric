package beans;

import com.google.gson.annotations.Expose;

public class Lokacija {

	@Expose
	private double geoDuzina;
	@Expose
	private double geoSirina;

	@Expose
	private Adresa adresa;
	
	public Lokacija()
	{
		
	}

	public Lokacija(double geoDuzina, double geoSirina, Adresa adresa) {
		super();
		this.geoDuzina = geoDuzina;
		this.geoSirina = geoSirina;
		this.adresa = adresa;
	}

	public double getGeoDuzina() {
		return geoDuzina;
	}

	public void setGeoDuzina(double geoDuzina) {
		this.geoDuzina = geoDuzina;
	}

	public double getGeoSirina() {
		return geoSirina;
	}

	public void setGeoSirina(double geoSirina) {
		this.geoSirina = geoSirina;
	}

	public Adresa getAdresa() {
		return adresa;
	}

	public void setAdresa(Adresa adresa) {
		this.adresa = adresa;
	}
	
	@Override
	public String toString()
	{
		return adresa.getUlica() + " " + adresa.getBroj() + ", " + adresa.getMesto();
	}

}
