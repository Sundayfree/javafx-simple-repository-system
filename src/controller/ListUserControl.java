package controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import application.MainApp;
import domain.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import service.BusinessService;
import service.impl.BusinessServiceImpl;

/**
 * dispaly users controller
 *
 * @param
 * @author Liu
 * @return
 * @exception
 */
public class ListUserControl implements Initializable {

    private MainApp mainApp;

    public MainApp getMainApp() {
        return mainApp;
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    private final BusinessService service = new BusinessServiceImpl();
    @FXML
    private TableView<User> table;

    @FXML
    private TableColumn<User, String> id;
    @FXML
    private TableColumn<User, String> name;
    @FXML
    private TableColumn<User, String> role;

    @FXML
    private TableColumn<User, String> date;


    /* to ininitialize table and show table data
     * @param
     * @return
     * @exception
     * @see javafx.fxml.Initializable#initialize(java.net.URL, java.util.ResourceBundle)
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        id.setCellValueFactory(new PropertyValueFactory<User, String>("userID"));
        name.setCellValueFactory(new PropertyValueFactory<User, String>("userName"));
        role.setCellValueFactory(new PropertyValueFactory<User, String>("role"));
        date.setCellValueFactory(new PropertyValueFactory<User, String>("date"));

        ObservableList<User> list = getTableData();
        table.setItems(list);

    }

    /**
     * to get table data
     *
     * @param
     * @return
     * @throws
     */
    public ObservableList<User> getTableData() {
        try {
            List<User> list = service.findAllUsers();
            ObservableList<User> tableList = FXCollections.observableArrayList();

            for (User u : list) {
                tableList.add(u);
            }
            return tableList;
        } catch (SQLException e) {
            throw new RuntimeException("Users Not Found");
        }
    }

    /**
     * handle the click event
     *
     * @param
     * @return
     * @throws
     */
    public void backToMain() {
        mainApp.showLoginView();
    }

}
