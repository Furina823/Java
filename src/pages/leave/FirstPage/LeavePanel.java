package pages.leave.FirstPage;

import rolemodel.BaseModel;

import javax.swing.*;

public class LeavePanel extends JPanel{

    public LeavePanel(BaseModel bm){

        this.add(new LeaveHeader());
        this.add(new LeaveAddRecord(bm));

    }

}
