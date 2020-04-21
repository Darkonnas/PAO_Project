package root.service;

import root.model.AssistedRegister;
import root.model.Register;
import root.model.SelfRegister;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Set;
import java.util.TreeSet;

public final class RegisterIOService {
    private static final String DATA_PATH = "src/main/java/root/data/";
    private static final String FILE_HEADER = "cashierId,id,active,inUse";
    private static RegisterIOService instance;
    
    private RegisterIOService() {
    }
    
    public static RegisterIOService getInstance() {
        if (null == instance) {
            instance = new RegisterIOService();
        }
        return instance;
    }
    
    public final Set<Register> loadRegisters() {
        final Set<Register> registers = new TreeSet<>();
        BufferedReader fileReader = null;
        try {
            String line;
            fileReader = new BufferedReader(new FileReader(DATA_PATH + "Register.csv"));
            fileReader.readLine();
            while (null != (line = fileReader.readLine())) {
                final String[] fields = line.split("\\s*,");
                if (0 < fields.length) {
                    if ("null".equals(fields[0])) {
                        registers.add(new SelfRegister(Boolean.parseBoolean(fields[2]), Boolean.parseBoolean(fields[3])));
                    } else {
                        registers.add(new AssistedRegister(Integer.parseInt(fields[0]), Boolean.parseBoolean(fields[2]), Boolean.parseBoolean(fields[3])));
                    }
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
        return registers;
    }
    
    public void saveRegisters() {
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(DATA_PATH + "Register.csv");
            fileWriter.write(FILE_HEADER + '\n');
            final Set<Register> registers = RegisterService.getInstance().getRegisters();
            for (final Register register : registers) {
                if (AssistedRegister.class == register.getClass()) {
                    fileWriter.append(String.valueOf(((AssistedRegister) register).getCashierId())).append(",");
                } else {
                    fileWriter.append("null,");
                }
                fileWriter.append(String.valueOf(register.getId())).append(",");
                fileWriter.append(String.valueOf(register.isActive())).append(",");
                fileWriter.append(String.valueOf(register.isInUse())).append("\n");
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
