package pages;

import rolemodel.BaseModel;
import calendar.CalendarGUI;

import javax.swing.*;
import java.awt.*;

public class TimeTablePanel extends JPanel {

    public TimeTablePanel(BaseModel bm){

    this.setLayout(new BorderLayout());
    this.add(new CalendarGUI(bm));

    }

}
