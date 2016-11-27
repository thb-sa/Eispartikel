package Softwarearchitektur.Eispartikel_Server_Model;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.concurrent.ConcurrentHashMap;

import datenKlassen.Station;
import datenKlassen.Tageswerte;

/**
 * Diese Klasse ist der Grundbaustein des Servers. Hier werden alle langlebigen
 * Objekte verwaltet.
 * 
 * @author Mario Kaulmann
 * 
 */
public class ServerKern extends Thread implements Stationenverwalter,
		Kommunikationsverwalter<Station> {
	/*
	 * Liste der Stationen
	 */
	private ConcurrentHashMap<String, Station> stationen;

	/*
	 * Liste der Verbindungen zu den Clients
	 */
	private ConcurrentHashMap<Socket, ObjectOutputStream> verbindungen;

	/**
	 * Konstruktor fuer einen Serverkern. Hier werden die langlebigen Objekte
	 * erzeugt.
	 */
	public ServerKern() {
		this.stationen = new ConcurrentHashMap<String, Station>();
		this.verbindungen = new ConcurrentHashMap<Socket, ObjectOutputStream>();
		StationGenerator sg = new StationGenerator(this);
		new AenderungsServer(this).start();
		sg.generiereStationen();
	}

	/*
	 * In der run-Methode werden neue Verbindungen angenommen und
	 * Clientbearbeitungsthreads erzeugt.
	 */
	public void run() {
		ServerSocket serverSocket = null;
		Socket newConnection = null;
		try {
			serverSocket = new ServerSocket(7000);

			while (true) {
				newConnection = serverSocket.accept();
				Thread newThread = new NeuerClientThread(newConnection, this);
				newThread.start();
			}
		} catch (IOException e) {
			System.err.println("Verbindung unterbrochen" + e);
			System.exit(1);
		} finally {

		}
	}

	public ConcurrentHashMap<String, Station> getStationen() {
		return stationen;
	}

	public void setStationen(ConcurrentHashMap<String, Station> stationen) {
		this.stationen = stationen;
	}

	public void fuegeVerbindunghinzu(Socket socket) throws IOException {
		verbindungen.put(socket,
				new ObjectOutputStream(socket.getOutputStream()));
		for (String schluessel : stationen.keySet()) {
			try {
				versende(stationen.get(schluessel), socket);
			} catch (IOException e) {
				verbindungen.remove(socket);
				e.printStackTrace();
			}
		}
	}

	public void versende(Station t, Socket s) throws IOException {
		verbindungen.get(s).writeObject(t);
	}

	public boolean fuegeStationHinzu(String name, int vorgabewert) {
		boolean hinzugefuegt;
		/*
		 * Das Hinzufuegen wird nur dann ausgefuehrt, wenn nicht bereits eine
		 * Station mit dem entsprechenden Namen bereits existiert
		 */
		if (!stationen.containsKey(name)) {
			Station station = new Station(name, vorgabewert);
			stationen.put(name, station);
			hinzugefuegt = true;
			/*
			 * Versenden der neuen station an alle angemeldeten Clients. Merken
			 * der Verbindungen, die nicht mehr funktionieren zum Entfernen.
			 */
			LinkedList<Socket> alteVerbindungen = new LinkedList<Socket>();
			for (Socket s : verbindungen.keySet()) {
				try {
					versende(station, s);
				} catch (IOException e) {
					System.out.println("Eine Clientverbindung wurde entfernt.");
					alteVerbindungen.add(s);
				}
			}
			/*
			 * Entfernen der alten Verbindungen.
			 */
			for (Socket s : alteVerbindungen) {
				entferneverbindung(s);
			}
		} else {
			hinzugefuegt = false;
		}
		return hinzugefuegt;
	}

	public void aendereWert(String stationID, String datum, int wert,
			int abweichung, int relativeAbweichung) {
		/*
		 * Falls vorhanden wird der Wert ueberschrieben, der eingetragen ist.
		 */
		stationen
				.get(stationID)
				.getAktuelleWerte()
				.put(datum,
						new Tageswerte(wert, abweichung, relativeAbweichung));
		System.out.println("Werte Empfangen: " + stationID + ": " + datum
				+ " --> " + wert);
	}

	public int berechneDifferenz(String stationID, int aktuellerWert) {
		return aktuellerWert - stationen.get(stationID).getVorgabewert();
	}

	public void entferneverbindung(Socket socket) {
		verbindungen.remove(socket);

	}

	public int berechneRelativeAbweichung(String staionID, int abweichung) {
		return (int) ((float) abweichung
				/ stationen.get(staionID).getVorgabewert() * 100f);
	}
}
