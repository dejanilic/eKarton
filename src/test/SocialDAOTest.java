/*
 * 
 */
package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import database.DBUtil;
import database.SocialDAO;
import models.Social;

 
/**
 * The Class SocialDAOTest.
 */
class SocialDAOTest {

	/**
	 * Test create new record.
	 *
	 * @throws SQLException the SQL exception
	 */
	@Test
	void testCreateNewRecord() throws SQLException {
		DBUtil con = DBUtil.getInstanca();
		con.automatskaTransakcija(false);
		Social socialTest = new Social("Ozenjen", 1, "Programer", "Programer", "Srednje imucan", "Oba roditelja");
		try {
			assertEquals(true, SocialDAO.createNewRecord(socialTest));
		} finally {
			con.getConnection().rollback();
			con.getConnection().close();
		}
	}
}
