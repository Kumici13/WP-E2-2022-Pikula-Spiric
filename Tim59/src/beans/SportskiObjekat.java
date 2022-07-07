package beans;

import java.io.File;
import java.nio.file.Files;
import java.util.Base64;

import enums.TipObjektaEnum;

public class SportskiObjekat 
{
	
	private String id;
	private String naziv;
	private Boolean status;
	private TipObjektaEnum tipObjekta;
	private String[] sadrzaj;
	private Lokacija lokacija;
	private String logo = "null";
	private String slika;
	private double prosecnaOcena;
	private String radnoVreme;
	
	public SportskiObjekat()
	{
		
	}
	
	

	public String getLogo() {
		return logo;
	}



	public void setLogo(String logo) 
	{
		this.logo = logo;
		if(logo != "null") 
		{
			this.slika = ucitajSliku("./static/Images/SportskiObjekat/" + this.logo);
		}
	}



	public SportskiObjekat(String id, String naziv, Boolean status, TipObjektaEnum tipObjekta, String[] sadrzaj,
			Lokacija lokacija, String logo, double prosecnaOcena, String radnoVreme) {
		super();
		this.id = id;
		this.naziv = naziv;
		this.status = status;
		this.tipObjekta = tipObjekta;
		this.sadrzaj = sadrzaj;
		this.lokacija = lokacija;
		this.logo = logo;
		this.prosecnaOcena = prosecnaOcena;
		this.radnoVreme = radnoVreme;
		this.slika = ucitajSliku("./static/Images/SportskiObjekat/" + this.logo);
	}



	public String getId() {
		return id;
	}

	public String getSadrzajText()
	{
		String s = "";
		for(int i = 0; i < sadrzaj.length;i++)
		{
			s+= sadrzaj[i] + " ";
		}
		
		return s;
	}
	
	public String getSadrzajSaveFormat()
	{
		String s = "";
		for(int i = 0; i < sadrzaj.length;i++)
		{
			if(i == 0)
			{
				s = sadrzaj[0];
			}
			else 
			{
				s+= "," + sadrzaj[i];
			}
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



	public String getSlika() 
	{
		return slika;
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
			System.out.println("Slika sportskog objekta: " + putanja + " nije pronadjen.\r\n");
			return null;
		}
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
	
	public String toSaveFormat()
	{
		return this.id+ ";" + this.naziv + ";" + this.status + ";"+ this.getTipObjekta().toString() + ";" + this.getSadrzajSaveFormat()+ ";" + this.lokacija.getAdresa().toString()+ ";"+this.lokacija.getGeoDuzina()+ ";"+ this.lokacija.getGeoSirina()+ ";" + this.logo+ ";" + this.prosecnaOcena+ ";"+this.radnoVreme+"\n";
	}
	
	
}
