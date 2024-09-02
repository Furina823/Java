package pages.home.HROfficer;

import pages.MyPanel;
import pages.home.SysAdmin.ACBody_List;

public class HRDepartment_List extends ACBody_List {

    private String id;

    public HRDepartment_List(String email, String empID) {

        super(email, empID);
        this.id = empID;

        getRightPanel().remove(getActionButton());
        getRightPanel().remove(getIDLabel());
        getViewButton().removeActionListener(getActionListener());
        getViewButton().addActionListener(e -> onView(id));

        MyPanel.setButtonAction(MyPanel.createListenerEvent(new HROfficerHome()));

    }


    private void onView(String id){

        MyPanel.replaceRightPanel(new ProfileCategories(id));

    }

}
