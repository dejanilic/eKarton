/*
 * 
 */
package test;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import database.DBUtil;
import database.UserDAO;
import models.User;

 
/**
 * The Class UserDAOTest.
 */
class UserDAOTest {
	
	/**
	 * Test exist.
	 */
	@Test
	void testExist() {
		assertEquals(true, UserDAO.exist(new User("demo", "demo")));
		assertEquals(false, UserDAO.exist(new User("demo123", "demo123")));
	}

	/**
	 * Test change password.
	 *
	 * @throws SQLException the SQL exception
	 */
	@Test
	void testChangePassword() throws SQLException {
		DBUtil con = DBUtil.getInstanca();
		con.automatskaTransakcija(false);
		try {
			UserDAO.changePassword("demo", "newdemopassword");
			assertEquals("newdemopassword", UserDAO.getPasswordForName("demo"));
		} finally {
			con.getConnection().rollback();
			con.getConnection().close();
		}
	}
}
