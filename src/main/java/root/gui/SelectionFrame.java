package root.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public final class SelectionFrame extends JFrame {
    private static SelectionFrame instance;
    private JPanel selectionPanel;
    private JLabel selectionLabel;
    private JButton cashierActionsButton;
    private JButton registerActionsButton;
    private JButton couponActionsButton;
    private JButton receiptActionsButton;
    private JButton productActionsButton;
    private JButton productCategoryActionsButton;
    private JButton soldProductActionsButton;
    private JPanel actionsPanel;
    
    private SelectionFrame(final String title) {
        super(title);
        
        setContentPane(selectionPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocation((Toolkit.getDefaultToolkit().getScreenSize().width - getWidth()) / 2, (Toolkit.getDefaultToolkit().getScreenSize().height - getHeight()) / 2);
        
        cashierActionsButton.addActionListener(this::onMouseClick);
        registerActionsButton.addActionListener(this::onMouseClick);
        couponActionsButton.addActionListener(this::onMouseClick);
        receiptActionsButton.addActionListener(this::onMouseClick);
        productActionsButton.addActionListener(this::onMouseClick);
        productCategoryActionsButton.addActionListener(this::onMouseClick);
        soldProductActionsButton.addActionListener(this::onMouseClick);
    }
    
    private void onMouseClick(final ActionEvent e) {
        final JButton button = (JButton) e.getSource();
        setVisible(false);
        if (button.equals(cashierActionsButton)) {
            CashierActionsFrame.getInstance().load();
        } else if (button.equals(registerActionsButton)) {
            RegisterActionsFrame.getInstance().load();
        } else if (button.equals(couponActionsButton)) {
            CouponActionsFrame.getInstance().load();
        } else if (button.equals(receiptActionsButton)) {
            ReceiptActionsFrame.getInstance().load();
        } else if (button.equals(productActionsButton)) {
            ProductActionsFrame.getInstance().load();
        } else if (button.equals(productCategoryActionsButton)) {
            ProductCategoryActionsFrame.getInstance().load();
        } else if (button.equals(soldProductActionsButton)) {
            SoldProductActionsFrame.getInstance().load();
        }
    }
    
    public static SelectionFrame getInstance() {
        if (null == instance) {
            instance = new SelectionFrame("Register Management App v1.0");
        }
        return instance;
    }
    
    public void load() {
        setVisible(true);
    }
}
