import org.eclipse.swt.widgets.Text;
import java.util.ArrayList;

/**
 * This Class Handles all Course objects. 
 * Each Course will store grades associated with it
 * These are stored in a Array List of Grade objects.
 * 
 * @author Jeremy Sawh
 * @version 2015-04-28
 *
 */

public class Course {
	
	private Text name;
	private Text courseID;
	private ArrayList<Grade> grades;
	
	/**
	 * Constructor for this Course Object
	 * @param name
	 * @param courseID
	 */
	public Course(Text name, Text courseID)
	{
		this.name = name;
		this.courseID = courseID;
		grades = new ArrayList<Grade>();
	}
	
	/**
	 * @return Name
	 */
	public String getName()
	{
		return name.getText();
	}
	
	/**
	 * @return CourseID
	 */
	public String getCourseID()
	{
		return courseID.getText();
	}
	
	/**
	 * Returns the Course Name and Course Number
	 * ex. SYSC 2004
	 * @return String
	 */
	
	public String toString()
	{
		return getName() + " " +  getCourseID();
	}
	
	/**
	 * Creates a new Grade object and add's it to and Array-list
	 * @param name
	 * @param grade
	 * @param weighting
	 */
	
	public void addGrade(Text name, Text grade, Text weighting)
	{
		Grade newGrade = new Grade(name, grade, weighting);
		grades.add(newGrade);
		
	}
	
	public String[] ListofGrades()
	{
		ArrayList<String> GradeList = new ArrayList<String>();
		for(Grade G: grades)
		{
			GradeList.add(G.toString());
		}
		String[] GradeListArray = GradeList.toArray(new String[GradeList.size()]);		
		return GradeListArray;
	}

	
	/**
	 * Determines the total weighting off all grades
	 * @return Combined weighting of all grades
	 */
	
	public double CombinedWeighting()
	{
		double weight = 0;
		for(Grade G: grades)
		{
			weight = weight + G.getWeight();
		}
		return weight;
	}
	
	/**
	 * Returns the Average of all grades in ArrayList
	 * @return Weighted average of all Grades in course
	 */
	public double Average()
	{
		double sum = 0;		
		for(Grade G: grades)
		{
			sum = sum + (G.getWeight() * G.getGrade());
		}
		return sum/CombinedWeighting();
	}
	

}
