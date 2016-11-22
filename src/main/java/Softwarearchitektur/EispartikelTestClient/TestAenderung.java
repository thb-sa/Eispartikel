package Softwarearchitektur.EispartikelTestClient;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

import datenKlassen.StationAenderung;

public class TestAenderung {

	public static void main(String args[]) {
		Socket newConnection = null;

		try {
			newConnection = new Socket("127.0.0.1", 7001);
			ObjectOutputStream oos = new ObjectOutputStream(
					newConnection.getOutputStream());
			oos.writeObject(new StationAenderung(args[0], args[1], 50));
			newConnection.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
