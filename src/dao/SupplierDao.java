package dao;

import java.sql.SQLException;
import java.util.List;

import domain.Supplier;
import domain.SupplierInfoView;

/**
 * @param
 * @author Liu
 * @return
 * @exception
 */
public interface SupplierDao {

    List<Supplier> findAllSuppliers() throws SQLException;

    List<SupplierInfoView> getSupplierInfos() throws SQLException;

}