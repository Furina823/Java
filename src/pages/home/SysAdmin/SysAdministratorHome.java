package pages.home.SysAdmin;

import rolemodel.SysAdministrator;

import javax.swing.*;

public class SysAdministratorHome extends JPanel {


    public SysAdministratorHome(SysAdministrator admin) {

        this.add(new SysAddGUI(admin));

    }

}