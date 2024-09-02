package pages.home.SysAdmin;

import datamodel.Emp;
import pages.MyPanel;

import javax.swing.*;
import java.awt.*;

public class Register extends JPanel {

    public Register(String department, Emp emp) {
        MyPanel.setButtonAction(MyPanel.createListenerEvent(new AC(department)));
        this.add(new ACHeader(department));
        this.add(new RegisterContent(emp,department));
        this.setBackground(Color.black);
        this.setLayout(new FlowLayout(FlowLayout.LEADING));

    }

    public Register(String department){
        MyPanel.setButtonAction(MyPanel.createListenerEvent(new AC(department)));
        this.add(new ACHeader(department));
        this.add(new RegisterContent(department));
        this.setBackground(Color.black);
        this.setLayout(new FlowLayout(FlowLayout.LEADING));

    }


}
