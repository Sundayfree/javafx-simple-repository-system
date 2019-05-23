package utils;

import java.util.Optional;
import java.util.UUID;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

/**
 * ui utils clase
 *
 * @param
 * @author Liu
 * @return
 * @exception
 */
public class SystemUtils {


    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static void showAlert(String text) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText(text);
        alert.showAndWait();

    }

    public static Optional<ButtonType> showConfirm(String text) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText(null);

        alert.setContentText(text);
        Optional<ButtonType> action = alert.showAndWait();
        return action;

    }

    public static void showWarning(String text) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning Dialog");
        alert.setHeaderText(null);
        alert.setContentText(text);
        alert.showAndWait();
    }


}