package Softwarearchitektur.Eispartikel_Server_Model;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.concurrent.ConcurrentHashMap;

import datenKlassen.Aenderungsmeldung;

/**
 * Diese Klasse dient der Kommunikation mit den Clients um Aenderungen an den
 * Stationen zu verarbeiten.
 * 
 * @author Mario Kaulmann
 * 
 */
public class AenderungsServer extends Thread implements
		Kommunikationsverwalter<Aenderungsmeldung> {
	private Stationenverwalter verwalter;
	private ConcurrentHashMap<Socket, ObjectOutputStream> verbindungen;

	public AenderungsServer(Stationenverwalter verwalter) {
		this.verwalter = verwalter;
		verbindungen = new ConcurrentHashMap<Socket, ObjectOutputStream>();
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
				Thread newThread = new AenderungsThread(newConnection, this,
						verwalter);
				newThread.start();
			}
		} catch (IOException e) {
			System.err.println("Verbindung unterbrochen" + e);
			System.exit(1);
		} finally {

		}
	}

	public void fuegeVerbindunghinzu(Socket socket) throws IOException {
		verbindungen.put(socket,
				new ObjectOutputStream(socket.getOutputStream()));
	}

	public void versende(Aenderungsmeldung t, Socket s) throws IOException {
		LinkedList<Socket> alteVerbindungen = new LinkedList<Socket>();
		for (Socket socket : verbindungen.keySet()) {
			try {
				verbindungen.get(socket).writeObject(t);
			} catch (IOException io) {
				alteVerbindungen.add(socket);
				System.out
						.println("Ein Client wurde aus der Liste der aktiven Verbindungen entfernt.");
			}
		}
		for (Socket socket : alteVerbindungen) {
			verbindungen.remove(socket);
		}
	}

	public void entferneverbindung(Socket socket) {
		verbindungen.remove(socket);
	}
}
