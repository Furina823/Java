package pages.home;

import rolemodel.DepManager;

import javax.swing.*;
import java.awt.*;

public class DepHome extends JPanel {

    public DepHome(DepManager manager){

        this.setPreferredSize(new Dimension(850,600));
        this.add(new JLabel(manager.getEmpPersonalInformation().getFirstname() + " " + manager.getEmpPersonalInformation().getLastname()));

    }

}
