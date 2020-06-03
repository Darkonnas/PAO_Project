package root.gui;

import root.service.SoldProductService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public final class SoldProductActionsFrame extends JFrame {
    private static SoldProductActionsFrame instance;
    private JPanel mainPanel;
    private JLabel actionsLabel;
    private JPanel actionsPanel;
    private JButton displayAllSoldProductsButton;
    private JButton addSoldProductButton;
    private JButton removeSoldProductButton;
    private JButton displaySoldProductByReceiptAndProductIdsButton;
    private JButton displaySoldProductsByReceiptIdButton;
    private JButton displaySoldProductsProductIdButton;
    private JButton displaySoldProductsByQuantityButton;
    private JPanel returnPanel;
    private JButton returnToActionCategorySelectionButton;
    
    private SoldProductActionsFrame(final String title) {
        super(title);
        setContentPane(mainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocation((Toolkit.getDefaultToolkit().getScreenSize().width - getWidth()) / 2, (Toolkit.getDefaultToolkit().getScreenSize().height - getHeight()) / 2);
        displayAllSoldProductsButton.addActionListener(this::onMouseClick);
        addSoldProductButton.addActionListener(this::onMouseClick);
        removeSoldProductButton.addActionListener(this::onMouseClick);
        displaySoldProductByReceiptAndProductIdsButton.addActionListener(this::onMouseClick);
        displaySoldProductsByReceiptIdButton.addActionListener(this::onMouseClick);
        displaySoldProductsProductIdButton.addActionListener(this::onMouseClick);
        displaySoldProductsByQuantityButton.addActionListener(this::onMouseClick);
        returnToActionCategorySelectionButton.addActionListener(this::onMouseClick);
    }
    
    private void onMouseClick(final ActionEvent e) {
        final SoldProductService service = SoldProductService.getInstance();
        final JButton button = (JButton) e.getSource();
        setVisible(false);
        if (button.equals(displayAllSoldProductsButton)) {
            ActionResultFrame.getInstance().load("Sold products", service.getSoldProducts().toArray(), this);
        } else if (button.equals(addSoldProductButton)) {
        
        } else if (button.equals(removeSoldProductButton)) {
        
        } else if (button.equals(displaySoldProductByReceiptAndProductIdsButton)) {
        
        } else if (button.equals(displaySoldProductsByReceiptIdButton)) {
        
        } else if (button.equals(displaySoldProductsProductIdButton)) {
        
        } else if (button.equals(displaySoldProductsByQuantityButton)) {
        
        } else if (button.equals(returnToActionCategorySelectionButton)) {
            SelectionFrame.getInstance().load();
        }
    }
    
    public static SoldProductActionsFrame getInstance() {
        if (null == instance) {
            instance = new SoldProductActionsFrame("Register Management App v1.0");
        }
        return instance;
    }
    
    public void load() {
        setVisible(true);
    }
}
