package pages;
import rolemodel.BaseModel;
import javax.swing.*;

public class ProfilePanel extends JPanel {

    public ProfilePanel(BaseModel bm) {

        JLabel label = new JLabel();
        label.setText("This is Profile Panel");
        this.add(label);

    }

}
