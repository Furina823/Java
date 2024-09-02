package pages.home.SysAdmin;

import rolemodel.SysAdministrator;

import javax.swing.*;
import java.awt.*;

public class SysAddGUI extends JPanel {

    private JPanel currentPanel;
    private JPanel originalPanel; // To store the original panel

    public SysAddGUI() {
        // Initialize the original panel
        originalPanel = new DepartmentGUI();
        currentPanel = originalPanel; // Set the current panel to the original
        currentPanel.setBackground(Color.black);

        this.setLayout(new FlowLayout(FlowLayout.LEADING));
        this.setBackground(Color.black);
        this.add(currentPanel); // Add the original panel to the JFrame

    }


}
