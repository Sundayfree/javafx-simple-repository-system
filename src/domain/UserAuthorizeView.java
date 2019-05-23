package domain;


import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;

/**
 * to store UserAuthorizeTable data
 *
 * @param
 * @author Liu
 * @return
 * @exception
 */
public class UserAuthorizeView {
    private String userID;
    private String userName;

    private String date;
    private String role;
    private CheckBox checkBox;
    private Button btn;


    public Button getBtn() {
        return btn;
    }

    public void setBtn(Button btn) {
        this.btn = btn;
    }

    public UserAuthorizeView() {

    }

    public UserAuthorizeView(String userID, String userName, String date,
                             String role) {

        this.userID = userID;
        this.userName = userName;
        this.date = date;
        this.role = role;
        this.checkBox = new CheckBox(Role.ADMIN.toString());
        this.btn = new Button("Confirm");
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public CheckBox getCheckBox() {
        return checkBox;
    }

    public void setCheckBox(CheckBox checkBox) {
        this.checkBox = checkBox;
    }


}
