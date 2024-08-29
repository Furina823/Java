package pages.leave.FirstPage;

import datamodel.Leave;
import datamodel.LeaveRequest;
import pages.MyPanel;
import rolemodel.BaseModel;
import utility.FontUtils;
import utility.RoundedBorder;
import utility.TextFileModifier;
import utility.fetchAllInformation;

import javax.swing.*;
import java.awt.*;

public class LeaveTab extends JPanel {

    private JPanel leftPanel;
    private JPanel rightPanel;
    private JButton resultButton;
    private JButton cancelButton;
    private LeavePanel panel;
    private BaseModel model;

    public LeaveTab(LeaveRequest lr, LeavePanel panel, BaseModel model) {

        this.panel = panel;
        this.model = model;

        leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        leftPanel.setBackground(new Color(47,47,47));

        JLabel nameLabel = new JLabel(lr.getLeaveId());
        nameLabel.setFont(FontUtils.getPoppinsFontWithColor(12f,Color.white));

        JLabel leaveTypeLabel = new JLabel(lr.getLeaveType());
        leaveTypeLabel.setFont(FontUtils.getPoppinsFontWithColor(12f,Color.white));

        JLabel dayStartLabel = new JLabel(lr.getLeaveStartDate());
        dayStartLabel.setFont(FontUtils.getPoppinsFontWithColor(12f,Color.white));

        JLabel dayEndLabel = new JLabel(lr.getLeaveEndDate());
        dayEndLabel.setFont(FontUtils.getPoppinsFontWithColor(12f,Color.white));

        leftPanel.add(nameLabel);
        leftPanel.add(leaveTypeLabel);
        leftPanel.add(dayStartLabel);
        leftPanel.add(dayEndLabel);

        rightPanel = new JPanel();
        rightPanel.setBackground(new Color(47,47,47));

        resultButton = new JButton(lr.getStatus());
        resultButton.setBorder(new RoundedBorder(Color.white, 2,10));
        resultButton.setFont(FontUtils.getPoppinsFontWithColor(15f,Color.white));

        cancelButton = new JButton("Cancel");
        cancelButton.setFont(FontUtils.getPoppinsFontWithColor(15f,Color.white));

        initializeCancelButton(lr.getStatus(),lr,model);

        rightPanel.add(resultButton);
        rightPanel.add(cancelButton);

        this.setBorder(new RoundedBorder(Color.black, 2,20));
        this.setPreferredSize(new Dimension(700,50));
        this.setLayout(new BorderLayout());
        this.add(leftPanel, BorderLayout.WEST);
        this.add(rightPanel, BorderLayout.EAST);
        this.setBackground(new Color(47,47,47));

    }

    private void initializeCancelButton(String status, LeaveRequest l, BaseModel bm){

        switch(status){
            case "Pending" -> cancelButton.addActionListener(e -> setCancelButton(l,bm));
            default -> {cancelButton.setFocusable(false);
            cancelButton.setEnabled(false);}
        }

    }

    private void setCancelButton(LeaveRequest lr, BaseModel baseModel){

        TextFileModifier tfm = new TextFileModifier("leave_request");
        tfm.deleteRecord(lr.getLeaveId());
        baseModel.setLeaveRequest(new LeaveRequest().getRecordsByEmpId(lr.getEmpId()));
        JOptionPane.showMessageDialog(null,"Leave Request had been canceled");
        Container parent = this.getParent();
        if(parent != null){

            parent.remove(this);
            parent.revalidate();
            parent.repaint();

        }else {
            JOptionPane.showMessageDialog(null,"Fail to delete Leave Request");
        }

    }

}
