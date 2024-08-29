package pages.profile.info;

import javax.swing.*;

import pages.MyPanel;
import pages.home.SysAdmin.AC;
import pages.home.SysAdmin.SysAddGUI;
import rolemodel.BaseModel;
import rolemodel.SysAdministrator;
import utility.FontUtils;
import utility.RoundedBorder;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class History_Label extends JLabel implements MouseListener{

    private String ss;
    private BaseModel model;

    public History_Label(String s, BaseModel model){

        ss = s;
        this.model = model;

        this.setText(s);
        this.addMouseListener(this);
        this.setFont(FontUtils.getPoppinsFontWithColor(16f,Color.white));
        this.setMaximumSize(new Dimension(Integer.MAX_VALUE,50));
        this.setPreferredSize(new Dimension(700,50));
        this.setBorder(new RoundedBorder(Color.black, 2,10));
        this.setBackground(new Color(47,47,47));
        this.setOpaque(true);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        if(e.getSource() == this){
            switch(ss){
                case "Position" -> MyPanel.replaceRightPanel(new Historical_Position(model));
                case "Salary Increment" -> MyPanel.replaceRightPanel(new Salary_Increment(model));
                default -> throw new RuntimeException();
            }
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


}
