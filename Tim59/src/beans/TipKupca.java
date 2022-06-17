package beans;

import enums.TipKupcaEnum;

public class TipKupca {
	
	private TipKupcaEnum ime;
	private double popust;
	private double brojBodova;
	
	public TipKupca()
	{
		
	}
	
	public TipKupca(TipKupcaEnum ime, double popust, double brojBodova) {
		super();
		this.ime = ime;
		this.popust = popust;
		this.brojBodova = brojBodova;
	}

	public TipKupcaEnum getIme() {
		return ime;
	}

	public void setIme(TipKupcaEnum ime) {
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
