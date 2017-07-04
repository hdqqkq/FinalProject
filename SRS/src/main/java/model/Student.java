package model;

import java.util.ArrayList;
import java.util.Collection;

public class Student extends Person {
	
	private String major;
	private String degree;
	private Transcript transcript;
	private ArrayList<Section> attends; 		
	public Student(String name, String ssn, String major, String degree) {
	
		super(name, ssn);

		this.setMajor(major);
		this.setDegree(degree);

		this.setTranscript(new Transcript(this));

		attends = new ArrayList<Section>();
	}
	
	public Student(String ssn) {
	
		this("TBD", ssn, "TBD", "TBD");
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public String getMajor() {
		return major;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}

	public String getDegree() {
		return degree;
	}

	public void setTranscript(Transcript t) {
		transcript = t;
	}

	public Transcript getTranscript() {
		return transcript;
	}


	public void display() {
		// First, let's display the generic Person info.

		super.display();
		
		// Then, display Student-specific info.

		System.out.println("Student-Specific Information:");
		System.out.println("\tMajor:  " + this.getMajor());
		System.out.println("\tDegree:  " + this.getDegree());
		this.displayCourseSchedule();
		this.printTranscript();

		// Finish with a blank line.
		System.out.println();
	}	

	public String toString() {
		return this.getName() + " (" + this.getSsn() + ") [" + this.getDegree() +
		       " - " + this.getMajor() + "]";
	}

	public void displayCourseSchedule() {
		// Display a title first.

		System.out.println("Course Schedule for " + this.getName());
		

		for (Section s : attends) {

                    if (s.getGrade(this) == null) {
			System.out.println("\tCourse No.:  " + 
				s.getRepresentedCourse().getCourseNo());
			System.out.println("\tSection No.:  " + 
				s.getSectionNo());
			System.out.println("\tCourse Name:  " + 
				s.getRepresentedCourse().getCourseName());
			System.out.println("\tMeeting Day and Time Held:  "  +
				s.getDayOfWeek() + " - " +
				s.getTimeOfDay());
			System.out.println("\tRoom Location:  "  +
				s.getRoom());
			System.out.println("\tProfessor's Name:  " +
				s.getInstructor().getName());
			System.out.println("\t-----");
		    }
		}
	}
	
	public void addSection(Section s) {
		attends.add(s);
	}
	
	public void dropSection(Section s) {
		attends.remove(s);
	}

	public boolean isEnrolledIn(Section s) {
		if (attends.contains(s)) return true;
		else return false;
	}
		

	public boolean isCurrentlyEnrolledInSimilar(Section s1) {
		boolean foundMatch = false;

		Course c1 = s1.getRepresentedCourse();

		for (Section s2 : attends) {
			Course c2 = s2.getRepresentedCourse();
			if (c1.getCourseNo().equals(c2.getCourseNo())) {
				if (s2.getGrade(this) == null) {
				
					foundMatch = true;
					break;
				}
			}
		}

		return foundMatch;
	}
		
	public void printTranscript() {
		this.getTranscript().display();
	}

	public Collection<Section> getEnrolledSections() {
		return attends;
	}
}
