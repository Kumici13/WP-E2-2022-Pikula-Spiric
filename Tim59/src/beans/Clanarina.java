package beans;

public class Clanarina {
	
	private String identifikator;
	private String tipClanarine;
	private String datumPlacanja;
	private String datumVazenja;
	private double cena;
	private String kupac;
	private boolean status;
	private int brojTermina;
	
	public Clanarina()
	{
		
	}

	public Clanarina(String identifikator, String tipClanarine, String datumPlacanja, String datumVazenja, double cena,
			String kupac, boolean status, int brojTermina) {
		super();
		this.identifikator = identifikator;
		this.tipClanarine = tipClanarine;
		this.datumPlacanja = datumPlacanja;
		this.datumVazenja = datumVazenja;
		this.cena = cena;
		this.kupac = kupac;
		this.status = status;
		this.brojTermina = brojTermina;
	}

	public String getIdentifikator() {
		return identifikator;
	}

	public void setIdentifikator(String identifikator) {
		this.identifikator = identifikator;
	}

	public String getTipClanarine() {
		return tipClanarine;
	}

	public void setTipClanarine(String tipClanarine) {
		this.tipClanarine = tipClanarine;
	}

	public String getDatumPlacanja() {
		return datumPlacanja;
	}

	public void setDatumPlacanja(String datumPlacanja) {
		this.datumPlacanja = datumPlacanja;
	}

	public String getDatumVazenja() {
		return datumVazenja;
	}

	public void setDatumVazenja(String datumVazenja) {
		this.datumVazenja = datumVazenja;
	}

	public double getCena() {
		return cena;
	}

	public void setCena(double cena) {
		this.cena = cena;
	}

	public String getKupac() {
		return kupac;
	}

	public void setKupac(String kupac) {
		this.kupac = kupac;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public int getBrojTermina() {
		return brojTermina;
	}

	public void setBrojTermina(int brojTermina) {
		this.brojTermina = brojTermina;
	}
	
	

}
