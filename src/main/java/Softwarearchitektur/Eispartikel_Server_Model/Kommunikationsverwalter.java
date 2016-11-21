package Softwarearchitektur.Eispartikel_Server_Model;

import java.io.IOException;
import java.net.Socket;

/**
 * Dieses Interface dient der Entkopplung der Kommunikationselemente.
 * 
 * @author Mario Kaulmann
 * 
 */
public interface Kommunikationsverwalter<T> {

	/**
	 * Diese Methode dient dazu eine Verbindung zu uebergeben.
	 * 
	 * @param socket
	 *            Verbindung zu einem Client
	 * @throws IOException 
	 */
	public void fuegeVerbindunghinzu(Socket socket) throws IOException;

	/**
	 * Diese Methode ist zum Versenden eines Objekts bestimmt.
	 * 
	 * @param t
	 *            , Objekt das versendet werden soll
	 * @param s
	 *            , Socket, ueber das die Kommunikation laeuft
	 * @throws IOException
	 */
	public void versende(T t, Socket s) throws IOException;
}
