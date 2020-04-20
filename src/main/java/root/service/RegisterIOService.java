package root.service;

import root.model.AssistedRegister;
import root.model.Register;
import root.model.SelfRegister;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Set;

public class RegisterIOService {
    private static final String DATA_PATH = "src/main/java/root/data/";
    private static final String FILE_HEADER = "cashierId,id,active,inUse";
    private static RegisterIOService instance;
    
    private RegisterIOService() {
    }
    
    public static RegisterIOService getInstance() {
        if (instance == null) {
            instance = new RegisterIOService();
        }
        return instance;
    }
    
    public void loadRegisters() {
        RegisterService registerService = RegisterService.getInstance();
        BufferedReader fileReader = null;
        try {
            String line;
            fileReader = new BufferedReader(new FileReader(DATA_PATH + "Register.csv"));
            fileReader.readLine();
            while ((line = fileReader.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields.length > 0) {
                    if (fields[0].equals("null")) {
                        registerService.addRegister(new SelfRegister(Boolean.parseBoolean(fields[2]), Boolean.parseBoolean(fields[3])));
                    } else {
                        registerService.addRegister(new AssistedRegister(Integer.parseInt(fields[0]), Boolean.parseBoolean(fields[2]), Boolean.parseBoolean(fields[3])));
                    }
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
    
    public void saveRegisters() {
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(DATA_PATH + "Register.csv");
            fileWriter.write(FILE_HEADER + '\n');
            Set<Register> registers = RegisterService.getInstance().getRegisters();
            for (Register register : registers) {
                if (register.getClass() == AssistedRegister.class) {
                    fileWriter.append(String.valueOf(((AssistedRegister) register).getCashierId())).append(",");
                } else {
                    fileWriter.append("null,");
                }
                fileWriter.append(String.valueOf(register.getId())).append(",");
                fileWriter.append(String.valueOf(register.isActive())).append(",");
                fileWriter.append(String.valueOf(register.isInUse())).append("\n");
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
