/*
 * 
 */
package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

 
/**
 * The Class DBUtil.
 */
public class DBUtil {
    
    /** The conn. */
    private Connection conn;
    
    /** The instanca. */
    private static DBUtil instanca;

    /**
     * Instantiates a new DB util.
     */
    private DBUtil() {
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:eKartonDB.db");
        } catch ( Exception e ) {
            System.err.println("Doslo je do greske pri konekciji na bazu podataka" + e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
    }

    /**
     * Gets the instanca.
     *
     * @return the instanca
     */
    public static DBUtil getInstanca() {
        if(instanca == null)
            instanca = new DBUtil();
        return instanca;
    }

    /**
     * Automatic transaction.
     *
     * @param on_off the on off
     * @throws SQLException the SQL exception
     */
    public void automatskaTransakcija(boolean on_off) throws SQLException { conn.setAutoCommit(on_off); }
    
    /**
     * Snimi transakciju.
     *
     * @throws SQLException the SQL exception
     */
    public void snimiTransakciju() throws SQLException { conn.commit(); }

    /**
     * Iud querry.
     *
     * @param sql the sql
     * @return the int
     * @throws SQLException the SQL exception
     */
    public int iudQuerry(String sql) throws SQLException {
        //System.out.println(sql);
        Statement statement = conn.createStatement();
        return statement.executeUpdate(sql);
    }

    /**
     * Select.
     *
     * @param sql the sql
     * @return the result set
     * @throws SQLException the SQL exception
     */
    public ResultSet select(String sql) throws SQLException {
        //System.out.println(sql);
        Statement statement= conn.createStatement();
        return statement.executeQuery(sql);
    }
    
    /**
     * Gets the connection.
     *
     * @return the connection
     */
    public Connection getConnection() {
    	return this.conn;
    }
}