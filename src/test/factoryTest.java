package test;

import org.junit.Test;

import dao.UserDao;
import utils.DaoFactory;

/**
 * test
 *
 * @param
 * @author Liu
 * @return
 * @exception
 */
public class factoryTest {


    /**
     * @param
     * @return
     * @throws
     */
    @Test
    public void test() {
        UserDao createDao = DaoFactory.getInstance().createDao(UserDao.class);
        System.out.println(createDao);
    }
}
