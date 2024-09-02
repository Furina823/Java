package pages.home.SysAdmin;

import rolemodel.SysAdministrator;

import javax.swing.*;
import java.awt.*;

public class SysAdministratorHome extends JPanel {

    public SysAdministratorHome() {

        this.add(new SysAddGUI());
        this.setBackground(Color.black);

    }

}