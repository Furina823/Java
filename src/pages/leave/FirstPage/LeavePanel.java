package pages.leave.FirstPage;

import pages.MyPanel;
import rolemodel.BaseModel;
import utility.InvisibleScrollBarUI;
import utility.ScrollPaneUtil;

import javax.swing.*;
import java.awt.*;

public class LeavePanel extends JPanel{

    public LeavePanel(BaseModel bm, JPanel panel){

        this.add(new LeaveHeader());
        this.add(new LeaveAddRecord(bm,panel));
        this.add(ScrollPaneUtil.createScrollPane(new LeaveBody(bm, this)));

        this.setLayout(new FlowLayout(FlowLayout.LEADING));
        this.setBackground(Color.black);
        this.setOpaque(true);

        MyPanel.setButtonAction(MyPanel.createListenerEvent(panel));

    }

}
