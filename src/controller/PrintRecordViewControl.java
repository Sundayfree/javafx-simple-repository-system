package controller;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.Optional;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import service.BusinessService;
import service.impl.BusinessServiceImpl;
import utils.SystemUtils;


public class PrintRecordViewControl {
    private final BusinessService service = new BusinessServiceImpl();
    @FXML
    private RadioButton weekly;
    @FXML
    private RadioButton monthly;

    @FXML
    private DatePicker date;

    @FXML
    private Button btn;

    public void comfirmBtnHandler() {
        LocalDate value = date.getValue();
        if (value == null) {
            SystemUtils.showWarning("Please choose date.. ");
            return;
        }
        try {
            if (weekly.isSelected()) {

                String monday = getMonday(value);
                String sunday = getSunday(value);
                Optional<ButtonType> action = SystemUtils.showConfirm("Do you want to backup product records?");
                if (action.get() == ButtonType.OK) {
                    service.generateWeeklyReport(monday, sunday);
                }

                date.setValue(null);
            }
            if (monthly.isSelected()) {
                String firstDay = getFirstDay(value);
                String endDay = getEndDay(value);
                Optional<ButtonType> action = SystemUtils.showConfirm("Do you want to backup product records?");
                if (action.get() == ButtonType.OK) {
                    service.generateMonthlyReport(firstDay, endDay);
                }

                date.setValue(null);
            }
        } catch (Exception e) {
            SystemUtils.showWarning(e.getMessage());
            date.setValue(null);
        }

    }

    private String getFirstDay(LocalDate value) {
        // TODO Auto-generated method stub
        return value.with(TemporalAdjusters.firstDayOfMonth()).toString();
    }

    private String getEndDay(LocalDate value) {
        // TODO Auto-generated method stub
        return value.with(TemporalAdjusters.lastDayOfMonth()).toString();
    }

    private String getSunday(LocalDate value) {
        // TODO Auto-generated method stub
        return value.with(TemporalAdjusters.next(DayOfWeek.SUNDAY)).toString();
    }

    private String getMonday(LocalDate value) {

        return value.with(TemporalAdjusters.previous(DayOfWeek.MONDAY)).toString();
    }


}
