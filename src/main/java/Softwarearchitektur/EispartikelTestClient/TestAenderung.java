package Softwarearchitektur.EispartikelTestClient;

import java.io.IOException;

import datenKlassen.Aenderungsmeldung;
import datenKlassen.StationAenderung;
import funktionaleKlassen.NeuesObjektListener;
import funktionaleKlassen.ZweiwegeClientkommunikator;

/**
 * Diese Klasse dient als Beispiel zur Implementierung der Verbindung zum Server
 * auf der alle aktuellen Werte versendet werden.
 * 
 * @author Mario Kaulmann
 * 
 */
public class TestAenderung {

	public static void main(String args[]) {
		try {
			/*
			 * Hier muss der ZweiwegeClientkommunikator angewendet werden. Der
			 * Konstruktor hat als Parameter die IP des Servers, die
			 * Implementierung des Listeners fuer empfangene Objekte und die
			 * Portnummer ueber die gearbeitet wird. Die Portnummer ist in der
			 * Kommunikatorklasse bereits als Konstante hinterlegt. In den
			 * spitzen Klammern die Typen sind zuerst die Empfangenen und dann
			 * die zu versendenden Typen.
			 */
			ZweiwegeClientkommunikator<Aenderungsmeldung, StationAenderung> zCK = new ZweiwegeClientkommunikator<Aenderungsmeldung, StationAenderung>(
					"54.89.87.213", new NeuesObjektListener<Aenderungsmeldung>() {
						/*
						 * Anonyme Implementierung des Interfaces: kein guter
						 * Stil, aber nur ein Beispiel
						 */
						public void neuesAustauschobjekt(
								Aenderungsmeldung austauschobjekt) {
							System.out.println(austauschobjekt.getTageswerte().getAbweichung());
						}
					}, ZweiwegeClientkommunikator.ZWEIWEGEKOMMUNIKATION);

			/*
			 * Sehr wichtig: das Starten des Threads nicht vergessen
			 */
			zCK.start();

			/*
			 * Hier wird jetzt beispielsweise eine StationsAenderung versendet.
			 */
			zCK.versende(new StationAenderung(args[0], args[1], 76));

			/*
			 * Der Client lauft weiter, da er immernoch mithoert, was es fuer
			 * Aenderungen gibt.
			 */
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
