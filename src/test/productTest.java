package test;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import dao.ProductDao;
import dao.impl.ProductDaoImpl;
import domain.Product;
import domain.Supplier;


/**
 * test
 *
 * @param
 * @author Liu
 * @return
 * @exception
 */
public class productTest {

    ProductDao pDao = new ProductDaoImpl();


    @Test
    public void test() throws SQLException {
        //JdbcUtils.StartTransaction();
        Product p = new Product();
        Supplier s = new Supplier();

        s.setName("sdfsf");
        s.setPhoneNum(213);
        p.setSupplier(s);
        p.setImportDate(new Date().toString());
        p.setCostPrice(321.0);

        p.setProductName("phone");
        p.setQuantity(300);
        p.setSellingPrice(120.0);
        pDao.addNewProduct(p, s);
        //JdbcUtils.commitTransaction();
        //JdbcUtils.rollback();
        System.out.println("qqqq");
    }


    @Test
    public void delete() throws SQLException {
        //JdbcUtils.StartTransaction();
        pDao.deleteProductById(2);
        //JdbcUtils.commitTransaction();
        //JdbcUtils.rollback();

    }

    @Test
    public void test2() throws SQLException {
        String a = "p";
        List<Product> list = pDao.findPoductByName(a);
        for (Product p : list) {
            System.out.println(p.getProductName());
        }
    }
}
