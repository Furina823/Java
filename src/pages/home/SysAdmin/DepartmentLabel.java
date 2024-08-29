package pages.home.SysAdmin;

import pages.MyPanel;
import rolemodel.SysAdministrator;
import utility.RoundedBorder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class DepartmentLabel extends JLabel implements MouseListener{

    private String text;
    private String department;

    public DepartmentLabel(String s){

        text = s + " >";
        department = s;
        this.setBorder(new RoundedBorder(Color.black, 2,10));
        this.setText(text);
        this.addMouseListener(this);
        this.setBackground(new Color(47,47,47));
        this.setOpaque(true);
        this.setFont(new Font("Poppins", Font.PLAIN,20));
        this.setForeground(Color.white);
        this.setMaximumSize(new Dimension(Integer.MAX_VALUE,50));

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource() == this){

            MyPanel.replaceRightPanel(new AC(department));

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

    public String getText(){
        return this.text;
    }

}
