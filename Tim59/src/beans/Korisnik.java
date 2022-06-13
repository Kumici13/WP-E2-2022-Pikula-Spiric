package beans;

public class Korisnik {
	
	private String korisnickoIme;
	private String lozinka;
	private String ime;
	private String prezime;
	private String pol;
	private String datumRodjenja;
	private String uloga;
	private String clanarina;
	private String sportskiObjekat;
	private String poseceniObjekat;
	private int brojBodova;
	private TipKupca tipKupca;
	
	public Korisnik()
	{
		
	}
	
	public Korisnik(String korisnickoIme, String lozinka, String ime, String prezime, String pol, String datumRodjenja,
			String uloga, String clanarina, String sportskiObjekat, String poseceniObjekat, int brojBodova,
			TipKupca tipKupca) {
		super();
		this.korisnickoIme = korisnickoIme;
		this.lozinka = lozinka;
		this.ime = ime;
		this.prezime = prezime;
		this.pol = pol;
		this.datumRodjenja = datumRodjenja;
		this.uloga = uloga;
		this.clanarina = clanarina;
		this.sportskiObjekat = sportskiObjekat;
		this.poseceniObjekat = poseceniObjekat;
		this.brojBodova = brojBodova;
		this.tipKupca = tipKupca;
	}
	
	public String getKorisnickoIme() {
		return korisnickoIme;
	}

	public void setKorisnickoIme(String korisnickoIme) {
		this.korisnickoIme = korisnickoIme;
	}

	public String getLozinka() {
		return lozinka;
	}

	public void setLozinka(String lozinka) {
		this.lozinka = lozinka;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public String getPol() {
		return pol;
	}

	public void setPol(String pol) {
		this.pol = pol;
	}

	public String getDatumRodjenja() {
		return datumRodjenja;
	}

	public void setDatumRodjenja(String datumRodjenja) {
		this.datumRodjenja = datumRodjenja;
	}

	public String getUloga() {
		return uloga;
	}

	public void setUloga(String uloga) {
		this.uloga = uloga;
	}

	public String getClanarina() {
		return clanarina;
	}

	public void setClanarina(String clanarina) {
		this.clanarina = clanarina;
	}

	public String getSportskiObjekat() {
		return sportskiObjekat;
	}

	public void setSportskiObjekat(String sportskiObjekat) {
		this.sportskiObjekat = sportskiObjekat;
	}

	public String getPoseceniObjekat() {
		return poseceniObjekat;
	}

	public void setPoseceniObjekat(String poseceniObjekat) {
		this.poseceniObjekat = poseceniObjekat;
	}

	public int getBrojBodova() {
		return brojBodova;
	}

	public void setBrojBodova(int brojBodova) {
		this.brojBodova = brojBodova;
	}

	public TipKupca getTipKupca() {
		return tipKupca;
	}

	public void setTipKupca(TipKupca tipKupca) {
		this.tipKupca = tipKupca;
	}

}
