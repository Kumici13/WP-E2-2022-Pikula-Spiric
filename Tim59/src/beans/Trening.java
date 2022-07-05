package beans;

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
	private String slika;
	
	public Trening()
	{
		
	}

	public Trening(String id,String naziv, TipTreninga tip, String sportskiObjekatid, double trajanje, String trenerid, String opis,
			String slika) {
		super();
		this.id = id;
		this.naziv = naziv;
		this.tip = tip;
		this.trajanje = trajanje;
		this.trenerid = trenerid;
		this.opis = opis;
		this.sportskiObjekatid = sportskiObjekatid;
		this.slika = slika;
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
	
	
	

}
