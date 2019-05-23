package application;


import java.io.IOException;

import controller.ListUserControl;
import controller.LoginControl;
import controller.MainLayoutControl;

import controller.RegisterFormControl;

import domain.User;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import javafx.scene.control.SplitPane;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;

import javafx.stage.Stage;


/**
 * entrance class
 *
 * @author Liu
 * @param
 * @return
 * @exception
 */

/**
 * @author Liu
 * @param
 * @return
 * @exception
 */
public class MainApp extends Application {

    private User user; // to retrieve user who login in system
    @FXML
    private AnchorPane loginView;
    @FXML
    private SplitPane formView;


    public SplitPane getFormView() {
        return formView;
    }


    public AnchorPane getLoginView() {
        return loginView;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    private Stage primaryStage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {

        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Inventory Mangament System");


        this.primaryStage.getIcons().add(
                new Image("file:src/resource/icon.png"));

        this.primaryStage.setResizable(false);
        showLoginView();

    }


    /**
     * @param
     * @return
     * @exception
     */
    public void showLoginView() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/view/LoginView.fxml"));
            loginView = loader.load();

            Scene scene = new Scene(loginView);
            this.primaryStage.setScene(scene);
            LoginControl controller = loader.getController();
            controller.setMianApp(this); // set mainApp reference to LogionControl
            this.primaryStage.show();
        } catch (IOException e) {

            e.printStackTrace();
        }

    }

    /**
     * @param
     * @return
     * @exception
     */
    public void showUserView() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/view/DisplayUsersView.fxml"));
            AnchorPane userView = loader.load();
            Scene scene = new Scene(userView);
            ListUserControl controller = loader.getController();
            controller.setMainApp(this);
            this.primaryStage.setScene(scene);
            this.primaryStage.show();
        } catch (IOException e) {

            e.printStackTrace();
        }

    }

    /**
     * @param
     * @return
     * @exception
     */
    public void showRegisterForm() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/view/RigisterForm.fxml"));

            formView = loader.load();
            Scene scene = new Scene(formView);
            RegisterFormControl controller = loader.getController();
            controller.setMainApp(this);
            this.primaryStage.setScene(scene);
            this.primaryStage.show();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    /**
     * @param
     * @return
     * @exception
     */
    public void showMainLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/view/ShowMainLayoutView.fxml"));

            SplitPane layout = loader.load();

            Scene scene = new Scene(layout);

            MainLayoutControl controller = loader.getController();
            controller.setMainApp(this);


            this.primaryStage.setScene(scene);
            this.primaryStage.show();

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }


}
