package Softwarearchitektur.Eispartikel_Server_Model;

import java.io.Serializable;

/**
 * Dieses Objekt bildet eine Aenderung einer Station ab.
 * 
 * @author Mario Kaulmann
 * 
 */
public class StationAenderung implements Serializable{
	/*
	 * Der Name / ID der betroffenen Station.
	 */
	private String name;
	/*
	 * Datum an dem sich der wert geaendert hat.
	 */
	private String datum;
	/*
	 * Wert fuer das entsprechende Datum.
	 */
	private int wert;

	public StationAenderung(String name, String datum, int wert) {
		this.name = name;
		this.datum = datum;
		this.wert = wert;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDatum() {
		return datum;
	}

	public void setDatum(String datum) {
		this.datum = datum;
	}

	public int getWert() {
		return wert;
	}

	public void setWert(int wert) {
		this.wert = wert;
	}

}
