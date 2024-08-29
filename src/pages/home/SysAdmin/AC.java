package pages.home.SysAdmin;

import rolemodel.SysAdministrator;

import javax.swing.*;
import java.awt.*;

public class AC extends JPanel {

    private JPanel flowPanel;
    private ACHeader header;
    private ACBody body;

    public AC(String department){

        this.header = new ACHeader(department);
        this.body = new ACBody(department);

        flowPanel = new JPanel();
        flowPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        JPanel leftEmptyPanel = new JPanel();
        leftEmptyPanel.setPreferredSize(new Dimension(50,0));

        JPanel rightEmptyPanel = new JPanel();
        rightEmptyPanel.setPreferredSize(new Dimension(50,0));

        flowPanel.add(header);
        flowPanel.add(body);

        this.setLayout(new BorderLayout());
        this.add(flowPanel,BorderLayout.CENTER);
        this.add(leftEmptyPanel,BorderLayout.WEST);
        this.add(rightEmptyPanel,BorderLayout.EAST);
        this.setPreferredSize(new Dimension(850,600));

    }

    protected JPanel getFlowPane(){
        return this.flowPanel;
    }

    protected ACHeader getHeader(){
        return this.header;
    }

    protected ACBody getBody(){
        return this.body;
    }

}
