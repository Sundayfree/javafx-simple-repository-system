package test;

import java.sql.SQLException;
import java.util.List;

import org.junit.Test;

import dao.SupplierDao;
import dao.impl.SupplierDaoImpl;
import domain.SupplierInfoView;

/**
 * test
 *
 * @param
 * @author Liu
 * @return
 * @exception
 */
public class supplierTest {

    SupplierDao dao = new SupplierDaoImpl();

    @Test
    public void test() throws SQLException {


        List<SupplierInfoView> list = dao.getSupplierInfos();
        for (SupplierInfoView s : list) {
            System.out.println(s.getSupplierName());
            System.out.println(s.getSupplierID());
            System.out.println(s.getProductName());
        }
    }
}
