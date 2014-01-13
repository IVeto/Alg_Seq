package types;

import java.util.Hashtable;

public class TreeNode {
	private int leafIndex;
	private String inputEdgeLabel;
	private Hashtable<Character,TreeNode> childHash = new Hashtable<Character,TreeNode>();
	private TreeNode parent;
	public TreeNode(TreeNode parent,String inputEdgeLabel)
	{
		this.parent=parent;
		this.inputEdgeLabel=inputEdgeLabel;
	}
	public TreeNode()
	{
		this.parent=null;
		this.inputEdgeLabel="";
	}
	/**
	 * @return the parent
	 */
	public TreeNode getParent() {
		return parent;
	}
	/**
	 * @param parent the parent to set
	 */
	public void setParent(TreeNode parent) {
		this.parent = parent;
	}
	/**
	 * @return the leafIndex
	 */
	public int getLeafIndex() {
		return leafIndex;
	}
	/**
	 * @param leafIndex the leafIndex to set
	 */
	public void setLeafIndex(int leafIndex) {
		this.leafIndex = leafIndex;
	}
	/**
	 * @return the inputEdgeLabe
	 */
	public String getInputEdgeLabe() {
		return inputEdgeLabel;
	}
	/**
	 * @param inputEdgeLabe the inputEdgeLabe to set
	 */
	public void setInputEdgeLabe(String inputEdgeLabe) {
		this.inputEdgeLabel = inputEdgeLabe;
	}
	
	public boolean hasChild()
	{
		if (childHash.size()==0)
		{
			return false;
		}
		else 
		{
			return true;
		}
	}
	public int getChildNumber()
	{
		return childHash.size();
	}
	/**
	 * @return the childHash
	 */
	public Hashtable<Character, TreeNode> getChildHash() {
		return childHash;
	}
	/**
	 * @param childHash the childHash to set
	 */
	public void setChildHash(Hashtable<Character, TreeNode> childHash) {
		this.childHash = childHash;
	}
	public String getPathLabel()
	{
		String pathLabel="";
		TreeNode current= this;
		while(current!=null)
		{
			pathLabel= current.getInputEdgeLabe() + pathLabel;
			current= current.getParent();
		}
		return pathLabel;
	}
	public void addChild(String inputEdgeLabel)
	{
		TreeNode newChild = new TreeNode(this,inputEdgeLabel);
		childHash.put(inputEdgeLabel.charAt(0), newChild);
	}
	public void addLeaf(String inputEdgeLabel,int textSize)
	{
		TreeNode newChild = new TreeNode(this,inputEdgeLabel);
		childHash.put(inputEdgeLabel.charAt(0), newChild);
		int newIndex=textSize-newChild.getPathLabel().length()+2;
		newChild.setLeafIndex(newIndex);
	}
	public void addToChildHash(Character key,TreeNode value)
	{
		childHash.put(key, value);
	}
}
