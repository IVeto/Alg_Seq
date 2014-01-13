package main;
/**
 * 
 * @author Axel Zieschank (210234303)
 * @author Dominik Wittig (212220939)
 */
import java.util.ArrayList;
import java.util.Hashtable;

import types.StringSequence;
/**
 * Stellt verschieden Methoden zum Suchen bereit
 * @author Axel Zieschank
 *
 */
public class Search {
	/**
	 * Simpler Suchalgorithmus
	 * @param ArrayList die durchsucht werden soll
	 * @param Muster das gesucht werden soll
	 */
	public static void simpleSearch(ArrayList<StringSequence> database,String pattern)
	{
        for(int sequence = 0;sequence<database.size();sequence++)
        {
        	ArrayList<Integer> foundInCurrent = new ArrayList<Integer>();
        	database.get(sequence).setPattern(pattern);
            String current=database.get(sequence).getSequence();
			int counter=0;
            for(int offset = 0;offset<(current.length()-pattern.length()+1);offset++)
            {
                boolean found = true;
                for(int j=0;j<pattern.length();j++)
                {
                	counter++;
                    if(pattern.charAt(j)!=current.charAt(j+offset))
                    {
                        found=false;
                        break;
                    }
                }
                if(found)
                {
                	foundInCurrent.add(offset);
                }
            }
            database.get(sequence).setFoundPositionList(foundInCurrent);
            System.out.println("Naiv-Vergleiche: "+counter);
        }
	}
	
	public static void badCharacter(ArrayList<StringSequence> database,String pattern)
	{
		Hashtable<Character,Integer> occuranceHash = new Hashtable<Character,Integer>();
		occuranceHash = makeOccuranceHash(pattern);
		int last;
		for(int sequence = 0;sequence<database.size();sequence++)
		{
			ArrayList<Integer> foundInCurrent = new ArrayList<Integer>();
        	database.get(sequence).setPattern(pattern);
			String current=database.get(sequence).getSequence();; 
			int counter=0;
            for(int offset = 0;offset<(current.length()-pattern.length()+1);offset++)
            {
                boolean found = true;
            	for(int n=pattern.length()-1;n>=0;n--)
            	{
            		counter++;
            		if(pattern.charAt(n)!=current.charAt(n+offset))
            		{
            			if(occuranceHash.get(current.charAt(n+offset))==null)
            				last =-1;
            			else
            				last=occuranceHash.get(current.charAt(n+offset));
            			if(last==-1)
            			{
            				offset=n+offset;
            			}
            			
            			if(last<n && last>-1)
            			{
                			offset=offset+(n-last-1);
            			}
            			found=false;
            			break;
            		}
            		
            	}
            	if(found)
        		{
                	foundInCurrent.add(offset);
                }
            }
            database.get(sequence).setFoundPositionList(foundInCurrent);
            System.out.println("BadC-Vergleiche: "+counter);
		}

	}
	
/*	public static void BoyerMoore(ArrayList<StringSequence> database ,String pattern)
	{
		Hashtable<Character,Integer> bcHash = new Hashtable<Character,Integer>();
		Hashtable<Integer,Integer> gsHash = new Hashtable<Integer,Integer>();
		bcHash = makeOccuranceHash(pattern);
		gsHash = makeStrongSuffixHash(pattern);
		for(int sequence = 0;sequence<database.size();sequence++)
		{
			ArrayList<Integer> foundInCurrent = new ArrayList<Integer>();
        	database.get(sequence).setPattern(pattern);
			String current=database.get(sequence).getSequence();
			int counter=0;
            for(int offset = 0;offset<(current.length()-pattern.length()+1);offset++)
            {
                boolean found = true;
            	for(int n=pattern.length()-1;n>=0;n--)
            	{
            		counter++;
            		if(pattern.charAt(n)!=current.charAt(n+offset))
            		{
            			if (bcAdvice(current.charAt(n+offset), n, bcHash)>gsAdvice(n, gsHash))
            				offset+=bcAdvice(current.charAt(n+offset), n, bcHash);
            			else
            				offset+=gsAdvice(n, gsHash);
            			found=false;
            			break;
            		}
            		
            	}
            	if(found)
        		{
                	foundInCurrent.add(offset);
                }
            }
            database.get(sequence).setFoundPositionList(foundInCurrent);
            System.out.println("BoyM-Vergleiche: "+counter);
		}
	}
	//Datentyp oder anderes Konzept!!!!!!
	
	private static int bcAdvice(Character interceptChar,int interceptPosiPattern,Hashtable<Character,Integer> bcHash)
	{
		int last=0;
		int result =0;
		if(bcHash.get(interceptChar)==null)
			last =-1;
		else
			last=bcHash.get(interceptChar);
		if(last==-1)
		{
			result=interceptPosiPattern;
		}
		
		if(last<interceptPosiPattern && last>-1)
		{
			result=interceptPosiPattern-last-1;
		}
		return result;
	}
	private static int gsAdvice(int interceptPosiPattern,Hashtable<Integer,Integer> gsHash)
	{	
		int result=0;
		int last =-1;
		//System.out.println(interceptPosiPattern);
		if(gsHash.get(interceptPosiPattern)!=null)
		{
			last =gsHash.get(interceptPosiPattern);
		}
		else
			return 0;
		int size=gsHash.size();
		if(last==-1)
		{
			result=size-(gsHash.size()-interceptPosiPattern);
		}
		else
		{
			result=interceptPosiPattern-last;
		}
		return result;
	}*/
	
