package root.gui;

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
    private JButton removeCashierButton;
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
        removeCashierButton.addActionListener(this::onMouseClick);
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
        setVisible(false);
        if (button.equals(displayAllCouponsButton)) {
            ActionResultFrame.getInstance().load("Coupons", service.getCoupons().toArray(), this);
        } else if (button.equals(returnToActionCategorySelectionButton)) {
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
