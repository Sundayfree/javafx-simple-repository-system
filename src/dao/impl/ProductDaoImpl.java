package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.ProductDao;
import domain.Product;
import domain.Supplier;
import utils.JdbcUtils;


/**
 * productDao implementation
 *
 * @param
 * @author Liu
 * @return
 * @exception
 */
public class ProductDaoImpl implements ProductDao {


    private PreparedStatement st = null;
    private ResultSet rs = null;

    @Override
    public List<Product> getAllRecords(String preDate, String nextDate) throws Exception {
        Connection conn = JdbcUtils.getConnection();
        String sql = "SELECT * from (SELECT proID,proName,date,quantity,cost,"
                + "sellprice,supplierName,phoneNum from product p LEFT JOIN "
                + "supplier s on p.supplierID= s.supplierID ) as temp "
                + "where date between ? and ?";
        st = conn.prepareStatement(sql);
        st.setString(1, preDate);
        st.setString(2, nextDate);
        rs = st.executeQuery();
        List<Product> list = new ArrayList<Product>();
        while (rs.next()) {
            Product p = new Product();
            Supplier s = new Supplier();
            p.setProductID(rs.getInt("proID"));
            p.setProductName(rs.getString("proName"));
            p.setImportDate(rs.getString("date"));
            p.setQuantity(rs.getInt("quantity"));
            p.setCostPrice(rs.getDouble("cost"));
            p.setSellingPrice(rs.getDouble("sellprice"));

            s.setName(rs.getString("supplierName"));
            s.setPhoneNum(rs.getInt("phoneNum"));
            p.setSupplier(s);
            list.add(p);
        }
        JdbcUtils.release(st, rs);
        JdbcUtils.closeConn();
        return list;
    }


    /* @param
     * @return
     * @exception
     * @see dao.ProductDao#addNewProduct(domain.Product, domain.Supplier)
     */
    @Override
    public void addNewProduct(Product pro, Supplier s) throws SQLException {
        int productId = 1;
        int supplierID;
        supplierID = getSupplierID(s);

        Connection conn = JdbcUtils.getConnection();


        String sql = "select * from product order by proID desc";
        st = conn.prepareStatement(sql);
        rs = st.executeQuery();
        if (rs.next()) {
            productId = rs.getInt("proID");
            productId++;
        } else {
            productId = 1;
        }

        sql = "insert into product(proID,proName,date,quantity,cost,sellprice,supplierID) VALUES(?,?,?,?,?,?,?)";
        st = conn.prepareStatement(sql);
        st.setInt(1, productId);
        st.setString(2, pro.getProductName());
        st.setString(3, pro.getImportDate());
        st.setInt(4, pro.getQuantity());
        st.setDouble(5, pro.getCostPrice());
        st.setDouble(6, pro.getSellingPrice());
        st.setInt(7, supplierID);
        st.executeUpdate();

        JdbcUtils.release(st, rs);

        JdbcUtils.closeConn();

    }

    /**
     * @param
     * @return
     * @throws
     */
    private int getSupplierID(Supplier s) throws SQLException {
        Connection conn = JdbcUtils.getConnection();
        int supplierId = 0;

        String sql = "select supplierID from supplier order by supplierID desc";
        st = conn.prepareStatement(sql);
        rs = st.executeQuery();
        if (rs.next()) {
            supplierId = rs.getInt("supplierID");
            supplierId++;
        } else {
            supplierId = 1;
        }

        sql = "insert into supplier(supplierID,supplierName,phoneNum) values(?,?,?)";
        st = conn.prepareStatement(sql);
        st.setInt(1, supplierId);
        st.setString(2, s.getName());
        st.setInt(3, s.getPhoneNum());

        st.executeUpdate();
        return supplierId;
    }


