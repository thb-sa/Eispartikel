package Softwarearchitektur.Eispartikel_Server_Model;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.LinkedList;
import java.util.Random;

/**
 * Diese Klasse simuliert das hinzugefuegt werden von neuen Stationen, indem
 * zufaellig neue Stationen erzeugt werden.
 * 
 * @author Mario Kaulmann
 * 
 */
public class StationGenerator {
	// HashMap mit den Staionen
	private ServerKern server;

	/**
	 * Konstruktor des Stationgenerators.
	 * 
	 * @param server
	 *            Serverobjekt fuer das die Stationen generiert werden.
	 */
	StationGenerator(ServerKern server) {
		this.server = server;
	}

	/**
	 * Diese Methode startet einen Thread, der zufaellig neue Stationen
	 * generiert und so lange aktiv ist, wie der Server. Initial werden einige
	 * Stationen erzeugt.
	 */
	public void generiereStationen() {
		Thread t = new Thread() {
			public void run() {
				Random verzoegerung = new Random();
				Random vorgabe = new Random();
				Random zusatz = new Random();
				/*
				 * Anzahl der bei der Initialisierung zu erzeugenden Stationen.
				 */
				int anzahlInit = 20;
				/*
				 * Variable zum Durchgehen des Staedte-Arrays
				 */
				int i = 0;
				/*
				 * Array mit Stadtnamen
				 */
				String[] staedte = { "Berlin", "Dresden", "Potsdam",
						"Magdeburg", "Brandenburg", "Werder", "Leipzig",
						"Wildau", "Hannover", "Hamburg", "Bremen", "Dortmund",
						"Bielefeld", "Frankfurt" };
				/*
				 * Array mit allen Namen, die bereits erzeugt wurden
				 */
				LinkedList<String> vorhanden = new LinkedList<String>();
				while (true) {
					try {
						/*
						 * Erzeuge Wartezeiten von 30 bis 180 Sekunden
						 */
						if (anzahlInit == 0) {
							sleep(1000 * verzoegerung.nextInt(180 - 30) + 30);
						} else {
							anzahlInit--;
						}
						String name = staedte[i] + " "
								+ (zusatz.nextInt(999) + 1);
						if (!vorhanden.contains(name)) {
							Station s = new Station(name, vorgabe.nextInt(100));
							vorhanden.add(name);
							server.getStationen().put(name, s);
							for (ObjectOutputStream o : server
									.getVerbindungen()) {
								try {
									o.writeObject(s);
								} catch (IOException e) {
									server.getVerbindungen().remove(o);
									e.printStackTrace();
								}
							}
						}
						i = (i + 1) % staedte.length;
						System.out.println("Generated: " + name);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		};
		t.start();
	}
}
