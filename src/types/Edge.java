package types;
/**
 * 
 * @author Axel Zieschank (210234303)
 * @author Dominik Wittig (212220939)
 */
public class Edge {
	private TreeNode parentTreeNode;
	private TreeNode childTreeNode;
	private String label;
	
	public Edge(TreeNode parent,TreeNode child,String label)
	{
		this.parentTreeNode=parent;
		this.childTreeNode=child;
		this.label=label;
	}

	/**
	 * @return the parentTreeNode
	 */
	public TreeNode getParentTreeNode() {
		return parentTreeNode;
	}

	/**
	 * @return the childTreeNode
	 */
	public TreeNode getChildTreeNode() {
		return childTreeNode;
	}

	/**
	 * @return the label
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * @param parentTreeNode the parentTreeNode to set
	 */
	public void setParentTreeNode(TreeNode parentTreeNode) {
		this.parentTreeNode = parentTreeNode;
	}

	/**
	 * @param childTreeNode the childTreeNode to set
	 */
	public void setChildTreeNode(TreeNode childTreeNode) {
		this.childTreeNode = childTreeNode;
	}

	/**
	 * @param label the label to set
	 */
	public void setLabel(String label) {
		this.label = label;
	}
	
	

}
