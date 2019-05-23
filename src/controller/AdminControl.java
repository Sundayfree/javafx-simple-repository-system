package controller;


import application.MainApp;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.control.ScrollPane;

import javafx.scene.layout.AnchorPane;

import utils.SystemUtils;

/**
 * admin controller
 *
 * @param
 * @author Liu
 * @return
 * @exception
 */
public class AdminControl {

    private MainLayoutControl mainLayout;
    private MainApp mainApp;


    public MainApp getMainApp() {
        return mainApp;
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

    }

    public MainLayoutControl getMainLayout() {
        return mainLayout;
    }

    public void setMainLayout(MainLayoutControl mainLayout) {
        this.mainLayout = mainLayout;

    }

    public void showUserAuthorizeView() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/view/DisplayUserAuthorizeView.fxml"));

            AnchorPane layout = loader.load();
            mainLayout.getTablePane().setCenter(layout);
            UserAuthorizeControl controller = loader.getController();// to get controller

            controller.displayTable();//display users table

        } catch (Exception e) {
            SystemUtils.showWarning("No staff..");
            e.printStackTrace();
        }
    }

    public void showDeleteTableView() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/view/DeleteRowView.fxml"));

            AnchorPane layout = loader.load();
            mainLayout.getTablePane().setCenter(layout);
            DeleteProductControl controller = loader.getController();

            controller.displayTable();

        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public void showAddProductView() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/view/AddProductView.fxml"));

            ScrollPane layout = loader.load();
            mainLayout.getTablePane().setCenter(layout);
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    /**
     * handle click event
     *
     * @param
     * @return
     * @throws
     */
    @FXML
    public void userBtnHandler() {
        showUserAuthorizeView();

    }

    /**
     * handle click event
     *
     * @param
     * @return
     * @throws
     */
    @FXML
    public void deleteRowHandler() {

        showDeleteTableView();

    }

    /**
     * handle click event
     *
     * @param
     * @return
     * @throws
     */
    @FXML
    public void addProductHandler() {
        showAddProductView();
    }

}
