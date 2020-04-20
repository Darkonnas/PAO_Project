package root.service;

import root.model.Cashier;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Set;

public class CashierIOService {
    private static final String DATA_PATH = "src/main/java/root/data/";
    private static final String FILE_HEADER = "id,firstName,lastName";
    private static CashierIOService instance;
    
    private CashierIOService() {
    }
    
    public static CashierIOService getInstance() {
        if (instance == null) {
            instance = new CashierIOService();
        }
        return instance;
    }
    
    public void loadCashiers() {
        CashierService cashierService = CashierService.getInstance();
        BufferedReader fileReader = null;
        try {
            String line;
            fileReader = new BufferedReader(new FileReader(DATA_PATH + "Cashier.csv"));
            fileReader.readLine();
            while ((line = fileReader.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields.length > 0) {
                    cashierService.addCashier(new Cashier(fields[1], fields[2]));
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
    
    public void saveCashiers() {
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(DATA_PATH + "Cashier.csv");
            fileWriter.write(FILE_HEADER + '\n');
            Set<Cashier> cashiers = CashierService.getInstance().getCashiers();
            for (Cashier cashier : cashiers) {
                fileWriter.append(String.valueOf(cashier.getId())).append(",");
                fileWriter.append(cashier.getFirstName()).append(",");
                fileWriter.append(cashier.getLastName()).append("\n");
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
