package root.service;

import root.model.Product;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Set;

public class ProductIOService {
    private static final String DATA_PATH = "src/main/java/root/data/";
    private static final String FILE_HEADER = "id,categoryId,name,price,discount,quantity";
    private static ProductIOService instance;
    
    private ProductIOService() {
    }
    
    public static ProductIOService getInstance() {
        if (instance == null) {
            instance = new ProductIOService();
        }
        return instance;
    }
    
    public void loadProducts() {
        ProductService productService = ProductService.getInstance();
        BufferedReader fileReader = null;
        try {
            String line;
            fileReader = new BufferedReader(new FileReader(DATA_PATH + "Product.csv"));
            fileReader.readLine();
            while ((line = fileReader.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields.length > 0) {
                    productService.addProduct(new Product(Integer.parseInt(fields[1]), fields[2], Float.parseFloat(fields[3]), Float.parseFloat(fields[4]), Integer.parseInt(fields[5])));
                }
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        } finally {
            try {
                if (fileReader != null) {
                    fileReader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    public void saveProducts() {
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(DATA_PATH + "Product.csv");
            fileWriter.write(FILE_HEADER + '\n');
            Set<Product> products = ProductService.getInstance().getProducts();
            for (Product product : products) {
                fileWriter.append(String.valueOf(product.getId())).append(",");
                fileWriter.append(String.valueOf(product.getCategoryId())).append(",");
                fileWriter.append(product.getName()).append(",");
                fileWriter.append(String.valueOf(product.getPrice())).append(",");
                fileWriter.append(String.valueOf(product.getDiscount())).append(",");
                fileWriter.append(String.valueOf(product.getQuantity())).append("\n");
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        } finally {
            try {
                if (fileWriter != null) {
                    fileWriter.flush();
                    fileWriter.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
