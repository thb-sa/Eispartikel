package Softwarearchitektur.EispartikelTestClient;

import java.io.IOException;
import java.net.UnknownHostException;

import datenKlassen.Station;
import funktionaleKlassen.EinwegClientkommunikator;
import funktionaleKlassen.NeuesObjektListener;

/**
 * Diese Klasse dient als Beispiel dafuer, wie eine Verbindung zum Server
 * hergestellt werden kann. Hier geht es um die Verbindung zum Server um die
 * Stationen zu beziehen.
 * 
 * @author Mario Kaulmann
 * 
 */
public class TestAnmeldung {

	public static void main(String args[]) {
		try {
			/*
			 * Implementierung der Inneren Klasse siehe unten.
			 */
			TestAnmeldung.KomTestimplements listener = new KomTestimplements();

			/*
			 * Benutzung des Einwegclients. Uebergabewerte: IP des Servers,
			 * Objekt, das den Listener implementiert und der Port, der in der
			 * Klasse allerdings als Konstante hinterlegt ist.
			 */
			EinwegClientkommunikator<Station> ew = new EinwegClientkommunikator<Station>(
					"54.89.87.213", listener,
					EinwegClientkommunikator.EINWEGKOMMUNIKATION);
			/*
			 * Sehr wichtig: den Thread starten.
			 */
			ew.start();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Diese innere Klasse dient nur dazu das Interface zu implementieren und
	 * ist kein Beispiel fuer guten Stil. Der Typ, der in den spitzen Klammern
	 * eingetragen wird ist der Typ, den ich als zu empfangenes Objekt erwarte.
	 * 
	 * @author Mario Kaulmann
	 * 
	 */
	private static class KomTestimplements implements
			NeuesObjektListener<Station> {
		/*
		 * Implementierung der Methode aus dem Interface. Im Hintergrund lauft
		 * der Thread, der immer neue stationen empfaengt. Deshalb ist es
		 * notwendig die Implementierung so vorzunehmen, dass keine
		 * Zugriffskonflikte entstehen koennen.
		 */
		public void neuesAustauschobjekt(Station station) {
			/*
			 * Die Station, die uns neu uebergeben wurde wird hier nur
			 * ausgegeben.
			 */
			System.out.println(station.getStationID() + " "
					+ station.getVorgabewert());
			/*
			 * Hier werden falls vorhanden die Aktuellen Tageswerte ausgegeben.
			 */
			for (String key : station.getAktuelleWerte().keySet()) {
				System.out.println("\t"
						+ key
						+ " "
						+ station.getAktuelleWerte().get(key)
								.getAktuellerWert()
						+ " / "
						+ station.getAktuelleWerte().get(key).getAbweichung()
						+ " ==> "
						+ station.getAktuelleWerte().get(key)
								.getRelativeAbweichung());
			}
		}
	}
}
