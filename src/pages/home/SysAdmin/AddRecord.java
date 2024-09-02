package pages.home.SysAdmin;

import pages.MyPanel;
import utility.ColorChanger;
import utility.RoundedBorder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

public class AddRecord extends JPanel implements MouseListener{

    private String department;
    private JPanel AddRecordPanel;

    public AddRecord(String department){

        this.department = department;

        ImageIcon originalIcon = new ImageIcon("src/picture/plus-solid.png");
        originalIcon.setImage(originalIcon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH));

        BufferedImage image = new BufferedImage(
                originalIcon.getIconWidth(),
                originalIcon.getIconHeight(),
                BufferedImage.TYPE_INT_ARGB);
        Graphics g = image.createGraphics();
        originalIcon.paintIcon(null, g, 0, 0);
        g.dispose();

        ColorChanger colorChanger = new ColorChanger(Color.white);
        BufferedImage newImage = colorChanger.changeColor(image);
        ImageIcon icon = new ImageIcon(newImage);

        JLabel AddRecordLabel = new JLabel();
        AddRecordLabel.setIcon(icon);
        AddRecordLabel.setHorizontalAlignment(SwingConstants.CENTER);
        AddRecordLabel.setVerticalAlignment(SwingConstants.CENTER);

        AddRecordPanel = new JPanel();
        AddRecordPanel.add(AddRecordLabel);
        AddRecordPanel.setOpaque(false);
        AddRecordPanel.addMouseListener(this);
        AddRecordPanel.setPreferredSize(new Dimension(840, 50));
        AddRecordPanel.setBorder(new RoundedBorder(Color.white, 2, 30));

        this.add(AddRecordPanel);
        this.setBackground(Color.black);
        AddRecordPanel.setBackground(Color.black);

    }


    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource() == AddRecordPanel){
            MyPanel.replaceRightPanel(new Register(department));
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
    public void paintComponent(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(getBackground());
        g2d.fillRoundRect(0, 0, getWidth()-1, getHeight()-1, 30,30);
        super.paintComponent(g);
    }

    public static JPanel removeListener(AddRecord record){
        for(MouseListener l : record.getAddRecordPanel().getMouseListeners()){
            record.getAddRecordPanel().removeMouseListener(l);
        }
        return record;
    }

    public enum MouseAction{
        CLICKED,
        PRESSED,
        RELEASED,
        ENTERED,
        EXITED
    }

    public static JPanel setMouseAction(AddRecord record, MouseAction action, Runnable runnable){
        record.getAddRecordPanel().addMouseListener(
                new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        if(action == MouseAction.CLICKED){
                            runnable.run();
                        }
                    }

                    @Override
                    public void mousePressed(MouseEvent e) {
                        if (action == MouseAction.PRESSED) {
                            runnable.run();
                        }
                    }

                    @Override
                    public void mouseReleased(MouseEvent e) {
                        if (action == MouseAction.RELEASED) {
                            runnable.run();
                        }
                    }

                    @Override
                    public void mouseEntered(MouseEvent e) {
                        if (action == MouseAction.ENTERED) {
                            runnable.run();
                        }
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        if (action == MouseAction.EXITED) {
                            runnable.run();
                        }
                    }
                }

        );
        return record;
    }

    public JPanel getAddRecordPanel(){
        return this.AddRecordPanel;
    }

}
