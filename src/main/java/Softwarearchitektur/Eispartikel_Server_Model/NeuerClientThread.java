package Softwarearchitektur.Eispartikel_Server_Model;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Diese Klasse dient der Bearbeitung einer neuen Verbindung in einem Thread.
 * 
 * @author Mario Kaulmann
 * 
 */
public class NeuerClientThread extends Thread {
	/*
	 * Socket fuer die Clientverbindung
	 */
	private Socket clientVerbindung;
	/*
	 * Serverobjekt, das den Thread startet
	 */
	private ServerKern server;

	/**
	 * Konstruktor fuer einen Thread, der eine neue Verbindung zu einem Client
	 * behandelt.
	 * 
	 * @param clientVerbindung
	 *            Socket fuer die Clientverbindung
	 * @param server
	 *            Serverinstanz, die den Thread startet
	 */
	public NeuerClientThread(Socket clientVerbindung, ServerKern server) {
		this.server = server;
		this.clientVerbindung = clientVerbindung;
	}

	/*
	 * In der run-Methode des Threads werden alle aktuell verfuegbaren Stationen
	 * an den Client uebermittelt.Ausserdem wird die Verbindung gespeichert um
	 * dem Client die neuen Stationen zu uebermitteln.
	 */
	public void run() {
		try {
			ObjectOutputStream sender = new ObjectOutputStream(
					clientVerbindung.getOutputStream());
			for (String key : server.getStationen().keySet()) {
				sender.writeObject(server.getStationen().get(key));
			}
			server.getVerbindungen().add(sender);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
