package Softwarearchitektur.Eispartikel_Server_Model;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Diese Klasse dient der Kommunikation mit den Clients um Aenderungen an den
 * Stationen zu verarbeiten.
 * 
 * @author Mario Kaulmann
 * 
 */
public class AenderungsServer extends Thread {
	private Stationenverwalter verwalter;

	public AenderungsServer(Stationenverwalter verwalter) {
		this.verwalter = verwalter;
	}

	/*
	 * In der run-Methode werden neue Verbindungen angenommen und
	 * Clientbearbeitungsthreads erzeugt.
	 */
	public void run() {
		ServerSocket serverSocket = null;
		Socket newConnection = null;
		try {
			serverSocket = new ServerSocket(7001);

			while (true) {
				newConnection = serverSocket.accept();
				Thread newThread = new AenderungsThread(newConnection,
						verwalter);
				newThread.start();
			}
		} catch (IOException e) {
			System.err.println("Verbindung unterbrochen" + e);
			System.exit(1);
		} finally {

		}
	}
}
