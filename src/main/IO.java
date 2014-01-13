package main;
/**
 * 
 * @author Axel Zieschank (210234303)
 * @author Dominik Wittig (212220939)
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import types.StringSequence;
import types.Tree;
import types.TreeNode;
/**
 * Stell verschieden Ein und Ausgabe Methoden bereit
 * @author Axel Zieschank
 *
 */
public class IO {
	/**
	 * Liefert einen String den der User eingibt.
	 * @param Nachricht die dem Nutzer vor der Eingabe Angezeigt wird
	 * @return eingegebener String
	 */
	//user name input
    public static String userStringInput(String messageToUser)
    {
        String input = "";
        try
        {
		    BufferedReader buffer = new BufferedReader(new InputStreamReader   (System.in));
    		System.out.println(messageToUser);
            input = buffer.readLine();
        }
        catch(IOException ex)
        {
            System.out.println("Fehler bei Eingabe");
            System.out.println("clear \n");
        }
        return input;
    }
    
    public static int userIntegerInput(String messageToUser)
    {
        int input=-1;
        try
        {
		    BufferedReader buffer = new BufferedReader(new InputStreamReader   (System.in));
    		System.out.println(messageToUser);
            input = Integer.parseInt(buffer.readLine());
        }
        catch(IOException ex)
        {
            System.out.println("Fehler bei Eingabe");
            System.out.println("clear \n");
        }
        return input;
    }
    
    /**
     * Liest Datei im Fasta Format und speichert sie
     * in einer ArrayList vom Datentyo String
     * @param Dateiname
     * @return ArrayList mit Daten
     */
	//read data internal
    public static ArrayList<String> readFileContent(String filename)
    {   
        ArrayList <String> input = new ArrayList<String>();
        try
        {     
            FileReader fr = new FileReader(filename);
            BufferedReader buffreader = new BufferedReader(fr);
            String line;
            int counter=0;
            while ((line=buffreader.readLine()) != null)
            {
                input.add(line);
                counter++;
                if(counter%10000==0)
                {
                	System.out.println("Gelesene Zeilen: " + counter);
                }
                
            }
            buffreader.close();
        }
        catch (IOException e)
        {
            System.out.println("Fehler beim lesen der Datei!");
            System.exit(1);
        }
        System.out.println("Einlesen Abgeschlossen!");
        return input;
    }
    /**
     * Formatiert die eingebene ArrayListe vom Typ String zu einem ArrayList vom Typ StringSequence
     * @param ArrayListe vom Typ String
     * @return ArrayList vom Typ StringSequence
     */
    public static ArrayList<StringSequence> formatToStringSequence(ArrayList<String> input)
    {
        ArrayList <StringSequence> output = new ArrayList<StringSequence>();
    	String description= "";
    	StringBuffer currentSequence = new StringBuffer();
        //String sequence="";
        for(int i=0;i<input.size();i++)
        {
        	if(input.get(i).contains(">"))
        	{
        		description=input.get(i);
        		while(input.get(i+1).contains(">")==false)
        		{
        			i++;
        			currentSequence.append(input.get(i));
        			//sequence=sequence.concat(input.get(i)); //= sequence +input.get(i);
        			if(i%10000==0)
        			{
        				System.out.println("Formatierte Zeilen: " +i);
        			}
        			if(i==input.size()-1)
        			{
        				break;
        			}
        		}
        		StringSequence current = new StringSequence(description,currentSequence.toString());
        		output.add(current);
        		description="";
        		currentSequence.delete(0, currentSequence.capacity());
        	}
        }
        System.out.println("Formatieren Abgeschlossen!");
        return output;
    }
    /**
     * Speichert eine ArrayList vom Datentyp StringSequence
     * in einer Datei.
     * @param ArrayList die gespeichert werden soll.
     * @param Name der Datei die erstellt werden soll.
     */
    //write data
    public static void writeArrayListContent(ArrayList<StringSequence> database,String filename)
    {
        try
        {
            File outputfile = new File(filename);
            FileWriter fw = new FileWriter(outputfile);
            for(int i=0;i<database.size();i++)
            {
                fw.write(database.get(i).getDescription() + "\n");
                fw.write(database.get(i).getSequence() + "\n");
            }
            fw.flush();
            fw.close();
        }
        catch(IOException e)
        {
            System.out.println("Fehler beim schreiben der Datein!");
            System.exit(1);
        }
    }
    public static void writeSearchResult(ArrayList<StringSequence> database,String filename)
    {
        try
        {
            File outputfile = new File(filename);
            FileWriter fw = new FileWriter(outputfile);
            for(int i=0;i<database.size();i++)
            {
                fw.write("sequence: " + database.get(i).getDescription() + "\n");
                fw.write("pattern: " + database.get(i).getPattern() + "\n");
                fw.write("positions: " + database.get(i).getFoundPositionList() + "\n");
            }
            fw.flush();
            fw.close();
        }
        catch(IOException e)
        {
            System.out.println("Fehler beim schreiben der Datein!");
            System.exit(1);
        }
    }
    /**
     * Methode zum Einlesen einer .tree Datei, in der zeilenweise Paare von Elternknoten und Kantenbezeichnung, mit Komma von
     * einander getrennt enthalten sind.
     * @param filename name of the file to read
     * @return
     */
    public static Tree readFileContentForTree(String filename)
    {   
        Tree input = new Tree();
        try
        {     
            FileReader fr = new FileReader(filename);
            BufferedReader buffreader = new BufferedReader(fr);
            String line;
            while ((line=buffreader.readLine()) != null)
            {
                String[] current = line.split(",");
                TreeNode currentParent = input.findTreeNode(current[0],input.getRoot());
                if (currentParent==null)
                {
                	System.out.println("sorry couldn't find parent of line: " + line);
                }
                else
                {
                	currentParent.addChild(current[1]);
                }               
            }
            buffreader.close();
        }
        catch (IOException e)
        {
            System.out.println("Fehler beim lesen der Datei!");
            System.exit(1);
        }
        System.out.println("Einlesen Abgeschlossen!");
        return input;
    }
    public static String readFileToString(String filename)
    {   
        StringBuffer currentSequence = new StringBuffer();
        try
        {     
            FileReader fr = new FileReader(filename);
            BufferedReader buffreader = new BufferedReader(fr);
            int counter=0;
            while (buffreader.ready())
            {
            	currentSequence.append(buffreader.readLine());
                counter++;
                if(counter%10000==0)
                {
                	System.out.println("Gelesene Zeilen: " + counter);
                }
                
            }
            buffreader.close();
        }
        catch (IOException e)
        {
            System.out.println("Fehler beim lesen der Datei!");
            System.exit(1);
        }
        System.out.println("Einlesen Abgeschlossen!");
        return currentSequence.toString();
    }
    public static char[] readFileToCharArray(String filename)
    {   
    	char[] result;
        StringBuffer currentSequence = new StringBuffer();
        try
        {  
            FileReader fr = new FileReader(filename);
            BufferedReader buffreader = new BufferedReader(fr);
            int counter=0;
            while (buffreader.ready())
            {
            	currentSequence.append(buffreader.readLine());
                counter++;
                if(counter%10000==0)
                {
                	System.out.println("Gelesene Zeilen: " + counter);
                }
                
            }
            buffreader.close();
        }
        catch (IOException e)
        {
            System.out.println("Fehler beim lesen der Datei!");
            System.exit(1);
        }
        System.out.println("Einlesen Abgeschlossen!");
        result = currentSequence.toString().toCharArray();
        return result;
    }

}
