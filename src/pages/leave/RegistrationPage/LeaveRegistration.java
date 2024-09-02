package pages.leave.RegistrationPage;

import pages.MyPanel;
import pages.leave.FirstPage.LeaveHeader;
import pages.leave.FirstPage.LeavePanel;
import rolemodel.BaseModel;

import javax.swing.*;
import java.awt.*;

public class LeaveRegistration extends JPanel {

    public LeaveRegistration(BaseModel bm, JPanel panel){

        this.add(new LeaveHeader());
        this.add(new RegistrationBody(bm));
        this.setBackground(Color.black);

        this.setLayout(new FlowLayout(FlowLayout.LEADING,0,0));

        MyPanel.setButtonAction(MyPanel.createListenerEvent(new LeavePanel(bm,panel)));

    }

}
