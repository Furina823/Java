package pages.home;

import rolemodel.HROfficer;

import javax.swing.*;

public class HROfficerHome2 extends JPanel {

    public HROfficerHome2(HROfficer hr){

        this.add(new JLabel("This is HR Home Page 2"));
        this.add(new JLabel(hr.getEmpCompany().getEmpID()));
        this.setSize(850,600);

    }

}
