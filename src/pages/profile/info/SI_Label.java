package pages.profile.info;

import datamodel.SalaryHistory;
import utility.FontUtils;
import utility.RoundedBorder;

import javax.swing.*;
import java.awt.*;

public class SI_Label extends JPanel {

    public SI_Label(SalaryHistory salary){

        JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
        leftPanel.setMaximumSize(new Dimension(830,50));
        leftPanel.setBackground(new Color(47,47,47));

        JLabel amountLabel = new JLabel(salary.getIncrementAmount());
        amountLabel.setFont(FontUtils.getPoppinsFontWithColor(14f,Color.white));
        JLabel dateLabel = new JLabel(salary.getDate());
        dateLabel.setFont(FontUtils.getPoppinsFontWithColor(14f,Color.white));

        leftPanel.add(amountLabel);
        leftPanel.add(dateLabel);

        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.TRAILING));
        rightPanel.setMaximumSize(new Dimension(830,50));
        rightPanel.setBackground(new Color(47,47,47));

        JLabel descriptionLabel = new JLabel(salary.getDescription());
        descriptionLabel.setFont(FontUtils.getPoppinsFontWithColor(14f,Color.white));

        rightPanel.add(descriptionLabel);

        this.add(leftPanel);
        this.add(rightPanel);

        this.setMaximumSize(new Dimension(830,50));
        this.setLayout(new BoxLayout(this,BoxLayout.X_AXIS));
        this.setBorder(new RoundedBorder(new Color(47,47,47),2,20));
        this.setBackground(Color.black);
        this.setOpaque(true);

    }

}
