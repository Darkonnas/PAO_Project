package root.gui;

import root.model.Coupon;
import root.service.CouponService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public final class CouponActionsFrame extends JFrame {
    private static CouponActionsFrame instance;
    private JPanel mainPanel;
    private JLabel actionsLabel;
    private JPanel actionsPanel;
    private JButton displayAllCouponsButton;
    private JButton addCouponButton;
    private JButton removeCouponButton;
    private JButton displayCouponWithIdButton;
    private JButton displayCouponsWithDiscountButton;
    private JButton modifyCouponDiscountButton;
    private JButton displayCouponsWithUsedStateButton;
    private JButton modifyCouponUsedStateButton;
    private JPanel returnPanel;
    private JButton returnToActionCategorySelectionButton;
    
    private CouponActionsFrame(final String title) {
        super(title);
        setContentPane(mainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocation((Toolkit.getDefaultToolkit().getScreenSize().width - getWidth()) / 2, (Toolkit.getDefaultToolkit().getScreenSize().height - getHeight()) / 2);
        displayAllCouponsButton.addActionListener(this::onMouseClick);
        addCouponButton.addActionListener(this::onMouseClick);
        removeCouponButton.addActionListener(this::onMouseClick);
        displayCouponWithIdButton.addActionListener(this::onMouseClick);
        displayCouponsWithDiscountButton.addActionListener(this::onMouseClick);
        modifyCouponDiscountButton.addActionListener(this::onMouseClick);
        displayCouponsWithUsedStateButton.addActionListener(this::onMouseClick);
        modifyCouponUsedStateButton.addActionListener(this::onMouseClick);
        returnToActionCategorySelectionButton.addActionListener(this::onMouseClick);
    }
    
    private void onMouseClick(final ActionEvent e) {
        final CouponService service = CouponService.getInstance();
        final JButton button = (JButton) e.getSource();
        if (button.equals(displayAllCouponsButton)) {
            setVisible(false);
            ActionResultFrame.getInstance().load("Coupons:", service.getCoupons().toArray(), this);
        } else if (button.equals(addCouponButton)) {
            String input = JOptionPane.showInputDialog(this, "The ID of the coupon you want to add:", "Enter action parameter", JOptionPane.INFORMATION_MESSAGE);
            if (null == input) {
                return;
            }
            final int id;
            try {
                id = Integer.parseInt(input);
                if (0 >= id) {
                    JOptionPane.showMessageDialog(this, "ID must be a positive integer!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            } catch (final NumberFormatException nfe) {
                JOptionPane.showMessageDialog(this, "ID must be a positive integer!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            input = JOptionPane.showInputDialog(this, "The discount of the coupon you want to add:", "Enter action parameter", JOptionPane.INFORMATION_MESSAGE);
            if (null == input) {
                return;
            }
            final float discount;
            try {
                discount = Float.parseFloat(input);
                if (0 >= discount) {
                    JOptionPane.showMessageDialog(this, "Discount must be a positive floating point number!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            } catch (final NumberFormatException nfe) {
                JOptionPane.showMessageDialog(this, "Discount must be a positive floating point number!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            input = JOptionPane.showInputDialog(this, "The used state of the coupon you want to add:", "Enter action parameter", JOptionPane.INFORMATION_MESSAGE);
            if (null == input) {
                return;
            }
            final boolean usedState = Boolean.parseBoolean(input);
            if (false == usedState && !input.equalsIgnoreCase("false")) {
                JOptionPane.showMessageDialog(this, "The used state must be either true or false!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            final int result = service.addCoupon(new Coupon(id, discount, usedState));
            if (result == 0) {
                JOptionPane.showMessageDialog(this, "There was an error processing your request!", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Succesfully added the new coupon!", "Success", JOptionPane.PLAIN_MESSAGE);
            }
        } else if (button.equals(removeCouponButton)) {
            final String input = JOptionPane.showInputDialog(this, "The ID of the coupon you want to remove:", "Enter action parameter", JOptionPane.INFORMATION_MESSAGE);
            if (null == input) {
                return;
            }
            final int id;
            try {
                id = Integer.parseInt(input);
                if (0 >= id) {
                    JOptionPane.showMessageDialog(this, "ID must be a positive integer!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            } catch (final NumberFormatException nfe) {
                JOptionPane.showMessageDialog(this, "ID must be a positive integer!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            final int result = service.removeCoupon(id);
            if (result == 0) {
                JOptionPane.showMessageDialog(this, "There was an error processing your request!", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Succesfully removed the coupon with ID " + id + '!', "Success", JOptionPane.PLAIN_MESSAGE);
            }
        } else if (button.equals(displayCouponWithIdButton)) {
            final String input = JOptionPane.showInputDialog(this, "Coupon ID to be searched for:", "Enter action parameter", JOptionPane.INFORMATION_MESSAGE);
            if (null == input) {
                return;
            }
            final int id;
            try {
                id = Integer.parseInt(input);
                if (0 >= id) {
                    JOptionPane.showMessageDialog(this, "ID must be a positive integer!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            } catch (final NumberFormatException nfe) {
                JOptionPane.showMessageDialog(this, "ID must be a positive integer!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            final Coupon result = service.getCouponById(id);
            if (null == result) {
                JOptionPane.showMessageDialog(this, "There is no coupon with ID " + id + '!', "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "The coupon with ID " + id + " is " + result, "Action result", JOptionPane.PLAIN_MESSAGE);
            }
        } else if (button.equals(displayCouponsWithDiscountButton)) {
            final String input = JOptionPane.showInputDialog(this, "Coupon discount to be searched for:", "Enter action parameter", JOptionPane.INFORMATION_MESSAGE);
            if (null == input) {
                return;
            }
            final float discount;
            try {
                discount = Float.parseFloat(input);
                if (0 >= discount) {
                    JOptionPane.showMessageDialog(this, "Discount must be a positive floating point number!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            } catch (final NumberFormatException nfe) {
                JOptionPane.showMessageDialog(this, "Discount must be a positive floating point number!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            final Object[] result = service.getCouponsByDiscount(discount).toArray();
            if (0 == result.length) {
                JOptionPane.showMessageDialog(this, "There are no coupons with discount " + discount + "'!", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                setVisible(false);
                ActionResultFrame.getInstance().load("Coupons with discount " + discount + ":", result, this);
            }
        } else if (button.equals(modifyCouponDiscountButton)) {
            String input = JOptionPane.showInputDialog(this, "The ID of the coupon you want to modify:", "Enter action parameter", JOptionPane.INFORMATION_MESSAGE);
            if (null == input) {
                return;
            }
            final int id;
            try {
                id = Integer.parseInt(input);
                if (0 >= id) {
                    JOptionPane.showMessageDialog(this, "ID must be a positive integer!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            } catch (final NumberFormatException nfe) {
                JOptionPane.showMessageDialog(this, "ID must be a positive integer!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            input = JOptionPane.showInputDialog(this, "The new discount:", "Enter action parameter", JOptionPane.INFORMATION_MESSAGE);
            final float newDiscount;
            try {
                newDiscount = Float.parseFloat(input);
                if (0 >= newDiscount) {
                    JOptionPane.showMessageDialog(this, "Discount must be a positive floating point number!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            } catch (final NumberFormatException nfe) {
                JOptionPane.showMessageDialog(this, "Discount must be a positive floating point number!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            final int result = service.setCouponDiscount(id, newDiscount);
            if (0 == result) {
                JOptionPane.showMessageDialog(this, "There was an error processing your request!", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Successfully modified the discount of the coupon with ID " + id + " to " + newDiscount + '!', "Success", JOptionPane.PLAIN_MESSAGE);
            }
        } else if (button.equals(displayCouponsWithUsedStateButton)) {
            final String input = JOptionPane.showInputDialog(this, "Coupon used state to be searched for:", "Enter action parameter", JOptionPane.INFORMATION_MESSAGE);
            if (null == input) {
                return;
            }
            final boolean usedState = Boolean.parseBoolean(input);
            if (false == usedState && !input.equalsIgnoreCase("false")) {
                JOptionPane.showMessageDialog(this, "The used state must be either true or false!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            final Object[] result = service.getCouponsByUsedState(usedState).toArray();
            if (0 == result.length) {
                JOptionPane.showMessageDialog(this, "There are no coupons with used state '" + usedState + "'!", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                setVisible(false);
                ActionResultFrame.getInstance().load("Coupons with used state '" + usedState + "':", result, this);
            }
        } else if (button.equals(modifyCouponUsedStateButton)) {
            String input = JOptionPane.showInputDialog(this, "The ID of the coupon you want to modify:", "Enter action parameter", JOptionPane.INFORMATION_MESSAGE);
            if (null == input) {
                return;
            }
            final int id;
            try {
                id = Integer.parseInt(input);
                if (0 >= id) {
                    JOptionPane.showMessageDialog(this, "ID must be a positive integer!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            } catch (final NumberFormatException nfe) {
                JOptionPane.showMessageDialog(this, "ID must be a positive integer!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            input = JOptionPane.showInputDialog(this, "The new used state:", "Enter action parameter", JOptionPane.INFORMATION_MESSAGE);
            final boolean newUsedState = Boolean.parseBoolean(input);
            if (false == newUsedState && !input.equalsIgnoreCase("false")) {
                JOptionPane.showMessageDialog(this, "The used state must be either true or false!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            final int result = service.setCouponUsedState(id, newUsedState);
            if (0 == result) {
                JOptionPane.showMessageDialog(this, "There was an error processing your request!", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Successfully modified the used state of the coupon with ID " + id + " to " + newUsedState + '!', "Success", JOptionPane.PLAIN_MESSAGE);
            }
        } else if (button.equals(returnToActionCategorySelectionButton)) {
            setVisible(false);
            SelectionFrame.getInstance().load();
        }
    }
    
    public static CouponActionsFrame getInstance() {
        if (null == instance) {
            instance = new CouponActionsFrame("Register Management App v1.0");
        }
        return instance;
    }
    
    public void load() {
        setVisible(true);
    }
}
