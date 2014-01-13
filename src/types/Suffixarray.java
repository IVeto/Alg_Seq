package types;

import java.util.Arrays;

public class Suffixarray {
	private String text;
	Suffix[] suffarr;
	
	public Suffixarray(String givenText)
	{
		this.text=givenText+"$";
		this.suffarr = new Suffix[text.length()];
		for(int i=0;i<text.length();i++)
		{
			Suffix current = new Suffix(text,i,text.length());
			suffarr[i]=current;
		}
		Arrays.sort(suffarr);
	}
	public void printsuffs()
	{
		for(int i=0;i<suffarr.length;i++)
		{
			System.out.println(text.substring(suffarr[i].getStartIndex(), suffarr[i].getEndIndex()));
		}
	}
	public void search(String pattern)
	{
		int leftBorder=0;
		int rightBorder=suffarr.length;
		int mid;
		int startIndex=0;
		while (leftBorder<rightBorder)
		{
			mid=((leftBorder+rightBorder)/2);
			if(compare(pattern,suffarr[mid])>0)
			{
				leftBorder=mid+1;
			}
			else
			{
				rightBorder=mid;
			}
			System.out.println("Linke Intervallsgrenze "+leftBorder+":"+rightBorder);
		}
		startIndex=leftBorder;
		rightBorder=suffarr.length;
		while (leftBorder<rightBorder)
		{
			mid=((leftBorder+rightBorder)/2);
			//System.out.println(leftBorder+":"+rightBorder+":"+mid);
			if(compare(pattern,suffarr[mid])<0)
			{
				rightBorder=mid;
			}
			else
			{
				leftBorder=mid+1;
			}
			System.out.println("Rechte Intervallsgrenze "+leftBorder+":"+rightBorder);
		}
		for(int i=startIndex;i<(rightBorder);i++)
		{
			System.out.println("Fundstelle an Position:"+(suffarr[i].getStartIndex()+1));
		}
	}
	
	private int compare(String pattern,Suffix suffix)
	{
		int max = Math.min(pattern.length(), suffix.length());
        for (int i = 0; i < max; i++) {
            if (pattern.charAt(i) < suffix.charAt(i)) return -1;
            if (pattern.charAt(i) > suffix.charAt(i)) return +1;
        }
        return 0;
	}
	

}
