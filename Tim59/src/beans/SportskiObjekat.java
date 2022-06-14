package beans;

public class SportskiObjekat {
	
	private String id;
	private String name;
	private String status;
	private String tipObjekta;
	private String sadrzaj;
	private String lokacija;
	//private String slika;  Obrisao sliku jer ne znam sta sa njom u htmlu(za sad)
	private double prosecnaOcena;
	private String radnoVreme;
	
	public SportskiObjekat()
	{
		
	}
	
	public SportskiObjekat(String id, String name, String status, String tipObjekta, String sadrzaj, String lokacija,  Double prosecnaOcena, String radnoVreme)
	{
		this.id = id;
		this.name = name;
		this.status = status;
		this.tipObjekta = tipObjekta;
		this.sadrzaj = sadrzaj;
		this.lokacija = lokacija;
	//	this.slika = slika;
		this.prosecnaOcena = prosecnaOcena;
		this.radnoVreme = radnoVreme;
		
	}
	

	public String getId() {
		return id;
	}

	public void setId(String naziv) {
		this.id = naziv;
	}

	public String isStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTipObjekta() {
		return tipObjekta;
	}

	public void setTipObjekta(String tipObjekta) {
		this.tipObjekta = tipObjekta;
	}

	public String getSadrzaj() {
		return sadrzaj;
	}

	public void setSadrzaj(String sadrzaj) {
		this.sadrzaj = sadrzaj;
	}

	public String getLokacija() {
		return lokacija;
	}

	public void setLokacija(String lokacija) {
		this.lokacija = lokacija;
	}

	
	public double getProsecnaOcena() {
		return prosecnaOcena;
	}

	public void setProsecnaOcena(double prosecnaOcena) {
		this.prosecnaOcena = prosecnaOcena;
	}

	public String getRadnoVreme() {
		return radnoVreme;
	}

	public void setRadnoVreme(String radnoVreme) {
		this.radnoVreme = radnoVreme;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStatus() {
		return status;
	}

	
	
	
}
