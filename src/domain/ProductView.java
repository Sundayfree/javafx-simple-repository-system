package domain;

import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;

/**
 * to show Product data
 *
 * @param
 * @author Liu
 * @return
 * @exception
 */
public class ProductView {

    private int productID;
    private String productName;
    private String importDate;
    private Integer quantity;
    private Double costPrice;
    private Double sellingPrice;
    private Integer supplierID;
    private CheckBox checkBox;
    private Button btn;


    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getImportDate() {
        return importDate;
    }

    public void setImportDate(String importDate) {
        this.importDate = importDate;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(Double costPrice) {
        this.costPrice = costPrice;
    }

    public Double getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(Double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public Integer getSupplierID() {
        return supplierID;
    }

    public void setSupplierID(Integer supplierID) {
        this.supplierID = supplierID;
    }

    public CheckBox getCheckBox() {
        return checkBox;
    }

    public void setCheckBox(CheckBox checkBox) {
        this.checkBox = checkBox;
    }

    public ProductView() {

    }

    public ProductView(int productID, String productName, String importDate, Integer quantity,
                       Integer supplierID) {

        this.productID = productID;
        this.productName = productName;
        this.importDate = importDate;
        this.quantity = quantity;
        this.supplierID = supplierID;
        this.checkBox = new CheckBox();
        this.setBtn(new Button("Delete"));
    }

    public Button getBtn() {
        return btn;
    }

    public void setBtn(Button btn) {
        this.btn = btn;
    }


}
