package Softwarearchitektur.Eispartikel_Server_Model;

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
}
