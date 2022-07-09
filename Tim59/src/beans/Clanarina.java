package beans;

import java.time.LocalDate;
import java.util.Date;

import enums.TipClanarine;

public class Clanarina {
	
	private String identifikator;
	private TipClanarine tipClanarine;
	private String datumPlacanja;
	private String datumVazenja;
	private double cena;
	private Kupac kupac;
	private String kupacid;
	private boolean status;
	private int brojTermina;
	
	public Clanarina()
	{
		
	}

	public Clanarina(String identifikator, TipClanarine tipClanarine, String datumPlacanja, String datumVazenja, double cena,
			String kupacid, boolean status, int brojTermina) {
		super();
		this.identifikator = identifikator;
		this.tipClanarine = tipClanarine;
		this.datumPlacanja = datumPlacanja;
		this.datumVazenja = datumVazenja;
		this.cena = cena;
		this.kupacid = kupacid;
		this.status = status;
		this.brojTermina = brojTermina;
	}

	public Clanarina(int i, String string, LocalDate now, LocalDate now2, int j, String string2, boolean b, int k) {
		super();
		
	}

	public String getIdentifikator() {
		return identifikator;
	}

	public void setIdentifikator(String identifikator) {
		this.identifikator = identifikator;
	}

	public TipClanarine getTipClanarine() {
		return tipClanarine;
	}

	public void setTipClanarine(TipClanarine tipClanarine) {
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

	public Kupac getKupac() {
		return kupac;
	}

	public void setKupac(Kupac kupac) {
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

	public String getKupacid() {
		return kupacid;
	}

	public void setKupacid(String kupacid) {
		this.kupacid = kupacid;
	}
	
	
	
	

}
