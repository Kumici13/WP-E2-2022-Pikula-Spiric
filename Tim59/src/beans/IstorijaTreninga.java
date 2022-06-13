package beans;

public class IstorijaTreninga {
	
	private String datumVreme;
	private Trening trening;
	private String kupac;
	private String trener;
	
	public IstorijaTreninga()
	{
		
	}

	public IstorijaTreninga(String datumVreme, Trening trening, String kupac, String trener) {
		super();
		this.datumVreme = datumVreme;
		this.trening = trening;
		this.kupac = kupac;
		this.trener = trener;
	}

	public String getDatumVreme() {
		return datumVreme;
	}

	public void setDatumVreme(String datumVreme) {
		this.datumVreme = datumVreme;
	}

	public Trening getTrening() {
		return trening;
	}

	public void setTrening(Trening trening) {
		this.trening = trening;
	}

	public String getKupac() {
		return kupac;
	}

	public void setKupac(String kupac) {
		this.kupac = kupac;
	}

	public String getTrener() {
		return trener;
	}

	public void setTrener(String trener) {
		this.trener = trener;
	}
	
	

}
