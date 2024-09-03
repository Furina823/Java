package pages;

import utility.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPanel extends JPanel implements ActionListener {

    JPanel RightLoginPanel;
    JPanel LeftLoginPanel;
    RoundedButton LoginButton;
    private JTextField usernameTextField;
    private JPasswordField passwordTextField;
    private MyFrame parentFrame;

    public LoginPanel(MyFrame parentFrame) {

        this.parentFrame = parentFrame;

        // Right panel setup
        RightLoginPanel = new JPanel();
        RightLoginPanel.setLayout(new BorderLayout());
        RightLoginPanel.setBackground(Color.white);
        RightLoginPanel.setOpaque(true);

        LoginPicture picture = new LoginPicture("src/picture/login.jpg");
        RightLoginPanel.add(picture);

        LeftLoginPanel = new JPanel();
        LeftLoginPanel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.VERTICAL; // Make components fill horizontally

        JLabel leftLabelTitle = new JLabel("Login");
        leftLabelTitle.setFont(new Font("Serif", Font.PLAIN, 50));
        leftLabelTitle.setPreferredSize(new Dimension(150, 70));
        leftLabelTitle.setBackground(Color.BLACK);
        leftLabelTitle.setForeground(Color.WHITE);
        leftLabelTitle.setOpaque(true);

        usernameTextField = new JTextField(){
            public void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(Color.white);
                g2d.drawRoundRect(1,1,getWidth()-1, getHeight()-1, 30,30);
                super.paintComponent(g);
            }
        };
        usernameTextField.setBorder(BorderFactory.createEmptyBorder());
        usernameTextField.setOpaque(false);
        usernameTextField.setForeground(Color.white);
        usernameTextField.setBackground(Color.black);
        usernameTextField.setFont(new Font("TimesRoman", Font.PLAIN, 15));
        usernameTextField.setPreferredSize(new Dimension(100,40));
        usernameTextField.setColumns(20);
        usernameTextField.setCaretColor(Color.white);

        passwordTextField = new JPasswordField();
        passwordTextField.setColumns(20);
        passwordTextField.setFont(new Font("TimesRoman", Font.PLAIN, 15));
        passwordTextField.setBorder(new RoundedBorder( Color.white,1,10));
        passwordTextField.setPreferredSize(new Dimension(100,40));
        passwordTextField.setCaretColor(Color.white);
        passwordTextField.setForeground(Color.white);
        passwordTextField.setBackground(Color.black);
        passwordTextField.setEchoChar('*');

        LoginButton = new RoundedButton("Login >",new Color(190,190,190));
        LoginButton.addActionListener(this);
        LoginButton.setBackground(Color.gray);
        LoginButton.setForeground(Color.white);
        LoginButton.setFocusable(false);
        LoginButton.setFont(new Font("TimesRoman", Font.PLAIN, 15));
        LoginButton.setPreferredSize(new Dimension(85, 40));

        // Add components to the LeftLoginPanel with GridBagLayout
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = GridBagConstraints.REMAINDER; // Span across the entire width
        gbc.anchor = GridBagConstraints.WEST; // Align to the left
        LeftLoginPanel.add(leftLabelTitle, gbc);

        gbc.gridy++;
        gbc.gridwidth = 1; // Reset to default
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(0,0,0,0);
        LeftLoginPanel.add(usernameTextField, gbc);

        gbc.gridy++;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10,5,10,5);
        LeftLoginPanel.add(passwordTextField, gbc);

        gbc.gridy++;
        gbc.insets = new Insets(0,0,0,0);
        gbc.anchor = GridBagConstraints.EAST; // Align to the right for the button
        LeftLoginPanel.add(LoginButton, gbc);

        LeftLoginPanel.setBackground(Color.BLACK);
        LeftLoginPanel.setOpaque(true);

        LeftLoginPanel.setPreferredSize(new Dimension(550, 600));
        RightLoginPanel.setPreferredSize(new Dimension(550, 600));


        this.setLayout(new GridBagLayout());
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.5;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
        this.add(LeftLoginPanel, gbc);

        gbc.gridx = 1;
        this.add(RightLoginPanel, gbc);

        this.setPreferredSize(new Dimension(1100, 600));
    }


    private void validateLogin() {
        String username = usernameTextField.getText();
        String password = new String(passwordTextField.getPassword());

        Validation validation = new Validation(parentFrame, username, password);
        if (validation.isSuccessful()) {

        }else {
            DisplayJoption.showMessage("Invalid username and password");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == LoginButton){
            validateLogin();
        }
    }
}
