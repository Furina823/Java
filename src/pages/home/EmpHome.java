package pages.home;

import rolemodel.Employee;

import javax.swing.*;
import java.awt.*;

public class EmpHome extends JPanel {

    public EmpHome(Employee employee){

        this.setPreferredSize(new Dimension(850,600));

        this.add(new JLabel("This is employee home"));

    }

}
