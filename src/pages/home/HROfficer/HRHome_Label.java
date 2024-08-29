package pages.home.HROfficer;

import pages.MyPanel;
import pages.home.SysAdmin.DepartmentLabel;

import java.awt.event.MouseEvent;


public class HRHome_Label extends DepartmentLabel {

    private String text;

    public HRHome_Label(String s) {

        super(s);
        this.text = s;

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println(getText());
        MyPanel.replaceRightPanel(new HRHome_Department(text));
    }

}

