package principal;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.postgresql.util.PSQLException;

import abstractClass.Character;
import abstractClass.DaoCharacter;
import daoClass.DaoStudent;

/**
 * @author Frederick
 * Class Student to manage and details Student
 */
public class Student extends Character {
private int pkClass; // the reference Key about the student class
private String className=""; // the name of class where is the student
	
/**
 * Default constructor	
 */
public Student() {
		
	}
	
	
/**
 * accessors	
 * @return
 */
public int getPkClass() {
		return pkClass;
	}
	
	public void setPkClass(int pkClass) {
		this.pkClass=pkClass;
	}
	
	public String getClassName() {
		return className;
	}
	
	public void setClassName(String className) {
		this.className=className;
	}
	
	public void prepareLink() {
		DaoCharacter daoStudent = new DaoStudent();
		Map<String, Integer> classList = new TreeMap<>();
		classList=daoStudent.link();
		SchoolManagement.displayMap(classList);
		do {
			try {
				 pkClass = SchoolManagement.getUserInputInt("Indicate the key of correct class");
			}catch(InputMismatchException e) {
				System.err.println("Your choice is not a valid character, please try again.");
				SchoolManagement.input.nextLine();
				pkClass =-1;
			}
			if(pkClass!=-1 && (pkClass<=0 || pkClass>classList.size())) {
				System.err.println("Your choice is not correct, please try again.");
			}
		}while(pkClass<=0 || pkClass>classList.size());
	}
	
	public void save() throws PSQLException, SQLException {
		DaoCharacter daoStudent = new DaoStudent();
		daoStudent.save(this);
	}
	
	public List<Character> display() {
		DaoCharacter daoStudent = new DaoStudent();
		List<Character> list=daoStudent.display();
		return list;
	}
	
	public void update() {
		prepareCharacter();
		prepareLink();
		DaoCharacter daoStudent = new DaoStudent();
		daoStudent.update(this);
	}
	
	public void delete() {
		DaoCharacter daoStudent = new DaoStudent();
		daoStudent.delete(this);
	}
	
	public String toString() {
		String str="Key: "+id+" ==> "+kind+" "+name+" "+firstName+" (date of birth: "+birthDate+") | This student is in the "+className+" class.";
		return str;
	}
	
}
