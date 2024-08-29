package pages.profile.info;

import datamodel.SalaryHistory;
import pages.home.SysAdmin.ACHeader;
import rolemodel.BaseModel;
import utility.EmptyPanel;
import utility.FontUtils;

import javax.swing.*;
import java.awt.*;

public class Salary_Increment extends JPanel {

    public Salary_Increment(BaseModel model){

        this.add(new ACHeader("Historical"));

        JPanel contentPane = new JPanel();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
        contentPane.setPreferredSize(new Dimension(700,420));
        contentPane.setBackground(Color.black);

        JLabel titleLabel = new JLabel("Salary");
        titleLabel.setFont(FontUtils.getPoppinsFontUnderlinedWithColor(12f, Color.white));

        JLabel footerLabel = new JLabel("Current Salary: RM " + model.getEmpCompany().getSalary());
        footerLabel.setFont(FontUtils.getPoppinsFontWithColor(16f,Color.white));

        JPanel footerPanel = new JPanel(new FlowLayout(FlowLayout.TRAILING));
        footerPanel.setBackground(Color.black);
        footerPanel.setPreferredSize(new Dimension(700,50));
        footerPanel.add(footerLabel);

        for(SalaryHistory s : model.getSalary()){
            contentPane.add(new SI_Label(s));
            contentPane.add(new EmptyPanel());
        }

        this.setLayout(new FlowLayout(FlowLayout.LEFT));
        this.add(contentPane);
        this.add(footerPanel);

    }

}
