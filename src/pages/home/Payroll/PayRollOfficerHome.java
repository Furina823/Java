package pages.home.Payroll;

import datamodel.PersonalInfo;
import pages.MyPanel;
import pages.home.SysAdmin.AC;
import pages.home.SysAdmin.ACBody_List;
import pages.home.SysAdmin.DepartmentGUI;
import pages.home.SysAdmin.DepartmentLabel;
import rolemodel.PayRollOfficer;
import utility.FontUtils;
import utility.RemoveButtonBackground;
import utility.RoundedBorder;

import java.awt.*;
import java.awt.event.*;


public class PayRollOfficerHome extends DepartmentGUI {

    private AC ac;

    public PayRollOfficerHome() {

        setHeaderLabel("Payroll Management:");
        getContentPanel().remove(getNext());

        for(Component component : getContentPanel().getComponents()){
            if(component instanceof DepartmentLabel departmentLabel){

                for(MouseListener listener : departmentLabel.getMouseListeners()){
                    departmentLabel.removeMouseListener(listener);
                }

                departmentLabel.addMouseListener(
                        new MouseAdapter(){
                            public void mouseClicked(MouseEvent e){
                                onClick(departmentLabel.getDepartment());
                            }
                        }
                );


            }
        }

    }

    private void onClick(String s){
        ac = new AC(s);
        MyPanel.replaceRightPanel(ac);
        MyPanel.setButtonAction(MyPanel.createListenerEvent(this));
        ac.getBody().remove(ac.getBody().getAddRecord());
        ac.getHeader().setTitle("Payroll Management");

        for(Component component : ac.getBody().getlistView().getComponents()){
            if(component instanceof ACBody_List list){
                list.getRightPanel().remove(list.getActionButton());
                list.getViewButton().removeActionListener(list.getActionListener());
                list.getLeftPanel().remove(list.getIDLabel());
                PersonalInfo info = PersonalInfo.getPersonalInfo(list.getIDLabel().getText());
                list.setUsernameLabelText(info.getFirstname()+" "+info.getLastname());
                list.getViewButton().setFocusPainted(false);
                list.getViewButton().setFont(FontUtils.getPoppinsFontWithColor(14f,Color.white));
                list.getViewButton().addActionListener(
                        new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                onAcClick(s,info.getEmpid());
                            }
                        }
                );
            }
        }
    }

    private void onAcClick(String department, String empID){

        MyPanel.replaceRightPanel(new MonthPage(department,empID));
        MyPanel.setButtonAction(MyPanel.createListenerEvent(ac));
        revalidate();
        repaint();

    }

}
