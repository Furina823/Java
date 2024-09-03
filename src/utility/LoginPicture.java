package utility;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class LoginPicture extends JPanel {
    private BufferedImage image;

     public LoginPicture(String imagePath) {

         try {
             image = ImageIO.read(new File(imagePath));
         } catch (IOException e) {
             e.printStackTrace();
         }

     }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (image != null) {
            g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(550, 600);
    }

}
