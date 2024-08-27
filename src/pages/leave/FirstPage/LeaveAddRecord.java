package pages.leave.FirstPage;

import pages.MyPanel;
import pages.home.SysAdmin.AddRecord;
import pages.leave.RegistrationPage.LeaveRegistration;
import rolemodel.BaseModel;

import javax.swing.*;

public class LeaveAddRecord extends JPanel {

    AddRecord recordpanel = new AddRecord("");

    public LeaveAddRecord(BaseModel bm) {

        AddRecord.removeListener(recordpanel);
        AddRecord.setMouseAction(recordpanel, AddRecord.MouseAction.CLICKED,onClick(bm));

        this.add(recordpanel);

    }

    public Runnable onClick(BaseModel bm){
        return ()-> {
            MyPanel.replaceRightPanel(new LeaveRegistration(bm));
        };
    }

}
