package root.service;

import root.model.SoldProduct;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class SoldProductIOService {
    private static final String DATA_PATH = "src/main/java/root/data/";
    private static final String FILE_HEADER = "receiptId,productId,quantity";
    private static SoldProductIOService instance;
    
    private SoldProductIOService() {
    }
    
    public static SoldProductIOService getInstance() {
        if (instance == null) {
            instance = new SoldProductIOService();
        }
        return instance;
    }
    
    public void loadSoldProducts() {
        SoldProductService soldProductService = SoldProductService.getInstance();
        BufferedReader fileReader = null;
        try {
            String line;
            fileReader = new BufferedReader(new FileReader(DATA_PATH + "SoldProduct.csv"));
            fileReader.readLine();
            while ((line = fileReader.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields.length > 0) {
                    soldProductService.addSoldProduct(new SoldProduct(Integer.parseInt(fields[0]), Integer.parseInt(fields[1]), Integer.parseInt(fields[2])));
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
    
    public void saveSoldProducts() {
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(DATA_PATH + "SoldProduct.csv");
            fileWriter.write(FILE_HEADER + '\n');
            List<SoldProduct> soldProducts = SoldProductService.getInstance().getSoldProducts();
            for (SoldProduct soldProduct : soldProducts) {
                fileWriter.append(String.valueOf(soldProduct.getReceiptId())).append(",");
                fileWriter.append(String.valueOf(soldProduct.getProductId())).append(",");
                fileWriter.append(String.valueOf(soldProduct.getQuantity())).append("\n");
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
