package pages.home;

import datamodel.Emp;
import datamodel.PersonalInfo;
import rolemodel.SysAdministrator;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class SysAdministratorHome extends JPanel {

    JPanel panel = new JPanel();

    public SysAdministratorHome(SysAdministrator admin) {

        this.setPreferredSize(new Dimension(850,600));
        this.add(new JLabel("Admin ID : " + admin.getEmpCompany().getEmpID()));
        this.add(new JLabel("Admin Name : " + admin.getEmpPersonalInformation().getFirstname() + " " + admin.getEmpPersonalInformation().getLastname() ));



    }

}
