package pages.home;

import rolemodel.HROfficer;

import javax.swing.*;
import java.awt.*;

public class HROfficerHome extends JPanel {

    JPanel panel;

    public HROfficerHome(HROfficer hr) {

        this.setPreferredSize(new Dimension(850,600));

//        JButton button = new JButton("Button");
//        button.addActionListener(e -> {this.removeAll();
//            this.add(new HROfficerHome2(hr));
//            this.revalidate();
//            this.repaint();
//
//        });
//
//        panel = new JPanel();
//        panel.add(button);
//        panel.add(new JLabel("This is HR Page 1This is HR Page 1This is HR Page 1This is HR Page 1This is HR Page 1This is HR Page 1This is HR Page 1This is HR Page 1"));
//        panel.setPreferredSize(new Dimension(850,600));
//        this.add(panel);

//        ArrayList<String> userIds = new ArrayList<>();
//        Emp e = new Emp();
//        PersonalInfo p = new PersonalInfo();
//
//        for(Emp emp : e.getEmpRecords()){
//
//            if (emp.getDepartment().equals("Marketing")){
//                userIds.add(emp.getEmpID());
//            }
//        }
//
//        for(PersonalInfo personal : p.getPersonalInfoRecords()){
//
//            for(String userid : userIds){
//
//                if(personal.getEmpid().equals(userid)) {
//                    this.add(new JLabel("Employee ID: " + userid + " \n Full Name :" + personal.getFirstname() + " " + personal.getLastname()));
//                }
//            }
//
//        }



//        this.add(new JLabel("ID : "+ hr.getEmpCompany().getEmpID()));
//        this.add(new JLabel());
//        this.add(new JLabel("Email : "+ hr.getEmpCompany().getRole()));
//        this.add(new JLabel());
//        this.add(new JLabel("Password : "+ hr.getEmpCompany().getEmpPassword()));
//        this.add(new JLabel());
//        this.add(new JLabel("Date_Join : "+ hr.getEmpCompany().getDateJoin()));
//        this.add(new JLabel());
//        this.add(new JLabel("Date_Leave : "+ hr.getEmpCompany().getDateLeave()));
//        this.add(new JLabel());
//        this.add(new JLabel("Salary : "+ hr.getEmpCompany().getSalary()));
//        this.add(new JLabel());
//        this.add(new JLabel("Position : "+ hr.getEmpCompany().getPosition()));
//        this.add(new JLabel());
//        this.add(new JLabel("Department : "+ hr.getEmpCompany().getDepartment()));
//        this.add(new JLabel());
//        this.add(new JLabel("Role : "+ hr.getEmpCompany().getRole()));

    }

}
