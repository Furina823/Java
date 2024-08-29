package pages.profile.FirstPage;

import pages.MyPanel;
import pages.profile.info.*;
import rolemodel.BaseModel;
import utility.FontUtils;
import utility.RoundedBorder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Content_Label extends JLabel implements MouseListener {

    String info;
    private BaseModel model;

    public Content_Label(String s, BaseModel model){

        this.model = model;
        this.info = s;
        this.setText(s);
        this.setBorder(new RoundedBorder(Color.black, 2,10));
        this.setBackground(new Color(47,47,47));
        this.setOpaque(true);
        this.setForeground(Color.white);
        this.setMaximumSize(new Dimension(Integer.MAX_VALUE,50));
        this.addMouseListener(this);
        this.setFont(FontUtils.getPoppinsFontWithColor(14f,Color.white));

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        switch(info){
            case "Employee Personal Information" -> MyPanel.replaceRightPanel(new Personal(model));
            case "Employee Next of Kin"  -> MyPanel.replaceRightPanel(new NextOfKin(model));
            case "Employee Background"  -> MyPanel.replaceRightPanel(new Background(model));
            case "Position/Salary"  -> MyPanel.replaceRightPanel(new Pos_Sal(model));
            case "Leave"  -> MyPanel.replaceRightPanel(new LeavePage(model));
//            case "Payslip"  -> {};
            case "Account"  -> MyPanel.replaceRightPanel(new Account(model));
            case "Historical"  -> MyPanel.replaceRightPanel(new Historical(model));
            default -> {
                try {
                    throw new Exception();
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
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
