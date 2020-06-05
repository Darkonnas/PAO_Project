package root.gui;

import root.model.AssistedRegister;
import root.model.Register;
import root.model.SelfRegister;
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
        if (button.equals(displayAllRegistersButton)) {
            setVisible(false);
            ActionResultFrame.getInstance().load("Registers", service.getRegisters().toArray(), this);
        } else if (button.equals(addRegisterButton)) {
            String input = JOptionPane.showInputDialog(this, "The cashier ID of the register you want to add:", "Enter action parameter", JOptionPane.INFORMATION_MESSAGE);
            if (null == input) {
                return;
            }
            final int cashierId;
            if (input.isEmpty()) {
                cashierId = -1;
            } else {
                try {
                    cashierId = Integer.parseInt(input);
                } catch (final NumberFormatException nfe) {
                    JOptionPane.showMessageDialog(this, "Cashier ID must be an integer!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
            input = JOptionPane.showInputDialog(this, "The ID of the register you want to add:", "Enter action parameter", JOptionPane.INFORMATION_MESSAGE);
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
            input = JOptionPane.showInputDialog(this, "The active state of the register you want to add:", "Enter action parameter", JOptionPane.INFORMATION_MESSAGE);
            if (null == input) {
                return;
            }
            final boolean activeState = Boolean.parseBoolean(input);
            if (false == activeState && !input.equalsIgnoreCase("false")) {
                JOptionPane.showMessageDialog(this, "The active state must be either true or false!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            input = JOptionPane.showInputDialog(this, "The in-use state of the register you want to add:", "Enter action parameter", JOptionPane.INFORMATION_MESSAGE);
            if (null == input) {
                return;
            }
            final boolean inUseState = Boolean.parseBoolean(input);
            if (false == inUseState && !input.equalsIgnoreCase("false")) {
                JOptionPane.showMessageDialog(this, "The in-use state must be either true or false!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            final int result;
            if (cashierId == -1) {
                result = service.addRegister(new SelfRegister(id, activeState, inUseState));
            } else {
                result = service.addRegister(new AssistedRegister(cashierId, id, activeState, inUseState));
            }
            if (result == 0) {
                JOptionPane.showMessageDialog(this, "There was an error processing your request!", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Succesfully added the new register!", "Success", JOptionPane.PLAIN_MESSAGE);
            }
        } else if (button.equals(removeRegisterButton)) {
            final String input = JOptionPane.showInputDialog(this, "The ID of the register you want to remove:", "Enter action parameter", JOptionPane.INFORMATION_MESSAGE);
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
            final int result = service.removeRegister(id);
            if (result == 0) {
                JOptionPane.showMessageDialog(this, "There was an error processing your request!", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Succesfully removed the register with ID " + id + '!', "Success", JOptionPane.PLAIN_MESSAGE);
            }
        } else if (button.equals(displayRegisterWithIdButton)) {
            final String input = JOptionPane.showInputDialog(this, "Register ID to be searched for:", "Enter action parameter", JOptionPane.INFORMATION_MESSAGE);
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
            final Register result = service.getRegisterById(id);
            if (null == result) {
                JOptionPane.showMessageDialog(this, "There is no register with ID " + id + '!', "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "The register with ID " + id + " is " + result, "Action result", JOptionPane.PLAIN_MESSAGE);
            }
        } else if (button.equals(displayRegistersWithActiveStateButton)) {
            final String input = JOptionPane.showInputDialog(this, "Register active state to be searched for:", "Enter action parameter", JOptionPane.INFORMATION_MESSAGE);
            if (null == input) {
                return;
            }
            final boolean activeState = Boolean.parseBoolean(input);
            if (false == activeState && !input.equalsIgnoreCase("false")) {
                JOptionPane.showMessageDialog(this, "The active state must be either true or false!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            final Object[] result = service.getRegistersByActiveState(activeState).toArray();
            if (0 == result.length) {
                JOptionPane.showMessageDialog(this, "There are no registers with active state '" + activeState + "'!", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                setVisible(false);
                ActionResultFrame.getInstance().load("Registers with active state '" + activeState + "':", result, this);
            }
        } else if (button.equals(modifyRegisterActiveStateButton)) {
            String input = JOptionPane.showInputDialog(this, "The ID of the register you wish to modify:", "Enter action parameter", JOptionPane.INFORMATION_MESSAGE);
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
            input = JOptionPane.showInputDialog(this, "The new active state:", "Enter action parameter", JOptionPane.INFORMATION_MESSAGE);
            final boolean activeState = Boolean.parseBoolean(input);
            if (false == activeState && !input.equalsIgnoreCase("false")) {
                JOptionPane.showMessageDialog(this, "The active state must be either true or false!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            final int result = service.setRegisterActiveState(id, activeState);
            if (0 == result) {
                JOptionPane.showMessageDialog(this, "There was an error processing your request!", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Successfully modified the active state of the coupon with ID " + id + " to " + activeState + '!', "Success", JOptionPane.PLAIN_MESSAGE);
            }
        } else if (button.equals(displayRegistersWithInUseStateButton)) {
            final String input = JOptionPane.showInputDialog(this, "Register in-use state to be searched for:", "Enter action parameter", JOptionPane.INFORMATION_MESSAGE);
            if (null == input) {
                return;
            }
            final boolean inUseState = Boolean.parseBoolean(input);
            if (false == inUseState && !input.equalsIgnoreCase("false")) {
                JOptionPane.showMessageDialog(this, "The in-use state must be either true or false!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            final Object[] result = service.getRegistersByInUseState(inUseState).toArray();
            if (0 == result.length) {
                JOptionPane.showMessageDialog(this, "There are no registers with in-use state '" + inUseState + "'!", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                setVisible(false);
                ActionResultFrame.getInstance().load("Registers with in-use state '" + inUseState + "':", result, this);
            }
        } else if (button.equals(modifyRegisterInUseStateButton)) {
            String input = JOptionPane.showInputDialog(this, "The ID of the coupon you wish to modify:", "Enter action parameter", JOptionPane.INFORMATION_MESSAGE);
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
            input = JOptionPane.showInputDialog(this, "The new in-use state:", "Enter action parameter", JOptionPane.INFORMATION_MESSAGE);
            final boolean inUseState = Boolean.parseBoolean(input);
            if (false == inUseState && !input.equalsIgnoreCase("false")) {
                JOptionPane.showMessageDialog(this, "The in-use state must be either true or false!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            final int result = service.setRegisterInUseState(id, inUseState);
            if (0 == result) {
                JOptionPane.showMessageDialog(this, "There was an error processing your request!", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Successfully modified the in-use state of the coupon with ID " + id + " to " + inUseState + '!', "Success", JOptionPane.PLAIN_MESSAGE);
            }
        } else if (button.equals(displayAssistedRegistersButton)) {
            setVisible(false);
            ActionResultFrame.getInstance().load("Assisted registers", service.getAssistedRegisters().toArray(), this);
        
        } else if (button.equals(assignCashierToAssistedButton)) {
            String input = JOptionPane.showInputDialog(this, "The ID of the assisted register you wish to modify:", "Enter action parameter", JOptionPane.INFORMATION_MESSAGE);
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
            input = JOptionPane.showInputDialog(this, "The ID of the cashier you want to assign:", "Enter action parameter", JOptionPane.INFORMATION_MESSAGE);
            if (null == input) {
                return;
            }
            final int cashierId;
            try {
                cashierId = Integer.parseInt(input);
            } catch (final NumberFormatException nfe) {
                JOptionPane.showMessageDialog(this, "Cashier ID must be an integer!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            final int result = service.assignCashier(id, cashierId);
            if (0 == result) {
                JOptionPane.showMessageDialog(this, "There was an error processing your request!", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Successfully assigned cashier with ID " + cashierId + " to the assisted register with ID " + id + '!', "Success", JOptionPane.PLAIN_MESSAGE);
            }
        } else if (button.equals(dropCashierFromAssistedButton)) {
            final String input = JOptionPane.showInputDialog(this, "The ID of the assisted register you wish to modify:", "Enter action parameter", JOptionPane.INFORMATION_MESSAGE);
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
            final int result = service.dropCashier(id);
            if (0 == result) {
                JOptionPane.showMessageDialog(this, "There was an error processing your request!", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Successfully dropped the cashier of the assisted register with ID " + id + '!', "Success", JOptionPane.PLAIN_MESSAGE);
            }
        } else if (button.equals(displaySelfRegistersButton)) {
            setVisible(false);
            ActionResultFrame.getInstance().load("Self registers", service.getSelfRegisters().toArray(), this);
        
        } else if (button.equals(returnToActionCategorySelectionButton)) {
            setVisible(false);
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
