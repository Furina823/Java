package pages.profile.FirstPage;

import rolemodel.BaseModel;
import utility.EmptyPanel;
import utility.FontUtils;

import javax.swing.*;
import java.awt.*;

public class firstPage_Content extends JPanel {


    private String[] infos;

    private Content_Label contentLabel;

    public firstPage_Content(String empID) {

        infos = new String[]{"Employee Personal Information",
                "Employee Next of Kin", "Employee Background",
                "Position/Salary", "Leave","Account"};

        for(String s : infos){
            contentLabel = new Content_Label(s, empID);
            contentLabel.setFont(FontUtils.getPoppinsFontWithColor(16f,Color.white));
            this.add(contentLabel);
            this.add(new EmptyPanel());
        }

        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        this.setPreferredSize(new Dimension(830,500));
        this.setBackground(Color.black);

    }

    public firstPage_Content(BaseModel baseModel){

        infos = new String[]{"Employee Personal Information",
        "Employee Next of Kin", "Employee Background",
        "Position/Salary", "Leave", "Payslip", "Account", "Historical"};

        for(String s : infos){
            contentLabel = new Content_Label(s,baseModel);
            contentLabel.setFont(FontUtils.getPoppinsFontWithColor(16f,Color.white));
            this.add(contentLabel);
            this.add(new EmptyPanel());
        }

        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        this.setPreferredSize(new Dimension(830,500));
        this.setBackground(Color.black);

    }

}
