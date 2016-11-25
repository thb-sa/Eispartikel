package Softwarearchitektur.EispartikelTestClient;

import java.io.IOException;

import datenKlassen.Aenderungsmeldung;
import datenKlassen.NeuesObjektListener;
import datenKlassen.StationAenderung;
import datenKlassen.ZweiwegeClientkommunikator;

public class TestAenderung {

	public static void main(String args[]) {
		try {
			ZweiwegeClientkommunikator<Aenderungsmeldung, StationAenderung> zCK = new ZweiwegeClientkommunikator<Aenderungsmeldung, StationAenderung>(
					"127.0.0.1", new NeuesObjektListener<Aenderungsmeldung>() {

						public void neuesAustauschobjekt(
								Aenderungsmeldung austauschobjekt) {
							System.out.println(austauschobjekt.getAbweichung());
						}
					}, ZweiwegeClientkommunikator.ZWEIWEGEKOMMUNIKATION);

			zCK.start();

			zCK.versende(new StationAenderung(args[0], args[1], 50));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
