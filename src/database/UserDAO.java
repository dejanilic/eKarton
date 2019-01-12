/*
 * 
 */
package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import models.User;

 
/**
 * The Class UserDAO.
 */
public class UserDAO {
	
	/** The db instance. */
	private static DBUtil dbInstance = DBUtil.getInstanca();
	
	/**
	 * Checks if a record exists.
	 * 
	 * @param user user object
	 * @return true if exists, otherwise false
	 * */
	public static boolean exist(User user) {
		String query = "SELECT * FROM user WHERE user_name = ? AND user_password = ?";
		PreparedStatement statement;
		boolean userExists = false;
		try {
			statement = dbInstance.getConnection().prepareStatement(query);
			statement.setString(1, user.getUsername());
			statement.setString(2, user.getPassword());
			ResultSet response = statement.executeQuery();
			if (response.next())
				userExists = true;
			else
				userExists = false;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userExists;
	}

	/**
	 * Updates a password for a given username.
	 * 
	 * @param username user's username
	 * @param password user's new password
	 * @return true is successful, otherwise false
	 * */
	public static boolean changePassword(String username, String password) {
		String query = "UPDATE user SET user_password = ? WHERE user_name = '" + username + "'";
		PreparedStatement statement;
		try {
			statement = dbInstance.getConnection().prepareStatement(query);
			statement.setString(1, password);
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	/**
	 * Counts rows from the user table.
	 * 
	 * @return number of rows.
	 * */
	public static String countRows() {
		String query = "SELECT COUNT(user_id) FROM user";
		int count = 0;
		try {
			for (ResultSet result = dbInstance.select(query); result.next();)
				count = result.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
			return "0";
		}
		return count + "";
	}
	
	/**
	 * Returns a password for a given firstname.
	 * 
	 * @param firstname user's first name.
	 * @return user's password
	 * */
	public static String getPasswordForName(String firstname) {
		String query = "SELECT * from user WHERE user_name = ?";
		PreparedStatement statement = null;
		String password = "";
		try {
			statement = dbInstance.getConnection().prepareStatement(query);
			statement.setString(1, firstname);
			ResultSet result = statement.executeQuery();
			if (result.next()) {
				password = result.getString("user_password");
			}
			return password;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "";
	}
}
