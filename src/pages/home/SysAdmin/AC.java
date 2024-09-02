package pages.home.SysAdmin;

import pages.MyPanel;
import rolemodel.SysAdministrator;

import javax.swing.*;
import java.awt.*;
import java.util.Optional;

public class AC extends JPanel {

    private JPanel flowPanel;
    private ACHeader header;
    private ACBody body;

    public AC(String department){

        this.header = new ACHeader(department);
        this.body = new ACBody(department);

        flowPanel = new JPanel();
        flowPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        flowPanel.setBackground(Color.black);

        JPanel leftEmptyPanel = new JPanel();
        leftEmptyPanel.setPreferredSize(new Dimension(50,0));

        JPanel rightEmptyPanel = new JPanel();
        rightEmptyPanel.setPreferredSize(new Dimension(50,0));

        flowPanel.add(header);
        flowPanel.add(body);

        this.setLayout(new BorderLayout());
        this.add(flowPanel,BorderLayout.CENTER);
        this.setPreferredSize(new Dimension(850,600));

    }

    protected JPanel getFlowPane(){
        return this.flowPanel;
    }

    public ACHeader getHeader(){
        return this.header;
    }

    public ACBody getBody(){
        return this.body;
    }

}
