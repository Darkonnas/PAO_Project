package root.gui;

import root.service.CashierService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public final class CashierActionsFrame extends JFrame {
    private static CashierActionsFrame instance;
    private JPanel mainPanel;
    private JLabel actionsLabel;
    private JPanel actionsPanel;
    private JButton displayAllCashiersButton;
    private JButton addCashierButton;
    private JButton removeCashierButton;
    private JButton displayCashierWithIDButton;
    private JButton displayCashiersWithFirstNameButton;
    private JButton modifyCashierFirstNameButton;
    private JButton displayCashiersWithLastNameButton;
    private JButton modifyCashierLastNameButton;
    private JButton returnToActionCategorySelectionButton;
    private JPanel returnPanel;
    
    private CashierActionsFrame(final String title) {
        super(title);
        setContentPane(mainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocation((Toolkit.getDefaultToolkit().getScreenSize().width - getWidth()) / 2, (Toolkit.getDefaultToolkit().getScreenSize().height - getHeight()) / 2);
        displayAllCashiersButton.addActionListener(this::onMouseClick);
        addCashierButton.addActionListener(this::onMouseClick);
        removeCashierButton.addActionListener(this::onMouseClick);
        displayCashierWithIDButton.addActionListener(this::onMouseClick);
        displayCashiersWithFirstNameButton.addActionListener(this::onMouseClick);
        modifyCashierFirstNameButton.addActionListener(this::onMouseClick);
        displayCashiersWithLastNameButton.addActionListener(this::onMouseClick);
        modifyCashierLastNameButton.addActionListener(this::onMouseClick);
        returnToActionCategorySelectionButton.addActionListener(this::onMouseClick);
    }
    
    private void onMouseClick(final ActionEvent e) {
        final CashierService service = CashierService.getInstance();
        final JButton button = (JButton) e.getSource();
        setVisible(false);
        if (button.equals(displayAllCashiersButton)) {
            ActionResultFrame.getInstance().load("Cashiers", service.getCashiers().toArray(), this);
        } else if (button.equals(addCashierButton)) {
        
        } else if (button.equals(removeCashierButton)) {
        
        } else if (button.equals(displayCashierWithIDButton)) {
        
        } else if (button.equals(displayCashiersWithFirstNameButton)) {
        
        } else if (button.equals(modifyCashierFirstNameButton)) {
        
        } else if (button.equals(displayCashiersWithLastNameButton)) {
        
        } else if (button.equals(modifyCashierLastNameButton)) {
        
        } else if (button.equals(returnToActionCategorySelectionButton)) {
            SelectionFrame.getInstance().load();
        }
    }
    
    public static CashierActionsFrame getInstance() {
        if (null == instance) {
            instance = new CashierActionsFrame("Register Management App v1.0");
        }
        return instance;
    }
    
    public void load() {
        setVisible(true);
    }
}
