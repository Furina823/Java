package utility;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class panelBuilder2 extends JPanel {

    private final JLabel label;
    private final JTextArea textField;
    private final JLabel binLabel;
    private final JPanel buttonPane;
    private final JLabel addLabel;

    public panelBuilder2(String s){

        label = new JLabel("<html><body style='font-family:Poppins, sans-serif; color:white;font-size:10px;'>" + s + "</body></html>");

        ImageIcon originalIcon = new ImageIcon("src/picture/bin.png");
        originalIcon.setImage(originalIcon.getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH));

        BufferedImage image = new BufferedImage(
                originalIcon.getIconWidth(),
                originalIcon.getIconHeight(),
                BufferedImage.TYPE_INT_ARGB);
        Graphics g = image.createGraphics();
        originalIcon.paintIcon(null, g, 0, 0);
        g.dispose();

        ColorChanger colorChanger = new ColorChanger(Color.white);
        BufferedImage newImage = colorChanger.changeColor(image);
        ImageIcon binIcon = new ImageIcon(newImage);

        ImageIcon originalIcon2 = new ImageIcon("src/picture/plus-symbol-button.png");
        originalIcon2.setImage(originalIcon2.getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH));

        BufferedImage image2 = new BufferedImage(
                originalIcon2.getIconWidth(),
                originalIcon2.getIconHeight(),
                BufferedImage.TYPE_INT_ARGB);
        Graphics g2 = image2.createGraphics();
        originalIcon2.paintIcon(null, g2, 0, 0);
        g2.dispose();

        ColorChanger colorChanger2 = new ColorChanger(Color.white);
        BufferedImage newImage2 = colorChanger2.changeColor(image2);
        ImageIcon addIcon = new ImageIcon(newImage2);

        binLabel = new JLabel(binIcon);
        binLabel.setMinimumSize(new Dimension(20, 15));
        binLabel.setBackground(new Color(47, 47, 47));

        addLabel = new JLabel(addIcon);
        addLabel.setMinimumSize(new Dimension(20, 15));
        addLabel.setBackground(new Color(47, 47, 47));

        label.setPreferredSize(new Dimension(80, 30));
        label.setFont(FontUtils.getPoppinsFontWithColor(12f, Color.white));

        JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
        leftPanel.add(label);
        leftPanel.setBackground(new Color(47, 47, 47));

        textField = new JTextArea(2, 12);
        textField.setBackground(new Color(47, 47, 47));
        textField.setFont(FontUtils.getPoppinsFontWithColor(12f, Color.white));
        textField.setCaretColor(Color.white);
        textField.setBorder(new RoundedBorder(Color.white, 2, 20));
        textField.setLineWrap(true);
        textField.setWrapStyleWord(true);

        buttonPane = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPane.setMaximumSize(new Dimension(50,20));
        buttonPane.setBackground(new Color(47,47,47));

        JPanel borderPane = new JPanel(new BorderLayout());
        borderPane.setBackground(new Color(47, 47, 47));
        borderPane.add(textField, BorderLayout.CENTER);
        borderPane.add(buttonPane, BorderLayout.SOUTH);


        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.TRAILING));
        rightPanel.add(borderPane);
        rightPanel.setBackground(new Color(47, 47, 47));

        this.add(leftPanel);
        this.add(rightPanel);
        this.setBackground(new Color(47, 47, 47));

    }

    public JLabel getBinLabel(){
        return this.binLabel;
    }

    public JLabel getAddLabel(){
        return this.addLabel;
    }

    public void addIconLabel(JLabel label){
        this.buttonPane.add(label);
    }

    public void removeIconLabel(JLabel label){
        this.buttonPane.remove(label);
    }

    public String getTextContent(){
        return textField.getText();
    }

    public void setTextContent(String s){
        textField.setText(s);
    }

    public void setLabel(String s){
        this.label.setText(s);
    }

}


