package jp02;

public class Inhaber
{
    private final String vorname;
    private final String nachname;
    private final String adresse;
    /**
	 * Konstruktor für Inhaber
	 * @param vorname Vornamen setzen
	 * @param nachname Nachnamen setzen
	 * @param adresse  Adresse setzen
	 */
	Inhaber(String vorname, String nachname, String adresse) //Konstruktor von Inhaber
    {
        this.vorname = vorname;
        this.nachname = nachname;
        this.adresse = adresse;
    }
	/**
	 * Rückgabe des Vornamens
	 * @return vorname
	 */
    public String getVorname() //Ausgabe vom Vornamen
    {
		return vorname;
    }
	/**
	 * Rückgabe des Nachnamens
	 * @return nachname
	 */
    public String getNachname() //Ausgabe vom Nachnamen
    {
		return nachname;
    }
	/**
	 * Rückgabe der Adresse
	 * @return adresse
	 */
    public String getAdresse() //Ausgabe von der Adresse
    {
		return adresse;
    }
}