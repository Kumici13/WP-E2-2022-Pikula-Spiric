package beans;

import enums.TipObjektaEnum;

public class SportskiObjekat {
	
	private String id;
	private String naziv;
	private Boolean status;
	private TipObjektaEnum tipObjekta;
	private String sadrzaj;
	private Lokacija lokacija;
	//private String slika;  Obrisao sliku jer ne znam sta sa njom u htmlu(za sad)
	private double prosecnaOcena;
	private String radnoVreme;
	
	public SportskiObjekat()
	{
		
	}
	
	public SportskiObjekat(String id, String naziv, Boolean status, TipObjektaEnum tipObjekta, String sadrzaj, Lokacija lokacija,  Double prosecnaOcena, String radnoVreme)
	{
		this.id = id;
		this.naziv = naziv;
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

	public Boolean isStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public TipObjektaEnum getTipObjekta() {
		return tipObjekta;
	}

	public void setTipObjekta(TipObjektaEnum tipObjekta) {
		this.tipObjekta = tipObjekta;
	}

	public String getSadrzaj() {
		return sadrzaj;
	}

	public void setSadrzaj(String sadrzaj) {
		this.sadrzaj = sadrzaj;
	}

	public Lokacija getLokacija() {
		return lokacija;
	}

	public void setLokacija(Lokacija lokacija) {
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
		return naziv;
	}

	public void setName(String name) {
		this.naziv = name;
	}

	public Boolean getStatus() {
		return status;
	}

	
	
	
}
