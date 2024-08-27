package pages.home.SysAdmin;

import datamodel.Emp;

import javax.swing.*;
import java.awt.*;

public class Register extends JPanel {

    public Register(String department, Emp emp) {

        this.add(new ACHeader(department));
        this.add(new RegisterContent(emp,department));
        this.setLayout(new FlowLayout(FlowLayout.LEADING));

    }

    public Register(String department){

        this.add(new ACHeader(department));
        this.add(new RegisterContent(department));
        this.setLayout(new FlowLayout(FlowLayout.LEADING));

    }


}
