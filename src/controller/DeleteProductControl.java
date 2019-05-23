package controller;

import java.util.List;
import java.util.Optional;

import domain.Product;

import domain.ProductView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import service.BusinessService;
import service.impl.BusinessServiceImpl;
import utils.SystemUtils;

/**
 * delete product controller
 *
 * @param
 * @author Liu
 * @return
 * @exception
 */
public class DeleteProductControl {

    private BusinessService service = new BusinessServiceImpl();
    @FXML
    private TableView<ProductView> table;
    @FXML
    private TableColumn<ProductView, Integer> productID;

    @FXML
    private TableColumn<ProductView, String> productName;

    @FXML
    private TableColumn<ProductView, String> importDate;

    @FXML
    private TableColumn<ProductView, Integer> quantity;

    @FXML
    private TableColumn<ProductView, Integer> supplierID;

    @FXML
    private TableColumn<ProductView, CheckBox> checkBox;
    @FXML
    private TableColumn<ProductView, Button> btn;

    /**
     * populate data into table
     *
     * @param
     * @return
     * @throws
     */
    public void displayTable() {
        productID.setCellValueFactory(new PropertyValueFactory<>("productID"));
        productName.setCellValueFactory(new PropertyValueFactory<>("productName"));
        importDate.setCellValueFactory(new PropertyValueFactory<>("importDate"));
        quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        supplierID.setCellValueFactory(new PropertyValueFactory<>("supplierID"));
        checkBox.setCellValueFactory(new PropertyValueFactory<>("checkBox"));
        btn.setCellValueFactory(new PropertyValueFactory<>("btn"));

        ObservableList<ProductView> tableData = getTableData();
        table.setItems(tableData);
        deleteProduct();
    }

    /**
     * to get product data and store data into ObservableList
     *
     * @param
     * @return
     * @throws
     */
    public ObservableList<ProductView> getTableData() {

        ObservableList<ProductView> newList = FXCollections.observableArrayList();
        List<Product> products = service.getAllProducts();


        for (Product p : products) {
            int id = p.getProductID();
            String name = p.getProductName();
            String date = p.getImportDate();
            Integer quatity = p.getQuantity();
            int sId = p.getSupplier().getSupplierID();

            ProductView table = new ProductView(id, name, date, quatity, sId);
            newList.add(table);

        }
        return newList;
    }

    /**
     * delete product handler
     *
     * @param
     * @return
     * @throws
     */
    @FXML
    public void deleteProduct() {
        ObservableList<ProductView> items = table.getItems();

        for (ProductView p : items) {
            p.getBtn().setOnAction(e -> { //add click event
                if (p.getCheckBox().isSelected()) {
                    Optional<ButtonType> action = SystemUtils.showConfirm("Do you want to delete item?");
                    if (action.get() == ButtonType.OK) {
                        service.deleteProductById(p.getProductID());
                        items.remove(p);

                    } else {
                        p.getCheckBox().setSelected(false);
                    }
                } else {
                    SystemUtils.showWarning("select the option first");
                }
            });
        }
    }
}
