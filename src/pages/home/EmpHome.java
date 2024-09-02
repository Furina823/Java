package pages.home;

import rolemodel.Employee;
import utility.ImagePanel;

import javax.swing.*;
import java.awt.*;

public class EmpHome extends JPanel {

    public EmpHome(Employee employee){

        ImagePanel imagePanel = new ImagePanel("src/picture/resized_image.jpg");
        imagePanel.setBackground(Color.black);

        this.setPreferredSize(new Dimension(850,600));
        this.setBackground(Color.black);
        this.add(imagePanel);

    }

}
