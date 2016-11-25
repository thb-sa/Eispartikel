package Softwarearchitektur.Eispartikel_Server_Model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import datenKlassen.Aenderungsmeldung;
import datenKlassen.StationAenderung;

/**
 * Diese Klasse dient der Bearbeitung einer konkreten Aenderung an einer
 * Station.
 * 
 * @author Mario Kaulmann
 * 
 */
public class AenderungsThread extends Thread {
	/*
	 * Verbindung zum Client
	 */
	Socket verbindung;
	/*
	 * Verwaltung der Stationen
	 */
	Stationenverwalter verwalter;

	/**
	 * Konstruktor fuer einen Aenderungsthread mit der Verbindung und dem
	 * verwalter, bei dem etwas getan werden soll.
	 * 
	 * @param verbindung
	 *            , Verbindung zum Client
	 * @param verwalter
	 *            , Datenverwaltung
	 */
	public AenderungsThread(Socket verbindung, Stationenverwalter verwalter) {
		this.verbindung = verbindung;
		this.verwalter = verwalter;
	}

	/*
	 * In dieser Methode wird erreicht, dass die Werte neu gesetzt werden.
	 */
	public void run() {
		try {
			ObjectInputStream ois = new ObjectInputStream(
					verbindung.getInputStream());
			ObjectOutputStream out = new ObjectOutputStream(
					verbindung.getOutputStream());
			StationAenderung sa = (StationAenderung) ois.readObject();
			verwalter.aendereWert(sa.getName(), sa.getDatum(), sa.getWert());
			out.writeObject(new Aenderungsmeldung(sa.getName(), sa.getDatum(),
					verwalter.berechneDifferenz(sa.getName(), sa.getWert())));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
