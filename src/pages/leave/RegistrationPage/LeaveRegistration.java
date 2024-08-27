package pages.leave.RegistrationPage;

import pages.leave.FirstPage.LeaveHeader;
import rolemodel.BaseModel;

import javax.swing.*;

public class LeaveRegistration extends JPanel {

    public LeaveRegistration(BaseModel bm){

        this.add(new LeaveHeader());
        this.add(new RegistrationBody(bm));

    }

}
