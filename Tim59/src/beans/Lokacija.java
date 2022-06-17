package beans;

public class Lokacija {
	
	private double geoDuzina;
	private double geoSirina;
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
	
	
	
	

}
