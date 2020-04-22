package root.service;

import root.model.ProductCategory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Set;
import java.util.TreeSet;

public final class ProductCategoryIOService {
    private static final String FILE_PATH = "src/main/java/root/data/ProductCategory.csv";
    private static final String FILE_HEADER = "id,name";
    private static ProductCategoryIOService instance;
    
    private ProductCategoryIOService() {
    }
    
    public static ProductCategoryIOService getInstance() {
        if (null == instance) {
            instance = new ProductCategoryIOService();
        }
        return instance;
    }
    
    public final Set<ProductCategory> loadProductCategories() {
        final Set<ProductCategory> productCategories = new TreeSet<>();
        BufferedReader fileReader = null;
        try {
            String line;
            fileReader = new BufferedReader(new FileReader(FILE_PATH));
            fileReader.readLine();
            while (null != (line = fileReader.readLine())) {
                final String[] fields = line.split("\\s*,");
                if (0 < fields.length) {
                    productCategories.add(new ProductCategory(fields[1]));
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
        LogService.getInstance().log("Loaded product categories", new Timestamp(System.currentTimeMillis()));
        return productCategories;
    }
    
    public void saveProductCategories() {
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(FILE_PATH);
            fileWriter.write(FILE_HEADER + '\n');
            final Set<ProductCategory> productCategories = ProductCategoryService.getInstance().getProductCategories();
            for (final ProductCategory productCategory : productCategories) {
                fileWriter.append(String.valueOf(productCategory.getId())).append(",");
                fileWriter.append(productCategory.getName()).append("\n");
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
        LogService.getInstance().log("Saved product categories", new Timestamp(System.currentTimeMillis()));
    }
}
