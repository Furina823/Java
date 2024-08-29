package pages.home.HROfficer;

import pages.home.SysAdmin.DepartmentGUI;
import pages.home.SysAdmin.DepartmentLabel;
import pages.home.SysAdmin.Departments;
import rolemodel.HROfficer;
import utility.EmptyPanel;

import javax.swing.*;
import java.awt.event.MouseEvent;

public class HROfficerHome extends DepartmentGUI{

    private Departments dep;

    public HROfficerHome(HROfficer hr) {

        dep = new Departments();
        setHeaderLabel("Profile Management:");
        replaceContentPane();

    }

    private void replaceContentPane(){

        getContentPanel().removeAll();
        for(String s : dep.getUniqueDepartments()){
            getContentPanel().add(new EmptyPanel());
            getContentPanel().add(new HRHome_Label(s));
        }


    }


}
