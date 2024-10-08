package pages.profile.info;

import datamodel.SalaryHistory;
import pages.MyPanel;
import pages.home.SysAdmin.ACHeader;
import rolemodel.BaseModel;
import utility.EmptyPanel;
import utility.FontUtils;

import javax.swing.*;
import java.awt.*;

public class Salary_Increment extends JPanel {

    public Salary_Increment(BaseModel model){

        MyPanel.setButtonAction(MyPanel.createListenerEvent(new Historical(model)));

        JPanel contentPane = new JPanel();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
        contentPane.setPreferredSize(new Dimension(830,420));
        contentPane.setBackground(Color.black);

        JLabel titleLabel = new JLabel("Salary Increment Record");
        titleLabel.setFont(FontUtils.getPoppinsFontUnderlinedWithColor(24f, Color.white));

        JLabel footerLabel = new JLabel("Current Salary: RM " + model.getEmpCompany().getSalary());
        footerLabel.setFont(FontUtils.getPoppinsFontWithColor(16f,Color.white));

        JPanel footerPanel = new JPanel(new FlowLayout(FlowLayout.TRAILING));
        footerPanel.setBackground(Color.black);
        footerPanel.setPreferredSize(new Dimension(830,50));
        footerPanel.add(footerLabel);

        for(SalaryHistory s : model.getSalary()){
            if(!s.getDate().equals("null")) {
                contentPane.add(new SI_Label(s));
                contentPane.add(new EmptyPanel());
            }
        }

        this.setLayout(new FlowLayout(FlowLayout.LEFT));
        this.setBackground(Color.black);
        this.add(titleLabel);
        this.add(contentPane);
        this.add(footerPanel);

    }

}
