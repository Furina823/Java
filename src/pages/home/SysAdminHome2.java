package pages.home;

import rolemodel.SysAdministrator;

import javax.swing.*;
import java.awt.*;

public class SysAdminHome2 extends JPanel {

    public SysAdminHome2(SysAdministrator admin) {

        this.add(new JLabel("System Administrator Layout 2"));
        this.add(new JLabel(admin.getEmpPersonalInformation().getFirstname() + " " + admin.getEmpPersonalInformation().getLastname()));

    }

}
