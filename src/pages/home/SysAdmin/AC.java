package pages.home.SysAdmin;

import rolemodel.SysAdministrator;

import javax.swing.*;
import java.awt.*;

public class AC extends JPanel {

    public AC(String department){

        JPanel flowPanel = new JPanel();
        flowPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        JPanel leftEmptyPanel = new JPanel();
        leftEmptyPanel.setPreferredSize(new Dimension(50,0));

        JPanel rightEmptyPanel = new JPanel();
        rightEmptyPanel.setPreferredSize(new Dimension(50,0));

        flowPanel.add(new ACHeader(department));
        flowPanel.add(new ACBody(department));

        this.setLayout(new BorderLayout());
        this.add(flowPanel,BorderLayout.CENTER);
        this.add(leftEmptyPanel,BorderLayout.WEST);
        this.add(rightEmptyPanel,BorderLayout.EAST);
        this.setPreferredSize(new Dimension(850,600));

    }

}
