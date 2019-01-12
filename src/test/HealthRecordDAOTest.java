/*
 * 
 */
package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import database.DBUtil;
import database.HealthRecordDAO;

 
/**
 * The Class HealthRecordDAOTest.
 */
class HealthRecordDAOTest {

	/**
	 * Test delete record.
	 *
	 * @throws SQLException the SQL exception
	 */
	@Test
	void testDeleteRecord() throws SQLException {
		DBUtil con = DBUtil.getInstanca();
		con.automatskaTransakcija(false);
		try {
			assertEquals(true, HealthRecordDAO.deleteRecord("1111111111111", "1"));
		} finally {
			con.getConnection().rollback();
			con.getConnection().close();
		}
	}
}
