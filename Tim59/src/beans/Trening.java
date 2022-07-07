package beans;

import java.io.File;
import java.nio.file.Files;
import java.util.Base64;

import enums.TipTreninga;

public class Trening {
	
	private String id;
	private String naziv;
	private TipTreninga tip;
	private SportskiObjekat sportskiObjekat;
	private String sportskiObjekatid;
	private double trajanje;
	private Trener trener;
	private String trenerid;
	private String opis;
	private String slikaNaziv = "null";
	private String slika;
	
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

	public Trening(String id,String naziv, TipTreninga tip, String sportskiObjekatid, double trajanje, String trenerid, String opis,
			String slikaNaziv) {
		super();
		this.id = id;
		this.naziv = naziv;
		this.tip = tip;
		this.trajanje = trajanje;
		this.trenerid = trenerid;
		this.opis = opis;
		this.sportskiObjekatid = sportskiObjekatid;
		setSlikaNaziv(slikaNaziv);
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

	public double getTrajanje() {
		return trajanje;
	}

	public void setTrajanje(double trajanje) {
		this.trajanje = trajanje;
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
		return getId()+";"+getNaziv()+";"+getTip()+";"+getSportskiObjekatid()+";"+getTrajanje()+";"+getTrenerid()+";"+getOpis()+";"+getSlikaNaziv()+"\n";
	}
	
	

}
