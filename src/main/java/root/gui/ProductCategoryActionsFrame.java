package root.gui;

import root.service.ProductCategoryService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public final class ProductCategoryActionsFrame extends JFrame {
    private static ProductCategoryActionsFrame instance;
    private JPanel mainPanel;
    private JLabel actionsLabel;
    private JPanel actionsPanel;
    private JButton displayAllProductCategoriesButton;
    private JButton addProductCategoryButton;
    private JButton removeProductCategoryButton;
    private JButton displayProductCategoryWithIdButton;
    private JButton displayProductCategoryWithNameButton;
    private JButton modifyProductCategoryNameForIdButton;
    private JButton modifyProductCategoryNameForNameButton;
    private JPanel returnPanel;
    private JButton returnToActionCategorySelectionButton;
    
    private ProductCategoryActionsFrame(final String title) {
        super(title);
        setContentPane(mainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocation((Toolkit.getDefaultToolkit().getScreenSize().width - getWidth()) / 2, (Toolkit.getDefaultToolkit().getScreenSize().height - getHeight()) / 2);
        displayAllProductCategoriesButton.addActionListener(this::onMouseClick);
        addProductCategoryButton.addActionListener(this::onMouseClick);
        removeProductCategoryButton.addActionListener(this::onMouseClick);
        displayProductCategoryWithIdButton.addActionListener(this::onMouseClick);
        displayProductCategoryWithNameButton.addActionListener(this::onMouseClick);
        modifyProductCategoryNameForIdButton.addActionListener(this::onMouseClick);
        modifyProductCategoryNameForNameButton.addActionListener(this::onMouseClick);
        returnToActionCategorySelectionButton.addActionListener(this::onMouseClick);
    }
    
    private void onMouseClick(final ActionEvent e) {
        final ProductCategoryService service = ProductCategoryService.getInstance();
        final JButton button = (JButton) e.getSource();
        setVisible(false);
        if (button.equals(displayAllProductCategoriesButton)) {
            ActionResultFrame.getInstance().load("Product categories", service.getProductCategories().toArray(), this);
        } else if (button.equals(returnToActionCategorySelectionButton)) {
            SelectionFrame.getInstance().load();
        }
    }
    
    public static ProductCategoryActionsFrame getInstance() {
        if (null == instance) {
            instance = new ProductCategoryActionsFrame("Register Management App v1.0");
        }
        return instance;
    }
    
    public void load() {
        setVisible(true);
    }
}
