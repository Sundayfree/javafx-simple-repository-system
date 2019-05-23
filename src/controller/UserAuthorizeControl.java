package controller;

import java.util.List;
import java.util.Optional;

import domain.User;
import domain.UserAuthorizeView;
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
 * authrization controller
 *
 * @param
 * @author Liu
 * @return
 * @exception
 */
public class UserAuthorizeControl {

    private final BusinessService service = new BusinessServiceImpl();

    @FXML
    private TableView<UserAuthorizeView> table;

    @FXML
    private TableColumn<UserAuthorizeView, String> UserID;
    @FXML
    private TableColumn<UserAuthorizeView, String> userName;
    @FXML
    private TableColumn<UserAuthorizeView, String> role;

    @FXML
    private TableColumn<UserAuthorizeView, String> date;

    @FXML
    private TableColumn<UserAuthorizeView, CheckBox> checkBox;

    @FXML
    private TableColumn<UserAuthorizeView, Button> btn;

    public void displayTable() throws Exception {

        UserID.setCellValueFactory(new PropertyValueFactory<>("UserID"));
        userName.setCellValueFactory(new PropertyValueFactory<>("userName"));
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        role.setCellValueFactory(new PropertyValueFactory<>("role"));
        checkBox.setCellValueFactory(new PropertyValueFactory<UserAuthorizeView, CheckBox>("checkBox"));
        btn.setCellValueFactory(new PropertyValueFactory<UserAuthorizeView, Button>("btn"));

        ObservableList<UserAuthorizeView> tableData = getTableData();
        table.setItems(tableData);
        authorizeRole();
    }

    public ObservableList<UserAuthorizeView> getTableData() throws Exception {

        List<User> pList = service.getAllStaff();

        ObservableList<UserAuthorizeView> tableList = FXCollections.observableArrayList();
        for (User p : pList) {

            String id = p.getUserID();
            String name = p.getUserName();
            String date = p.getDate();
            String role = p.getRole();

            UserAuthorizeView table = new UserAuthorizeView(id, name, date, role);

            tableList.add(table);
        }

        return tableList;

    }

    /**
     * to authorize Role
     *
     * @param
     * @return
     * @throws
     */
    @FXML
    public void authorizeRole() {
        ObservableList<UserAuthorizeView> list = table.getItems();
        for (UserAuthorizeView user : list) {

            user.getBtn().setOnAction((e) -> { // add click event
                if (user.getCheckBox().isSelected()) {
                    Optional<ButtonType> action = SystemUtils.showConfirm("Do you want to authorize?");
                    if (action.get() == ButtonType.OK) {
                        service.updateRole(user.getUserID(), user.getCheckBox().getText());

                        list.remove(user);
                    } else {
                        user.getCheckBox().setSelected(false);
                    }
                } else {
                    SystemUtils.showWarning("select the option first");
                }
            });
        }
    }

}
