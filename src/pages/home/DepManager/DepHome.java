package pages.home.DepManager;

import datamodel.Emp;
import datamodel.LeaveRequest;
import pages.MyPanel;
import pages.home.SysAdmin.AC;
import rolemodel.DepManager;
import utility.EmptyPanel;

import javax.swing.*;
import java.awt.*;

public class DepHome extends JPanel {

    private AC acPanel;

    public DepHome(DepManager manager){

        // Initialize AC panel
        acPanel = new AC(manager.getEmpCompany().getDepartment());

        // Set layout
        this.setLayout(new BorderLayout());

        // Add the AC panel to the current panel
        this.add(acPanel, BorderLayout.CENTER);

        acPanel.getHeader().setTitle("Leave Management:");
        acPanel.getBody().remove(acPanel.getBody().getAddRecord());
        acPanel.getBody().getlistView().removeAll();

        for(LeaveRequest r : LeaveRequest.getRecords()){
            if(getDepartmentByEmpID(r.getEmpId()).equals(manager.getEmpCompany().getDepartment())
                    && r.getStatus().equalsIgnoreCase("Pending")
            ){
                acPanel.getBody().getlistView().add(new DepManagerLabel(r, manager));
                acPanel.getBody().getlistView().add(new EmptyPanel());
            }
        }
    }

    private String getDepartmentByEmpID(String empID){
        return Emp.getRecord(empID).getDepartment();
    }

}
