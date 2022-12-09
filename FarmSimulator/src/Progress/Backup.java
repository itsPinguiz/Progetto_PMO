package Progress;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

interface Backup {
    void saveCurrent() throws IOException;
    void printSavesList();
    List<Serializable> loadSave(int index) throws Exception;
}
