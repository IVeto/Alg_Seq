/**
 * 
 */
package types;

import java.util.ArrayList;
import java.util.List;

/**
 * @author actyc
 *
 */
public class AlignmentGraph {

	private char[] p;
	private char[] t;
	private int[] effort = new int[4];
	private int[][] matrix;
	private int matrixMax=0;
	
	public AlignmentGraph(String pattern,String text,int Ins,int Del,int Rep,int Mat)
	{
		this.p=pattern.toCharArray();
		this.t=text.toCharArray();
		effort[0]=Ins;
		effort[1]=Del;
		effort[2]=Rep;
		effort[3]=Mat;
		matrix = new int[p.length+1][t.length+1];
		SmithWaterman();
	}
	
	public void SmithWaterman()
	{
		int x,y;
		matrix[0][0]=0;
		//System.out.println(matrix.length + ":" +  matrix[1].length);

		for(x=1;x<matrix[0].length;x++)
		{
			matrix[0][x]= Math.max(matrix[0][x-1] +effort[0], 0);
		}
		for(y=1;y<matrix.length;y++)
		{
			matrix[y][0]= Math.max(matrix[y-1][0] +effort[1], 0);
		}	
		//Berechnung des inneren
		for(y=1;y<matrix.length;y++)
		{
			for(x=1;x<matrix[y].length;x++)
			{
				matrix[y][x]= calc(y,x);
				//Berechne Wert für stelle matrix[y][x]);
			}
		}			
	}
	
	private int calc(int y,int x)
	{
		int ins = matrix[y][x-1] +effort[0];
		int del = matrix[y-1][x]+effort[1];
		int dia = matrix[y-1][x-1];
		if(p[y-1]!=t[x-1])
		{
			dia += effort [2];
		}
		else
		{
			dia+= effort[3];
		}
		int result = Math.max(Math.max(ins, del), Math.max(dia, 0));
		setMax(result);
		return result;
	}
	
	private void setMax(int obj)
	{
		if (obj>matrixMax)
		{
			matrixMax= obj;
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
				System.out.print("|"+matrix[y][x]+"|");
			}
			System.out.println();
		}
		System.out.println("Maximum der Matrix: "+ matrixMax);
	}
	
	public void findSweetSpots()
	{
		int x,y;
		//System.out.println(matrix.length + ":" +  matrix[1].length);
		for(y=0;y<matrix.length;y++)
		{
			for(x=0;x<matrix[y].length;x++)
			{
				if(matrix[y][x]==matrixMax)
				{
					pathRek(x,y,"");
				}
			}
		}
	}
	
	
	private void pathRek(int x, int y,String path)
	{
		if(matrix[y][x]==0)
		{
			alignmentPrint(path, y, x);
			//System.out.println(path);
		}
		else
		{
			if(y==0)
			{
				String temp=path+"I";
				pathRek(x-1,y,temp);
			}
			if(x==0)
			{
				String temp=path+"D";
				pathRek(x,y-1,temp);
			}
			else
			{
				if(matrix[y][x-1]+effort[0]==matrix[y][x])
				{
					String temp=path+"I";
					pathRek(x-1,y,temp);
				}
				if(matrix[y-1][x]+effort[1]==matrix[y][x])
				{
					String temp=path+"D";
					pathRek(x,y-1,temp);
				}
				if(matrix[y-1][x-1]+effort[2]==matrix[y][x] && p[y-1]!=t[x-1])
				{
					String temp=path+"R";
					pathRek(x-1,y-1,temp);
				}
				if(matrix[y-1][x-1]+effort[3]==matrix[y][x] && p[y-1]==t[x-1])
				{
					String temp=path+"M";
					pathRek(x-1,y-1,temp);
				}
			}
		}
	}
	private void alignmentPrint(String ref,int pID,int tID)
	{
		int patternInd=pID;
		int textInd=tID;
		String p="";
		String t="";
		String a="";
		for(int i=ref.length()-1;i>=0;i--)
		{
			if(ref.charAt(i)=='M' || ref.charAt(i)=='R')
			{
				p+=this.p[patternInd];
				t+=this.t[textInd];
				a+=ref.charAt(i);
				patternInd++;
				textInd++;
			}
			if(ref.charAt(i)=='I')
			{
				p+="-";
				t+=this.t[textInd];
				a+=ref.charAt(i);
				textInd++;
			}
			if(ref.charAt(i)=='D')
			{
				p+=this.p[patternInd];
				t+="-";
				a+=ref.charAt(i);
				patternInd++;
			}
		}
		System.out.println(p);
		System.out.println(t);
		System.out.println(a);
		}
	
}
