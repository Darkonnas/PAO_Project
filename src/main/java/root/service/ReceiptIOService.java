package root.service;

import root.model.Receipt;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Set;
import java.util.TreeSet;

public final class ReceiptIOService {
    private static final String FILE_PATH = "src/main/java/root/data/Receipt.csv";
    private static final String FILE_HEADER = "id,registerId,cashierId,couponId";
    private static ReceiptIOService instance;
    
    private ReceiptIOService() {
    }
    
    public static ReceiptIOService getInstance() {
        if (null == instance) {
            instance = new ReceiptIOService();
        }
        return instance;
    }
    
    public final Set<Receipt> loadReceipts() {
        final Set<Receipt> receipts = new TreeSet<>();
        BufferedReader fileReader = null;
        try {
            String line;
            fileReader = new BufferedReader(new FileReader(FILE_PATH));
            fileReader.readLine();
            while (null != (line = fileReader.readLine())) {
                final String[] fields = line.split("\\s*,");
                if (0 < fields.length) {
                    receipts.add(new Receipt(Integer.parseInt(fields[1]), Integer.parseInt(fields[2]), Integer.parseInt(fields[3])));
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
        return receipts;
    }
    
    public void saveReceipts() {
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(FILE_PATH);
            fileWriter.write(FILE_HEADER + '\n');
            final Set<Receipt> receipts = ReceiptService.getInstance().getReceipts();
            for (final Receipt receipt : receipts) {
                fileWriter.append(String.valueOf(receipt.getId())).append(",");
                fileWriter.append(String.valueOf(receipt.getRegisterId())).append(",");
                fileWriter.append(String.valueOf(receipt.getCashierId())).append(",");
                fileWriter.append(String.valueOf(receipt.getCouponId())).append("\n");
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