	public static Hashtable<Character,Integer> makeOccuranceHash(String pattern)
	{
		Hashtable<Character,Integer> result = new Hashtable<Character,Integer>();
		for(int i=0;i<pattern.length();i++)
		{
			result.put(pattern.charAt(i),i);
		}
		return result;
	}
/*	
	public static Hashtable<Integer,Integer> makeSuffixHash(String pattern)
	{
		Hashtable<Integer,Integer> result = new Hashtable<Integer,Integer>();
		int rightBoarder= pattern.length()-1;
		for(int currentSuffix= rightBoarder;currentSuffix>0;currentSuffix--)
		{
			String currentSub = pattern.substring(0, currentSuffix);
			String currentSuf = pattern.substring(currentSuffix, rightBoarder+1);
			result.put(currentSuffix, currentSub.lastIndexOf(currentSuf));
		}
		return result;
	}
	
	public static Hashtable<Integer,Integer> makeSuffixHashpresuf(String pattern)
	{
		Hashtable<Integer,Integer> result = new Hashtable<Integer,Integer>();
		int patternlength = pattern.length();
		for(int i=0;i<patternlength;i++)
		{
			String sub = pattern.substring(0, i+1);
			if(pattern.lastIndexOf(sub)>0 && pattern.lastIndexOf(sub)+sub.length()==patternlength)
			{
				result.put(pattern.lastIndexOf(sub),i);
			}
		}
		return result;
	}
	
	public static Hashtable<Integer,Integer> makeStrongSuffixHash(String pattern)
	{
		Hashtable<Integer,Integer> result = new Hashtable<Integer,Integer>();
		int rightBoarder= pattern.length()-1;
		for(int currentSuffix= rightBoarder;currentSuffix>0;currentSuffix--)
		{
			String currentSub =pattern.substring(0, currentSuffix);
			String currentSuf =pattern.substring(currentSuffix, rightBoarder+1);
			int last = currentSub.lastIndexOf(currentSuf);
			if (last>0)
					{
						if(pattern.charAt(currentSuffix-1)!=pattern.charAt(last-1))
						{
							result.put(currentSuffix, last);
						}
						else
						{
							result.put(currentSuffix, -1);
						}
					}
			else
			{
				result.put(currentSuffix, -1);
			}
			
		}
		return result;
	}
*/
}
