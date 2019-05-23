package controller;

import java.util.List;

import domain.Product;
import domain.ProductView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * @param
 * @author Liu
 * @return
 * @exception
 */
public class SearchTableControl {

    private MainLayoutControl mainlaLayoutControl;

    private ObservableList<ProductView> tableList;// to store data


    @FXML
    private TableView<ProductView> tableView;
    @FXML
    private TableColumn<ProductView, Integer> productID;
    @FXML
    private TableColumn<ProductView, String> productName;
    @FXML
    private TableColumn<ProductView, String> importDate;
    @FXML
    private TableColumn<ProductView, Integer> quantity;
    @FXML
    private TableColumn<ProductView, Double> costPrice;
    @FXML
    private TableColumn<ProductView, Double> sellingPrice;
    @FXML
    private TableColumn<ProductView, Integer> supplierID;

    public SearchTableControl() {

    }

    public MainLayoutControl getMainlaLayoutControl() {
        return mainlaLayoutControl;
    }

    public void setMainlaLayoutControl(MainLayoutControl mainlaLayoutControl) {
        this.mainlaLayoutControl = mainlaLayoutControl;

        List<Product> pList = this.mainlaLayoutControl.getSearchList();

        createTableData(pList); // to store data into tableList

    }

    private void createTableData(List<Product> pList) {


        ObservableList<ProductView> list = FXCollections.observableArrayList();
        for (Product p : pList) {
            ProductView table = new ProductView();
            table.setProductID(p.getProductID());
            table.setCostPrice(p.getCostPrice());
            table.setImportDate(p.getImportDate());
            table.setProductName(p.getProductName());
            table.setQuantity(p.getQuantity());
            table.setSellingPrice(p.getSellingPrice());
            table.setSupplierID(p.getSupplier().getSupplierID());
            list.add(table);
        }
        this.setTableList(list);
    }


    public void displayTable() {
        productID.setCellValueFactory(new PropertyValueFactory<ProductView, Integer>("productID"));
        productName.setCellValueFactory(new PropertyValueFactory<>("productName"));
        importDate.setCellValueFactory(new PropertyValueFactory<>("importDate"));
        quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        costPrice.setCellValueFactory(new PropertyValueFactory<>("costPrice"));
        sellingPrice.setCellValueFactory(new PropertyValueFactory<>("sellingPrice"));
        supplierID.setCellValueFactory(new PropertyValueFactory<>("supplierID"));
        tableView.setItems(this.tableList);
    }

    public ObservableList<ProductView> getTableList() {
        return tableList;
    }

    public void setTableList(ObservableList<ProductView> tableList) {
        this.tableList = tableList;
    }

}
