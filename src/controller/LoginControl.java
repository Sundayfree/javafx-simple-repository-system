package controller;


import application.MainApp;

import domain.User;
import javafx.application.Platform;
import javafx.fxml.FXML;

import javafx.scene.control.PasswordField;

import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import service.BusinessService;
import service.impl.BusinessServiceImpl;
import utils.SystemUtils;

/**
 * login controller
 *
 * @param
 * @author Liu
 * @return
 * @exception
 */
public class LoginControl {

    private MainApp mainApp;
    private final BusinessService service = new BusinessServiceImpl();


    @FXML
    private TextField nameText;
    @FXML
    private PasswordField passwordText;

    public LoginControl() {

    }

    public LoginControl(MainApp mian) {

        this.mainApp = mian;
    }


    public MainApp getMianApp() {
        return mainApp;
    }

    public void setMianApp(MainApp mianApp) {
        this.mainApp = mianApp;
        AnchorPane loginView = mainApp.getLoginView();
        Platform.runLater(() -> loginView.requestFocus());// remove textfield default
    }

    @FXML
    public void showUser() {
        this.mainApp.showUserView();

    }

    @FXML
    public void showRegisterForm() {
        mainApp.showRegisterForm();
    }

    /**
     * to login
     *
     * @param
     * @return
     * @throws
     */
    @FXML
    public void login() {

        String name = nameText.getText();
        String password = passwordText.getText();
        try {
            boolean isRegister = service.isRegister(name, password);
            if (isRegister) {
                setCurrentUser(name, password);

                mainApp.showMainLayout();

                return;
            }
        } catch (Exception e) {
            SystemUtils.showWarning(e.getMessage());
        }

    }


    /**
     * to set Current User
     *
     * @param
     * @return
     * @throws
     */
    private void setCurrentUser(String name, String password) {


        try {
            User user = service.getUser(name, password);
            this.mainApp.setUser(user);
        } catch (Exception e) {
            SystemUtils.showAlert(e.getMessage());
            e.printStackTrace();
        }


    }

    @FXML
    public void handleReset() {
        nameText.setText("");
        passwordText.setText("");
    }

}
