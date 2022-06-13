package beans;

public class Komentar {
	
	private String imeKupca;
	private SportskiObjekat ime;
	private String tekst;
	private int ocena;
	
	public Komentar()
	{
		
	}

	public Komentar(String imeKupca, SportskiObjekat ime, String tekst, int ocena) {
		super();
		this.imeKupca = imeKupca;
		this.ime = ime;
		this.tekst = tekst;
		this.ocena = ocena;
	}

	public String getImeKupca() {
		return imeKupca;
	}

	public void setImeKupca(String imeKupca) {
		this.imeKupca = imeKupca;
	}

	public SportskiObjekat getIme() {
		return ime;
	}

	public void setIme(SportskiObjekat ime) {
		this.ime = ime;
	}

	public String getTekst() {
		return tekst;
	}

	public void setTekst(String tekst) {
		this.tekst = tekst;
	}

	public int getOcena() {
		return ocena;
	}

	public void setOcena(int ocena) {
		this.ocena = ocena;
	}
	
	

}
