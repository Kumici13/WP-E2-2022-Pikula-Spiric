package beans;

public class Trening {
	
	private String naziv;
	private String tip;
	private SportskiObjekat ime;
	private double trajanje;
	private String trener;
	private String opis;
	private String slika;
	
	public Trening()
	{
		
	}

	public Trening(String naziv, String tip, SportskiObjekat ime, double trajanje, String trener, String opis,
			String slika) {
		super();
		this.naziv = naziv;
		this.tip = tip;
		this.ime = ime;
		this.trajanje = trajanje;
		this.trener = trener;
		this.opis = opis;
		this.slika = slika;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getTip() {
		return tip;
	}

	public void setTip(String tip) {
		this.tip = tip;
	}

	public SportskiObjekat getIme() {
		return ime;
	}

	public void setIme(SportskiObjekat ime) {
		this.ime = ime;
	}

	public double getTrajanje() {
		return trajanje;
	}

	public void setTrajanje(double trajanje) {
		this.trajanje = trajanje;
	}

	public String getTrener() {
		return trener;
	}

	public void setTrener(String trener) {
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
