package pages;

import rolemodel.BaseModel;

import javax.swing.*;

public class TimeTablePanel extends JPanel {

    public TimeTablePanel(BaseModel bm){



        JLabel label = new JLabel();
        label.setText("This is TimeTable Panel");

        this.add(label);
    }

}
