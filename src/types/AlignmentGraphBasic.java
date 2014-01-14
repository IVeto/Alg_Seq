/**
 * 
 */
package types;

/**
 * @author actyc
 *
 */
public class AlignmentGraphBasic {

	private char[] p;
	private char[] t;
	private int[] effort = new int[4];
	private int[][] matrix;
	private int matrixMax=0;
	
	public AlignmentGraphBasic(String pattern,String text,int Ins,int Del,int Rep,int Mat)
	{
		this.p=pattern.toCharArray();
		this.t=text.toCharArray();
		effort[0]=Ins;
		effort[1]=Del;
		effort[2]=Rep;
		effort[3]=Mat;
		matrix = new int[p.length+1][t.length+1];
	}
	public AlignmentGraphBasic(AlignmentGraphBasic old)
	{
		this.p=old.p;
		this.t=old.t;
		effort[0]=old.getEffort()[0];
		effort[1]=old.getEffort()[1];
		effort[2]=old.getEffort()[2];
		effort[3]=old.getEffort()[3];
		matrix = new int[p.length+1][t.length+1];
	}
	/**
	 * @return the effort
	 */
	public int[] getEffort() {
		return effort;
	}
	
	public boolean equalChars(int pID, int tID)
	{
		if(p[pID]==t[tID])
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	/**
	 * @return the matrix
	 */
	public int getMatrix(int y, int x) {
		return matrix[y][x];
	}

	/**
	 * @param matrix the matrix to set
	 */
	public void setMatrix(int y, int x,int value) {
		this.matrix[y][x] = value;
	}
	
	public int getMatrixXLength()
	{
		return matrix[0].length;
	}
	
	public int getMatrixYLength()
	{
		return matrix.length;
	}

	/**
	 * @return the matrixMax
	 */
	public int getMatrixMax() {
		return matrixMax;
	}
	/**
	 * @param set the matrixMax if obj > currentMax
	 */
	public void setMax(int obj)
	{
		if (obj>matrixMax)
		{
			matrixMax= obj;
		}
	}
	
	public void print()
	{
		int x,y;
		for(y=0;y<matrix.length;y++)
		{
			for(x=0;x<matrix[y].length;x++)
			{
				System.out.print("|"+matrix[y][x]+"|");
			}
			System.out.println();
		}
		System.out.println("Maximum der Matrix: "+ matrixMax);
	}
	
}
