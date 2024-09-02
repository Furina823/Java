package pages.profile;

import utility.ColorChanger;
import utility.ComboBoxFactory;
import utility.FontUtils;
import utility.RoundedBorder;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class panelBuilder extends JPanel {

    private JLabel label;
    private JTextArea textField;
    private String text;
    private JPanel borderPane;
    private JLabel iconLabel;
    private ImageIcon binIcon;
    private ImageIcon addIcon;
    private JComboBox box;

    public panelBuilder(String s){

        text = s;

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
        binIcon = new ImageIcon(newImage);

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
        addIcon = new ImageIcon(newImage2);

        iconLabel = new JLabel(binIcon);
        iconLabel.setMinimumSize(new Dimension(20, 15));
        iconLabel.setBackground(new Color(47, 47, 47));

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

        borderPane = new JPanel(new BorderLayout());
        borderPane.setBackground(new Color(47, 47, 47));
        borderPane.add(textField, BorderLayout.CENTER);

        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.TRAILING));
        rightPanel.add(borderPane);
        rightPanel.setBackground(new Color(47, 47, 47));

        this.add(leftPanel);
        this.add(rightPanel);
        this.setBackground(new Color(47, 47, 47));


    }

    public void setComboBox(String[] array){
           borderPane.remove(textField);
           box = ComboBoxFactory.createComboBox(array);
           borderPane.add(box, BorderLayout.CENTER);
           box.setPreferredSize(new Dimension(140,20));
           revalidate();
           repaint();
    }

    public JComboBox getComboBox(){
        return this.box;
    }

    public ImageIcon getBinIcon(){
        return this.binIcon;
    }

    public ImageIcon getAddIcon(){
        return this.addIcon;
    }

    public JLabel getIconLabel(){
        return this.iconLabel;
    }

    public void setIcontoIconLabel(ImageIcon icon){
        this.iconLabel.setIcon(icon);
    }

    public void addIconLabel(){
        this.borderPane.add(iconLabel,BorderLayout.SOUTH);
    }

    public void removeIconLabel(){
        this.borderPane.remove(iconLabel);
    }

    public String getLabelText(){
        return this.text;
    }

    public void setTextField(int x){
        textField.setColumns(x);
    }

    public void setTextContent(String s){
        textField.setText(s);
    }

    public JTextArea getTextField(){
        return this.textField;
    }

    public void setTextEditable(boolean b){
        textField.setEditable(b);
    }

    public void setLabel(String s){
        this.label.setText(s);
    }

}
