/**
 * package daoClass => to group all classes to use for data save
 */
package daoClass;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.postgresql.util.PSQLException;

import abstractClass.Character;
import abstractClass.DaoCharacter;
import principal.Student;

/**
 * @author Frederick
 * this class is used to manage the data saved about Student
 */
public class DaoStudent implements DaoCharacter {
	private DaoConnection daoConnection;

	public DaoStudent(){
		this.daoConnection = new DaoConnection();
	}
	
	@Override
	public Map<String, Integer> link() {
		Map<String, Integer> map = new TreeMap<>();
		String rqSql = "select class_pk_id, name from class";
		Connection connection = daoConnection.getConnection();
		try (PreparedStatement ps = connection.prepareStatement(rqSql)) {
			ResultSet result = ps.executeQuery();
			while (result.next()) {
				map.put(result.getString(2), result.getInt(1));
			}
		} catch (SQLException e) {
			System.err.println(e+"\nImpossible to read the database.");
		}finally {
			daoConnection.getClose(connection);
		}
		return map;
	}
	
	@Override
	public void save(Character character) throws PSQLException, SQLException{
		Student student = (Student) character;
		String rqSql = "insert into student (kind,name,firstName,birthDate,class_pk_id) values (?,?,?,?,?)";
		Connection connection = daoConnection.getConnection();
		try(PreparedStatement ps = connection.prepareStatement(rqSql)){
			ps.setString(1, student.getKind());
			ps.setString(2, student.getName());
			ps.setString(3, student.getFirstName());
			Date dateSql = new Date(student.getBirthDate().getTime());
			ps.setDate(4, dateSql);
			ps.setInt(5, student.getPkClass());
			int result=ps.executeUpdate();
			if(result==1) {System.out.println("Your student has been added.");}
		}catch (PSQLException e) {
			System.err.println(e+"\nImpossible to insert the teacher on the database.");
		}catch (SQLException e) {
			System.err.println(e+"\nImpossible to insert the teacher on the database.");
		}finally {
			daoConnection.getClose(connection);
		}
	}
	
	@Override
	public List<Character> display() {
		List<Character> list = new ArrayList<>();
		String rqSql = "select * from student";
		Connection connection = daoConnection.getConnection();
		try(PreparedStatement ps = connection.prepareStatement(rqSql)){
			ResultSet result= ps.executeQuery();
			while (result.next()) {
				Student student = new Student();
				student.setId(result.getInt(1));
				student.setKind(result.getString(2));
				student.setName(result.getString(3));
				student.setFirstName(result.getString(4));
				student.setBirthDate(result.getDate(5));
				student.setPkClass(result.getInt(6));
				rqSql="select name from class where class_pk_id=?";
				try(PreparedStatement ps2 = connection.prepareStatement(rqSql)){
					ps2.setInt(1, student.getPkClass());
					ResultSet result2=ps2.executeQuery();
					while(result2.next()) {
					student.setClassName(result2.getString(1));
					}
				}
				list.add(student);
			}
		}catch (PSQLException e) {
			System.err.println(e+"\nImpossible to read the students on the database.");
		}catch (SQLException e) {
			System.err.println(e+"\nImpossible to read the students on the database.");
		}finally {
			daoConnection.getClose(connection);
		}
		return list;
	}
	
	@Override
	public void update(Character character) {
		Student student = (Student) character;
		String rqSql = "update student set kind=?,name=?,firstName=?,birthDate=?,class_pk_id=? where student_pk_id=?";
		Connection connection = daoConnection.getConnection();
		try(PreparedStatement ps = connection.prepareStatement(rqSql)){
			ps.setString(1, student.getKind());
			ps.setString(2, student.getName());
			ps.setString(3, student.getFirstName());
			Date dateSql = new Date(student.getBirthDate().getTime());
			ps.setDate(4, dateSql);
			ps.setInt(5, student.getPkClass());
			ps.setInt(6, student.getId());
			int result=ps.executeUpdate();
			if(result==1) {System.out.println("Your student has been modified.");}
		}catch (PSQLException e) {
			System.err.println(e+"\nImpossible to modify the student on the database.");
		}catch (SQLException e) {
			System.err.println(e+"\nImpossible to modify the student on the database.");
		}finally {
			daoConnection.getClose(connection);
		}
	}
	
	@Override
	public void delete(Character character) {
		Student student = (Student) character;
		String rqSql = "delete from student where student_pk_id=?";
		Connection connection = daoConnection.getConnection();
		try(PreparedStatement ps = connection.prepareStatement(rqSql)){
			ps.setInt(1, student.getId());
			int result=ps.executeUpdate();
			if(result==1) {System.out.println("Your student has been deleted.");}
		}catch (PSQLException e) {
			System.err.println(e+"\nImpossible to delete the student on the database.");
		}catch (SQLException e) {
			System.err.println(e+"\nImpossible to delete the student on the database.");
		}finally {
			daoConnection.getClose(connection);
		}
	}

}
