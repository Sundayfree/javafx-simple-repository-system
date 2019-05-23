package domain;

/**
 * supplier entity class
 *
 * @param
 * @author Liu
 * @return
 * @exception
 */
public class Supplier {

    private int supplierID;
    private String name;
    private int phoneNum;

    public Supplier() {

    }

    public Supplier(int supplierID, String name, int phoneNum) {
        super();
        this.supplierID = supplierID;
        this.name = name;
        this.phoneNum = phoneNum;
    }

    public int getSupplierID() {
        return supplierID;
    }

    public void setSupplierID(int supplierID) {
        this.supplierID = supplierID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(int phoneNum) {
        this.phoneNum = phoneNum;
    }

}
