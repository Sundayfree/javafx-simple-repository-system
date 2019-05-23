package controller;

import java.util.List;

import domain.SupplierInfoView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import service.BusinessService;
import service.impl.BusinessServiceImpl;

/**
 * dispaly suppliers controller
 *
 * @param
 * @author Liu
 * @return
 * @exception
 */
public class ListSupplierControl {

    private BusinessService service = new BusinessServiceImpl();
    @FXML
    private TableView<SupplierInfoView> tableView;

    @FXML
    private TableColumn<SupplierInfoView, Integer> supplierID;

    @FXML
    private TableColumn<SupplierInfoView, String> supplierName;

    @FXML
    private TableColumn<SupplierInfoView, Integer> phoneNum;
    @FXML
    private TableColumn<SupplierInfoView, String> productName;


    public void displayTable() {

        supplierID.setCellValueFactory(new PropertyValueFactory<>("supplierID"));
        supplierName.setCellValueFactory(new PropertyValueFactory<>("supplierName"));
        phoneNum.setCellValueFactory(new PropertyValueFactory<>("phoneNum"));
        productName.setCellValueFactory(new PropertyValueFactory<>("productName"));
        List<SupplierInfoView> suppliers = service.getSupplierInfos();
        ObservableList<SupplierInfoView> list = FXCollections.observableArrayList();
        for (SupplierInfoView s : suppliers) {
            System.out.println(s.getPhoneNum());
            list.add(s);
        }
        tableView.setItems(list);
    }

}
