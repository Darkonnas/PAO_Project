package root.gui;

import root.service.ProductService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public final class ProductActionsFrame extends JFrame {
    private static ProductActionsFrame instance;
    private JPanel mainPanel;
    private JLabel actionsLabel;
    private JPanel actionsPanel;
    private JButton displayAllProductsButton;
    private JButton addProductButton;
    private JButton removeProductButton;
    private JButton displayProductWithIdButton;
    private JButton displayProductsWithCategoryIdButton;
    private JButton modifyProductCategoryIdButton;
    private JButton displayProductsWithNameButton;
    private JButton modifyProductNameButton;
    private JButton displayProductsWithPriceButton;
    private JButton modifyProductPriceButton;
    private JButton displayProductsWithDiscountButton;
    private JButton modifyProductDiscountButton;
    private JButton displayProductsByQuantityButton;
    private JButton modifyProductQuantityButton;
    private JPanel returnPanel;
    private JButton returnToActionCategorySelectionButton;
    
    private ProductActionsFrame(final String title) {
        super(title);
        setContentPane(mainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocation((Toolkit.getDefaultToolkit().getScreenSize().width - getWidth()) / 2, (Toolkit.getDefaultToolkit().getScreenSize().height - getHeight()) / 2);
        displayAllProductsButton.addActionListener(this::onMouseClick);
        addProductButton.addActionListener(this::onMouseClick);
        removeProductButton.addActionListener(this::onMouseClick);
        displayProductWithIdButton.addActionListener(this::onMouseClick);
        displayProductsWithCategoryIdButton.addActionListener(this::onMouseClick);
        modifyProductCategoryIdButton.addActionListener(this::onMouseClick);
        displayProductsWithNameButton.addActionListener(this::onMouseClick);
        modifyProductNameButton.addActionListener(this::onMouseClick);
        displayProductsWithPriceButton.addActionListener(this::onMouseClick);
        modifyProductPriceButton.addActionListener(this::onMouseClick);
        displayProductsWithDiscountButton.addActionListener(this::onMouseClick);
        modifyProductDiscountButton.addActionListener(this::onMouseClick);
        displayProductsByQuantityButton.addActionListener(this::onMouseClick);
        modifyProductQuantityButton.addActionListener(this::onMouseClick);
        returnToActionCategorySelectionButton.addActionListener(this::onMouseClick);
    }
    
    private void onMouseClick(final ActionEvent e) {
        final ProductService service = ProductService.getInstance();
        final JButton button = (JButton) e.getSource();
        setVisible(false);
        if (button.equals(displayAllProductsButton)) {
            ActionResultFrame.getInstance().load("Products", service.getProducts().toArray(), this);
        } else if (button.equals(returnToActionCategorySelectionButton)) {
            SelectionFrame.getInstance().load();
        }
    }
    
    public static ProductActionsFrame getInstance() {
        if (null == instance) {
            instance = new ProductActionsFrame("Register Management App v1.0");
        }
        return instance;
    }
    
    public void load() {
        setVisible(true);
    }
}
