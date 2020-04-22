package root.service;

import root.model.SoldProduct;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public final class SoldProductIOService {
    private static final String FILE_PATH = "src/main/java/root/data/SoldProduct.csv";
    private static final String FILE_HEADER = "receiptId,productId,quantity";
    private static SoldProductIOService instance;
    
    private SoldProductIOService() {
    }
    
    public static SoldProductIOService getInstance() {
        if (null == instance) {
            instance = new SoldProductIOService();
        }
        return instance;
    }
    
    public final List<SoldProduct> loadSoldProducts() {
        final List<SoldProduct> soldProducts = new ArrayList<>();
        BufferedReader fileReader = null;
        try {
            String line;
            fileReader = new BufferedReader(new FileReader(FILE_PATH));
            fileReader.readLine();
            while (null != (line = fileReader.readLine())) {
                final String[] fields = line.split("\\s*,");
                if (0 < fields.length) {
                    soldProducts.add(new SoldProduct(Integer.parseInt(fields[0]), Integer.parseInt(fields[1]), Integer.parseInt(fields[2])));
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
        LogService.getInstance().log("Loaded sold products", new Timestamp(System.currentTimeMillis()));
        return soldProducts;
    }
    
    public void saveSoldProducts() {
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(FILE_PATH);
            fileWriter.write(FILE_HEADER + '\n');
            final List<SoldProduct> soldProducts = SoldProductService.getInstance().getSoldProducts();
            for (final SoldProduct soldProduct : soldProducts) {
                fileWriter.append(String.valueOf(soldProduct.getReceiptId())).append(",");
                fileWriter.append(String.valueOf(soldProduct.getProductId())).append(",");
                fileWriter.append(String.valueOf(soldProduct.getQuantity())).append("\n");
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
        LogService.getInstance().log("Saved sold products", new Timestamp(System.currentTimeMillis()));
    }
}
