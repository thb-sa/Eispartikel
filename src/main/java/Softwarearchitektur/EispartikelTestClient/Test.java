package Softwarearchitektur.EispartikelTestClient;

import java.io.IOException;
import java.net.UnknownHostException;

import datenKlassen.EinwegClientkommunikator;
import datenKlassen.NeuesObjektListener;
import datenKlassen.Station;

public class Test {

	public static void main(String args[]) {
		try {
			Test.KomTestimplements listener = new KomTestimplements();
			EinwegClientkommunikator<Station> ew = new EinwegClientkommunikator<Station>(
					"127.0.0.1", listener,
					EinwegClientkommunikator.EINWEGKOMMUNIKATION);
			ew.start();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static class KomTestimplements implements
			NeuesObjektListener<Station> {
		public void neuesAustauschobjekt(Station station) {
			System.out.println(station.getStationID() + " "
					+ station.getVorgabewert());
			for (String key : station.getAktuelleWerte().keySet()) {
				System.out.println("\t"
						+ key
						+ " "
						+ station.getAktuelleWerte().get(key)
								.getAktuellerWert() + " / "
						+ station.getAktuelleWerte().get(key).getAbweichung());
			}

		}
	}
}
