/*
 * 
 */
package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import models.Social;

 
/**
 * The Class SocialDAO.
 */
public class SocialDAO {
	
	/** The db instance. */
	private static DBUtil dbInstance = DBUtil.getInstanca();
	
	/**
	 * Creates a new record in the social data table, for a given social object.
	 * 
	 * @param social social object
	 * @return true if insertion is successfu, otherwise false
	 * */
	public static boolean createNewRecord(Social social) {
		String query = "INSERT INTO social_data(marital_status, number_of_children, vocation, occupation, material_status, family_status)"
				+ "VALUES(?, ?, ?, ?, ?, ?)";
		PreparedStatement statement;
		try {
			statement = dbInstance.getConnection().prepareStatement(query);
			statement.setString(1, social.getMaritalStatus());
			statement.setInt(2, social.getNumOfChildren());
			statement.setString(3, social.getVocation());
			statement.setString(4, social.getOccupation());
			statement.setString(5, social.getMaterialStatus());
			statement.setString(6, social.getFamilyStatus());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	/**
	 * Returns a last record's id from the social data table.
	 * 
	 * @return last record id
	 * */
	public static int getLastRecordId() {
		String query = "SELECT * FROM social_data ORDER BY social_data_id DESC LIMIT 1";
		int lastRecord = 0;
		try {
			ResultSet result = dbInstance.select(query);
			if (result.next())
				lastRecord = result.getInt("social_data_id");
			else
				lastRecord = 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lastRecord;
	}
	
	/**
	 * Returns social object for a given JMBG and record number.
	 * 
	 * @param JMBG user's JMBG
	 * @param recordNumber user's record number
	 * @return social object
	 * */
	public static Social getSocial(String JMBG, int recordNumber) {
		String query = "SELECT * FROM social_data WHERE social_data_id IN(SELECT social_data_id FROM health_record_data WHERE JMBG = "
				+ JMBG + " AND record_number = " + recordNumber + ")";	
		Social social = null;
		try {
			ResultSet result = dbInstance.select(query);
			if (result.next()) {
				String maritalStatus = result.getString("marital_status");
				int numOfChildren = result.getInt("number_of_children");
				String vocation = result.getString("vocation");
				String occupation = result.getString("occupation");
				String materialStatus = result.getString("material_status");
				String familyStatus = result.getString("family_status");
				social = new Social(maritalStatus, numOfChildren, vocation, occupation, materialStatus, familyStatus);
			}
		} catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
		return social;
	}

	/**
	 * Updates a specific record for a given social object, jmbg and record number.
	 * 
	 * @param social social object
	 * @param jmbg user's JMBG
	 * @param recordNum user's record number
	 * @return true if update is successful, otherwise false
	 * */
	public static boolean updateRecord(Social social, String jmbg, String recordNum) {
		String query = "UPDATE social_data SET marital_status = ?, number_of_children = ?, vocation = ?, occupation = ?, material_status = ?, family_status = ? "
				+ "WHERE social_data_id IN(SELECT social_data_id FROM health_record_data WHERE JMBG = " + jmbg + " AND "
				+ "record_number = " + recordNum + ")";
		PreparedStatement statement;
		try {
			statement = dbInstance.getConnection().prepareStatement(query);
			statement.setString(1, social.getMaritalStatus());
			statement.setInt(2, social.getNumOfChildren());
			statement.setString(3, social.getVocation());
			statement.setString(4, social.getOccupation());
			statement.setString(5, social.getMaterialStatus());
			statement.setString(6, social.getFamilyStatus());
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
		String query = "DELETE FROM social_data WHERE social_data_id IN(SELECT social_data_id FROM health_record_data WHERE JMBG = " + jmbg + " AND "
				+ "record_number = " + recordNumber + ")";
		try {
			dbInstance.iudQuerry(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
