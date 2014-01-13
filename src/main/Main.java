package main;

import types.AlignmentGraph;

/**
 * 
 * @author Axel Zieschank (210234303)
 * @author Dominik Wittig (212220939)
 */
public class Main {
/**
 * Testklasse zum ausprobieren
 * @param args
 */
	public static void main(String[] args) {
		if(args.length<6)
		{
			Menu.mainmenu();
		}
		else
		{	
			int ins = Integer.parseInt(args[2]);
			int del = Integer.parseInt(args[3]);
			int rep = Integer.parseInt(args[4]);
			int mat = Integer.parseInt(args[5]);
			AlignmentGraph test = new AlignmentGraph(args[0],args[1],ins,del,rep,mat);
			test.print();
			test.findSweetSpots();

		}
	}
	
	public static void verify(FastaFile obj1, FastaFile obj2)
	{
		boolean equal=true;
		if(obj1.getDataBase().size()!=obj2.getDataBase().size())
		{
			equal=false;
		}
		for(int i=0;i<obj1.getDataBase().size();i++)
		{
			for(int j=0;j<obj1.getDataBase().get(i).getFoundPositionList().size();j++)
			if((obj1.getDataBase().get(i).getFoundPositionList().get(j)!=obj2.getDataBase().get(i).getFoundPositionList().get(j)))
			{
				equal=false;
			}
		}
		if(equal==true)
		{
			System.out.println("Verify: same matches");
		}
		else
		{
			System.out.println("Verify: not same matches!");
		}
	}

}
