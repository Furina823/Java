import datamodel.WorkSchedule;
import utility.ImagePanel;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;

public class test {

    public static void main(String[] args) {

        ImagePanel panel = new ImagePanel("src/picture/login.jpg");

        JFrame frame = new JFrame();
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setPreferredSize(new Dimension(550, 600));
        frame.setMaximumSize(new Dimension(550, 600));
        frame.setMinimumSize(new Dimension(550, 600));
        frame.add(panel);

    }



}