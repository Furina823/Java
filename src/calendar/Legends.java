package calendar;

import utility.FontUtils;

import javax.swing.*;
import java.awt.*;

public class Legends extends JPanel {

    public Legends(){

        JLabel legendLabel = new JLabel("Legends");
        legendLabel.setFont(FontUtils.getPoppinsFontWithColor(10f,Color.white));

        JLabel holidayLabel = FormatLabel("Holiday", Color.magenta);
        JLabel attendLabel = FormatLabel("Attend", Color.GREEN);
        JLabel leaveLabel = FormatLabel("Leave", Color.RED);
        JLabel workDayLabel = FormatLabel("Work Day", Color.YELLOW);
        JLabel notLabeledLabel = FormatLabel("Not Labeled", Color.WHITE);

        JSeparator separator = new JSeparator();
        separator.setForeground(Color.white);
        separator.setPreferredSize(new Dimension(0,3));
        separator.setOpaque(true);
        separator.setMaximumSize(new Dimension(Integer.MAX_VALUE, 3));

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setPreferredSize(new Dimension(240,210));
        setBackground(new Color(47,47,47));
        setOpaque(true);
        add(separator);
        add(legendLabel);
        add(holidayLabel);
        add(attendLabel);
        add(leaveLabel);
        add(workDayLabel);
        add(notLabeledLabel);

    }

    private JLabel FormatLabel(String text, Color color){
        JLabel label = new JLabel();
        label.setText("<html>"+text+"</html>");
        label.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        label.setForeground(Color.white);
        label.setPreferredSize(new Dimension(100,40));
        label.setBorder(BorderFactory.createCompoundBorder(
                new CustomRoundedBorder(4, color, 7, 0, CustomRoundedBorder.BorderSide.LEFT),
                BorderFactory.createEmptyBorder(3,3,3,3)
        ));
        return label;
    }


}
