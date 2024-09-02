package pages.leave.FirstPage;

import datamodel.LeaveRequest;
import rolemodel.BaseModel;
import utility.EmptyPanel;
import utility.InvisibleScrollBarUI;

import javax.swing.*;
import java.awt.*;

public class LeaveBody extends JPanel {

    public LeaveBody(BaseModel bm, LeavePanel panel){

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        for(LeaveRequest lr : bm.getLeaveRequest()){
            this.add(new LeaveTab(lr, panel,bm));
            this.add(new EmptyPanel());
        }

        this.setBackground(Color.black);


    }

}
