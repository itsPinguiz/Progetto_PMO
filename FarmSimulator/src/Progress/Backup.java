package Progress;

import java.io.IOException;
import java.util.List;

import GUI.Model;

interface Backup {
    String saveCurrent() throws IOException;
    List<String> getSavesList();
    Model loadSave(String saveName) throws Exception;
}
