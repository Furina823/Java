package pages.profile.FirstPage;

import pages.MyPanel;
import pages.home.Payroll.MonthPage;
import pages.profile.info.*;
import rolemodel.BaseModel;
import utility.FontUtils;
import utility.RoundedBorder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Content_Label extends JLabel implements MouseListener {

    String info;
    private BaseModel model;
    private String department;
    private String empID;
    private MonthPage monthPage;

    public Content_Label(String s, String empID){

        MouseListener listener = new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                switch (info) {
                    case "Employee Personal Information" -> MyPanel.replaceRightPanel(new Personal(empID));
                    case "Employee Next of Kin" -> MyPanel.replaceRightPanel(new NextOfKin(empID));
                    case "Employee Background" -> MyPanel.replaceRightPanel(new Background(empID));
                    case "Position/Salary" -> MyPanel.replaceRightPanel(new Pos_Sal(empID));
                    case "Leave" -> MyPanel.replaceRightPanel(new LeavePage(empID));
                    case "Account" -> MyPanel.replaceRightPanel(new Account(empID));
                    default -> {
                        try {
                            throw new Exception();
                        } catch (Exception ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                }
            }
        };

        this.info = s;
        this.setText(s);
        this.setBorder(new RoundedBorder(Color.black, 2,10));
        this.setBackground(new Color(47,47,47));
        this.setOpaque(true);
        this.setForeground(Color.white);
        this.setMaximumSize(new Dimension(Integer.MAX_VALUE,50));
        this.addMouseListener(listener);
        this.setFont(FontUtils.getPoppinsFontWithColor(14f,Color.white));

    }


    public Content_Label(String s, BaseModel model){

        empID = model.getEmpCompany().getEmpID();
        department = model.getEmpCompany().getDepartment();

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
        monthPage = new MonthPage(department,empID);
        monthPage.getBody().remove(monthPage.getBody().getAddRecord());

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        switch(info){
            case "Employee Personal Information" -> MyPanel.replaceRightPanel(new Personal(model));
            case "Employee Next of Kin"  -> MyPanel.replaceRightPanel(new NextOfKin(model));
            case "Employee Background"  -> MyPanel.replaceRightPanel(new Background(model));
            case "Position/Salary"  -> MyPanel.replaceRightPanel(new Pos_Sal(model));
            case "Leave"  -> MyPanel.replaceRightPanel(new LeavePage(model));
            case "Payslip"  -> MyPanel.replaceRightPanel(monthPage);
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
