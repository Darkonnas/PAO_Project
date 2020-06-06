package root.gui;

import root.model.Product;
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
        if (button.equals(displayAllProductsButton)) {
            setVisible(false);
            ActionResultFrame.getInstance().load("Products", service.getProducts().toArray(), this);
        } else if (button.equals(addProductButton)) {
            String input = JOptionPane.showInputDialog(this, "The ID of the product you want to add:", "Enter action parameter", JOptionPane.INFORMATION_MESSAGE);
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
            input = JOptionPane.showInputDialog(this, "The category ID of the product you want to add:", "Enter action parameter", JOptionPane.INFORMATION_MESSAGE);
            if (null == input) {
                return;
            }
            final int categoryId;
            try {
                categoryId = Integer.parseInt(input);
                if (0 >= categoryId) {
                    JOptionPane.showMessageDialog(this, "Category ID must be a positive integer!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            } catch (final NumberFormatException nfe) {
                JOptionPane.showMessageDialog(this, "Category ID must be a positive integer!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            String name = JOptionPane.showInputDialog(this, "The of the product you want to add:", "Enter action parameter", JOptionPane.INFORMATION_MESSAGE);
            if (null == name) {
                return;
            }
            if (name.isEmpty()) {
                name = null;
            }
            input = JOptionPane.showInputDialog(this, "The price of the product you want to add:", "Enter action parameter", JOptionPane.INFORMATION_MESSAGE);
            if (null == input) {
                return;
            }
            final float price;
            try {
                price = Float.parseFloat(input);
                if (0 >= price) {
                    JOptionPane.showMessageDialog(this, "Price must be a positive floating point number!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            } catch (final NumberFormatException nfe) {
                JOptionPane.showMessageDialog(this, "Price must be a positive floating point number!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            input = JOptionPane.showInputDialog(this, "The discount of the product you want to add (leave empty if no discount):", "Enter action parameter", JOptionPane.INFORMATION_MESSAGE);
            if (null == input) {
                return;
            }
            final Float discount;
            if (input.isEmpty()) {
                discount = null;
            } else {
                try {
                    discount = Float.parseFloat(input);
                    if (0 >= price) {
                        JOptionPane.showMessageDialog(this, "Discount must be a positive floating point number!", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                } catch (final NumberFormatException nfe) {
                    JOptionPane.showMessageDialog(this, "Discount must be a positive floating point number!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
            input = JOptionPane.showInputDialog(this, "The quantity of the product you want to add:", "Enter action parameter", JOptionPane.INFORMATION_MESSAGE);
            if (null == input) {
                return;
            }
            final int quantity;
            try {
                quantity = Integer.parseInt(input);
                if (0 > quantity) {
                    JOptionPane.showMessageDialog(this, "Quantity must be a positive integer!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            } catch (final NumberFormatException nfe) {
                JOptionPane.showMessageDialog(this, "Quantity must be a positive integer!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            final int result = service.addProduct(new Product(id, categoryId, name, price, discount, quantity));
            if (0 == result) {
                JOptionPane.showMessageDialog(this, "There was an error processing your request!", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Successfully added the new product!", "Success", JOptionPane.PLAIN_MESSAGE);
            }
        } else if (button.equals(removeProductButton)) {
            final String input = JOptionPane.showInputDialog(this, "The ID of the product you want to remove:", "Enter action parameter", JOptionPane.INFORMATION_MESSAGE);
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
            final int result = service.removeProduct(id);
            if (0 == result) {
                JOptionPane.showMessageDialog(this, "There was an error processing your request!", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Successfully removed the product with ID " + id + '!', "Success", JOptionPane.PLAIN_MESSAGE);
            }
        } else if (button.equals(displayProductWithIdButton)) {
            final String input = JOptionPane.showInputDialog(this, "Product ID to be searched for:", "Enter action parameter", JOptionPane.INFORMATION_MESSAGE);
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
            final Product result = service.getProductById(id);
            if (null == result) {
                JOptionPane.showMessageDialog(this, "There is no product with ID " + id + '!', "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "The product with ID " + id + " is " + result, "Action result", JOptionPane.PLAIN_MESSAGE);
            }
        } else if (button.equals(displayProductsWithCategoryIdButton)) {
            final String input = JOptionPane.showInputDialog(this, "Product category ID to be searched for:", "Enter action parameter", JOptionPane.INFORMATION_MESSAGE);
            if (null == input) {
                return;
            }
            final int categoryId;
            try {
                categoryId = Integer.parseInt(input);
                if (0 >= categoryId) {
                    JOptionPane.showMessageDialog(this, "Category ID must be a strictly positive integer!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            } catch (final NumberFormatException nfe) {
                JOptionPane.showMessageDialog(this, "Category ID must be a strictly positive integer!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            final Object[] result = service.getProductsByCategoryId(categoryId).toArray();
            if (0 == result.length) {
                JOptionPane.showMessageDialog(this, "There are no products with category ID " + categoryId + "!", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                setVisible(false);
                ActionResultFrame.getInstance().load("Products with category ID " + categoryId + ":", result, this);
            }
        } else if (button.equals(modifyProductCategoryIdButton)) {
            String input = JOptionPane.showInputDialog(this, "The ID of the product you want to modify:", "Enter action parameter", JOptionPane.INFORMATION_MESSAGE);
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
            input = JOptionPane.showInputDialog(this, "The new category ID:", "Enter action parameter", JOptionPane.INFORMATION_MESSAGE);
            final int categoryId;
            try {
                categoryId = Integer.parseInt(input);
                if (0 >= categoryId) {
                    JOptionPane.showMessageDialog(this, "Category ID must be a strictly positive integer!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            } catch (final NumberFormatException nfe) {
                JOptionPane.showMessageDialog(this, "Category ID must be a strictly positive integer!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            final int result = service.setProductCategoryId(id, categoryId);
            if (0 == result) {
                JOptionPane.showMessageDialog(this, "There was an error processing your request!", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Successfully modified the category ID of the product with ID " + id + " to " + categoryId + '!', "Success", JOptionPane.PLAIN_MESSAGE);
            }
        } else if (button.equals(displayProductsWithNameButton)) {
            String name = JOptionPane.showInputDialog(this, "Product name to be searched for:", "Enter action parameter", JOptionPane.INFORMATION_MESSAGE);
            if (null == name) {
                return;
            }
            if (name.isEmpty()) {
                name = null;
            }
            final Object[] result = service.getProductsByName(name).toArray();
            if (0 == result.length) {
                JOptionPane.showMessageDialog(this, "There are no products with name '" + name + "'!", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                setVisible(false);
                ActionResultFrame.getInstance().load("Products with name '" + name + ":", result, this);
            }
        } else if (button.equals(modifyProductNameButton)) {
            final String input = JOptionPane.showInputDialog(this, "The ID of the product you want to modify:", "Enter action parameter", JOptionPane.INFORMATION_MESSAGE);
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
            String newName = JOptionPane.showInputDialog(this, "The new name:", "Enter action parameter", JOptionPane.INFORMATION_MESSAGE);
            if (null == newName) {
                return;
            }
            if (newName.isEmpty()) {
                newName = null;
            }
            final int result = service.setProductName(id, newName);
            if (0 == result) {
                JOptionPane.showMessageDialog(this, "There was an error processing your request!", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Successfully modified the name of the product with ID " + id + " to " + newName + '!', "Success", JOptionPane.PLAIN_MESSAGE);
            }
        } else if (button.equals(displayProductsWithPriceButton)) {
            final String input = JOptionPane.showInputDialog(this, "Product price to be searched for:", "Enter action parameter", JOptionPane.INFORMATION_MESSAGE);
            if (null == input) {
                return;
            }
            final float price;
            try {
                price = Float.parseFloat(input);
                if (0 >= price) {
                    JOptionPane.showMessageDialog(this, "Price must be a strictly positive floating point number!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            } catch (final NumberFormatException nfe) {
                JOptionPane.showMessageDialog(this, "Price must be a strictly positive floating point number!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            final Object[] result = service.getProductsByPrice(price).toArray();
            if (0 == result.length) {
                JOptionPane.showMessageDialog(this, "There are no products with price " + price + "!", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                setVisible(false);
                ActionResultFrame.getInstance().load("Products with price " + price + ":", result, this);
            }
        } else if (button.equals(modifyProductPriceButton)) {
            String input = JOptionPane.showInputDialog(this, "The ID of the product you want to modify:", "Enter action parameter", JOptionPane.INFORMATION_MESSAGE);
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
            input = JOptionPane.showInputDialog(this, "The new price:", "Enter action parameter", JOptionPane.INFORMATION_MESSAGE);
            final float newPrice;
            try {
                newPrice = Float.parseFloat(input);
                if (0 >= newPrice) {
                    JOptionPane.showMessageDialog(this, "Price must be a strictly positive floating point number!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            } catch (final NumberFormatException nfe) {
                JOptionPane.showMessageDialog(this, "Price must be a strictly positive floating point number!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            final int result = service.setProductPrice(id, newPrice);
            if (0 == result) {
                JOptionPane.showMessageDialog(this, "There was an error processing your request!", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Successfully modified the price of the product with ID " + id + " to " + newPrice + '!', "Success", JOptionPane.PLAIN_MESSAGE);
            }
        } else if (button.equals(displayProductsWithDiscountButton)) {
            final String input = JOptionPane.showInputDialog(this, "Product discount to be searched for (leave empty for no discount):", "Enter action parameter", JOptionPane.INFORMATION_MESSAGE);
            if (null == input) {
                return;
            }
            final Float discount;
            if (input.isEmpty()) {
                discount = null;
            } else {
                try {
                    discount = Float.parseFloat(input);
                    if (0 >= discount) {
                        JOptionPane.showMessageDialog(this, "Discount must be a strictly positive floating point number!", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                } catch (final NumberFormatException nfe) {
                    JOptionPane.showMessageDialog(this, "Discount must be a strictly positive floating point number!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
            final Object[] result = service.getProductsByDiscount(discount).toArray();
            if (0 == result.length) {
                JOptionPane.showMessageDialog(this, "There are no products with discount " + discount + "!", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                setVisible(false);
                ActionResultFrame.getInstance().load("Products with discount " + discount + ":", result, this);
            }
        } else if (button.equals(modifyProductDiscountButton)) {
            String input = JOptionPane.showInputDialog(this, "The ID of the product you want to modify:", "Enter action parameter", JOptionPane.INFORMATION_MESSAGE);
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
            input = JOptionPane.showInputDialog(this, "The new discount  (leave empty for no discount):", "Enter action parameter", JOptionPane.INFORMATION_MESSAGE);
            final Float newDiscount;
            if (input.isEmpty()) {
                newDiscount = null;
            } else {
                try {
                    newDiscount = Float.parseFloat(input);
                    if (0 >= newDiscount) {
                        JOptionPane.showMessageDialog(this, "Discount must be a strictly positive floating point number!", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                } catch (final NumberFormatException nfe) {
                    JOptionPane.showMessageDialog(this, "Discount must be a strictly positive floating point number!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
            final int result = service.setProductDiscount(id, newDiscount);
            if (0 == result) {
                JOptionPane.showMessageDialog(this, "There was an error processing your request!", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Successfully modified the discount of the product with ID " + id + " to " + newDiscount + '!', "Success", JOptionPane.PLAIN_MESSAGE);
            }
        } else if (button.equals(displayProductsByQuantityButton)) {
            final String input = JOptionPane.showInputDialog(this, "Product quantity to be searched for:", "Enter action parameter", JOptionPane.INFORMATION_MESSAGE);
            if (null == input) {
                return;
            }
            final int quantity;
            try {
                quantity = Integer.parseInt(input);
                if (0 > quantity) {
                    JOptionPane.showMessageDialog(this, "Quantity must be a positive integer!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            } catch (final NumberFormatException nfe) {
                JOptionPane.showMessageDialog(this, "Quantity must be a positive integer!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            final Object[] result = service.getProductsByQuantity(quantity).toArray();
            if (0 == result.length) {
                JOptionPane.showMessageDialog(this, "There are no products with quantity " + quantity + "!", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                setVisible(false);
                ActionResultFrame.getInstance().load("Products with quantity " + quantity + ":", result, this);
            }
        } else if (button.equals(modifyProductQuantityButton)) {
            String input = JOptionPane.showInputDialog(this, "The ID of the product you want to modify:", "Enter action parameter", JOptionPane.INFORMATION_MESSAGE);
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
            input = JOptionPane.showInputDialog(this, "The new quantity:", "Enter action parameter", JOptionPane.INFORMATION_MESSAGE);
            final int quantity;
            try {
                quantity = Integer.parseInt(input);
                if (0 >= quantity) {
                    JOptionPane.showMessageDialog(this, "Quantity must be a strictly positive integer!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            } catch (final NumberFormatException nfe) {
                JOptionPane.showMessageDialog(this, "Quantity must be a strictly positive integer!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            final int result = service.setProductQuantity(id, quantity);
            if (0 == result) {
                JOptionPane.showMessageDialog(this, "There was an error processing your request!", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Successfully modified the quantity of the product with ID " + id + " to " + quantity + '!', "Success", JOptionPane.PLAIN_MESSAGE);
            }
        } else if (button.equals(returnToActionCategorySelectionButton)) {
            setVisible(false);
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
