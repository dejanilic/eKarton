/*
 * 
 */
package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import models.Contact;

 
/**
 * The Class ContactDAO.
 */
public class ContactDAO {
	
	/** The db instance. */
	private static DBUtil dbInstance = DBUtil.getInstanca();

	/**
	 * Returns a last record's id from the contact table.
	 * 
	 * @return last record id
	 * */
	public static int getLastRecordId() {
		String query = "SELECT * FROM contact ORDER BY contact_id DESC LIMIT 1";
		int lastRecord = 0;
		try {
			ResultSet result = dbInstance.select(query);
			if (result.next())
				lastRecord = result.getInt("contact_id");
			else
				lastRecord = 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lastRecord;
	}

	/**
	 * Creates a new record in the contact table, for a given contact object.
	 * 
	 * @param contact contact object
	 * @return true if insertion is successfu, otherwise false
	 * */
	public static boolean createNewRecord(Contact contact) {
		String query = "INSERT INTO contact(phone, mobile_phone, email) VALUES(?, ?, ?)";
		PreparedStatement statement;
		try {
			statement = dbInstance.getConnection().prepareStatement(query);
			statement.setString(1, contact.getPhone());
			statement.setString(2, contact.getMobilePhone());
			statement.setString(3, contact.getEmail());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * Returns a specific contact from the contact table, for a given JMBG and record number.
	 * 
	 * @param JMBG user's JMBG
	 * @param recordNumber user's record number
	 * @return contact if exists, otherwise null
	 * */
	public static Contact getContact(String JMBG, int recordNumber) {
		String query = "SELECT * FROM contact WHERE contact_id IN(SELECT contact_id FROM health_record_data WHERE JMBG = ? AND record_number = ?)";
		PreparedStatement statement;
		Contact contact = null;
		try {
			statement = dbInstance.getConnection().prepareStatement(query);
			statement.setString(1, JMBG);
			statement.setInt(2, recordNumber);
			ResultSet result = statement.executeQuery();
			if (result.next()) {
				String phone = result.getString("phone");
				String mobilePhone = result.getString("mobile_phone");
				String email = result.getString("email");
				contact = new Contact(phone, mobilePhone, email);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return contact;
	}

	/**
	 * Updates a specific record for a given contact object, JMBG and record number.
	 * 
	 * @param contact contact object
	 * @param jmbg user's JMBG
	 * @param recordNum user's record number
	 * @return true if successfully updated, otherwise false
	 * */
	public static boolean updateRecord(Contact contact, String jmbg, String recordNum) {
		String query = "UPDATE contact SET phone = ?, mobile_phone = ?, email = ?"
				+ "WHERE contact_id IN(SELECT contact_id FROM health_record_data WHERE JMBG = ? AND "
				+ "record_number = ?)";
		PreparedStatement statement;
		try {
			statement = dbInstance.getConnection().prepareStatement(query);
			statement.setString(1, contact.getPhone());
			statement.setString(2, contact.getMobilePhone());
			statement.setString(3, contact.getEmail());
			statement.setString(4, jmbg);
			statement.setString(5, recordNum);
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * Deletes a specific record for a given JMBG and record number.
	 * 
	 * @param jmbg user's JMBG
	 * @param recordNumber user's record number
	 * @return void
	 * */
	public static void deleteRecord(String jmbg, String recordNumber) {
		String query = "DELETE FROM contact WHERE contact_id IN(SELECT contact_id FROM health_record_data WHERE JMBG = ? AND "
				+ "record_number = ?)";
		PreparedStatement statement;
		try {
			statement = dbInstance.getConnection().prepareStatement(query);
			statement.setString(1, jmbg);
			statement.setString(2, recordNumber);
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
