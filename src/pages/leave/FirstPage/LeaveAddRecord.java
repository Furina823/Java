package pages.leave.FirstPage;

import pages.MyPanel;
import pages.home.SysAdmin.AddRecord;
import pages.leave.RegistrationPage.LeaveRegistration;
import rolemodel.BaseModel;

import javax.swing.*;
import java.awt.*;

public class LeaveAddRecord extends JPanel {

    AddRecord recordpanel = new AddRecord("");

    public LeaveAddRecord(BaseModel bm, JPanel panel) {

        AddRecord.removeListener(recordpanel);
        AddRecord.setMouseAction(recordpanel, AddRecord.MouseAction.CLICKED,onClick(bm,panel));

        this.setBackground(Color.black);
        this.add(recordpanel);
        this.setPreferredSize(new Dimension(840, 80));

    }

    public Runnable onClick(BaseModel bm, JPanel panel){
        return ()-> {
            MyPanel.replaceRightPanel(new LeaveRegistration(bm, panel));
        };
    }

}
