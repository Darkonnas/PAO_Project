package root.gui;

import root.model.Receipt;
import root.service.ReceiptService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public final class ReceiptActionsFrame extends JFrame {
    private static ReceiptActionsFrame instance;
    private JPanel mainPanel;
    private JLabel actionsLabel;
    private JPanel actionsPanel;
    private JButton displayAllReceiptsButton;
    private JButton addReceiptButton;
    private JButton removeReceiptButton;
    private JButton displayReceiptWithIdButton;
    private JButton displayReceiptsWithRegisterIdButton;
    private JButton displayReceiptsWithCashierIdButton;
    private JButton displayReceiptsWithCouponIdButton;
    private JPanel returnPanel;
    private JButton returnToActionCategorySelectionButton;
    
    private ReceiptActionsFrame(final String title) {
        super(title);
        setContentPane(mainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocation((Toolkit.getDefaultToolkit().getScreenSize().width - getWidth()) / 2, (Toolkit.getDefaultToolkit().getScreenSize().height - getHeight()) / 2);
        displayAllReceiptsButton.addActionListener(this::onMouseClick);
        addReceiptButton.addActionListener(this::onMouseClick);
        removeReceiptButton.addActionListener(this::onMouseClick);
        displayReceiptWithIdButton.addActionListener(this::onMouseClick);
        displayReceiptsWithRegisterIdButton.addActionListener(this::onMouseClick);
        displayReceiptsWithCashierIdButton.addActionListener(this::onMouseClick);
        displayReceiptsWithCouponIdButton.addActionListener(this::onMouseClick);
        returnToActionCategorySelectionButton.addActionListener(this::onMouseClick);
    }
    
    private void onMouseClick(final ActionEvent e) {
        final ReceiptService service = ReceiptService.getInstance();
        final JButton button = (JButton) e.getSource();
        if (button.equals(displayAllReceiptsButton)) {
            setVisible(false);
            ActionResultFrame.getInstance().load("Receipts", service.getReceipts().toArray(), this);
        } else if (button.equals(addReceiptButton)) {
            String input = JOptionPane.showInputDialog(this, "The ID of the receipt you want to add:", "Enter action parameter", JOptionPane.INFORMATION_MESSAGE);
            if (null == input) {
                return;
            }
            final int id;
            try {
                id = Integer.parseInt(input);
                if (0 >= id) {
                    JOptionPane.showMessageDialog(this, "ID must be a strictly positive integer!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            } catch (final NumberFormatException nfe) {
                JOptionPane.showMessageDialog(this, "ID must be a strictly positive integer!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            input = JOptionPane.showInputDialog(this, "The register ID of the receipt you want to add:", "Enter action parameter", JOptionPane.INFORMATION_MESSAGE);
            if (null == input) {
                return;
            }
            final int registerId;
            try {
                registerId = Integer.parseInt(input);
                if (0 >= id) {
                    JOptionPane.showMessageDialog(this, "Register ID must be a strictly positive integer!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            } catch (final NumberFormatException nfe) {
                JOptionPane.showMessageDialog(this, "Register ID must be a strictly positive integer!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            input = JOptionPane.showInputDialog(this, "The cashier ID (leave empty if not applicable) of the receipt you want to add:", "Enter action parameter", JOptionPane.INFORMATION_MESSAGE);
            if (null == input) {
                return;
            }
            final Integer cashierId;
            if (input.isEmpty()) {
                cashierId = null;
            } else {
                try {
                    cashierId = Integer.parseInt(input);
                    if (0 >= id) {
                        JOptionPane.showMessageDialog(this, "Cashier ID must be a strictly positive integer!", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                } catch (final NumberFormatException nfe) {
                    JOptionPane.showMessageDialog(this, "Cashier ID must be a strictly positive integer!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
            input = JOptionPane.showInputDialog(this, "The coupon ID (leave empty if not applicable) of the receipt you want to add:", "Enter action parameter", JOptionPane.INFORMATION_MESSAGE);
            if (null == input) {
                return;
            }
            final Integer couponId;
            if (input.isEmpty()) {
                couponId = null;
            } else {
                try {
                    couponId = Integer.parseInt(input);
                    if (0 >= id) {
                        JOptionPane.showMessageDialog(this, "Coupon ID must be a strictly positive integer!", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                } catch (final NumberFormatException nfe) {
                    JOptionPane.showMessageDialog(this, "Coupon ID must be a strictly positive integer!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
            final int result = service.addReceipt(new Receipt(id, registerId, cashierId, couponId));
            if (0 == result) {
                JOptionPane.showMessageDialog(this, "There was an error processing your request!", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Successfully added the new receipt!", "Success", JOptionPane.PLAIN_MESSAGE);
            }
        } else if (button.equals(removeReceiptButton)) {
            final String input = JOptionPane.showInputDialog(this, "The ID of the receipt you want to remove:", "Enter action parameter", JOptionPane.INFORMATION_MESSAGE);
            if (null == input) {
                return;
            }
            final int id;
            try {
                id = Integer.parseInt(input);
                if (0 >= id) {
                    JOptionPane.showMessageDialog(this, "ID must be a strictly positive integer!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            } catch (final NumberFormatException nfe) {
                JOptionPane.showMessageDialog(this, "ID must be a strictly positive integer!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            final int result = service.removeReceipt(id);
            if (0 == result) {
                JOptionPane.showMessageDialog(this, "There was an error processing your request!", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Successfully removed the receipt with ID " + id + '!', "Success", JOptionPane.PLAIN_MESSAGE);
            }
        } else if (button.equals(displayReceiptWithIdButton)) {
            final String input = JOptionPane.showInputDialog(this, "Receipt ID to be searched for:", "Enter action parameter", JOptionPane.INFORMATION_MESSAGE);
            if (null == input) {
                return;
            }
            final int id;
            try {
                id = Integer.parseInt(input);
                if (0 >= id) {
                    JOptionPane.showMessageDialog(this, "ID must be a strictly positive integer!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            } catch (final NumberFormatException nfe) {
                JOptionPane.showMessageDialog(this, "ID must be a strictly positive integer!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            final Receipt result = service.getReceiptById(id);
            if (null == result) {
                JOptionPane.showMessageDialog(this, "There is no receipt with ID " + id + '!', "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "The receipt with ID " + id + " is " + result, "Action result", JOptionPane.PLAIN_MESSAGE);
            }
        } else if (button.equals(displayReceiptsWithRegisterIdButton)) {
            final String input = JOptionPane.showInputDialog(this, "Receipt register ID to be searched for:", "Enter action parameter", JOptionPane.INFORMATION_MESSAGE);
            if (null == input) {
                return;
            }
            final int registerId;
            try {
                registerId = Integer.parseInt(input);
                if (0 >= registerId) {
                    JOptionPane.showMessageDialog(this, "Register ID must be a strictly positive integer!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            } catch (final NumberFormatException nfe) {
                JOptionPane.showMessageDialog(this, "Register ID must be a strictly positive integer!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            final Object[] result = service.getReceiptsByRegisterId(registerId).toArray();
            if (0 == result.length) {
                JOptionPane.showMessageDialog(this, "There are no receipts with register ID " + registerId + "!", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                setVisible(false);
                ActionResultFrame.getInstance().load("Receipts with register ID " + registerId + ":", result, this);
            }
        } else if (button.equals(displayReceiptsWithCashierIdButton)) {
            final String input = JOptionPane.showInputDialog(this, "Receipt cashier ID (leave blank for no cashier ID) to be searched for:", "Enter action parameter", JOptionPane.INFORMATION_MESSAGE);
            if (null == input) {
                return;
            }
            final Integer cashierId;
            if (input.isEmpty()) {
                cashierId = null;
            } else {
                try {
                    cashierId = Integer.parseInt(input);
                    if (0 >= cashierId) {
                        JOptionPane.showMessageDialog(this, "Cashier ID must be a strictly positive integer!", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                } catch (final NumberFormatException nfe) {
                    JOptionPane.showMessageDialog(this, "Cashier ID must be a strictly positive integer!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
            final Object[] result = service.getReceiptsByCashierId(cashierId).toArray();
            if (0 == result.length) {
                JOptionPane.showMessageDialog(this, "There are no receipts with cashier ID " + cashierId + "!", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                setVisible(false);
                if (null == cashierId) {
                    ActionResultFrame.getInstance().load("Receipts with no cashier ID :", result, this);
                
                } else {
                    ActionResultFrame.getInstance().load("Receipts with cashier ID " + cashierId + ":", result, this);
                }
            }
        } else if (button.equals(displayReceiptsWithCouponIdButton)) {
            final String input = JOptionPane.showInputDialog(this, "Receipt coupon ID (leave blank for no coupon ID) to be searched for:", "Enter action parameter", JOptionPane.INFORMATION_MESSAGE);
            if (null == input) {
                return;
            }
            final Integer couponID;
            if (input.isEmpty()) {
                couponID = null;
            } else {
                try {
                    couponID = Integer.parseInt(input);
                    if (0 >= couponID) {
                        JOptionPane.showMessageDialog(this, "Coupon ID must be a strictly positive integer!", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                } catch (final NumberFormatException nfe) {
                    JOptionPane.showMessageDialog(this, "Coupon ID must be a strictly positive integer!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
            final Object[] result = service.getReceiptsByCouponId(couponID).toArray();
            if (0 == result.length) {
                JOptionPane.showMessageDialog(this, "There are no receipts with coupon ID " + couponID + "!", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                setVisible(false);
                if (null == couponID) {
                    ActionResultFrame.getInstance().load("Receipts with no coupon ID :", result, this);
                
                } else {
                    ActionResultFrame.getInstance().load("Receipts with coupon ID " + couponID + ":", result, this);
                }
            }
        } else if (button.equals(returnToActionCategorySelectionButton)) {
            setVisible(false);
            SelectionFrame.getInstance().load();
        }
    }
    
    public static ReceiptActionsFrame getInstance() {
        if (null == instance) {
            instance = new ReceiptActionsFrame("Register Management App v1.0");
        }
        return instance;
    }
    
    public void load() {
        setVisible(true);
    }
}
