package beans;

public class Dan 
{
	private String imeDana;
	private String danStart;
	private String danEnd;
	private boolean radniDan = true;
	
	
	
	public Dan(String imeDana, String danStart, String danEnd, boolean radniDan) {
		super();
		this.imeDana = imeDana;
		this.danStart = danStart;
		this.danEnd = danEnd;
		this.radniDan = radniDan;
	}
	public boolean isRadniDan() {
		return radniDan;
	}
	public void setRadniDan(boolean radniDan) {
		this.radniDan = radniDan;
	}
	public String getImeDana() {
		return imeDana;
	}
	public void setImeDana(String imeDana) {
		this.imeDana = imeDana;
	}
	public String getDanStart() {
		return danStart;
	}
	public void setDanStart(String danStart) {
		this.danStart = danStart;
	}
	public String getDanEnd() {
		return danEnd;
	}
	public void setDanEnd(String danEnd) {
		this.danEnd = danEnd;
	}
	
	
}
