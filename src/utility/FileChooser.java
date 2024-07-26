package utility;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

class FileChooser implements ActionListener {

    JButton button;
    String path;

    FileChooser(){

        button = new JButton("Select File");
        button.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource()==button){

            JFileChooser filechooser = new JFileChooser();
            filechooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            int returnVal = filechooser.showOpenDialog(button);
            if(returnVal == JFileChooser.APPROVE_OPTION){
                File file = filechooser.getSelectedFile();
                this.path = file.getAbsolutePath();

            }
        }
    }


    public JButton getButton(){
        return button;
    }

    public String getPath() {
        return path;
    }
}
