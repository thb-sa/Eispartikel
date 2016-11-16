package Softwarearchitektur.Eispartikel_Server_Model;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.concurrent.ConcurrentHashMap;

public class ServerKern extends Thread {
	/*
	 * Liste der Stationen
	 */
	private ConcurrentHashMap<String, Station> stationen;
	
	private LinkedList<ObjectOutputStream> verbindungen; 

	public ServerKern() {
		this.stationen = new ConcurrentHashMap<String, Station>();
		this.verbindungen = new LinkedList<ObjectOutputStream>();
		StationGenerator sg = new StationGenerator(this);
		sg.generiereStationen();
	}

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

	public LinkedList<ObjectOutputStream> getVerbindungen() {
		return verbindungen;
	}

	public void setVerbindungen(LinkedList<ObjectOutputStream> verbindungen) {
		this.verbindungen = verbindungen;
	}
}
