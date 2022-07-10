package beans;

import java.util.Date;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.google.gson.annotations.Expose;

public class IstorijaTreninga 
{
	@Expose
	private String id;
	@Expose
	private String datumVreme;
	@Expose
	private String treningId;
	@Expose
	private String kupacId;
	@Expose
	private String trenerId;
	
	public IstorijaTreninga()
	{
		
	}

	public IstorijaTreninga(String id, String datumVreme, String treningId, String kupacId, String trenerId) {
		super();
		this.id = id;
		this.datumVreme = datumVreme;
		this.treningId = treningId;
		this.kupacId = kupacId;
		this.trenerId = trenerId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDatumVreme() {
		return datumVreme;
	}

	public void setDatumVreme(String datumVreme) {
		this.datumVreme = datumVreme;
	}

	public String getTreningId() {
		return treningId;
	}

	public void setTreningId(String treningId) {
		this.treningId = treningId;
	}

	public String getKupacId() {
		return kupacId;
	}

	public void setKupacId(String kupacId) {
		this.kupacId = kupacId;
	}

	public String getTrenerId() {
		return trenerId;
	}

	public void setTrenerId(String trenerId) {
		this.trenerId = trenerId;
	}

	public String toSaveFormat() 
	{

		Gson gson =  new GsonBuilder().excludeFieldsWithoutExposeAnnotation().registerTypeAdapter(Date.class, (JsonDeserializer) (json, typeOfT, context) -> new Date(json.getAsLong())).create();
		
		return gson.toJson(this)+"\n";
	}

	
	

}
