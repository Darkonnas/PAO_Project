package root.service;

import root.model.Receipt;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Set;

public class ReceiptIOService {
    private static final String DATA_PATH = "src/main/java/root/data/";
    private static final String FILE_HEADER = "id,registerId,cashierId,couponId";
    private static ReceiptIOService instance;
    
    private ReceiptIOService() {
    }
    
    public static ReceiptIOService getInstance() {
        if (instance == null) {
            instance = new ReceiptIOService();
        }
        return instance;
    }
    
    public void loadReceipts() {
        ReceiptService receiptService = ReceiptService.getInstance();
        BufferedReader fileReader = null;
        try {
            String line;
            fileReader = new BufferedReader(new FileReader(DATA_PATH + "Receipt.csv"));
            fileReader.readLine();
            while ((line = fileReader.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields.length > 0) {
                    receiptService.addReceipt(new Receipt(Integer.parseInt(fields[1]), Integer.parseInt(fields[2]), Integer.parseInt(fields[3])));
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
    
    public void saveReceipts() {
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(DATA_PATH + "Receipt.csv");
            fileWriter.write(FILE_HEADER + '\n');
            Set<Receipt> receipts = ReceiptService.getInstance().getReceipts();
            for (Receipt receipt : receipts) {
                fileWriter.append(String.valueOf(receipt.getId())).append(",");
                fileWriter.append(String.valueOf(receipt.getRegisterId())).append(",");
                fileWriter.append(String.valueOf(receipt.getCashierId())).append(",");
                fileWriter.append(String.valueOf(receipt.getCouponId())).append("\n");
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
