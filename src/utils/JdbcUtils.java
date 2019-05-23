package utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * @param
 * @author Liu
 * @return
 * @exception
 */
public class JdbcUtils {
    // to bind connection with current thread
    private static ThreadLocal<Connection> tl = new ThreadLocal<Connection>();
    private static DataSource ds;

    static {
        ds = new ComboPooledDataSource(); // create C3p0 instance
    }

    public static DataSource getDataSource() {
        return ds;
    }


    public static Connection getConnection() throws SQLException {

        Connection conn = tl.get(); //to get current thread
        if (conn == null) {
            conn = ds.getConnection();
            tl.set(conn);

        }
        return conn;
    }

    public static void StartTransaction() throws SQLException {

        Connection conn = getConnection();
        if (conn != null) {
            conn.setAutoCommit(false);
        }


    }

    public static void rollback() throws SQLException {

        Connection con = getConnection();
        if (con != null) {
            con.rollback();
        }
    }

    public static void commitTransaction() throws SQLException {

        Connection conn = getConnection();
        if (conn != null) {
            conn.commit();
        }

    }

    public static void closeConn() {

        try {
            Connection conn = getConnection();
            if (conn != null) {
                conn.close();
            }
            conn = null;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            tl.remove();
        }
    }

    public static void release(Statement st, ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            rs = null;
        }
        if (st != null) {
            try {
                st.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            st = null;
        }

    }

}



