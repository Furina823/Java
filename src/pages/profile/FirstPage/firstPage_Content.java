package pages.profile.FirstPage;

import rolemodel.BaseModel;

import javax.swing.*;
import java.awt.*;

public class firstPage_Content extends JPanel {

    public firstPage_Content(BaseModel baseModel){

        String[] infos = {"Employee Personal Information",
        "Employee Next of Kin", "Employee Background",
        "Position/Salary", "Leave", "Payslip", "Account", "Historical"};

        for(String s : infos){
            this.add(new Content_Label(s,baseModel));
        }

        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        this.setPreferredSize(new Dimension(700,500));

    }


}
