package beans;

import com.google.gson.annotations.Expose;

public class Adresa {

	@Expose
	private String ulica;

	@Expose
	private String broj;

	@Expose
	private String mesto;

	@Expose
	private int postanskiBroj;
	
	public Adresa() {}
	
	public Adresa(String ulica, String broj, String mesto, int postanskiBroj) {
		
		this.ulica = ulica;
		this.broj = broj;
		this.mesto = mesto;
		this.postanskiBroj = postanskiBroj;
	}

	public String getUlica() {
		return ulica;
	}

	public void setUlica(String ulica) {
		this.ulica = ulica;
	}

	public String getBroj() {
		return broj;
	}

	public void setBroj(String broj) {
		this.broj = broj;
	}

	public String getMesto() {
		return mesto;
	}

	public void setMesto(String mesto) {
		this.mesto = mesto;
	}

	public int getPostanskiBroj() {
		return postanskiBroj;
	}

	public void setPostanskiBroj(int postanskiBroj) {
		this.postanskiBroj = postanskiBroj;
	}

	@Override
	public String toString() {
		return  ulica + "," + broj + "," + mesto + "," + postanskiBroj;
	}

}
