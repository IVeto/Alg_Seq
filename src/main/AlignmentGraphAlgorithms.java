/**
 * 
 */
package main;

import types.AlignmentGraphBasic;

/**
 * @author actyc
 *
 */
public class AlignmentGraphAlgorithms {

	public static void SmithWaterman(AlignmentGraphBasic matrix)
	{
		int x,y;
		matrix.setMatrix(0,0,0);
		for(x=1;x<matrix.getMatrixXLength();x++)
		{
			matrix.setMatrix(0, x, Math.max(matrix.getMatrix(0, x-1)+matrix.getEffort()[0], 0));
		}
		for(y=1;y<matrix.getMatrixYLength();y++)
		{
			matrix.setMatrix(y, 0, Math.max(matrix.getMatrix(y-1, 0)+matrix.getEffort()[1], 0));
		}	
		//Berechnung des inneren
		for(y=1;y<matrix.getMatrixYLength();y++)
		{
			for(x=1;x<matrix.getMatrixXLength();x++)
			{
				matrix.setMatrix(y,x,calcSmithWaterman(y,x,matrix));
				//Berechne Wert für stelle matrix[y][x]);
			}
		}			
	}
	
	private static int calcSmithWaterman(int y,int x,AlignmentGraphBasic matrix)
	{
		int ins = matrix.getMatrix(y,x-1)+matrix.getEffort()[0];
		int del = matrix.getMatrix(y-1,x)+matrix.getEffort()[1];
		int dia = matrix.getMatrix(y-1,x-1);
		if(matrix.equalChars(y-1, x-1))
		{
			dia += +matrix.getEffort()[3];
		}
		else
		{
			dia+= +matrix.getEffort()[2];
		}
		int result = Math.max(Math.max(ins, del), Math.max(dia, 0));
		matrix.setMax(result);
		return result;
	}
	
	public static void affineMatrixFill(AlignmentGraphBasic d)
	{
		AlignmentGraphBasic e = new AlignmentGraphBasic(d);
		AlignmentGraphBasic f = new AlignmentGraphBasic(d);
		int x,y;
		d.setMatrix(0,0,0);
		for(x=1;x<d.getMatrixXLength();x++)
		{
			e.setMatrix(0, x,(e.getEffort()[0]+x*e.getEffort()[1]));
			d.setMatrix(0, x, e.getMatrix(0, x));
		}
		for(y=1;y<d.getMatrixYLength();y++)
		{
			f.setMatrix(y, 0,(f.getEffort()[0]+y*f.getEffort()[1]));
			d.setMatrix(y, 0, f.getMatrix(y, 0));
		}
		for(y=1;y<d.getMatrixYLength();y++)
		{
			for(x=1;x<d.getMatrixXLength();x++)
			{
				e.setMatrix(y,x,calcAfineCostsE(y,x,e,f,d));
				f.setMatrix(y,x,calcAfineCostsF(y,x,e,f,d));
				d.setMatrix(y,x,calcAfineCostsD(y,x,e,f,d));
				//Berechne Wert für stelle matrix[y][x]);
			}
		}
		e.print();
		f.print();
		d.print();
	}
	
	private static int calcAfineCostsE(int y,int x,AlignmentGraphBasic e,AlignmentGraphBasic f,AlignmentGraphBasic d)
	{
		if(x-1==0)
		{
			int costOfF = f.getMatrix(y,x-1)+f.getEffort()[1]+f.getEffort()[0];
			int result = costOfF;
			e.setMax(result);
			return result;
		}
		else
		{
			int costOfE = e.getMatrix(y,x-1)+e.getEffort()[1];
			int costOfF = f.getMatrix(y,x-1)+f.getEffort()[1]+f.getEffort()[0];
			int costOfD = d.getMatrix(y,x-1)+d.getEffort()[1]+d.getEffort()[0];
			int result = Math.min(Math.min(costOfF, costOfD),costOfE);
			e.setMax(result);
			return result;
		}

	}
	
	private static int calcAfineCostsF(int y,int x,AlignmentGraphBasic e,AlignmentGraphBasic f,AlignmentGraphBasic d)
	{
		if(y-1==0)
		{
			int costOfE = e.getMatrix(y-1,x)+e.getEffort()[1]+e.getEffort()[0];
			int result = costOfE;
			d.setMax(result);
			return result;	
		}
		else
		{
			int costOfF = f.getMatrix(y-1,x)+f.getEffort()[1];
			int costOfE = e.getMatrix(y-1,x)+e.getEffort()[1]+e.getEffort()[0];
			int costOfD = d.getMatrix(y-1,x)+d.getEffort()[1]+d.getEffort()[0];
			int result = Math.min(Math.min(costOfE, costOfD),costOfF);
			d.setMax(result);
			return result;
		}

	}
	private static int calcAfineCostsD(int y,int x,AlignmentGraphBasic e,AlignmentGraphBasic f,AlignmentGraphBasic d)
	{
		int costOfE = e.getMatrix(y,x);
		int costOfF = f.getMatrix(y,x);
		int costOfD = d.getMatrix(y-1,x-1);
		if(d.equalChars(y-1, x-1)==false)
		{
			costOfD+=d.getEffort()[2];
		}
		int result = Math.min(Math.min(costOfF, costOfD),costOfE);
		d.setMax(result);
		return result;
	}
}
