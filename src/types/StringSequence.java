package types;
/**
 * 
 * @author Axel Zieschank (210234303)
 * @author Dominik Wittig (212220939)
 */
import java.util.ArrayList;

/**
 * Modelliert Beschreibung eines Gens und die dazugehörige Sequenz
 * @author Axel Zieschank
 *
 */
public class StringSequence {
	/** 
	 * Gensequenz
	 */
	private String sequence=null;
	/**
	 * Beschreibung der Sequenz
	 */
	private String description=null;
	private String pattern=null;
	/**
	 * @return the pattern
	 */
	public String getPattern() {
		return pattern;
	}

	/**
	 * @param pattern the pattern to set
	 */
	public void setPattern(String pattern) {
		this.pattern = pattern;
	}

	/**
	 * @return the foundPositionList
	 */
	public ArrayList<Integer> getFoundPositionList() {
		return foundPositionList;
	}

	/**
	 * @param foundPositionList the foundPositionList to set
	 */
	public void setFoundPositionList(ArrayList<Integer> foundPositionList) {
		this.foundPositionList = foundPositionList;
	}
	/**
	 * Liste der Gefunden Position des Patterns
	 */
	private ArrayList<Integer> foundPositionList = new ArrayList<Integer>();
	public StringSequence(String description,String sequence)
	{
		this.description=description;
		this.sequence=sequence;
	}

	/**
	 * Liefert die Gensequenz
	 * @return Gensequenz
	 */
	public String getSequence()
	{
		return this.sequence;
	}
	/**
	 * Liefert die Beschreibung
	 * @return Beschreibung der Sequence
	 */
	public String getDescription()
	{
		return this.description;
	}
}
