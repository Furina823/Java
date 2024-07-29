package pages;

import rolemodel.BaseModel;

import javax.swing.*;

public class Logout extends JPanel{

    public Logout(BaseModel baseModel){

        baseModel.getFrame().dispose();
        new MyFrame();

    }

}
