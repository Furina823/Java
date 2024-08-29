package pages.home.HROfficer;

import datamodel.Emp;
import pages.home.SysAdmin.AC;
import utility.EmptyPanel;

public class HRHome_Department extends AC {


    public HRHome_Department(String department) {

        super(department);
        getHeader().setTitle("Profile Management");
        getBody().remove(getBody().getAddRecord());
        getBody().getlistView().removeAll();
        Emp emp = new Emp();
        for(Emp e : emp.getEmpRecords()){
            if(e.getDepartment().equals(department)){
                getBody().getlistView().add(new HRDepartment_List(e.getEmpEmail(),e.getEmpID()));
                getBody().getlistView().add(new EmptyPanel());
            }
        }

    }

}
