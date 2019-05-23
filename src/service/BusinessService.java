package service;

import java.sql.SQLException;
import java.util.List;

import domain.Product;
import domain.Supplier;
import domain.SupplierInfoView;
import domain.User;

/**
 * @param
 * @author Liu
 * @return
 * @exception
 */
public interface BusinessService {

    boolean isRegister(String name, String password) throws SQLException;

    User getUser(String name, String password) throws SQLException;

    void regiser(String name, String password) throws SQLException;

    List<User> findAllUsers() throws SQLException;

    List<User> getAllStaff() throws Exception;

    List<Product> getAllProducts();

    List<Product> getProductByName(String name) throws Exception;

    List<Product> getProductById(String id) throws Exception;

    void updateRole(String id, String role);

    void deleteProductById(int proID);

    void addNewProduct(String name, String sum, String cost, String price, String sName, String phone);

    List<Supplier> getAllSuppliers();

    List<SupplierInfoView> getSupplierInfos();


    void generateWeeklyReport(String monday, String sunday);

    void generateMonthlyReport(String firstDay, String endDay);

}