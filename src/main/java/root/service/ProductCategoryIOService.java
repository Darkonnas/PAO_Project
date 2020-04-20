package root.service;

import root.model.ProductCategory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Set;

public class ProductCategoryIOService {
    private static final String DATA_PATH = "src/main/java/root/data/";
    private static final String FILE_HEADER = "id,name";
    private static ProductCategoryIOService instance;
    
    private ProductCategoryIOService() {
    }
    
    public static ProductCategoryIOService getInstance() {
        if (instance == null) {
            instance = new ProductCategoryIOService();
        }
        return instance;
    }
    
    public void loadProductCategories() {
        ProductCategoryService productCategoryService = ProductCategoryService.getInstance();
        BufferedReader fileReader = null;
        try {
            String line;
            fileReader = new BufferedReader(new FileReader(DATA_PATH + "ProductCategory.csv"));
            fileReader.readLine();
            while ((line = fileReader.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields.length > 0) {
                    productCategoryService.addProductCategory(new ProductCategory(fields[1]));
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
    
    public void saveProductCategories() {
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(DATA_PATH + "ProductCategory.csv");
            fileWriter.write(FILE_HEADER + '\n');
            Set<ProductCategory> productCategories = ProductCategoryService.getInstance().getProductCategories();
            for (ProductCategory productCategory : productCategories) {
                fileWriter.append(String.valueOf(productCategory.getId())).append(",");
                fileWriter.append(productCategory.getName()).append("\n");
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
