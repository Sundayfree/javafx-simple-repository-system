package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import application.MainApp;
import domain.Product;
import domain.Role;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import service.BusinessService;
import service.impl.BusinessServiceImpl;
import utils.SystemUtils;

/**
 * main layout controller
 *
 * @param
 * @author Liu
 * @return
 * @exception
 */
public class MainLayoutControl implements Initializable {

    private final BusinessService service = new BusinessServiceImpl();
    private MainApp mainApp;
    private List<Product> searchList; //to store search data

    @FXML
    private BorderPane adminPane;

    @FXML
    private BorderPane tablePane;

    @FXML
    private ComboBox<String> comboBox;

    @FXML
    private Label welcomLable;

    @FXML
    private Button btnSearch;

    @FXML
    private TextField searchText;

    @FXML
    private Button supplierBtn;


    public List<Product> getSearchList() {
        return searchList;
    }

    /* to initialize search options
     * @param
     * @return
     * @exception
     * @see javafx.fxml.Initializable#initialize(java.net.URL, java.util.ResourceBundle)
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> list = FXCollections.observableArrayList("Product Name", "Produt ID");
        comboBox.setValue("----Option----");
        comboBox.setItems(list);


    }


    public MainApp getMainApp() {
        return mainApp;
    }

    public BorderPane getTablePane() {
        return tablePane;
    }

    public void setTablePane(BorderPane tablePane) {
        this.tablePane = tablePane;
    }


    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
        String role = mainApp.getUser().getRole();
        String name = mainApp.getUser().getUserName();
        if (role.equals(Role.ADMIN.toString())) {
            welcomLable.setText("Admin: " + name);// to show current user
        } else {
            welcomLable.setText("Staff: " + name);
        }

        if (Role.ADMIN.toString().equals(role)) {
            showAdminLayout(); // to show admin layout
        }
    }

    public void showProductTableLayout() {
        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/view/DisplayInventoryView.fxml"));
            AnchorPane layout = loader.load();
            this.tablePane.setCenter(layout);

            ProductTableControl controller = loader.getController();
            controller.setMainLayout(this);
            controller.displayTable();

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }

    }

    public void showAdminLayout() {
        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/view/ShowAdminView.fxml"));
            AnchorPane layout = loader.load();
            this.adminPane.setCenter(layout);
            AdminControl controller = loader.getController();
            controller.setMainLayout(this);
            controller.setMainApp(mainApp);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }

    }


    public void showSearchTableView() {
        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/view/searchTableView.fxml"));

            ScrollPane layout = loader.load();
            this.tablePane.setCenter(layout);
            SearchTableControl controller = loader.getController();

            controller.setMainlaLayoutControl(this);
            controller.displayTable();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public void showPrintView() {
        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/view/PrintRecordView.fxml"));

            AnchorPane layout = loader.load();
            this.tablePane.setCenter(layout);
            PrintRecordViewControl controller = loader.getController();


        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void showListSupplierView() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/view/DisplaySuppliersView.fxml"));
            AnchorPane layout = loader.load();
            this.tablePane.setCenter(layout);
            ListSupplierControl controller = loader.getController();
            controller.displayTable();

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }

    /**
     * switch account handler and add click event
     *
     * @param
     * @return
     * @throws
     */
    @FXML
    public void handleSwitch() {
        Optional<ButtonType> action = SystemUtils.showConfirm("Do you want to switch account?");
        if (action.get() == ButtonType.OK) {
            mainApp.showLoginView();
        }


    }


    /**
     * logout handler and add click event
     *
     * @param
     * @return
     * @throws
     */
    @FXML
    public void handleLogout() {
        Optional<ButtonType> action = SystemUtils.showConfirm("Dear  " + mainApp.getUser().getUserName() + " : Do you want to log out??");
        if (action.get() == ButtonType.OK) {
            Platform.exit();
            System.exit(0);

        }

    }

    @FXML
    public void handleProductTable() {
        showProductTableLayout();
    }

    /**
     * search handler and add click event
     *
     * @param
     * @return
     * @throws
     */
    @FXML
    public void handleSearch() {
        String head = comboBox.getSelectionModel().getSelectedItem();
        try {
            if (head.equals("----Option----")) {
                SystemUtils.showAlert("Please select option..");
                this.searchText.setText("");
                return;

            }

            String input = this.searchText.getText();

            setSearchList(input);
            comboBox.setValue("----Option----");

        } catch (Exception e) {

            SystemUtils.showAlert(e.getMessage());
            comboBox.setValue("----Option----");
            this.searchText.setText("");
            e.printStackTrace();
            return;
        }

        this.showSearchTableView();

    }

    /**
     * to get search result
     *
     * @param
     * @return
     * @throws
     */
    public List<Product> setSearchList(String input) throws Exception {

        String name = comboBox.getSelectionModel().getSelectedItem();

        searchList = new ArrayList<Product>();


        if ("Product Name".equals(name)) {
            List<Product> nameList = service.getProductByName(input);
            searchList.addAll(nameList);


        }
        if (name.equals("Produt ID")) {

            List<Product> idList = service.getProductById(input);
            searchList.addAll(idList);

        }
        return searchList;

    }

    /**
     * to display suppliers info and add click event
     *
     * @param
     * @return
     * @throws
     */
    @FXML
    public void supplierBtnHandler() {
        showListSupplierView();
    }

    /**
     * generate excel and add click event
     *
     * @param
     * @return
     * @throws
     */
    @FXML
    public void exportHandler() {

        showPrintView();

    }


}
