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
import service.BusinessService;
import service.impl.BusinessServiceImpl;

/**
 * product table controller
 *
 * @param
 * @author Liu
 * @return
 * @exception
 */
public class ProductTableControl {
    private final BusinessService service = new BusinessServiceImpl();

    private MainLayoutControl mainLayout;
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

    public ProductTableControl() {

    }

    public MainLayoutControl getMainLayout() {
        return mainLayout;
    }

    public void setMainLayout(MainLayoutControl mainLayout) {
        this.mainLayout = mainLayout;

    }

    public void displayTable() {
        productID.setCellValueFactory(new PropertyValueFactory<>("productID"));
        productName.setCellValueFactory(new PropertyValueFactory<>("productName"));
        importDate.setCellValueFactory(new PropertyValueFactory<>("importDate"));
        quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        costPrice.setCellValueFactory(new PropertyValueFactory<>("costPrice"));
        sellingPrice.setCellValueFactory(new PropertyValueFactory<>("sellingPrice"));
        supplierID.setCellValueFactory(new PropertyValueFactory<>("supplierID"));
        ObservableList<ProductView> tableData = getTableData();


        tableView.setItems(tableData);
        tableView.refresh();
    }

    public ObservableList<ProductView> getTableData() {
        List<Product> pList = service.getAllProducts();

        ObservableList<ProductView> tableList = FXCollections.observableArrayList();


        for (Product p : pList) {
            ProductView view = new ProductView();

            view.setProductID(p.getProductID());
            view.setCostPrice(p.getCostPrice());
            view.setImportDate(p.getImportDate());
            view.setProductName(p.getProductName());
            view.setQuantity(p.getQuantity());
            view.setSellingPrice(p.getSellingPrice());
            view.setSupplierID(p.getSupplier().getSupplierID());


            tableList.add(view);
        }

        return tableList;

    }


}
