package Softwarearchitektur.Eispartikel_Server_Model;

import java.io.Serializable;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Diese Klasse bildet eine Eispartikelmessstation im System ab.
 * 
 * @author Mario Kaulmann
 * 
 */
public class Station implements Serializable {
	/*
	 * Dieser Wert ist der eindeutige identifizierer einer Station im System
	 */
	private String stationID;
	/*
	 * Dieser Wert gibt an, wie hoch die Konzentration von Eispartikeln in der
	 * Luft sein soll
	 */
	private int vorgabewert;
	/*
	 * Diese Hashmap enthaelt alle einem Datum zugeordneten aktuellen Werte der
	 * Eispartikelkonzentrationen.
	 */
	private ConcurrentHashMap<Long, Integer> aktuelleWerte;

	/**
	 * Dieser Konstruktor wird genutzt um eine neue Station zum System
	 * hinzuzufuegen.
	 * 
	 * @param name
	 *            Name der Station
	 * @param vorgabewert
	 *            Vorgabe des Eispartikelwerts
	 */
	public Station(String stationID, int vorgabewert) {
		aktuelleWerte = new ConcurrentHashMap<Long, Integer>();
		setVorgabewert(vorgabewert);
		setStationID(stationID);
	}

	public String getStationID() {
		return stationID;
	}

	public void setStationID(String stationID) {
		this.stationID = stationID;
	}

	public int getVorgabewert() {
		return vorgabewert;
	}

	public void setVorgabewert(int vorgabewert) {
		this.vorgabewert = vorgabewert;
	}

	public ConcurrentHashMap<Long, Integer> getAktuelleWerte() {
		return aktuelleWerte;
	}

	public void setAktuelleWerte(ConcurrentHashMap<Long, Integer> aktuelleWerte) {
		this.aktuelleWerte = aktuelleWerte;
	}
}