    /* @param
     * @return
     * @exception
     * @see dao.ProductDao#findProductByID(int)
     */
    @Override
    public List<Product> findProductByID(int id) throws SQLException {
        Connection conn = JdbcUtils.getConnection();

        String sql = "select * from product where proID=?";
        st = conn.prepareStatement(sql);
        st.setInt(1, id);
        rs = st.executeQuery();
        Product pro = new Product();
        Supplier s = new Supplier();
        List<Product> list = new ArrayList<Product>();
        while (rs.next()) {
            pro.setProductID(rs.getInt("proID"));
            pro.setProductName(rs.getString("proName"));
            pro.setImportDate(rs.getString("date"));
            pro.setQuantity(rs.getInt("quantity"));
            pro.setCostPrice(rs.getDouble("cost"));
            pro.setSellingPrice(rs.getDouble("sellprice"));
            s.setSupplierID(rs.getInt("supplierID"));
            pro.setSupplier(s);
            list.add(pro);
            JdbcUtils.release(st, rs);
            JdbcUtils.closeConn();
            return list;
        }
        JdbcUtils.release(st, rs);
        JdbcUtils.closeConn();
        return null;

    }

    /* @param
     * @return
     * @exception
     * @see dao.ProductDao#findPoductByName(java.lang.String)
     */
    @Override
    public List<Product> findPoductByName(String name) throws SQLException {
        Connection conn = JdbcUtils.getConnection();

        String sql = "select * from product where proName like ?";
        st = conn.prepareStatement(sql);
        st.setString(1, "%" + name + "%");
        rs = st.executeQuery();
        List<Product> list = new ArrayList<>();


        while (rs.next()) {
            Product pro = new Product();
            Supplier s = new Supplier();
            pro.setProductID(rs.getInt("proID"));
            pro.setProductName(rs.getString("proName"));
            pro.setImportDate(rs.getString("date"));
            pro.setQuantity(rs.getInt("quantity"));
            pro.setCostPrice(rs.getDouble("cost"));
            pro.setSellingPrice(rs.getDouble("sellprice"));
            s.setSupplierID(rs.getInt("supplierID"));
            pro.setSupplier(s);
            list.add(pro);
        }
        JdbcUtils.release(st, rs);
        JdbcUtils.closeConn();
        return list;
    }

    /* @param
     * @return
     * @exception
     * @see dao.ProductDao#findAllProducts()
     */
    @Override
    public List<Product> findAllProducts() throws SQLException {
        Connection conn = JdbcUtils.getConnection();
        String sql = "select * from product";
        st = conn.prepareStatement(sql);
        rs = st.executeQuery();

        List<Product> list = new ArrayList<Product>();

        while (rs.next()) {

            Product pro = new Product();
            Supplier s = new Supplier();
            pro.setProductID(rs.getInt("proID"));
            pro.setProductName(rs.getString("proName"));
            pro.setImportDate(rs.getString("date"));
            pro.setQuantity(rs.getInt("quantity"));
            pro.setCostPrice(rs.getDouble("cost"));
            pro.setSellingPrice(rs.getDouble("sellprice"));
            s.setSupplierID(rs.getInt("supplierID"));
            pro.setSupplier(s);
            list.add(pro);

        }
        JdbcUtils.release(st, rs);
        JdbcUtils.closeConn();
        return list;

    }

    /* @param
     * @return
     * @exception
     * @see dao.ProductDao#deleteProductById(int)
     */
    @Override
    public void deleteProductById(int proID) throws SQLException {
        Connection conn = JdbcUtils.getConnection();
        JdbcUtils.StartTransaction(); // start transaction
        String sql = "select supplierID from product where proID=?";
        st = conn.prepareStatement(sql);
        st.setInt(1, proID);
        rs = st.executeQuery();
        int SupplierID = 0;
        if (rs.next()) {
            SupplierID = rs.getInt("supplierID");
        }

        sql = "delete from supplier where supplierID=?";
        st = conn.prepareStatement(sql);
        st.setInt(1, SupplierID);
        int resulte = st.executeUpdate();

        if (resulte > 0) {
            sql = "delete from product where proID=?";
            st = conn.prepareStatement(sql);
            st.setInt(1, proID);
            st.executeUpdate();


        }
        JdbcUtils.commitTransaction(); //commit transaction
        JdbcUtils.rollback();  // rollback
        JdbcUtils.release(st, rs);
        JdbcUtils.closeConn();

    }


}
