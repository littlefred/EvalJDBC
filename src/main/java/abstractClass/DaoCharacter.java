/**
 * package abstract => to group all classes who are abstract
 */
package abstractClass;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.postgresql.util.PSQLException;

/**
 * @author Frederick
 * an interface to implement the method in depends of object
 * this class was made to separate the fonctionales classes of management of data save
 */
public interface DaoCharacter {
	
	public Map<String, Integer> link();
	
	public void save(Character character) throws PSQLException, SQLException;
	
	public List<Character> display();
	
	public void update(Character character);
	
	public void delete(Character character);

}
