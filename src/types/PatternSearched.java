package types;
/**
 * 
 * @author Axel Zieschank (210234303)
 * @author Dominik Wittig (212220939)
 */
import java.util.ArrayList;

public class PatternSearched {

	private String patern;
	private ArrayList<Integer> matchPositions = new ArrayList<Integer>();
	public String getPatern() {
		return patern;
	}
	public void setPatern(String patern) {
		this.patern = patern;
	}
	public ArrayList<Integer> getMatchePositions() {
		return matchPositions;
	}
	public void setMatchePositions(ArrayList<Integer> matchePositions) {
		this.matchPositions = matchePositions;
	}
	public void addMatchPosition(Integer matchToAdd)
	{
		matchPositions.add(matchToAdd);
	}
}
