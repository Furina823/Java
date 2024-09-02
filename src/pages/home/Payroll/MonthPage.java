package pages.home.Payroll;

import datamodel.Emp;
import datamodel.Paid;
import pages.MyPanel;
import pages.home.SysAdmin.AC;
import pages.home.SysAdmin.ACBody_List;
import pages.home.SysAdmin.AddRecord;
import pages.profile.info.PaySlip;
import rolemodel.PayRollOfficer;
import utility.EmptyPanel;
import utility.FontUtils;
import utility.PaidSorter;
import utility.RemoveButtonBackground;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class MonthPage extends AC {

    private ACBody_List acb;

    public MonthPage(String s, String empID){

        super(s);
        Emp emp = Emp.getRecord(empID);
        getHeader().setTitle("Payroll Management");
        getBody().getlistView().removeAll();

        ArrayList<Paid> paidList = Paid.getRecordByEmpId(empID);
        PaidSorter.sortPaidByDateDescending(paidList);

        AddRecord.removeListener(getBody().getAddRecord());
        getBody().getAddRecord().addMouseListener(
                new MouseAdapter() {
                    public void mouseClicked(MouseEvent e) {
                        onAddRecord(empID);
                    }
                }
        );

        for(Paid paid : paidList){
            acb = new ACBody_List("",empID);
            getBody().getlistView().add(acb);
            getBody().getlistView().add(new EmptyPanel());
            acb.getLeftPanel().remove(acb.getIDLabel());
            acb.getRightPanel().remove(acb.getActionButton());
            acb.getViewButton().removeActionListener(acb.getActionListener());
            acb.getViewButton().setFocusPainted(false);
            acb.getViewButton().setFont(FontUtils.getPoppinsFont(14f));
            acb.getUsernameLabel().setText(paid.getDate());
            acb.getViewButton().addActionListener(
                    new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            onView(paid.getPaidId());
                        }
                    }
            );
        }


    }

    private void onView(String TaxID){
        new PaySlip(TaxID);
    }

    private void onAddRecord(String empID){
        MyPanel.replaceRightPanel(new AddSalaryPage(empID));
    }

}
