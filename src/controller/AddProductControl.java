package controller;


import javafx.fxml.FXML;

import javafx.scene.control.TextField;
import service.BusinessService;
import service.impl.BusinessServiceImpl;
import utils.SystemUtils;

/**
 * adding product controller
 *
 * @param
 * @author Liu
 * @return
 * @exception
 */
public class AddProductControl {
    private final BusinessService service = new BusinessServiceImpl();
    @FXML
    private TextField textName;
    @FXML
    private TextField textSum;
    @FXML
    private TextField textCost;
    @FXML
    private TextField textPrice;
    @FXML
    private TextField textSupplierName;
    @FXML
    private TextField textPhone;


    /**
     * submit button handler
     *
     * @param
     * @return
     * @throws
     */
    @FXML
    public void submitHandler() {

        try {
            String name = textName.getText();
            String sum = textSum.getText();
            String cost = textCost.getText();
            String price = textPrice.getText();
            String sName = textSupplierName.getText();
            String phone = textPhone.getText();


            service.addNewProduct(name, sum, cost, price, sName, phone);

            SystemUtils.showAlert("Success..");
            setTextAsNull();


        } catch (Exception e) {

            SystemUtils.showWarning(e.getMessage());

            e.printStackTrace();
        }

    }

    /**
     * set textfiled as null
     *
     * @param
     * @return
     * @throws
     */
    private void setTextAsNull() {
        textName.setText("");
        textSum.setText("");
        textCost.setText("");
        textPrice.setText("");
        textSupplierName.setText("");
        textPhone.setText("");

    }

    @FXML
    public void resetHandler() {
        setTextAsNull();
    }


}
