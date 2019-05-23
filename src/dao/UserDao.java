package dao;

import java.sql.SQLException;
import java.util.List;

import domain.User;

/**
 * @param
 * @author Liu
 * @return
 * @exception
 */
public interface UserDao {

    void register(String name, String password) throws SQLException;

    List<User> getAllUsers() throws SQLException;

    boolean updateRole(String id, String role) throws SQLException;

}