package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dao.UserDao;
import domain.Role;
import domain.User;
import utils.JdbcUtils;
import utils.SystemUtils;

/**
 * UserDaoImplementation
 *
 * @param
 * @author Liu
 * @return
 * @exception
 */
public class UserDaoImpl implements UserDao {

    private PreparedStatement st = null;
    private ResultSet rs = null;

    /* @param
     * @return
     * @exception
     * @see dao.UserDao#register(java.lang.String, java.lang.String)
     */
    @Override
    public void register(String name, String password) throws SQLException {
        Connection conn = JdbcUtils.getConnection();
        String sql = "insert into user (userID,name,password,date,role) values(?,?,?,?,?)";
        st = conn.prepareStatement(sql);
        st.setString(1, SystemUtils.getUUID());
        st.setString(2, name);
        st.setString(3, password);
        st.setString(4, new Date().toString());
        st.setString(5, Role.STAFF.toString());
        st.executeUpdate();
        JdbcUtils.release(st, rs);
        JdbcUtils.closeConn();
    }

    /* @param
     * @return
     * @exception
     * @see dao.UserDao#getAllUsers()
     */
    @Override
    public List<User> getAllUsers() throws SQLException {

        Connection conn = JdbcUtils.getConnection();

        String sql = "select * from user";
        st = conn.prepareStatement(sql);
        rs = st.executeQuery();
        List<User> list = new ArrayList<User>();

        while (rs.next()) {
            User u = new User();
            u.setUserID(rs.getString("userID"));
            u.setUserName(rs.getString("name"));
            u.setPassword(rs.getString("password"));
            u.setDate(rs.getString("date"));
            u.setRole(rs.getString("role"));
            list.add(u);

        }
        JdbcUtils.release(st, rs);
        JdbcUtils.closeConn();
        return list;

    }

    /* @param
     * @return
     * @exception
     * @see dao.UserDao#updateRole(java.lang.String, java.lang.String)
     */
    @Override
    public boolean updateRole(String id, String role) throws SQLException {

        Connection conn = JdbcUtils.getConnection();
        String sql = "update user set role=? where userID=?";
        st = conn.prepareStatement(sql);
        st.setString(1, role);
        st.setString(2, id);
        int result = st.executeUpdate();
        JdbcUtils.release(st, rs);
        JdbcUtils.closeConn();
        return result > 0 ? true : false;

    }
}
