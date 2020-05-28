package root.repository;

import java.util.Map;
import java.util.Set;

abstract class Repository {
    protected static final String CONFIG_PATH = "src/main/java/root/config/database.config";
    
    protected abstract int insert(Object obj);
    
    protected abstract Set<?> query(Set<String> columns, Map<String, ?> projections);
    
    protected abstract int update(Map<String, ?> updates, Map<String, ?> projections);
    
    protected abstract int delete(Map<String, ?> projections);
}
