package dao;

import java.sql.SQLException;
import java.util.List;

import domain.Product;
import domain.Supplier;

/**
 * @param
 * @author Liu
 * @return
 * @exception
 */
public interface ProductDao {

    void addNewProduct(Product pro, Supplier s) throws SQLException;

    List<Product> findProductByID(int id) throws SQLException;

    List<Product> findPoductByName(String name) throws SQLException;

    List<Product> findAllProducts() throws SQLException;

    void deleteProductById(int proID) throws SQLException;

    List<Product> getAllRecords(String monday, String sunday) throws Exception;

}