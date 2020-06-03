package root.gui;

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
        setVisible(false);
        if (button.equals(displayAllReceiptsButton)) {
            ActionResultFrame.getInstance().load("Receipts", service.getReceipts().toArray(), this);
        } else if (button.equals(returnToActionCategorySelectionButton)) {
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
