import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.jface.viewers.ListViewer;



import java.util.ArrayList;

import org.eclipse.swt.custom.CLabel;

/**
 * GUI manager for Grades Manager
 * 
 * @author Jeremy Sawh
 * @version 2015-04-28
 */
public class GradeManagerGUI {

	protected Shell shell;
	private Course course;
	
	private Text CourseNumber;
	private Text CourseName;
	private Text GradeName;
	private Text GradeWeighting;
	private Text GradeNumber;

	private ArrayList<String> S;		
	private ArrayList<Course> Courses; 

	private static int NEGATIVE_ONE = -1;
	private int SelectedItemIndex;
	

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			GradeManagerGUI window = new GradeManagerGUI();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(500, 289);  //451,300
		shell.setText("Grade Manager");
		
		
		/**
		 * Text fields located here
		 */
		CourseName = new Text(shell, SWT.BORDER);
		CourseName.setBounds(10, 44, 76, 21);
		
		CourseNumber = new Text(shell, SWT.BORDER);
		CourseNumber.setBounds(115, 44, 76, 21);
		
		GradeName = new Text(shell, SWT.BORDER);
		GradeName.setBounds(115, 105, 76, 21);
		
		GradeNumber = new Text(shell, SWT.BORDER);
		GradeNumber.setBounds(210, 105, 76, 21);
		
		GradeWeighting = new Text(shell, SWT.BORDER);
		GradeWeighting.setBounds(304, 105, 76, 21);

		/**
		 * Labels Located here
		 */
		Label lblCouseName = new Label(shell, SWT.NONE);
		lblCouseName.setBounds(10, 23, 76, 15);
		lblCouseName.setText("Couse Name");
		
		Label lblCourseNumber = new Label(shell, SWT.NONE);
		lblCourseNumber.setBounds(115, 23, 88, 15);
		lblCourseNumber.setText("Course Number");
		
		Label lblOutput = new Label(shell, SWT.NONE);
		lblOutput.setBounds(10, 148, 88, 15);
		lblOutput.setText("List of Grades: ");
		
		Label lblGradeName = new Label(shell, SWT.NONE);
		lblGradeName.setBounds(115, 84, 76, 15);
		lblGradeName.setText("Grade Name");
		
		Label lblGrade = new Label(shell, SWT.NONE);
		lblGrade.setBounds(213, 84, 55, 15);
		lblGrade.setText("Grade #");
		
		Label lblWeighting = new Label(shell, SWT.NONE);
		lblWeighting.setBounds(304, 84, 55, 15);
		lblWeighting.setText("Weighting %");
		
		Label lblSelect = new Label(shell, SWT.NONE);
		lblSelect.setBounds(10, 84, 88, 15);
		lblSelect.setText("Select Course");
		
		CLabel lblAverage = new CLabel(shell, SWT.NONE);
		lblAverage.setBounds(210, 169, 264, 21);
		lblAverage.setText("Average: ");
		
		CLabel lblCombinedWeighting = new CLabel(shell, SWT.NONE);
		lblCombinedWeighting.setBounds(210, 191, 264, 21);
		lblCombinedWeighting.setText("Combined Weighting: ");
		
		ListViewer listViewer = new ListViewer(shell, SWT.BORDER | SWT.V_SCROLL);
		List list = listViewer.getList();
		list.setBounds(10, 169, 160, 68);
		
		
		/**
		 * Combo Boxes
		 */
		
		//Combo Box and Combo Box listener
		Combo combo = new Combo(shell, SWT.NONE);
		combo.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				System.out.println("Selected");
				SelectedItemIndex = combo.getSelectionIndex();
				list.setItems(Courses.get(SelectedItemIndex).ListofGrades());
				lblAverage.setText("Average:                           " +  Courses.get(SelectedItemIndex).Average());
				lblCombinedWeighting.setText("Combined Weighting:   " + Courses.get(SelectedItemIndex).CombinedWeighting());
								
			}
		});
		combo.setBounds(10, 105, 91, 23);
		

		Courses = new ArrayList<Course>(); //Will hold Course Objects
		S = new ArrayList<String>(); //String Array for Combo Box
		
		/**
		 * Button and Button Listeners
		 */
		
		//"Add Course" Button and ButtonListener
		Button btnAddCourse = new Button(shell, SWT.NONE);
		btnAddCourse.setBounds(399, 42, 75, 25);
		btnAddCourse.setText("Add Course");
		btnAddCourse.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				//If Text Fields are empty do not add them to combo Box
				if(CourseName.getText().equals("") || CourseNumber.getText().equals("")){}
				else{
					
				//Create a new course object, then add it to the Courses ArrayList	
				course = new Course(CourseName, CourseNumber);
				Courses.add(course);
				
				//Produce a String Array to display contents to GUI combo Box
				S.add(course.toString());
				String[] S1 = S.toArray(new String[S.size()]);
				System.out.println("S1.length = " + S1.length);
								
				combo.setItems(S1);
				}
				
											
			}
		});

		//"Add Grade" Button and Button Listener
		Button btnAddGrade = new Button(shell, SWT.NONE);
		btnAddGrade.setBounds(399, 103, 75, 25);
		btnAddGrade.setText("Add Grade");
		btnAddGrade.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {				
				
				//Get Selected Item from Combo Box
				SelectedItemIndex = combo.getSelectionIndex();
				
				//Make sure Combo Box is not referencing NULL
				if(SelectedItemIndex != NEGATIVE_ONE)
				{					
					if(GradeName.getText().equals("")||GradeNumber.getText().equals("")|| GradeWeighting.getText().equals("")){}
					else{
					Courses.get(SelectedItemIndex).addGrade(GradeName, GradeNumber, GradeWeighting);
					list.setItems(Courses.get(SelectedItemIndex).ListofGrades());
					lblAverage.setText("Average:                           " +  Courses.get(SelectedItemIndex).Average());
					lblCombinedWeighting.setText("Combined Weighting:   " + Courses.get(SelectedItemIndex).CombinedWeighting());
					}
				 }				
			}
		});
		

	}
}
