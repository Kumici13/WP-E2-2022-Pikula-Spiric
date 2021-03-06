package beans;

import java.util.Date;

import com.google.gson.annotations.Expose;


public class RadnoVreme 
{
	@Expose
	private Dan ponedeljakDan;
	@Expose
	private Dan utorakDan;
	@Expose
	private Dan sredaDan;
	@Expose
	private Dan cetvrtakDan;
	@Expose
	private Dan petakDan;
	@Expose
	private Dan subotaDan;
	@Expose
	private Dan nedeljaDan;
	
	
	
	public RadnoVreme(Dan ponedeljakDan, Dan utorakDan, Dan sredaDan, Dan cetvrtakDan, Dan petakDan, Dan subotaDan,
			Dan nedeljaDan) {
		super();
		this.ponedeljakDan = ponedeljakDan;
		this.utorakDan = utorakDan;
		this.sredaDan = sredaDan;
		this.cetvrtakDan = cetvrtakDan;
		this.petakDan = petakDan;
		this.subotaDan = subotaDan;
		this.nedeljaDan = nedeljaDan;
	}
	public Dan getPonedeljakDan() {
		return ponedeljakDan;
	}
	public void setPonedeljakDan(Dan ponedeljakDan) {
		this.ponedeljakDan = ponedeljakDan;
	}
	public Dan getUtorakDan() {
		return utorakDan;
	}
	public void setUtorakDan(Dan utorakDan) {
		this.utorakDan = utorakDan;
	}
	public Dan getSredaDan() {
		return sredaDan;
	}
	public void setSredaDan(Dan sredaDan) {
		this.sredaDan = sredaDan;
	}
	public Dan getCetvrtakDan() {
		return cetvrtakDan;
	}
	public void setCetvrtakDan(Dan cetvrtakDan) {
		this.cetvrtakDan = cetvrtakDan;
	}
	public Dan getPetakDan() {
		return petakDan;
	}
	public void setPetakDan(Dan petakDan) {
		this.petakDan = petakDan;
	}
	public Dan getSubotaDan() {
		return subotaDan;
	}
	public void setSubotaDan(Dan subotaDan) {
		this.subotaDan = subotaDan;
	}
	public Dan getNedeljaDan() {
		return nedeljaDan;
	}
	public void setNedeljaDan(Dan nedeljaDan) {
		this.nedeljaDan = nedeljaDan;
	}
	
}