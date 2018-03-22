package principal;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.postgresql.util.PSQLException;

import abstractClass.Character;
import abstractClass.DaoCharacter;
import daoClass.DaoTeacher;
import principal.SchoolManagement;

/**
 * @author Frederick
 * Class Teacher to manage and details Teacher
 */
public class Teacher extends Character {
	private int subjectTeach=-1; // the reference Key about the teach subject about teacher
	private String subject=""; // the name of teach subject of the teacher

	/**
	 * default constructor
	 */
	public Teacher() {

	}
	
	/**
	 * accessors
	 * @return
	 */
	public int getSubjectTeach() {
		return subjectTeach;
	}
	
	public void setSubjectTeach(int subjectTeach) {
		this.subjectTeach=subjectTeach;
	}
	
	public String getSubject() {
		return subject;
	}
	
	public void setSubject(String subject) {
		this.subject=subject;
	}
	
	public void prepareLink() {
		DaoCharacter daoTeacher = new DaoTeacher();
		Map<String, Integer> teachMap = new TreeMap<>();
		teachMap=daoTeacher.link();
		SchoolManagement.displayMap(teachMap);
		do {
			try {
				 subjectTeach = SchoolManagement.getUserInputInt("Indicate the key of teacher's subject");
			}catch(InputMismatchException e) {
				System.err.println("Your choice is not a valid character, please try again.");
				SchoolManagement.input.nextLine();
				subjectTeach=-1;
			}
			if(subjectTeach!=-1 && (subjectTeach<=0 || subjectTeach>teachMap.size())) {
				System.err.println("Your choice is not correct, please try again.");
			}
		}while(subjectTeach<=0 || subjectTeach>teachMap.size());
	}
	
	public void save() throws PSQLException, SQLException {
		DaoCharacter daoTeacher = new DaoTeacher();
		daoTeacher.save(this);
	}
	
	public List<Character> display() {
		DaoCharacter daoTeacher = new DaoTeacher();
		List<Character> list=daoTeacher.display();
		return list;
	}
	
	public void update() {
		prepareCharacter();
		prepareLink();
		DaoCharacter daoTeacher = new DaoTeacher();
		daoTeacher.update(this);
	}
	
	public void delete() {
		DaoCharacter daoTeacher = new DaoTeacher();
		daoTeacher.delete(this);
	}
	
	public String toString() {
		String str="Key: "+id+" ==> "+kind+" "+name+" "+firstName+" (date of birth: "+birthDate+") | teach "+subject;
		return str;
	}

}
