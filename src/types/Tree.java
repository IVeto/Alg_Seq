package types;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Hashtable;

public class Tree {
	private TreeNode root;
	public Tree()
	{
		TreeNode newRoot = new TreeNode();
		this.root=newRoot;
	}
	/**
	 * @return the root
	 */
	public TreeNode getRoot() {
		return root;
	}

	/**
	 * @param root the root to set
	 */
	public void setRoot(TreeNode root) {
		this.root = root;
	}
    public void print(TreeNode current)
    {   
    	if(current.hasChild()==false)
    	{
    		
    	}
    	else
    	{
    		Hashtable<Character, TreeNode> currentChildHash = current.getChildHash();
    		Enumeration<Character> currentChilds =currentChildHash.keys();
			while(currentChilds.hasMoreElements())
			{
				TreeNode currentChild=currentChildHash.get(currentChilds.nextElement());
				if(currentChild.hasChild()==false)
				{
					System.out.print("(Index:"+currentChild.getLeafIndex()+")");
				}
				//System.out.println(current.getPathLabel()+" -- "+currentChild.getPathLabel()+" [type=s];");
				System.out.println("["+current.getPathLabel()+";"+currentChild.getPathLabel()+"]"+"("+currentChild.getInputEdgeLabe()+")");
				print(currentChild);
			}
    	} 
    }
    public TreeNode findTreeNode(String pathlabel,TreeNode start)
    {
    	TreeNode targetNode=null;
    	TreeNode current= start;
    	if(current.getPathLabel().equals(pathlabel))
    	{
    		targetNode=current;
    	}
    	else
    	{
    		if(current.hasChild()==true)
    		{
        		Hashtable<Character, TreeNode> currentChildHash = current.getChildHash();
        		Enumeration<Character> currentChilds =currentChildHash.keys();
    			while(currentChilds.hasMoreElements())
    			{
    				TreeNode currentChild=currentChildHash.get(currentChilds.nextElement());
        			targetNode=findTreeNode(pathlabel, currentChild);
        			if(targetNode!=null)
        			{
        				break;
        			}
    			}
    		}
    	}
    	return targetNode;
    }
    /**
     * Methoden um in einem leeren Baum ein Suffix-Baum für das gegebene Wort zu erstellen
     * @param String des gegebenen Wortes
     */
    public void buildTree(String text)
    {
    	suffixTreeRec(makeSuffix(text),root,text.length());
    }
    public void buildTreeAlternativ(String text)
    {
    	suffixTreeRec(makeSuffixAlternativ(text),root,text.length());
    }
    /**
     * Methode zu erstellen einer Liste mit Suffixen eines gegeben Wortes
     * @param String des gegebenen Wortes
     * @return Liste mit Suffixen
     */
    public ArrayList<String> makeSuffix(String input)
    {
    	ArrayList<String> output = new ArrayList<String>();
    	String currentSuffix = input.concat("$");
    	int textLength=currentSuffix.length();
    	for(int i=textLength;i>0;i--)
    	{
    		output.add(currentSuffix.substring(i-1,textLength));
    	}
    	/*while(currentSuffix.length()!=0)
    	{
    		output.add(currentSuffix);
    		System.out.println("String Länge:"+currentSuffix.length());
    		currentSuffix=currentSuffix.substring(1);
    	}*/
    	Collections.sort(output);
    	System.out.println(output);
    	return output;
    }
    public ArrayList<String> makeSuffixAlternativ(String input)
    {
    	StringBuffer buf = new StringBuffer();
    	ArrayList<String> output = new ArrayList<String>();
    	String currentSuffix = input.concat("$");
    	int textLength=currentSuffix.length();
    	int counter=0;
    	for(int i=textLength;i>0;i--)
    	{
    		counter++;
    		System.out.println(counter);
    		buf.delete(0, buf.length());
    		for(int e=i-1;e<textLength;e++)
    		{
    			buf.append(currentSuffix.charAt(e));
    		}
    		output.add(buf.toString());
    	}
    	/*while(currentSuffix.length()!=0)
    	{
    		output.add(currentSuffix);
    		System.out.println("String Länge:"+currentSuffix.length());
    		currentSuffix=currentSuffix.substring(1);
    	}*/
    	Collections.sort(output);
    	System.out.println(output);
    	return output;
    }
    /**
     * Methode um Eine SuffixListe in SubListen aufzutteilen die mit dem selben Buchstaben beginnen
     * @param Liste mit Suffixen
     * @return Lite mit Listen von Suffixen
     */
    public ArrayList<ArrayList<String>> makeGroup(ArrayList<String> input)
    {
    	ArrayList<ArrayList<String>> output= new ArrayList<ArrayList<String>>();
    	int i=0;
    	while(i<input.size())
    	{
    		Character currentFirst=input.get(i).charAt(0);
    		ArrayList<String> currentGroup = new ArrayList<String>();
    		while(i<input.size() && input.get(i).charAt(0)==currentFirst)
    		{
    			currentGroup.add(input.get(i));
    			i++;
    		}
    		output.add(currentGroup);
    	}
    	return output;
    }
    /**
     * Rekursive Methode um einen Baum zu einem gegeben Suffix zu erstellen
     * @param SuffixList Liste mit Suffixen
     * @param currentNode Knoten unter dem der Baum Konstruiert werden soll
     * @param textSize Größe des ursprünglich Textes
     */
    public void suffixTreeRec(ArrayList<String> SuffixList,TreeNode currentNode,int textSize)
    {
    	ArrayList<ArrayList<String>> currentGroupList = makeGroup(SuffixList);
    	//old
    	//for(int i=0;i<currentGroupList.size();i++)
    	//{
    		//new
    	while(currentGroupList.size()>0)
    	{
    		ArrayList<String> currentGroup = currentGroupList.get(0);
    		//old
    		//ArrayList<String> currentGroup=currentGroupList.get(i);
    		//Fall_1 Guppe enthält nur ein Element
    		if(currentGroup.size()==1)
    		{
    			currentNode.addLeaf(currentGroup.get(0),textSize);
    		}
    		//Fall_2 Gruppe enthält mehr als ein Element
    		else
    		{
    			int longestCommon=commonPrefix(currentGroup);
    			String labelToInnerNode=currentGroup.get(0).substring(0, longestCommon);
    			//currentNode.addChild(labelToInnerNode);
    			for(int index=0;index<currentGroup.size();index++)
    			{
    				currentGroup.set(index, currentGroup.get(index).substring(longestCommon));
    			}
    			TreeNode newChild = new TreeNode(currentNode,labelToInnerNode);
    			currentNode.addToChildHash(labelToInnerNode.charAt(0), newChild);
    			suffixTreeRec(currentGroup,newChild,textSize);
    		}
    		//NEW
    		currentGroupList.remove(0);
    	}
    }
    /**
     * Methode um die Länge, des längsten gemeinsamen Präfix zu finden, ab$ und aba$ würde zu Beispiel 2 liefern. 
     * @param input ArrayListe welche die zu untersuchende String's enthält.
     * @return Länge des längsten gemeinsamen Präfix
     */
    public int commonPrefix(ArrayList<String> input)
    {
    	if(input.size()>1)
    	{
    		boolean end=false;
        	int charPosi =0;
        	int min=input.get(0).length();
        	int listIndex=0;
        	Character currentFirst;
        	for(int i=1;i<input.size();i++)
        	{
        		if(input.get(i).length()<min)
        			min=input.get(i).length();
        	}
        	while(end!=true && charPosi<=min)
        	{
        		currentFirst=input.get(listIndex).charAt(charPosi);
        		while(listIndex<input.size())
        		{
        			if(input.get(listIndex).charAt(charPosi)!=currentFirst)
        			{
        				end=true;
        				break;
        			}
        			listIndex++;
        		}
        		charPosi++;
        		listIndex=0;
        	}
        	return charPosi-1;	
    	}
    	else
    	{
    		return input.get(0).length();
    	}
    	
    }
    /**
     * Methode zum suchen in einem gegeben SuffixBaum
     * @param pattern zu suchender String
     * @return Liste mit Trefferpositionen
     */
    public ArrayList<Integer> suffixTreeSearch(String pattern)
    {
    	ArrayList<Integer> resultList = new ArrayList<Integer>();
    	boolean found = true;
    	TreeNode currentNode=root;
    	while(pattern.length()>0 && found)
    	{	
    		if(currentNode.getChildHash().get(pattern.charAt(0))!=null)
    		{
    			TreeNode currentChild=currentNode.getChildHash().get(pattern.charAt(0));
    			String alpha= currentChild.getInputEdgeLabe();
    			for(int i=1;i<Math.min(alpha.length(), pattern.length());i++)
    			{
    				if(pattern.charAt(i)!=alpha.charAt(i))
    				{
    					found=false;
    					break;
    				}
    				
    			}
    			if(found)
    			{
    				if(pattern.length()<=alpha.length())
    				{
    					System.out.println(findAllLeafs(currentChild));
    					resultList=findAllLeafs(currentChild);
    					//System.out.println("Juhu gefunden!");
    					break;
    				}
    				else
    				{
    					pattern=pattern.substring(alpha.length());
    					currentNode=currentChild;
    				}
    			}
    		}
    		else
    		{
    			found=false;
    			System.out.println("Leider nein!");
    		}
    		
    	}
    	return resultList;
    }
    /**
     * Methode zum finden des Blatt Index alle Blätter die unter einem gegeben Knoten liegen
     * @param start TreeNode von dem aus gesucht wird
     * @return Liste mit Indizes der Blätter
     */
    public ArrayList<Integer> findAllLeafs(TreeNode start)
    {
    	TreeNode current = start;
    	ArrayList<Integer> result = new ArrayList<Integer>();
    	if(current.hasChild()==false)
    	{
    		result.add(current.getLeafIndex());
    	}
    	else
    	{
    		Hashtable<Character, TreeNode> currentChildHash = current.getChildHash();
    		Enumeration<Character> currentChilds =currentChildHash.keys();
			while(currentChilds.hasMoreElements())
			{
				TreeNode currentChild=currentChildHash.get(currentChilds.nextElement());
    			result.addAll(findAllLeafs(currentChild));
			}
    	}
    	Collections.sort(result);
    	return result;
    }
}
