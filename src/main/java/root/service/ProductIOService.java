package root.service;

import root.model.Product;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Set;
import java.util.TreeSet;

public final class ProductIOService {
    private static final String DATA_PATH = "src/main/java/root/data/";
    private static final String FILE_HEADER = "id,categoryId,name,price,discount,quantity";
    private static ProductIOService instance;
    
    private ProductIOService() {
    }
    
    public static ProductIOService getInstance() {
        if (null == instance) {
            instance = new ProductIOService();
        }
        return instance;
    }
    
    public final Set<Product> loadProducts() {
        final Set<Product> products = new TreeSet<>();
        BufferedReader fileReader = null;
        try {
            String line;
            fileReader = new BufferedReader(new FileReader(DATA_PATH + "Product.csv"));
            fileReader.readLine();
            while (null != (line = fileReader.readLine())) {
                final String[] fields = line.split("\\s*,");
                if (0 < fields.length) {
                    products.add(new Product(Integer.parseInt(fields[1]), fields[2], Float.parseFloat(fields[3]), Float.parseFloat(fields[4]), Integer.parseInt(fields[5])));
                }
            }
        } catch (final IOException exception) {
            exception.printStackTrace();
        } finally {
            try {
                if (null != fileReader) {
                    fileReader.close();
                }
            } catch (final IOException e) {
                e.printStackTrace();
            }
        }
        return products;
    }
    
    public void saveProducts() {
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(DATA_PATH + "Product.csv");
            fileWriter.write(FILE_HEADER + '\n');
            final Set<Product> products = ProductService.getInstance().getProducts();
            for (final Product product : products) {
                fileWriter.append(String.valueOf(product.getId())).append(",");
                fileWriter.append(String.valueOf(product.getCategoryId())).append(",");
                fileWriter.append(product.getName()).append(",");
                fileWriter.append(String.valueOf(product.getPrice())).append(",");
                fileWriter.append(String.valueOf(product.getDiscount())).append(",");
                fileWriter.append(String.valueOf(product.getQuantity())).append("\n");
            }
        } catch (final IOException exception) {
            exception.printStackTrace();
        } finally {
            try {
                if (null != fileWriter) {
                    fileWriter.flush();
                    fileWriter.close();
                }
            } catch (final IOException e) {
                e.printStackTrace();
            }
        }
    }
}
