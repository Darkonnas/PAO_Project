package root.gui;

import root.model.Cashier;
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
    
    private void onMouseClick(final ActionEvent actionEvent) {
        final CashierService service = CashierService.getInstance();
        final JButton button = (JButton) actionEvent.getSource();
        if (button.equals(displayAllCashiersButton)) {
            setVisible(false);
            ActionResultFrame.getInstance().load("Cashiers:", service.getCashiers().toArray(), this);
        } else if (button.equals(addCashierButton)) {
            final String input = JOptionPane.showInputDialog(this, "The ID of the cashier you want to add:", "Enter action parameter", JOptionPane.INFORMATION_MESSAGE);
            if (null == input) {
                return;
            }
            final int id;
            try {
                id = Integer.parseInt(input);
            } catch (final NumberFormatException nfe) {
                JOptionPane.showMessageDialog(this, "ID must be an integer!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            String firstName = JOptionPane.showInputDialog(this, "The first name of the cashier you want to add:", "Enter action parameter", JOptionPane.INFORMATION_MESSAGE);
            if (null == firstName) {
                return;
            }
            if (firstName.isEmpty()) {
                firstName = null;
            }
            String lastName = JOptionPane.showInputDialog(this, "The last name of the cashier you want to add:", "Enter action parameter", JOptionPane.INFORMATION_MESSAGE);
            if (null == lastName) {
                return;
            }
            if (lastName.isEmpty()) {
                lastName = null;
            }
            final int result = service.addCashier(new Cashier(id, firstName, lastName));
            if (result == 0) {
                JOptionPane.showMessageDialog(this, "There was an error processing your request!", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Succesfully added the new cashier!", "Success", JOptionPane.PLAIN_MESSAGE);
            }
        } else if (button.equals(removeCashierButton)) {
            final String input = JOptionPane.showInputDialog(this, "The ID of the cashier you want to remove:", "Enter action parameter", JOptionPane.INFORMATION_MESSAGE);
            if (null == input) {
                return;
            }
            final int id;
            try {
                id = Integer.parseInt(input);
            } catch (final NumberFormatException nfe) {
                JOptionPane.showMessageDialog(this, "ID must be an integer!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            final int result = service.removeCashier(id);
            if (result == 0) {
                JOptionPane.showMessageDialog(this, "There was an error processing your request!", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Succesfully removed the cashier with ID " + id + '!', "Success", JOptionPane.PLAIN_MESSAGE);
            }
        } else if (button.equals(displayCashierWithIDButton)) {
            final String input = JOptionPane.showInputDialog(this, "Cashier ID to be searched for:", "Enter action parameter", JOptionPane.INFORMATION_MESSAGE);
            if (null == input) {
                return;
            }
            final int id;
            try {
                id = Integer.parseInt(input);
            } catch (final NumberFormatException nfe) {
                JOptionPane.showMessageDialog(this, "ID must be an integer!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            final Cashier result = service.getCashierById(id);
            if (null == result) {
                JOptionPane.showMessageDialog(this, "There is no cashier with ID " + id + '!', "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "The cashier with ID " + id + " is " + result, "Action result", JOptionPane.PLAIN_MESSAGE);
            }
        } else if (button.equals(displayCashiersWithFirstNameButton)) {
            String firstName = JOptionPane.showInputDialog(this, "Cashier first name to be searched for:", "Enter action parameter", JOptionPane.INFORMATION_MESSAGE);
            if (null == firstName) {
                return;
            }
            if (firstName.isEmpty()) {
                firstName = null;
            }
            final Object[] result = service.getCashiersByFirstName(firstName).toArray();
            if (0 == result.length) {
                JOptionPane.showMessageDialog(this, "There are no cashiers with first name '" + firstName + "'!", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                setVisible(false);
                ActionResultFrame.getInstance().load("Cashiers with first name '" + firstName + ":", result, this);
            }
        } else if (button.equals(modifyCashierFirstNameButton)) {
            final String input = JOptionPane.showInputDialog(this, "The ID of the cashier you wish to modify:", "Enter action parameter", JOptionPane.INFORMATION_MESSAGE);
            if (null == input) {
                return;
            }
            final int id;
            try {
                id = Integer.parseInt(input);
            } catch (final NumberFormatException nfe) {
                JOptionPane.showMessageDialog(this, "ID must be an integer!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            String newFirstName = JOptionPane.showInputDialog(this, "The new first name:", "Enter action parameter", JOptionPane.INFORMATION_MESSAGE);
            if (null == newFirstName) {
                return;
            }
            if (newFirstName.isEmpty()) {
                newFirstName = null;
            }
            final int result = service.setCashierFirstName(id, newFirstName);
            if (0 == result) {
                JOptionPane.showMessageDialog(this, "There was an error processing your request!", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Successfully modified the first name of the cashier with ID " + id + " to " + newFirstName + '!', "Success", JOptionPane.PLAIN_MESSAGE);
            }
        } else if (button.equals(displayCashiersWithLastNameButton)) {
            String lastName = JOptionPane.showInputDialog(this, "Cashier last name to be searched for:", "Enter action parameter", JOptionPane.INFORMATION_MESSAGE);
            if (null == lastName) {
                return;
            }
            if (lastName.isEmpty()) {
                lastName = null;
            }
            final Object[] result = service.getCashiersByLastName(lastName).toArray();
            if (0 == result.length) {
                JOptionPane.showMessageDialog(this, "There are no cashiers with last name '" + lastName + "'!", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                setVisible(false);
                ActionResultFrame.getInstance().load("Cashiers with last name '" + lastName + ":", result, this);
            }
        } else if (button.equals(modifyCashierLastNameButton)) {
            final String input = JOptionPane.showInputDialog(this, "The ID of the cashier you wish to modify:", "Enter action parameter", JOptionPane.INFORMATION_MESSAGE);
            if (null == input) {
                return;
            }
            final int id;
            try {
                id = Integer.parseInt(input);
            } catch (final NumberFormatException nfe) {
                JOptionPane.showMessageDialog(this, "ID must be an integer!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            String newLastName = JOptionPane.showInputDialog(this, "The new last name:", "Enter action parameter", JOptionPane.INFORMATION_MESSAGE);
            if (null == newLastName) {
                return;
            }
            if (newLastName.isEmpty()) {
                newLastName = null;
            }
            final int result = service.setCashierLastName(id, newLastName);
            if (0 == result) {
                JOptionPane.showMessageDialog(this, "There was an error processing your request!", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Successfully modified the last name of the cashier with ID " + id + " to " + newLastName + '!', "Success", JOptionPane.PLAIN_MESSAGE);
            }
        } else if (button.equals(returnToActionCategorySelectionButton)) {
            setVisible(false);
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
