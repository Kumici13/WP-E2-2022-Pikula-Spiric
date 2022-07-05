package beans;

import enums.TipTreninga;

public class Trening {
	
	private String id;
	private String naziv;
	private TipTreninga tip;
	private SportskiObjekat sportskiObjekat;
	private double trajanje;
	private Trener trener;
	private String opis;
	private String slika;
	
	public Trening()
	{
		
	}

	public Trening(String id,String naziv, TipTreninga tip, SportskiObjekat sportskiObjekat, double trajanje, Trener trener, String opis,
			String slika) {
		super();
		this.id = id;
		this.naziv = naziv;
		this.tip = tip;
		this.sportskiObjekat = sportskiObjekat;
		this.trajanje = trajanje;
		this.trener = trener;
		this.opis = opis;
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
	
	

}
