package pages;

import javax.swing.*;


public class MyFrame extends JFrame {

    private JPanel currentPanel;

    // Constructor to initialize with LoginPanel
    public MyFrame() {
        initializeFrame();
        this.currentPanel = new LoginPanel(this);
        this.add(currentPanel);
        this.pack();
        this.setLocationRelativeTo(null);
    }

    // Common initialization method
    private void initializeFrame() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("As You Wish");
        this.setVisible(true);
    }

    // Method to replace the current panel with a new one
    public void replacePanel(JPanel newPanel) {
        if (currentPanel != null) {
            this.remove(currentPanel);
        }
        this.currentPanel = newPanel;
        this.add(currentPanel);
        this.revalidate();
        this.repaint();
        this.pack();
    }


}
