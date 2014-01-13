package types;
public class AlignmentGraphOld {
	
	private char[] s1;
	private char[] s2;
	private int[] effort = new int[3];
	private int[][][] matrix;

	public AlignmentGraphOld(String t1,String t2,int e1,int e2,int e3)
	{
		this.s1=t1.toCharArray();
		this.s2=t2.toCharArray();
		effort[0]=e1;
		effort[1]=e2;
		effort[2]=e3;
		matrix = new int[s2.length+1][s1.length+1][4];
		fill();
	}
	
	public void fill()
	{
		int x,y;
		matrix[0][0][0]=0;
		//System.out.println(matrix.length + ":" +  matrix[1].length);

		for(x=1;x<matrix[0].length;x++)
		{
			matrix[0][x][0]= matrix[0][x-1][0] +effort[1];
			matrix[0][x][2]= 1;
		}
		for(y=1;y<matrix.length;y++)
		{
			matrix[y][0][0]= matrix[y-1][0][0] +effort[0];
			matrix[y][0][1]= 1;
		}	
		//Berechnung des inneren
		for(y=1;y<matrix.length;y++)
		{
			for(x=1;x<matrix[y].length;x++)
			{
				matrix[y][x][0]= calc(y,x);
				//Berechne Wert für stelle matrix[y][x]);
			}
			System.out.println();
		}
	}	
	
	public void print()
	{
		int x,y;
		//System.out.println(matrix.length + ":" +  matrix[1].length);
		for(y=0;y<matrix.length;y++)
		{
			for(x=0;x<matrix[y].length;x++)
			{
				System.out.print("|"+matrix[y][x][0]+"|");
			}
			System.out.println();
		}
	}
	
	private int calc(int y,int x)
	{
		int ins = matrix[y][x-1][0] +effort[1];
		int del = matrix[y-1][x][0]+effort[0];
		int dia = matrix[y-1][x-1][0];
		if(s1[x-1]!=s2[y-1])
		{
			dia += effort [2];
		}
		//System.out.println(del+":"+ins+":"+dia);
		if(del<=ins && del<=dia)
		{
			matrix[y][x][1]=1;
			return del;
		}
		if(ins<=del && ins<=dia)
		{
			matrix[y][x][2]=1;
			return ins;
		}
		else
		{
			matrix[y][x][3]=1;
			return dia;
		}
	}
	
	public String path()
	{
		int x=matrix[0].length-1;
		int y=matrix.length-1;
		String rs1="";
		String rs2="";
		String resultalt="";
		int pos1=matrix[0].length-2;
		int pos2=matrix.length-2;
		while(x>0 || y>0)
		{

			if(matrix[y][x][1]==1 && y>0)
			{
				rs1=s2[pos2]+rs1;
				pos2--;
				rs2="-"+rs2;
				y--;
				resultalt="D"+resultalt;
			}
			else
			{
				if(matrix[y][x][2]==1 && x>0)
				{
					rs2=s1[pos1]+rs2;
					pos1--;
					rs1="-"+rs1;
					x--;
					resultalt="I"+resultalt;
				}
				else
				{
					rs1=s2[pos2]+rs1;
					rs2=s1[pos1]+rs2;
					pos1--;
					pos2--;
					x--;
					y--;
					//System.out.println(rs1 +"\n" + rs2);
					resultalt="-"+resultalt;	
				}		
			}
		}
		String result = rs1 +"\n" + rs2;
		System.out.println("eine optimale Editdistanz: "+matrix[matrix.length-1][matrix[0].length-1][0]);
		return result;
	}
}
