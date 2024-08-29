package pages.home.SysAdmin;

import datamodel.Emp;
import utility.EmptyPanel;
import utility.InvisibleScrollBarUI;

import javax.swing.*;
import java.awt.*;

public class ACBody extends JPanel {

    private AddRecord record;
    private JPanel listView;

    public ACBody(String department) {

        record = new AddRecord(department);

        // Set layout for this panel
        setLayout(new BorderLayout());

        this.add(record, BorderLayout.NORTH);

        listView = new JPanel();
        listView.setLayout(new BoxLayout(listView, BoxLayout.Y_AXIS));

        Emp emp = new Emp();
        for(Emp e : emp.getEmpRecords()){
            if(e.getDepartment().equals(department)){
                listView.add(new ACBody_List(e.getEmpEmail(),e.getEmpID()));
                listView.add(new EmptyPanel());
            }
        }

        JScrollPane scrollPane = new JScrollPane(listView);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        JScrollBar verticalBar = scrollPane.getVerticalScrollBar();
        verticalBar.setUI(new InvisibleScrollBarUI());
        JScrollBar horizontalBar = scrollPane.getHorizontalScrollBar();
        horizontalBar.setUI(new InvisibleScrollBarUI());

        this.add(scrollPane, BorderLayout.CENTER);

        this.setPreferredSize(new Dimension(700, 500));

    }

    public AddRecord getAddRecord(){
        return this.record;
    }

    public JPanel getlistView(){
        return this.listView;
    }


}
