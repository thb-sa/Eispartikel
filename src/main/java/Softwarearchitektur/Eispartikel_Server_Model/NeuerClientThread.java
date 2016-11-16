package Softwarearchitektur.Eispartikel_Server_Model;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class NeuerClientThread extends Thread {
	private Socket clientVerbindung;
	private ServerKern server;

	public NeuerClientThread(Socket clientVerbindung, ServerKern server) {
		this.server = server;
		this.clientVerbindung = clientVerbindung;
	}

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
