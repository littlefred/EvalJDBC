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

import abstractClass.DaoCharacter;
import abstractClass.Character;

import principal.Teacher;

/**
 * @author Frederick
 * this class is used to manage the data saved about Teacher
 */
public class DaoTeacher implements DaoCharacter {
	private DaoConnection daoConnection;
	
	public DaoTeacher(){
		this.daoConnection = new DaoConnection();
	}

	@Override
	public Map<String, Integer> link() {
		Map<String, Integer> map = new TreeMap<>();
		String rqSql = "select teach_pk_id, name from teach";
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
	public void save(Character character) throws PSQLException, SQLException {
		Teacher teacher = (Teacher) character;
		String rqSql = "insert into teacher (kind,name,firstName,birthDate,teach_pk_id) values (?,?,?,?,?)";
		Connection connection = daoConnection.getConnection();
		try(PreparedStatement ps = connection.prepareStatement(rqSql)){
			ps.setString(1, teacher.getKind());
			ps.setString(2, teacher.getName());
			ps.setString(3, teacher.getFirstName());
			Date dateSql = new Date(teacher.getBirthDate().getTime());
			ps.setDate(4, dateSql);
			ps.setInt(5, teacher.getSubjectTeach());
			int result=ps.executeUpdate();
			if(result==1) {System.out.println("Your teacher has been added.");}
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
		String rqSql = "select * from teacher";
		Connection connection = daoConnection.getConnection();
		try(PreparedStatement ps = connection.prepareStatement(rqSql)){
			ResultSet result= ps.executeQuery();
			while (result.next()) {
				Teacher teacher = new Teacher();
				teacher.setId(result.getInt(1));
				teacher.setKind(result.getString(2));
				teacher.setName(result.getString(3));
				teacher.setFirstName(result.getString(4));
				teacher.setBirthDate(result.getDate(5));
				teacher.setSubjectTeach(result.getInt(6));
				rqSql="select name from teach where teach_pk_id=?";
				try(PreparedStatement ps2 = connection.prepareStatement(rqSql)){
					ps2.setInt(1, teacher.getSubjectTeach());
					ResultSet result2=ps2.executeQuery();
					while(result2.next()) {
					teacher.setSubject(result2.getString(1));
					}
				}
				list.add(teacher);
			}
		}catch (PSQLException e) {
			System.err.println(e+"\nImpossible to read the teachers on the database.");
		}catch (SQLException e) {
			System.err.println(e+"\nImpossible to read the teachers on the database.");
		}finally {
			daoConnection.getClose(connection);
		}
		return list;
	}
	
	@Override
	public void update(Character character) {
		Teacher teacher = (Teacher) character;
		String rqSql = "update teacher set kind=?,name=?,firstName=?,birthDate=?,teach_pk_id=? where teacher_pk_id=?";
		Connection connection = daoConnection.getConnection();
		try(PreparedStatement ps = connection.prepareStatement(rqSql)){
			ps.setString(1, teacher.getKind());
			ps.setString(2, teacher.getName());
			ps.setString(3, teacher.getFirstName());
			Date dateSql = new Date(teacher.getBirthDate().getTime());
			ps.setDate(4, dateSql);
			ps.setInt(5, teacher.getSubjectTeach());
			ps.setInt(6, teacher.getId());
			int result=ps.executeUpdate();
			if(result==1) {System.out.println("Your teacher has been modified.");}
		}catch (PSQLException e) {
			System.err.println(e+"\nImpossible to modify the teacher on the database.");
		}catch (SQLException e) {
			System.err.println(e+"\nImpossible to modify the teacher on the database.");
		}finally {
			daoConnection.getClose(connection);
		}
	}
	
	@Override
	public void delete(Character character) {
		Teacher teacher = (Teacher) character;
		String rqSql = "delete from teacher where teacher_pk_id=?";
		Connection connection = daoConnection.getConnection();
		try(PreparedStatement ps = connection.prepareStatement(rqSql)){
			ps.setInt(1, teacher.getId());
			int result=ps.executeUpdate();
			if(result==1) {System.out.println("Your teacher has been deleted.");}
		}catch (PSQLException e) {
			System.err.println(e+"\nImpossible to delete the teacher on the database.");
		}catch (SQLException e) {
			System.err.println(e+"\nImpossible to delete the teacher on the database.");
		}finally {
			daoConnection.getClose(connection);
		}
	}

}
