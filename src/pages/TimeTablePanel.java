package pages;

import rolemodel.BaseModel;
import test.CalendarGUI;

import javax.swing.*;

public class TimeTablePanel extends JPanel {

    public TimeTablePanel(BaseModel bm){

    this.setLayout(null);
    this.add(new CalendarGUI());

    }

}
