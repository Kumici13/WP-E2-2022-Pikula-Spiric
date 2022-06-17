package beans;

public class IstorijaTreninga {
	
	private String datumVreme;
	private Trening trening;
	private Kupac kupac;
	private Trener trener;
	
	public IstorijaTreninga()
	{
		
	}

	public IstorijaTreninga(String datumVreme, Trening trening, Kupac kupac, Trener trener) {
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

	public Kupac getKupac() {
		return kupac;
	}

	public void setKupac(Kupac kupac) {
		this.kupac = kupac;
	}

	public Trener getTrener() {
		return trener;
	}

	public void setTrener(Trener trener) {
		this.trener = trener;
	}
	
	

}
