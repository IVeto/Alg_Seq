package types;

public class Suffix implements Comparable<Suffix>{
	private String text;
	private int startIndex;
	private int endIndex;
	
	public Suffix(String text,int start,int end)
	{
		this.text=text;
		startIndex=start;
		endIndex=end;
	}
	/**
	 * @return the startIndex
	 */
	public int getStartIndex() {
		return startIndex;
	}
	/**
	 * @param startIndex the startIndex to set
	 */
	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}
	/**
	 * @return the endIndex
	 */
	public int getEndIndex() {
		return endIndex;
	}
	/**
	 * @param endIndex the endIndex to set
	 */
	public void setEndIndex(int endIndex) {
		this.endIndex = endIndex;
	}
    public int length() {
        return endIndex-startIndex+1;
    }
    public char charAt(int i) {
        return text.charAt(startIndex + i);
    }
    public int compareTo(Suffix that) {
        if (this.equals(that)) return 0;  // optimization
        int N = Math.min(this.length(), that.length());
        for (int i = 0; i < N; i++) {
            if (this.charAt(i) < that.charAt(i)) return -1;
            if (this.charAt(i) > that.charAt(i)) return +1;
        }
        return this.length() - that.length();
    }
    public String getSuffix()
    {
    	return text.substring(startIndex, endIndex);
    }
}
