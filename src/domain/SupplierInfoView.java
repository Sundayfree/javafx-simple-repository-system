package domain;

/**
 * to show SupplierInfo data
 *
 * @param
 * @author Liu
 * @return
 * @exception
 */
public class SupplierInfoView {

    private int supplierID;
    private String supplierName;
    private int phoneNum;
    private String productName;

    public int getSupplierID() {
        return supplierID;
    }

    public void setSupplierID(int supplierID) {
        this.supplierID = supplierID;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public int getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(int phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public SupplierInfoView() {

    }

    public SupplierInfoView(int supplierID, String supplierName, int phoneNum, String productName) {
        super();
        this.supplierID = supplierID;
        this.supplierName = supplierName;
        this.phoneNum = phoneNum;
        this.productName = productName;
    }


}
