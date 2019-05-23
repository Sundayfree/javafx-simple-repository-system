package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.SupplierDao;
import domain.Supplier;
import domain.SupplierInfoView;
import utils.JdbcUtils;

/**
 * supplierDao implementation
 *
 * @param
 * @author Liu
 * @return
 * @exception
 */
public class SupplierDaoImpl implements SupplierDao {
    private PreparedStatement st;
    private ResultSet rs;

    /* @param
     * @return
     * @exception
     * @see dao.SupplierDao#findAllSuppliers()
     */
    @Override
    public List<Supplier> findAllSuppliers() throws SQLException {

        Connection conn = JdbcUtils.getConnection();
        String sql = "select * from supplier";
        st = conn.prepareStatement(sql);
        rs = st.executeQuery();
        List<Supplier> list = new ArrayList<Supplier>();

        while (rs.next()) {
            Supplier s = new Supplier();
            s.setSupplierID(rs.getInt("supplierID"));
            s.setName(rs.getString("supplierName"));
            s.setPhoneNum(rs.getInt("phoneNum"));

            list.add(s);
        }
        JdbcUtils.release(st, rs);
        JdbcUtils.closeConn();
        return list;

    }

    /* @param
     * @return
     * @exception
     * @see dao.SupplierDao#getSupplierInfos()
     */
    @Override
    public List<SupplierInfoView> getSupplierInfos() throws SQLException {

        Connection conn = JdbcUtils.getConnection();
        String sql = "select supplier.supplierID,supplierName,phoneNum ,proName "
                + "FROM supplier left JOIN product on "
                + "supplier.supplierID=product.supplierID ";
        st = conn.prepareStatement(sql);
        rs = st.executeQuery();
        List<SupplierInfoView> list = new ArrayList<>();
        while (rs.next()) {
            SupplierInfoView supplier = new SupplierInfoView();
            supplier.setSupplierID(rs.getInt("supplierID"));
            supplier.setSupplierName(rs.getString("supplierName"));
            supplier.setPhoneNum(rs.getInt("phoneNum"));

            supplier.setProductName(rs.getString("proName"));
            list.add(supplier);
        }
        JdbcUtils.release(st, rs);
        JdbcUtils.closeConn();
        return list;
    }

}
