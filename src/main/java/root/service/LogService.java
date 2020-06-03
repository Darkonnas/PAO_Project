package root.service;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public final class LogService {
    private static final String FILE_PATH = "src/main/java/root/data/Log.csv";
    private static final String FILE_HEADER = "commandDescription,timestamp,thread";
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("d:MM:yyyy HH:mm:ss");
    private static LogService instance;
    
    private LogService() {
        final Path path = Path.of(FILE_PATH);
        if (!Files.exists(path)) {
            try {
                Files.createFile(path);
            } catch (final IOException exception) {
                exception.printStackTrace();
            } finally {
                FileWriter fileWriter = null;
                try {
                    fileWriter = new FileWriter(FILE_PATH);
                    fileWriter.write(FILE_HEADER);
                    fileWriter.append("\n");
                } catch (final IOException exception) {
                    exception.printStackTrace();
                } finally {
                    try {
                        if (null != fileWriter) {
                            fileWriter.flush();
                            fileWriter.close();
                        }
                    } catch (final IOException exception) {
                        exception.printStackTrace();
                    }
                }
            }
        }
    }
    
    public static LogService getInstance() {
        if (null == instance) {
            instance = new LogService();
        }
        return instance;
    }
    
    public void log(final CharSequence action, final Timestamp timestamp, final String threadName) {
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(FILE_PATH, true);
            fileWriter.append(action).append(',');
            fileWriter.append(DATE_FORMAT.format(timestamp)).append(',');
            fileWriter.append(threadName).append('\n');
        } catch (final IOException exception) {
            exception.printStackTrace();
        } finally {
            try {
                if (null != fileWriter) {
                    fileWriter.flush();
                    fileWriter.close();
                }
            } catch (final IOException exception) {
                exception.printStackTrace();
            }
        }
    }
}
