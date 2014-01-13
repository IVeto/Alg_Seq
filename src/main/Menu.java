package main;
/**
 * 
 * @author Axel Zieschank (210234303)
 * @author Dominik Wittig (212220939)
 */
import java.util.ArrayList;

import types.Suffixarray;
import types.Tree;
import types.TreeNode;

public class Menu {
	static ArrayList<FastaFile> fastaFileList = new ArrayList<FastaFile>();
	static Tree currentTree = new Tree();

	public static void mainmenu()
    {
		System.out.println("\n"+"Welcome user");
        System.out.println("1 - read fasta file from disk");
        System.out.println("2 - count off readed fastaFiles");
        System.out.println("3 - search menue");
        System.out.println("4 - enter sequence on terminal");
        System.out.println("5 - save fasta File on disk");
        System.out.println("6 - tree menue");
        System.out.println("7 - SuffixArray");
        System.out.println("9 - exit programm");
        int choice = IO.userIntegerInput("please choose:");
        switch(choice)
        {
            case 1:
            	FastaFile current = new FastaFile();
            	current.setDataBase(IO.formatToStringSequence(IO.readFileContent(IO.userStringInput("Please enter path:"))));
                fastaFileList.add(current);
                System.out.println("read data succeed id of file is: " + (fastaFileList.size()-1));
                break;
            case 2:
            	System.out.println(fastaFileList.size());
                break;
            case 3:
                searchmenu();
                break;
            case 4:
            	if(fastaFileList.size()==0)
            	{
            		FastaFile emptyFastaFile = new FastaFile();
            		String userDescription = IO.userStringInput("please enter description of sequnce:");
            		String usersequence = IO.userStringInput("please enter sequnce:");
            		emptyFastaFile.newSequence(userDescription, usersequence);
            	}
            	else
            	{
            		int userChoice1 =IO.userIntegerInput("choose id the sequence should be added to:");
                	while(inputOkay(userChoice1)==false)
                	{
                		System.out.println("unknow command"); 
                		userChoice1 =IO.userIntegerInput("choose id the sequence should be added to:");
                	}
            		String userDescription = IO.userStringInput("please enter description of sequnce:");
            		String usersequence = IO.userStringInput("please enter sequnce:");
            		fastaFileList.get(userChoice1).newSequence(userDescription, usersequence);
            	}
                searchmenu();
                break;
            case 5:
            	if(fastaFileList.size()==0)
            	{
            		System.out.println("Sorry nothing to save");
            		mainmenu();
            	}
            	else
            	{
            		int userChoice1 =IO.userIntegerInput("choose id of sequence that should be saved:");
                	while(inputOkay(userChoice1)==false)
                	{
                		System.out.println("unknow command"); 
                		userChoice1 =IO.userIntegerInput("choose id of sequence that should be saved:");
                	}
            		String userFileName = IO.userStringInput("filename");
            		IO.writeArrayListContent(fastaFileList.get(userChoice1).getDataBase(), userFileName);
            	}
            	System.out.println("file saved");
                mainmenu();
            	break;
            case 6:
                treemenu();
                break;
            case 7:
            	String usertext = IO.userStringInput("choose text that should be searched");
            	String pattern = IO.userStringInput("choose pattern that should be searched");
				Suffixarray text = new Suffixarray(usertext);
				text.search(pattern);
				mainmenu();
            case 9:
                System.exit(1);
                break;
            default:
                System.out.println("unknow command");
        }
        mainmenu();
    }
	
	public static void treemenu()
	{
		System.out.println("\n"+"press key for desired option: ");
        System.out.println("1 - find pattern in Tree");
        System.out.println("2 - print tree");
        System.out.println("3 - read file to tree");
        System.out.println("4 - read userinput to tree");
        System.out.println("9 - back");
        int choice= IO.userIntegerInput("please choose:");
        switch(choice)
        {
            case 1:
            	String pattern =IO.userStringInput("pattern to search:");
            	System.out.println("start indizes:");
            	currentTree.suffixTreeSearch(pattern);
            	break;
            case 2:
            	currentTree.print(currentTree.getRoot());
                break;
            case 3:
            	TreeNode newRoot = new TreeNode();
            	currentTree.setRoot(newRoot);
            	System.out.println("Zum Einlesen einer Datei aus der ein Suffixbaum erstellt werden soll.");
            	currentTree.buildTree(IO.readFileToString(IO.userStringInput("File to read?:")));
                break;
            case 9:
                mainmenu();
                break;
            case 4:
            	TreeNode newRoot1 = new TreeNode();
            	currentTree.setRoot(newRoot1);
            	currentTree.buildTree(IO.userStringInput("Input Text:"));
            	break;
            default:
                System.out.println("unknow command");
        }
        treemenu();
	}
	
	public static void searchmenu()
    {
        System.out.println("\n"+"press key for desired option: ");
        System.out.println("1 - naive search");
        System.out.println("2 - bad-character-Search");
        System.out.println("9 - back");
        int choice= IO.userIntegerInput("please choose:");
        switch(choice)
        {
            case 1:
            	int userChoice1 =IO.userIntegerInput("choose id that should be scanned");
            	while(inputOkay(userChoice1)==false)
            	{
            		System.out.println("unknow command"); 
            		userChoice1 =IO.userIntegerInput("choose id that should be scanned");
            	}
            	String pattern1 =IO.userStringInput("please enter pattern:");
            	Search.simpleSearch(fastaFileList.get(userChoice1).getDataBase(),pattern1);
            	String saveDestination1 =IO.userStringInput("please enter path to save results:");
            	IO.writeSearchResult(fastaFileList.get(userChoice1).getDataBase(), saveDestination1);
                break;
            case 2:
            	int userChoice2 =IO.userIntegerInput("choose id that should be scanned");
            	while(inputOkay(userChoice2)==false)
            	{
            		System.out.println("unknow command"); 
            		userChoice2 =IO.userIntegerInput("choose id that should be scanned");
            	}
            	String pattern2 =IO.userStringInput("please enter pattern:");
                Search.badCharacter(fastaFileList.get(userChoice2).getDataBase(),pattern2);
                String saveDestination2 =IO.userStringInput("please enter path to save results:");
                IO.writeSearchResult(fastaFileList.get(userChoice2).getDataBase(), saveDestination2);
                break;
            case 9:
                mainmenu();
                break;
            default:
                System.out.println("unknow command");
        }
        searchmenu();
    
    }
	private static boolean inputOkay(int toCheck)
	{
		if(toCheck<fastaFileList.size())
			return true;
		else
			return false;
	}

}
