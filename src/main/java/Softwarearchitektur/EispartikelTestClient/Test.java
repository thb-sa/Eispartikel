package Softwarearchitektur.EispartikelTestClient;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

import Softwarearchitektur.Eispartikel_Server_Model.Station;

public class Test {

	public static void main(String args[]) {
		Socket newConnection = null;

		try {
			newConnection = new Socket("127.0.0.1", 7000);
			ObjectInputStream ois = new ObjectInputStream(
					newConnection.getInputStream());
			 while (true){
			Station std = (Station) ois.readObject();
			 if(std == null) break;
			System.out.println(std.getStationID() + " " + std.getVorgabewert());
			 }
			newConnection.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
