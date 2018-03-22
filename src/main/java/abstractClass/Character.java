/**
 * package abstract => to group all classes who are abstract
 */
package abstractClass;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.postgresql.util.PSQLException;

import principal.SchoolManagement;

/**
 * @author Frederick
 * this class is parent of Teacher and Student
 */
public abstract class Character {
	protected Long id;
	protected String kind;
	protected String name;
	protected String firstName;
	protected Date birthDate;
	
	/**
	 * accessors
	 * @return
	 */
	public int getId() {
		return id.intValue();
	}

	public void setId(int id) {
		this.id = new Long(id);
	}

	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	/**
	 * common method of Teacher and Student to keep the user informations for add an element 
	 */
	public void prepareCharacter() {
		boolean answer = false;
		do {
			String kind = SchoolManagement.getUserInputString("What is his kind (Mr, Ms or Mrs) ");
			if (kind.equals("Mr") || kind.equals("Ms") || kind.equals("Mrs")) {
				this.kind = kind;
				answer = true;
			}
		} while (!answer);
		String name;
		do {
			name = SchoolManagement.getUserInputString("What is his name");
		} while (name.equals(""));
		this.name = name;
		String firstName;
		do {
			firstName = SchoolManagement.getUserInputString("What is his firstname");
		} while (firstName.equals(""));
		this.firstName = firstName;
		this.birthDate = SchoolManagement.getUserInputDate("What is his birth date (YYYY-MM-DD) ");
	};

	/**
	 * method to see the good link between Teacher and teaches or Student and Classes
	 */
	public void prepareLink() {
	};

	/**
	 * method to save a new object (Teacher or Student) in database
	 * @throws PSQLException
	 * @throws SQLException
	 */
	public void save() throws PSQLException, SQLException {
	};

	/**
	 * method to update an object (Teacher or Student) 
	 */
	public void update() {
	};

	/**
	 * method to delete an object (Teacher or Student)
	 */
	public void delete() {
	};

	/**
	 * method to find information in database to manage the display
	 * @return
	 */
	public List<Character> display() {
		return null;
	};

}
