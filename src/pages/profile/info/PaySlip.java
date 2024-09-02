package pages.profile.info;

import datamodel.Emp;
import datamodel.Paid;
import datamodel.PersonalInfo;
import utility.BorderUtils;
import utility.FontUtils;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedHashMap;
import java.util.Map;

public class PaySlip {

        public PaySlip(String TaxID){

            Paid paid = Paid.getRecordByTaxID(TaxID);

            Emp emp = Emp.getRecord(paid.getEmpId());
            PersonalInfo infos = PersonalInfo.getPersonalInfo(paid.getEmpId());

            String[] info = {
                    String.format("%-12s %s", "Name:", infos.getFirstname() + " " + infos.getLastname()),
                    String.format("%-12s %s", "Date:", paid.getDate()),
                    String.format("%-12s %s", "IC:", infos.getNric()),
                    String.format("%-12s %s", "Department:", emp.getDepartment()),
                    String.format("%-12s %s", "Emp ID:", paid.getEmpId()),
                    String.format("%-12s %s", "Role:", emp.getRole()),
                    String.format("%-12s %s", "Position:", emp.getPosition()),
                    String.format("%-12s %s", "Tax ID:", paid.getPaidId())
            };

            LinkedHashMap<String,String> maps1 = new LinkedHashMap<>();
            maps1.put("Income","Amount(RM)");
            maps1.put("Basic PAY", paid.getBasicGross());
            maps1.put("Bonus", paid.getBonus());
            maps1.put("Allowance", paid.getAllowance());
            maps1.put("Total", "");

            LinkedHashMap<String,String> maps2 = new LinkedHashMap<>();
            maps2.put("Deduction","Amount(RM)");
            maps2.put("Employee EPF", paid.getEpfEmpe());
            maps2.put("Employee SOCSO", paid.getSocsoEmpe());
            maps2.put("Employee EIS", paid.getEisEmpe());
            maps2.put("Income Tax PCB", paid.getPcbEmpe());
            maps2.put("Late Attendance", paid.getLateAttendance());
            maps2.put("Unpaid Leave", paid.getUnpaidLeave());
            maps2.put("Total","");

            LinkedHashMap<String,String> maps3 = new LinkedHashMap<>();
            maps3.put("Contribution","Amount(RM)");
            maps3.put("Employer EPF", paid.getEpfEmpy());
            maps3.put("Employer SOCSO", paid.getSocsoEmpy());
            maps3.put("Employer EIS", paid.getEisEmpy());
            maps3.put("Income Tax PCB", paid.getPcbEmpy());
            maps3.put("Total","");

            JPanel infoPane = new JPanel(new GridLayout(4,2));
            for(String s : info) infoPane.add(infoLabel(s));

            JPanel TaxPane = new JPanel(new GridLayout(1,3));
            TaxPane.add(centerPane(maps1));
            TaxPane.add(centerPane(maps2));
            TaxPane.add(centerPane(maps3));

            for (Component component : TaxPane.getComponents()) {
                if(component instanceof JPanel) {
                    ((JPanel)component).setBorder(BorderUtils.createCustomBorder(Color.white,1,0));
                }
            }

            JPanel contentPane = new JPanel(new GridBagLayout());
            GridBagConstraints c = new GridBagConstraints();

            c.fill = GridBagConstraints.BOTH;
            c.weightx = 1;
            c.weighty = 1;
            c.gridx = 0;
            c.gridy = 0;

            contentPane.add(infoPane,c);

            c.gridy++;

            contentPane.add(TaxPane,c);

            JLabel companyLabel = new JLabel("As You Wish Payslip");
            companyLabel.setFont(FontUtils.getPoppinsFontWithColor(16f,Color.white));

            JPanel topPanel = new JPanel();
            topPanel.setLayout(new FlowLayout(FlowLayout.LEADING));
            topPanel.add(companyLabel);

            double net = Double.parseDouble(paid.getBasicGross()) + Double.parseDouble(paid.getAllowance()) + Double.parseDouble(paid.getBonus()) -
            Double.parseDouble(paid.getEpfEmpe()) - Double.parseDouble(paid.getSocsoEmpe()) -
            Double.parseDouble(paid.getEisEmpe()) - Double.parseDouble(paid.getPcbEmpe());

            JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.TRAILING));
            JLabel netAmountLabel = new JLabel("Net Amount:"+net);
            netAmountLabel.setFont(FontUtils.getPoppinsFontWithColor(16f,Color.white));

            bottomPanel.add(netAmountLabel);

            JPanel panel = new JPanel(new BorderLayout());
            panel.setBounds(0, 0, 730, 500); // Set panel size and position

            panel.add(contentPane,BorderLayout.CENTER);
            panel.add(topPanel,BorderLayout.NORTH);
            panel.add(bottomPanel,BorderLayout.SOUTH);
            panel.setBackground(Color.black);

            JFrame frame = new JFrame();
            frame.setLayout(null); // Set layout to null
            frame.setSize(745, 535); // Set the frame size to fit the panel
            frame.add(panel); // Add panel to the frame
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setVisible(true);
            frame.setLocationRelativeTo(null); // Center the frame on the screen
            frame.setResizable(false);

            setBackgroundColor(panel,Color.black);

        }

        private static JLabel infoLabel (String s){
            JLabel label = new JLabel(s);
            label.setPreferredSize(new Dimension(100,15));
            label.setFont(FontUtils.getPoppinsFontWithColor(12f,Color.white));
            return label;
        }

        private static JPanel centerPane(LinkedHashMap<String, String> maps) {
            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

            double total = 0;
            for (Map.Entry<String, String> e : maps.entrySet()) {
                if (!e.getKey().equals("Total") && !e.getKey().equals("Income") &&
                        !e.getKey().equals("Deduction") && !e.getKey().equals("Contribution")) {
                    total += Double.parseDouble(e.getValue());
                }
            }

            for (Map.Entry<String, String> e : maps.entrySet()) {
                String key = e.getKey();
                String value = key.equals("Total") ? String.valueOf(total) : e.getValue();
                JPanel panel2 = linePanel(key,value);
                panel.add(panel2);
                if(key.equals("Income") || key.equals("Deduction") || key.equals("Contribution")){
                    panel2.setBorder(BorderUtils.createCustomBorder(Color.white,1,0));
                    panel2.setMaximumSize(new Dimension(500,20));
                }
            }

            return panel;
        }


        private static JPanel linePanel(String x, String y){

            JLabel leftLabel = infoLabel(x);
            JLabel rightLabel = infoLabel(y);

            JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
            leftPanel.add(leftLabel);
            JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.TRAILING));
            rightPanel.add(rightLabel);

            JPanel panel = new JPanel(new GridLayout(1,2));
            panel.add(leftPanel);
            panel.add(rightPanel);
            panel.setMaximumSize(new Dimension(600,30));
            return panel;

        }

        private static void setBackgroundColor(Component component, Color color) {
            if (component instanceof JComponent) {
                component.setBackground(color);
            }
            if (component instanceof Container) {
                for (Component child : ((Container) component).getComponents()) {
                    setBackgroundColor(child, color);
                }
            }
        }
}
