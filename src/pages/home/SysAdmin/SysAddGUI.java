package pages.home.SysAdmin;

import rolemodel.SysAdministrator;

import javax.swing.*;
import java.awt.*;

public class SysAddGUI extends JPanel {

    private JPanel currentPanel;
    private JPanel originalPanel; // To store the original panel

    public SysAddGUI(SysAdministrator admin) {
        // Initialize the original panel
        originalPanel = new DepartmentGUI();
        currentPanel = originalPanel; // Set the current panel to the original

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBackground(Color.black);
        this.add(currentPanel); // Add the original panel to the JFrame

    }

    public void replacePanel(JPanel panel) {
        if (currentPanel != null) {
            this.remove(currentPanel);
        }

        currentPanel = panel;
        this.add(currentPanel);
        this.revalidate();
        this.repaint();
    }

    // Method to return to the original panel
    public void returnToOriginalPanel() {
        replacePanel(originalPanel);
    }

}
