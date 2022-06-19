package beans;

import enums.TipObjektaEnum;

public class SportskiObjekat {
	
	private String id;
	private String naziv;
	private Boolean status;
	private TipObjektaEnum tipObjekta;
	private String[] sadrzaj;
	private Lokacija lokacija;
	private String slika;
	private double prosecnaOcena;
	private String radnoVreme;
	
	public SportskiObjekat()
	{
		
	}
	
	

	public SportskiObjekat(String id, String naziv, Boolean status, TipObjektaEnum tipObjekta, String[] sadrzaj,
			Lokacija lokacija, String slika, double prosecnaOcena, String radnoVreme) {
		super();
		this.id = id;
		this.naziv = naziv;
		this.status = status;
		this.tipObjekta = tipObjekta;
		this.sadrzaj = sadrzaj;
		this.lokacija = lokacija;
		this.slika = slika;
		this.prosecnaOcena = prosecnaOcena;
		this.radnoVreme = radnoVreme;
	}



	public String getId() {
		return id;
	}

	public String getSadrzajText()
	{
		String s = "";
		for(int i = 0; i < sadrzaj.length;i++)
		{
			s+= sadrzaj[0] + " ";
		}
		
		return s;
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



	public Boolean getStatus() {
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



	public String[] getSadrzaj() {
		return sadrzaj;
	}



	public void setSadrzaj(String[] sadrzaj) {
		this.sadrzaj = sadrzaj;
	}



	public Lokacija getLokacija() {
		return lokacija;
	}



	public void setLokacija(Lokacija lokacija) {
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
	
	@Override
	public String toString()
	{
		return this.naziv + " - " + this.sadrzaj[0].toString() + " - " + this.tipObjekta +  " - " +prosecnaOcena;
		
	}
	
	
}
