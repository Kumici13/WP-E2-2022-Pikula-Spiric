package beans;

public class TipKupca {
	
	private String ime;
	private double popust;
	private double brojBodova;
	
	public TipKupca()
	{
		
	}
	
	public TipKupca(String ime, double popust, double brojBodova) {
		super();
		this.ime = ime;
		this.popust = popust;
		this.brojBodova = brojBodova;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public double getPopust() {
		return popust;
	}

	public void setPopust(double popust) {
		this.popust = popust;
	}

	public double getBrojBodova() {
		return brojBodova;
	}

	public void setBrojBodova(double brojBodova) {
		this.brojBodova = brojBodova;
	}

}
