package dam2.add.p22.modelo;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class Ubicacion {

	@SerializedName("id")
	@Expose
	private String id;
	@SerializedName("nm")
	@Expose
	private String nm;
	
	/**
	* No args constructor for use in serialization
	*/
	public Ubicacion() {
	}
	
	/**
	* @param id
	* @param nm
	*/
	public Ubicacion(String id, String nm) {
	super();
	this.id = id;
	this.nm = nm;
	}
	
	public String getId() {
	return id;
	}
	
	public void setId(String id) {
	this.id = id;
	}
	
	public String getNm() {
	return nm;
	}
	
	public void setNm(String nm) {
	this.nm = nm;
	}
}
