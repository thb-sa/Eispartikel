package Softwarearchitektur.Eispartikel_Server_Model;

import datenKlassen.Tageswerte;

/**
 * Dieses Interface sorgt dafuer, dass Methoden implementiert werden muessen,
 * die zum Verwalten einer Station notwendig sind, ohne dass die aufrufende
 * Klasse Wissen ueber eine Station besitzen muss.
 * 
 * @author Mario Kaulmann
 * 
 */
public interface Stationenverwalter {

	/**
	 * Diese Methode wird benoetigt, damit eine Station hinzugefuegt werden
	 * kann. Es koennen Abfragen implementiert werden, die das hinzufuegen
	 * verhindern. Der Rueckgabewert gibt Auskunft, ob die Station hinzugefuegt
	 * wurde.
	 * 
	 * @param name
	 *            , der Station
	 * @param vorgabewert
	 *            vorgegebener Wert fuer die Eispartikelkonzentration
	 * @return true, die Station wurde hinzugefuegt, false die Station wurde
	 *         nicht hinzugefuegt
	 */
	public boolean fuegeStationHinzu(String name, int vorgabewert);

	/**
	 * Aendere den aktuellen Wert fuer eine Station an einem Tag.
	 * 
	 * @param stationID
	 *            , ID der Station = Name der Station
	 * @param datum
	 *            , Datum fuer das der WErt eingetragen werden soll
	 * @param wert
	 *            , Wert der Eispartikelkonzentration
	 */
	public void aendereWert(String stationID, String datum, int wert,
			int abweichung, int relativeAbweichung, Tageswerte.Darstellung darstellung);

	/**
	 * Methode zum Berechnen der Abweichung eines Tageswerts.
	 * 
	 * @param stationID
	 *            , ID der zu betrachtenden Station
	 * @param aktuellerWert
	 *            , aktueller Wert zur Berechnung der Differenz
	 * @return Differenz aus aktuellerWert und Vorgabewert
	 */
	public int berechneDifferenz(String stationID, int aktuellerWert);

	/**
	 * Methode zum Berechnen der relativen Abweichung.
	 * 
	 * @param stationID
	 *            , ID der Station
	 * @param abweichung
	 *            , absoluter Wert der Abweichung (- [kleiner] / + [groesser])
	 * @return relative Abweichung, abgeschnitten nach dem Komma
	 */
	public int berechneRelativeAbweichung(String stationID, int abweichung);

	/**
	 * Methode zur Berechnung der Darstellung
	 *
	 * @param relativeAbweichung
	 *   		  , die relative Abweichung
	 * @return die Darstellung
	 */
    public Tageswerte.Darstellung berechneDarstellung(int relativeAbweichung);
}
