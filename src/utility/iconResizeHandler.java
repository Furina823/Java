package utility;

import javax.swing.*;
import java.awt.*;

public class iconResizeHandler{

    final private ImageIcon icon;

    public iconResizeHandler(int width, int height, ImageIcon icon){
        Image image = icon.getImage();
        Image newing = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        this.icon = new ImageIcon(newing);
    }

    public ImageIcon getImageIcon(){
        return this.icon;
    }

}
