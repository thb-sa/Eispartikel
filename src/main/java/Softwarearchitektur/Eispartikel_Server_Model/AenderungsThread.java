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
	private Socket verbindung;
	/*
	 * Verwaltung der Stationen
	 */
	private Kommunikationsverwalter<Aenderungsmeldung> komVerwalter;

	private Stationenverwalter verwalter;

	/**
	 * Konstruktor fuer einen Aenderungsthread mit der Verbindung und dem
	 * verwalter, bei dem etwas getan werden soll.
	 * 
	 * @param verbindung
	 *            , Verbindung zum Client
	 * @param verwalter
	 *            , Datenverwaltung
	 */
	public AenderungsThread(Socket verbindung,
			Kommunikationsverwalter<Aenderungsmeldung> komVerwalter,
			Stationenverwalter verwalter) {
		this.verbindung = verbindung;
		this.komVerwalter = komVerwalter;
		this.verwalter = verwalter;
	}

	/*
	 * In dieser Methode wird erreicht, dass die Werte neu gesetzt werden.
	 * Ausserdem wird das Versenden der Aenderungen gestartet.
	 */
	public void run() {
		try {
			ObjectInputStream ois = new ObjectInputStream(
					verbindung.getInputStream());
			/*
			 * Hinzufuegen einer neuen Verbindung
			 */
			komVerwalter.fuegeVerbindunghinzu(verbindung);
			while (true) {
				StationAenderung sa = (StationAenderung) ois.readObject();
				/*
				 * Berechnung der Abweichung
				 */
				int abweichung = verwalter.berechneDifferenz(sa.getName(),
						sa.getWert());
				/*
				 * Berechnung der relativen Abweichung
				 */
				int relativeAbweichung = verwalter.berechneRelativeAbweichung(
						sa.getName(), abweichung);

				komVerwalter.versende(
						new Aenderungsmeldung(sa.getName(), sa.getDatum(),
								sa.getWert(), abweichung, relativeAbweichung), verbindung);
				/*
				 * Speichern des Wertes
				 */
				verwalter.aendereWert(sa.getName(), sa.getDatum(),
						sa.getWert(), abweichung, relativeAbweichung);
			}
		} catch (IOException e) {
			System.out.println("Ein Client hat sich abgemeldet.");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
