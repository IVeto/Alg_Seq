package main;
/**
 * 
 * @author Axel Zieschank (210234303)
 * @author Dominik Wittig (212220939)
 */
import java.util.ArrayList;

import types.StringSequence;
/**
 * Moddeliert eine Liste von Beschreibung/Gen-Daten 
 * @author Axel Zieschank
 *
 */
public class FastaFile {
/**
 * ArrayList mit den StringSequence Objekten
 */
	private ArrayList<StringSequence> database = new ArrayList<StringSequence>();
	/**
	 * Liefert die ArrayList mit darin gespeicherten Fasta Daten.
	 * @return ArrayList mit Daten
	 */
	public ArrayList<StringSequence> getDataBase()
	{
		return database;
	}
	/**
	 * Speichert ein ArrayListe in dem database Atribut
	 * @param ArrayListe vom Typ StringSequence
	 */
	public void setDataBase(ArrayList<StringSequence> database)
	{
		this.database=database;
	}
	/**
	 * Methode die alle im Objekt gespeicherten Sequenzen ausgibt.
	 */
    public void printDataBase()
    {
    	for(int i=0;i<database.size();i++)
    	{
    		System.out.println("Description: " + database.get(i).getDescription());
    		System.out.println("Sequence: " + database.get(i).getSequence());
    		System.out.println("Pattern: " + database.get(i).getPattern());
    		System.out.println("Fundstellen: " + database.get(i).getFoundPositionList());
    	}
    }
    /**
     * Schreibt die Übergeben Beschreibung nd Sequenz in die Datenbank
     * @param Beschreibung - der Sequenze
     * @param Sequenze - DNA/RNA Sequenze
     */
    public void newSequence(String description,String sequence)
    {
    	StringSequence current = new StringSequence(description,sequence);
    	database.add(current);
    }
}
