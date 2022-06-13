package beans;

public class SportskiObjekat {
	
	private String naziv;
	private boolean status;
	private String tipObjekta;
	private String sadrzaj;
	private String lokacija;
	private String slika;
	private double prosecnaOcena;
	private String radnoVreme;
	
	public SportskiObjekat()
	{
		
	}
	
	public SportskiObjekat(String naziv, boolean status, String tipObjekta, String sadrzaj, String lokacija, String slika, Double prosecnaOcena, String radnoVreme)
	{
		this.naziv = naziv;
		this.status = status;
		this.tipObjekta = tipObjekta;
		this.sadrzaj = sadrzaj;
		this.lokacija = lokacija;
		this.slika = slika;
		this.prosecnaOcena = prosecnaOcena;
		this.radnoVreme = radnoVreme;
		
	}
	

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
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

	public String getSlika() {
		return slika;
	}

	public void setSlika(String slika) {
		this.slika = slika;
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

	
	
}
