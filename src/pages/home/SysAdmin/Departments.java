package pages.home.SysAdmin;

import datamodel.Emp;
import rolemodel.Employee;

import java.util.ArrayList;

public class Departments {

    private ArrayList<String> departments = new ArrayList<>();
    private ArrayList<String> uniqueDepartments = new ArrayList<>();

    public Departments(){

        Emp emp = new Emp();
        departments = new ArrayList<>();

        for(Emp e : emp.getEmpRecords()){
            departments.add(e.getDepartment());
        }


        uniqueDepartments = new ArrayList<>();
        for(String s : departments){
            if(!uniqueDepartments.contains(s)){
                uniqueDepartments.add(s);
            }
        }
    }

    public ArrayList<String> getUniqueDepartments(){
        return uniqueDepartments;
    }

}
