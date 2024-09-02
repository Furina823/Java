package pages.home.DepManager;

import datamodel.Emp;
import datamodel.LeaveRequest;
import pages.home.SysAdmin.AC;
import rolemodel.DepManager;
import utility.EmptyPanel;


public class DepHome extends AC {

    public DepHome(DepManager manager){
        super(manager.getEmpCompany().getDepartment());
        getHeader().setTitle("Leave Management:");
        getBody().remove(getBody().getAddRecord());
        getBody().getlistView().removeAll();


        for(LeaveRequest r : LeaveRequest.getRecords()){
            if(getDepartmentByEmpID(r.getEmpId()).equals(manager.getEmpCompany().getDepartment())
            && r.getStatus().equalsIgnoreCase("Pending")
            ){
                getBody().getlistView().add(new DepManagerLabel(r,manager));
                getBody().getlistView().add(new EmptyPanel());
            }
        }

    }

    private String getDepartmentByEmpID(String empID){

        return Emp.getRecord(empID).getDepartment();

    }

}
