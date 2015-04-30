import org.eclipse.swt.widgets.Text;
import java.lang.Double;
 /**
  * Creates a new Grade object to represent one grade
  * 
  * @author Jeremy Sawh
  * @version 2015-04-28
  *
  */

public class Grade {
	
	private double grade;
	private double weighting;
	private String name;
	
	/**
	 * Constructor for class Grade
	 * Takes Grade name, the grade and it's weighting
	 */
	public Grade(Text name, Text grade, Text weighting)
	{
		this.name = name.getText();
		this.grade = Double.parseDouble(grade.getText());
		this.weighting = Double.parseDouble(weighting.getText());
	}
	
	/**
	 * @return The name of Grade
	 * EX. Assignment 1
	 */
	public String getName()
	{
		return name;
	}
	
	/**
	 * @return grade
	 * EX. 89.58
	 */
	public double getGrade()
	{
		return grade;
	}
	
	/**
	 * @return grade weighting
	 * EX. 20%
	 */
	public double getWeight()
	{
		return weighting;
	}

	/**
	 * Calculates the weighted Grade
	 * @return
	 */
	public double CalculatedGrade()
	{
		return getWeight()/100 * getGrade();
	}
	
	/**
	 * Returns  String Representation of Grade name, grade and Weighting
	 * Example: Exam, 80, 50%
	 * @return String  
	 */
	public String toString()
	{
		String S = name + ", " + Double.toString(getGrade()) + ", " +  Double.toString(getWeight()) + "%";
		return S;
	
	}
}
