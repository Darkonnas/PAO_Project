package root.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public final class LoginFrame extends JFrame {
    private static LoginFrame instance;
    private JButton loginButton;
    private JPanel loginPanel;
    private JLabel loginLabel;
    
    private LoginFrame(final String title) {
        super(title);
        
        setContentPane(loginPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocation((Toolkit.getDefaultToolkit().getScreenSize().width - getWidth()) / 2, (Toolkit.getDefaultToolkit().getScreenSize().height - getHeight()) / 2);
        loginButton.addActionListener(this::onMouseClick);
    }
    
    private void onMouseClick(final ActionEvent e) {
        setVisible(false);
        SelectionFrame.getInstance().load();
    }
    
    public static LoginFrame getInstance() {
        if (null == instance) {
            instance = new LoginFrame("Register Management App v1.0");
        }
        return instance;
    }
    
    public void load() {
        setVisible(true);
    }
}
