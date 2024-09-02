package pages.profile;
import datamodel.Emp;
import pages.MyPanel;
import pages.home.HROfficer.HRDepartment_List;
import pages.home.HROfficer.HRHome_Department;
import pages.home.HROfficer.HROfficerHome;
import pages.profile.FirstPage.Content_Label;
import pages.profile.FirstPage.Header;
import pages.profile.FirstPage.firstPage_Content;
import rolemodel.BaseModel;
import javax.swing.*;
import java.awt.*;

public class ProfilePanel extends JPanel {

    public ProfilePanel(String empID){

        this.add(new Header());
        this.add(new firstPage_Content(empID));
        this.setBackground(Color.black);
        this.setLayout(new FlowLayout(FlowLayout.LEADING));

        MyPanel.setButtonAction(MyPanel.createListenerEvent(new HRHome_Department(Emp.getRecord(empID).getDepartment())));

    }


    public ProfilePanel(BaseModel bm) {

        this.add(new Header());
        this.add(new firstPage_Content(bm));
        this.setBackground(Color.black);
        this.setLayout(new FlowLayout(FlowLayout.LEADING));

        MyPanel.setButtonAction(MyPanel.createListenerEvent(this));

    }

}
