package pages.profile;
import pages.MyPanel;
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

    }


    public ProfilePanel(BaseModel bm) {

        this.add(new Header());
        this.add(new firstPage_Content(bm));
        this.setBackground(Color.black);
        this.setLayout(new FlowLayout(FlowLayout.LEADING));

        MyPanel.setButtonAction(MyPanel.createListenerEvent(this));

    }

}
