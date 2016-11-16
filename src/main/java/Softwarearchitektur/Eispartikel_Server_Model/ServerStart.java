package Softwarearchitektur.Eispartikel_Server_Model;

/**
 * Diese Klasse startet nur den Server.
 * 
 * @author Mario Kaulmann
 * 
 */
public class ServerStart {
	public static void main(String[] args) {
		/*
		 * Erzeugen des Servers und anschliessend starten.
		 */
		ServerKern server = new ServerKern();
		server.start();
	}
}
