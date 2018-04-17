package gdi.jp01;

public class Inhaber
{
    private String vorname;
    private String nachname;
    private String adresse;
    /**
     * Konstruker zum anlegen eines Inhabers
     * @param String vorname String nachname String adresse
     */
    Inhaber(String vorname, String nachname, String adresse) //Konstruktor von Inhaber
    {
        this.vorname = vorname;
        this.nachname = nachname;
        this.adresse = adresse;
    }
    /*public String getVorname() //Ausgabe vom Vornamen
    {
    return vorname;
    }
    public String getNachname() //Ausgabe vom Nachnamen
    {
    return nachname;
    }
    public String getAdresse() //Ausgabe von der Adresse
    {
    return adresse;
    }*/
}