package root.service;

import root.model.Cashier;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Set;
import java.util.TreeSet;

public final class CashierIOService {
    private static final String FILE_PATH = "src/main/java/root/data/Cashier.csv";
    private static final String FILE_HEADER = "id,firstName,lastName";
    private static CashierIOService instance;
    
    private CashierIOService() {
    }
    
    public static CashierIOService getInstance() {
        if (null == instance) {
            instance = new CashierIOService();
        }
        return instance;
    }
    
    public final Set<Cashier> loadCashiers() {
        final Set<Cashier> cashiers = new TreeSet<>();
        BufferedReader fileReader = null;
        try {
            String line;
            fileReader = new BufferedReader(new FileReader(FILE_PATH));
            fileReader.readLine();
            while (null != (line = fileReader.readLine())) {
                final String[] fields = line.split("\\s*,");
                if (0 < fields.length) {
                    cashiers.add(new Cashier(fields[1], fields[2]));
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
        return cashiers;
    }
    
    public void saveCashiers() {
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(FILE_PATH);
            fileWriter.write(FILE_HEADER + '\n');
            final Set<Cashier> cashiers = CashierService.getInstance().getCashiers();
            for (final Cashier cashier : cashiers) {
                fileWriter.append(String.valueOf(cashier.getId())).append(",");
                fileWriter.append(cashier.getFirstName()).append(",");
                fileWriter.append(cashier.getLastName()).append("\n");
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
