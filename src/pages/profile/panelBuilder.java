package pages.profile;

import utility.FontUtils;
import utility.RoundedBorder;

import javax.swing.*;
import java.awt.*;

public class panelBuilder extends JPanel {

    private JLabel label;
    private JTextArea textField;

    public panelBuilder(String s){


        label = new JLabel("<html><body style='font-family:Poppins, sans-serif; color:white;font-size:10px;'>"+s+"</body></html>");
        label.setPreferredSize(new Dimension(80,30));
        label.setFont(FontUtils.getPoppinsFontWithColor(12f,Color.white));

        JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
        leftPanel.add(label);
        leftPanel.setBackground(new Color(47,47,47));

        textField = new JTextArea(2,12);
        textField.setBackground(new Color(47,47,47));
        textField.setFont(FontUtils.getPoppinsFontWithColor(12f,Color.white));
        textField.setCaretColor(Color.white);
        textField.setBorder(new RoundedBorder(Color.white,2,20));
        textField.setLineWrap(true);
        textField.setWrapStyleWord(true);

        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.TRAILING));
        rightPanel.add(textField);
        rightPanel.setBackground(new Color(47,47,47));

        this.add(leftPanel);
        this.add(rightPanel);
        this.setBackground(new Color(47,47,47));

    }

    public void setTextField(int x){
        textField.setColumns(x);
    }

    public void setTextContent(String s){
        textField.setText(s);
    }

}
