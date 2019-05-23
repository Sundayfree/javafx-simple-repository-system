package controller;

import application.MainApp;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import service.BusinessService;
import service.impl.BusinessServiceImpl;
import utils.SystemUtils;

/**
 * register handler
 *
 * @param
 * @author Liu
 * @return
 * @exception
 */
public class RegisterFormControl {
    private final BusinessService service = new BusinessServiceImpl();
    private MainApp mainApp;


    public MainApp getMainApp() {
        return mainApp;
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
        SplitPane formView = this.mainApp.getFormView();
        Platform.runLater(() -> formView.requestFocus());// remove text field default
    }

    @FXML
    private TextField nameText;
    @FXML
    private TextField passwordText;

    /**
     * @param
     * @return
     * @throws
     */
    public void register() {
        String name = nameText.getText();
        String password = passwordText.getText();

        try {
            service.regiser(name, password);
            nameText.setText("");
            passwordText.setText("");
            SystemUtils.showAlert("Success!!");


        } catch (Exception e) {
            SystemUtils.showWarning(e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * add click event
     *
     * @param
     * @return
     * @throws
     */
    public void backToMain() {

        this.mainApp.showLoginView();

    }


}
