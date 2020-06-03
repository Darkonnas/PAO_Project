package root.gui;

import root.service.RegisterService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public final class RegisterActionsFrame extends JFrame {
    private static RegisterActionsFrame instance;
    private JPanel mainPanel;
    private JLabel actionsLabel;
    private JPanel actionsPanel;
    private JButton displayAllRegistersButton;
    private JButton addRegisterButton;
    private JButton removeRegisterButton;
    private JButton displayRegisterWithIdButton;
    private JButton displayRegistersWithActiveStateButton;
    private JButton modifyRegisterActiveStateButton;
    private JButton displayRegistersWithInUseStateButton;
    private JButton modifyRegisterInUseStateButton;
    private JButton displayAssistedRegistersButton;
    private JButton assignCashierToAssistedButton;
    private JButton dropCashierFromAssistedButton;
    private JButton displaySelfRegistersButton;
    private JPanel returnPanel;
    private JButton returnToActionCategorySelectionButton;
    
    private RegisterActionsFrame(final String title) {
        super(title);
        setContentPane(mainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocation((Toolkit.getDefaultToolkit().getScreenSize().width - getWidth()) / 2, (Toolkit.getDefaultToolkit().getScreenSize().height - getHeight()) / 2);
        displayAllRegistersButton.addActionListener(this::onMouseClick);
        addRegisterButton.addActionListener(this::onMouseClick);
        removeRegisterButton.addActionListener(this::onMouseClick);
        displayRegisterWithIdButton.addActionListener(this::onMouseClick);
        displayRegistersWithActiveStateButton.addActionListener(this::onMouseClick);
        modifyRegisterActiveStateButton.addActionListener(this::onMouseClick);
        displayRegistersWithInUseStateButton.addActionListener(this::onMouseClick);
        modifyRegisterInUseStateButton.addActionListener(this::onMouseClick);
        displayAssistedRegistersButton.addActionListener(this::onMouseClick);
        assignCashierToAssistedButton.addActionListener(this::onMouseClick);
        dropCashierFromAssistedButton.addActionListener(this::onMouseClick);
        displaySelfRegistersButton.addActionListener(this::onMouseClick);
        returnToActionCategorySelectionButton.addActionListener(this::onMouseClick);
    }
    
    private void onMouseClick(final ActionEvent e) {
        final RegisterService service = RegisterService.getInstance();
        final JButton button = (JButton) e.getSource();
        setVisible(false);
        if (button.equals(displayAllRegistersButton)) {
            ActionResultFrame.getInstance().load("Registers", service.getRegisters().toArray(), this);
        } else if (button.equals(returnToActionCategorySelectionButton)) {
            SelectionFrame.getInstance().load();
        }
    }
    
    public static RegisterActionsFrame getInstance() {
        if (null == instance) {
            instance = new RegisterActionsFrame("Register Management App v1.0");
        }
        return instance;
    }
    
    public void load() {
        setVisible(true);
    }
}
