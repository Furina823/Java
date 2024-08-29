package pages.profile;
import pages.profile.FirstPage.Content_Label;
import pages.profile.FirstPage.Header;
import pages.profile.FirstPage.firstPage_Content;
import rolemodel.BaseModel;
import javax.swing.*;

public class ProfilePanel extends JPanel {

    public ProfilePanel(BaseModel bm) {

        this.add(new Header());
        this.add(new firstPage_Content(bm));

    }

}
