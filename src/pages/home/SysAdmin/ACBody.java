package pages.home.SysAdmin;

import datamodel.Emp;
import utility.InvisibleScrollBarUI;

import javax.swing.*;
import java.awt.*;

public class ACBody extends JPanel {

    public ACBody(String department) {
        // Set layout for this panel
        setLayout(new BorderLayout());

        this.add(new AddRecord(department), BorderLayout.NORTH);

        JPanel listView = new JPanel();
        listView.setLayout(new BoxLayout(listView, BoxLayout.Y_AXIS));

        Emp emp = new Emp();
        for(Emp e : emp.getEmpRecords()){
            if(e.getDepartment().equals(department)){
                listView.add(new ACBody_List(e.getEmpEmail(),e.getEmpID()));
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
}
