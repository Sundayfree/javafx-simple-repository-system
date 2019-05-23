package test;

import java.sql.SQLException;
import java.util.List;

import org.junit.Test;

import dao.UserDao;
import dao.impl.UserDaoImpl;
import domain.User;
import service.BusinessService;
import service.impl.BusinessServiceImpl;

/**
 * test
 *
 * @param
 * @author Liu
 * @return
 * @exception
 */
public class userTest {

    UserDao udao = new UserDaoImpl();


    public void list() throws SQLException {
        List<User> list = udao.getAllUsers();
        for (User u : list) {
            System.out.println(u.getUserID());
            System.out.println(u.getUserName());
            System.out.println(u.getDate());
            System.out.println(u.getRole());
        }

    }

    @Test
    public void register() throws SQLException {
        udao.register("wang", "123");
    }

    public void find() throws Exception {
        BusinessService s = new BusinessServiceImpl();
        List<User> list = s.getAllStaff();
        for (User u : list) {
            System.out.println(u.getUserName());
        }
    }

}
